package Controller;

/**
 * Created by flo on 2016-11-02.
 */

import Model.*;
import Repository.IRepo;
import Utils.IStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepo repo;
    private ExecutorService executor;

    public Controller(IRepo nRepo)
    {
        repo = nRepo;
    }

    public IRepo getRepo() {
        return repo;
    }

    Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap) {
        return heap.entrySet()
                .stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void executeAll() throws CustomException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        while (true) {
            ArrayList<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
            if (prgList.size() == 0)
                break ;
            oneStepForAllPrg(prgList);
        }
        System.out.println(repo.getPrgList().toString());
        executor.shutdownNow();
//        try {
//            PrgState prgState = repo.getCurrent();
//
//            while (!prgState.getStack().isEmpty()) {
//                executeOneStatement(prgState);
//                prgState.getHeap().setMap(this.conservativeGarbageCollector(prgState.getSymTable().values(), prgState.getHeap().getMap()));
//                repo.logPrgStateExec();
//            }
//            System.out.println(prgState);
//        }
//
//        catch(IOException e){
//            throw new CustomException(e.getMessage());
//        }
    }

    public void oneStepForAllPrg(ArrayList<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.executeOneStatement(); }))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {try {
                    return future.get();
                }
                catch (Exception e) {
                    e.printStackTrace();
                } return null;
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);

        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        repo.setPrgList(prgList);


    }

    public void allStepGUI() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        ArrayList<PrgState> prgList = removeCompletedPrg(repo.getPrgList());

        if (prgList.size() == 0) {
            executor.shutdownNow();
        }
        else {
            oneStepForAllPrg(prgList);
            executor.shutdownNow();
        }
    }

    public ArrayList<PrgState> removeCompletedPrg(ArrayList<PrgState> inPrgList) {
        return new ArrayList<PrgState>(inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return repo.getPrgList().get(0).initialPrg.toString();
    }

//    public void executeOneStatement(PrgState prg) throws CustomException
//    {
//        IStack<IStmt> execStack = prg.getStack();
//
//        if (!execStack.isEmpty())
//        {
//            IStmt statement = execStack.pop();
//            statement.execute(prg);
//        }
//    }
}

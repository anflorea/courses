package Repository;
/**
 * Created by flo on 2016-11-02.
 */

import Model.FileData;
import Model.IStmt;
import Model.PrgState;
import Utils.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Repo implements IRepo {
    private ArrayList<PrgState> prgStates;
    private String logFilePath;
    private PrintWriter logFile;

    public Repo() {

        prgStates = new ArrayList<PrgState>();
    }
    /*
    public Repo(String filename){
        prgStates = new ArrayList<PrgState>();
        logFilePath=filename;
    }
    */
    public Repo(PrgState prg, String filename){
        prgStates = new ArrayList<PrgState>();
        prgStates.add(prg);
        logFilePath = filename;
    }

    public void addProgramState(PrgState prg) {

        prgStates.add(prg);
    }

    @Override
    public ArrayList<PrgState> getPrgList() {
        return prgStates;
    }

    @Override
    public void setPrgList(ArrayList<PrgState> list) {
        prgStates = list;
    }

    public void logPrgStateExec(PrgState prg) throws IOException {

        this.logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

        IStack<IStmt> exe = prg.getStack();
        ISymTable<String, Integer> st = prg.getSymTable();
        IFileTable<Integer, FileData> ft=prg.getFileTable();
        IOut<Integer> out = prg.getOutput();
        IHeap<Integer> heap = prg.getHeap();
        logFile.println("ExeStack: ");

        for (IStmt stmt: exe.getAll())
        {
            logFile.println("\t" + stmt);
        }
        logFile.println("\n");

        logFile.println("SymbolTable: ");
        for (Map.Entry<String,Integer> entry: st.getAll())
            logFile.println(entry.getKey()+"-->"+entry.getValue());
        logFile.println("\n");
        logFile.println("Output: ");

        for(Integer v: out.getAll())
            logFile.println(v);
        logFile.println("\n");
        logFile.println("FileTable: ");
        for (Map.Entry<Integer,FileData> entry: ft.getAll())
            logFile.println(entry.getKey()+"-->"+entry.getValue().getFilename());
        logFile.println("\n");
        logFile.println("Heap: ");
        logFile.println(heap.toString());
        logFile.println("\n");

        this.logFile.close();

    }

    @Override
    public void serialize() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("serialize.txt"));
            out.writeObject(this.prgStates);
        } catch (IOException e) {
            System.out.println("IOError\nError: " + e.toString());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println("IOException\nError: " + e.toString());
                }
            }
        }
    }

    @Override
    public void deserialize() {
        ObjectInputStream in = null;
        ArrayList<PrgState> states = null;
        try {
            in = new ObjectInputStream(new FileInputStream("serialize.txt"));
            states = (ArrayList<PrgState>) in.readObject();
        } catch (IOException e) {
            System.out.println("IOError\nError: " + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException\nError: " + e.toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("IOError\nError: " + e.toString());
                }
            }
        }
        if (states != null) {
            this.prgStates = states;
        }
    }

}

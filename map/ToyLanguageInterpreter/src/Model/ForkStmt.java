package Model;

import Utils.ExeStack;

/**
 * Created by flo on 11/12/2016.
 */
public class ForkStmt implements IStmt {
    IStmt stmt;

    public ForkStmt(IStmt statemment) {
        stmt = statemment;
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        PrgState new_prg = new PrgState(new ExeStack<IStmt>(), state.getSymTable().copy(), state.getOutput(), stmt, state.getFileTable(), state.getHeap());
        return new_prg;
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }
}

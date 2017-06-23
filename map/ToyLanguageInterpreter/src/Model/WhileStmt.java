package Model;

import Utils.IStack;

/**
 * Created by flo on 08/12/2016.
 */
public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        IStack<IStmt> exeStack = state.getStack();
        if (exp.eval(state.getSymTable(), state.getHeap()) != 0) {
            exeStack.push(this);
            exeStack.push(stmt);
        }
        return null;
    }

    @Override
    public String toString() {
        return "While (" + this.exp.toString() + ") " + this.stmt.toString();
    }
}

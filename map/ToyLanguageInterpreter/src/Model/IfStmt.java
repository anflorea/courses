package Model;
import Utils.IStack;
/**
 * Created by flo on 2016-11-01.
 */
public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return "IF (" + exp.toString() + ") THEN (" + thenS.toString()
                + ") ELSE (" + elseS.toString() + ")";
    }

    public PrgState execute(PrgState state) throws CustomException{

        int tmp = exp.eval(state.getSymTable(), state.getHeap());
        IStack<IStmt> exeStack = state.getStack();

        if (tmp != 0)
            exeStack.push(thenS);
        else
            exeStack.push(elseS);

        return null;
    }
}

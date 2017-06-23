package Model;

import Utils.IStack;

/**
 * Created by flo on 2016-10-28.
 */
public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt sec;


    public CompStmt(IStmt f, IStmt s) {
        this.first = f;
        this.sec = s;
    }

    public String toString() {

        return "("+first.toString() + "; " + sec.toString()+")";
    }

    public PrgState execute(PrgState state) throws CustomException{
        IStack<IStmt> stk=state.getStack() ;
        stk.push(sec);
        stk.push(first);
        return null;
    }
}

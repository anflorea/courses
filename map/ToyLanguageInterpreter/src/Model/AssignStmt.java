package Model;

import Utils.*;

/**
 * Created by flo on 2016-10-28.
 */
public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;

    public AssignStmt(String _id, Exp _exp){
        this.id=_id;
        this.exp=_exp;
    }

    public String toString() {

        return this.id + " = " + this.exp.toString();
    }

    public PrgState execute(PrgState state) throws CustomException
    {
        IStack<IStmt> stk = state.getStack();
        ISymTable<String, Integer> symTbl = state.getSymTable();
        int val = exp.eval(symTbl, state.getHeap());
        if (symTbl.contains(id))
            symTbl.setValue(id, val);
        else
            symTbl.add(id, val);
        return null;

    }
}

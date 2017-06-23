package Model;

/**
 * Created by flo on 08/12/2016.
 */
public class WriteHeapStmt implements IStmt {
    String id;
    Exp exp;

    public WriteHeapStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        Integer var_val = state.getSymTable().getValue(id);
        if (var_val == null)
            throw  new CustomException("Unknown variable expression\nError at: " + toString());
        int val = this.exp.eval(state.getSymTable(), state.getHeap());
        state.getHeap().writeAddr(var_val, val);
        return null;
    }

    @Override
    public String toString() {
        return "WriteHeap (" + this.id + ", " + this.exp.toString() + ")";
    }
}

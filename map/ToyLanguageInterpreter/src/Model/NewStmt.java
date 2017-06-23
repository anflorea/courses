package Model;

/**
 * Created by flo on 08/12/2016.
 */
public class NewStmt implements IStmt {
    private String var;
    private Exp expression;

    public NewStmt(String var, Exp expression) {
        this.var = var;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws CustomException {
        int res = this.expression.eval(state.getSymTable(), state.getHeap());
        int loc = state.getHeap().allocate(res);
        state.getSymTable().add(var, loc);
        return null;
    }

    @Override
    public String toString() {
        return "NewStmt (" + this.var + ", " + this.expression.toString() + ")";
    }
}

package Model;
import Utils.IOut;

/**
 * Created by flo on 2016-10-28.
 */
public class PrintStmt implements IStmt {
    private Exp exp;
    public PrintStmt(Exp _exp){
        this.exp=_exp;
    }
    public String toString(){
        return "print("+this.exp.toString()+")";
    }
    public PrgState execute(PrgState state) throws CustomException{

        int tmp = exp.eval(state.getSymTable(), state.getHeap());
        IOut<Integer> o = state.getOutput();

        o.add(tmp);

        return null;
    }

}

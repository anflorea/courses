package Model;

import Utils.IHeap;
import Utils.ISymTable;

/**
 * Created by flo on 08/12/2016.
 */
public class CompExp implements Exp {
    private String type;
    private Exp e1;
    private Exp e2;

    public CompExp(String type, Exp e1, Exp e2) {
        this.type = type;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public int eval(ISymTable<String, Integer> tbl, IHeap<Integer> heap) throws CustomException {
        int val1 = this.e1.eval(tbl, heap);
        int val2 = this.e2.eval(tbl, heap);
        switch (this.type) {
            case "<":
                return val1 < val2 ? 1 : 0;
            case "<=":
                return val1 <= val2 ? 1 : 0;
            case ">":
                return val1 > val2 ? 1 : 0;
            case ">=":
                return val1 >= val2 ? 1 : 0;
            case "==":
                return val1 == val2 ? 1 : 0;
            case "!=":
                return val1 != val2 ? 1 : 0;
            default:
                throw new CustomException("Unknown comparison exception: " + this.type + "\n" + "At: " + this.toString());
        }
    }

    @Override
    public String toString() {
        return "(" + this.e1 + " " + this.type + " " + this.e2 + ")";
        //return "CompExp (" + this.type + ", " + this.e1.toString() + ", " + this.e2.toString() + ")";
    }
}

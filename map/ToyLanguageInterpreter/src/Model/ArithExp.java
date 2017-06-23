package Model;
import Model.CustomException;
import Model.Exp;

/**
 * Created by flo on 2016-10-28.
 */
import Utils.*;

public class ArithExp implements Exp {
    private char op;
    private Exp e1;
    private Exp e2;


    public ArithExp(char _op,Exp _e1, Exp _e2){
        op=_op;
        e1=_e1;
        e2=_e2;

    }

    @Override
    public int eval(ISymTable<String, Integer> tbl, IHeap<Integer> heap) throws CustomException {
        int tmp1 = e1.eval(tbl, heap);
        int tmp2 = e2.eval(tbl, heap);

        switch(op)
        {
            case '+': return tmp1 + tmp2;
            case '-': return tmp1 - tmp2;
            case '*': return tmp1 * tmp2;
            case '/':
                if (tmp2 == 0)
                    throw new CustomException("ERROR: DIVISION BY ZERO!!!");
                else
                    return tmp1 / tmp2;

        }
        return tmp2;
    }

    @Override
    public String toString(){
        return e1.toString() + " " + op + " " + e2.toString();
    }


}

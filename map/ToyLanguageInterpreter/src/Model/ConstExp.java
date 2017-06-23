package Model;
import Utils.*;

/**
 * Created by flo on 2016-10-28.
 */


public class ConstExp implements Exp {
    private int number;

    public ConstExp(int number){

        this.number=number;
    }
    public int eval(ISymTable<String, Integer> tbl, IHeap<Integer> heap) throws CustomException{

        return number;
    }
    public String toString(){

        return this.number + "";
    }
}


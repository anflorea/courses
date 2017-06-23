package Model;

/**
 * Created by flo on 2016-10-28.
 */
import Utils.*;

public class VarExp implements Exp {
    private String id;
    public VarExp(String _id){
        this.id=_id;
    }

    public int eval(ISymTable<String,Integer> tbl, IHeap<Integer> heap) throws CustomException {
        if (tbl.contains(id))
        {
            return tbl.getValue(id);
        }
        throw new CustomException("ERROR: NOT FOUND!!!");
    }

    public String toString(){
        return this.id + "";
    }


}
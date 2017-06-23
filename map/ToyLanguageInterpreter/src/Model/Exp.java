package Model;

import Utils.IHeap;
import Utils.ISymTable;

/**
 * Created by flo on 2016-10-27.
 */

public interface Exp {
    public int eval(ISymTable<String,Integer> tbl, IHeap<Integer> heap) throws CustomException;

}

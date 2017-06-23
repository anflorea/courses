package Model;

import Utils.IHeap;
import Utils.ISymTable;

/**
 * Created by flo on 08/12/2016.
 */
public class ReadHeapExp implements Exp {
    String id;

    public ReadHeapExp(String id) {
        this.id = id;
    }

    @Override
    public int eval(ISymTable<String, Integer> tbl, IHeap<Integer> heap) throws CustomException {
        Integer var_val = tbl.getValue(this.id);
        if (var_val == null)
            throw new CustomException("Error! Unkown variable!");
        Integer heap_val = heap.readAddr(var_val);
        if (heap_val == null)
            throw new NullPointerException("There is no such memory address\nError at ReadHeapExp: " + toString());
        return heap_val;
    }

    @Override
    public String toString() {
        return "ReadHeapExp (" + id + ")";
    }
}

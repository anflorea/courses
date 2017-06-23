package Model;

import Utils.ISymTable;
import Utils.SymTable;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by flo on 2016-11-04.
 */
public class VarExpTest {
    @Test
    public void eval() throws Exception {
        ISymTable tbl=new SymTable<String,Integer>();
        tbl.add("a",2);
        assertEquals(tbl.getValue("a"),2);
    }

}
package Model;
import Utils.*;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.nio.Buffer;

/**
 * Created by flo on 2016-11-02.
 */


public class PrgState {
    private static int id_number = 0;
    private int id;
    private IStack<IStmt> execStack;
    private ISymTable<String, Integer> symTable;
    private IOut<Integer> output;
    private IFileTable<Integer,FileData> filetable;
    private IStmt statement;
    private IHeap<Integer> heap;
    public  IStmt initialPrg;

    public PrgState(IStack exec_stack, ISymTable symb_table, IOut out, IStmt state, IFileTable ft, IHeap heap)
    {
        this.execStack = exec_stack;
        this.symTable = symb_table;
        this.output = out;
        this.filetable=ft;
        this.heap = heap;
        this.id_number++;
        this.id = this.id_number;
        initialPrg = state;

        execStack.push(state);
    }

    public boolean isNotCompleted() {
        return !execStack.isEmpty();
    }

    public PrgState executeOneStatement() throws CustomException {
        if (execStack.isEmpty())
            throw new CustomException("Error! Execution stack is empty!");
        IStmt crtStmt = execStack.pop();
        return crtStmt.execute(this);
    }

    public ISymTable<String, Integer> getSymTable()
    {
        return this.symTable;
    }

    public IStack<IStmt> getStack()
    {
        return this.execStack;
    }

    public IOut<Integer> getOutput()
    {
        return this.output;
    }

    public IStmt getStatement() {
        return  this.statement;
    }

    public IFileTable<Integer, FileData > getFileTable()
    {
        return this.filetable;
    }

    public IHeap<Integer> getHeap() {
        return this.heap;
    }

    public void setFiletable(IFileTable<Integer, FileData> fd)
    {
        this.filetable = fd;
    }

    public void setSymTable(ISymTable<String, Integer> _st)
    {
        this.symTable = _st;
    }

    public void setStack(IStack<IStmt> _execStack)
    {
        this.execStack = _execStack;
    }

    public void setOutput(IOut<Integer> o)
    {
        this.output = o;
    }

    public void setStatement(IStmt s)
    {
        this.statement = s;
    }

    public void setHeap(IHeap heap) {
        this.heap = heap;
    }

    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("id: " + id + " -> " + execStack.toString() + " " + symTable.toString() + " " + output.toString() + " " + filetable.toString() + " {" + heap.toString() + "}");

        return s.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

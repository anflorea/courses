package Utils;

/**
 * Created by flo on 2016-11-02.
 */

import java.util.ArrayList;

public class Out<T> implements IOut<T> {
    private ArrayList<T> outList;

    public Out()
    {
        outList = new ArrayList<>();
    }

    public void add(T element)
    {
        outList.add(element);
    }

    public String toString()
    {
        return outList.toString();
    }

    public Iterable<T> getAll(){
        return outList;
    }

    @Override
    public ArrayList<T> toList() {
        return outList;
    }

}
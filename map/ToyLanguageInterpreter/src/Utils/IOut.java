package Utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by flo on 2016-11-02.
 */

public interface IOut<T> {
    void add(T element);
    public Iterable<T> getAll();
    public ArrayList<T> toList();
}

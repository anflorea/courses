package Utils;

import java.util.ArrayList;

/**
 * Created by flo on 2016-10-27.
 */
public interface IStack<T> {
    /** Adds a new entry to the top of this stack.
     param newEntry  an object to be added to the stack */

    public void push(T newEntry);

    /** Removes and returns the stack's top entry.
     return either the object at the top of the stack or, if the
     stack is empty before the operation, null */

    public T pop();

    /** Retrieves this stack's top entry.
     return either the object at the top of the stack or null if
     the stack is empty */

    public T peek();

    /** Detects whether this stack is empty.
     return true if the stack is empty */

    public boolean isEmpty();

    Iterable<T> getAll();

    //public String toStr();

    public ArrayList<T> toList();
}

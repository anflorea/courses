package Utils;
/**
 * Created by flo on 2016-10-27.
 */
import java.util.*;

public class ExeStack<T> implements IStack<T> {
    private Stack<T> stack;
    private Deque<T> deque; //Iteratorul pe stack nu da elementele in ordine

    public ExeStack()
    {
        //stack=new Stack<T>();
        this.deque=new ArrayDeque<T>();


    }

    public void push(T newEntry)
    {
        //stack.push(newEntry);
        deque.addFirst(newEntry);
    }

    public T pop()
    {
        //return stack.pop();
        return deque.removeFirst();

    }

    public T peek()
    {
        //return stack.peek();
        return deque.peekFirst();
    }

    public boolean isEmpty()
    {
        //return stack.isEmpty();
        return deque.isEmpty();
    }

    public Iterable<T> getAll(){
        return this.deque;
    }

    public String toString()
    {
        /*
        StringBuffer str = new StringBuffer();

        for (T r:stack)
        {

            str.append(r + " | ");
        }
        return str.toString();
        */
        StringBuffer str = new StringBuffer();
        for (T r:deque)
        {
            str.append(r + " | ");
        }
        return str.toString();
    }

    public ArrayList<T> toList() {
        ArrayList<T> theList = new ArrayList<>();
        for (T r:deque) {
            theList.add(r);
        }
        return theList;
    }

}

package Utils;

import java.util.Map;
import java.util.Set;

/**
 * Created by flo on 04/12/2016.
 */
public interface IHeap<T> {
    public int allocate(T value);
    public T readAddr(int addr);
    public void writeAddr(int addr, T value);
    public T deallocate(int addr);

    Map<Integer, T> getMap();
    void setMap(Map<Integer, T> map);

    public Set<Integer> keySet();
}

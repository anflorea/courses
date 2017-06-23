package Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by flo on 04/12/2016.
 */
public class MyHeap<T> implements IHeap<T> {
    int memory;
    Map<Integer, T> vals;

    public MyHeap(Map<Integer, T> values) {
        this.vals = values;
    }

    @Override
    public int allocate(T value) {
        this.memory++;
        this.vals.put(memory, value);
        return memory;
    }

    @Override
    public T readAddr(int addr) {
        return this.vals.get(addr);
    }

    @Override
    public void writeAddr(int addr, T value) {
        this.vals.put(addr, value);
    }

    @Override
    public T deallocate(int addr) {
        return this.vals.remove(addr);
    }

    @Override
    public String toString() {
        String returnValue = "";
        boolean ok = false;
        for(HashMap.Entry<Integer, T> entry : this.vals.entrySet()) {
            if(ok)
                returnValue = returnValue + "\n";
            returnValue += entry.getKey().toString() + " -> " + entry.getValue().toString();
            ok = true;
        }
        return returnValue;
    }

    @Override
    public Map<Integer, T> getMap() {
        return this.vals;
    }

    @Override
    public void setMap(Map<Integer, T> map) {
        this.vals = map;
    }

    @Override
    public Set<Integer> keySet() {
        return vals.keySet();
    }
}

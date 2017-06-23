package ro.ubbcluj;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Flo on 24-Apr-16.
 */
public class PriorityQueue {
    private HashMap<Integer, Integer> values;

    public PriorityQueue() {
        values = new HashMap<>();
    }

    public boolean isEmpty() {
        return values.size() == 0;
    }

    public int pop() {
        int topPriority = 2000000000;
        int topObject = 0;
        for(Integer key: values.keySet()) {
            int objPriority = values.get(key);
            if (topPriority > objPriority) {
                topPriority = objPriority;
                topObject = key;
            }
        }
        values.remove(topObject);
        return topObject;
    }

    public void add(int obj, int priority) {
        values.put(obj, priority);
    }

    public boolean contains(int val) {
        return values.containsKey(val);
    }

}

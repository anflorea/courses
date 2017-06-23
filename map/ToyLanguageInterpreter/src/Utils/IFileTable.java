package Utils;

import Model.CustomException;

import java.util.Map;
import java.util.Set;

/**
 * Created by flo on 2016-11-11.
 */
public interface IFileTable<K,V> {
    public void add(K key, V val);
    public V getValue(K key) throws CustomException;
    public boolean contains(K key);
    public void setValue(K key, V val);
    public int size();
    Iterable<Map.Entry<K,V>> getAll();
    public Set<K> keySet();
}

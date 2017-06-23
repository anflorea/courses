package Utils;

import Model.CustomException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by flo on 2016-11-01.
 */
public interface ISymTable<K,V> {
    public void add(K key, V val);
    public V getValue(K key) throws CustomException;
    public boolean contains(K key);
    public void setValue(K key, V val);
    public int size();
    public Collection<V> values();
    public ISymTable<K, V> copy();
    Iterable<Map.Entry<K,V>> getAll();
    public Set<K> keySet();
}

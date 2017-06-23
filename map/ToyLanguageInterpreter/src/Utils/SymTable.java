package Utils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Model.CustomException;

/**
 * Created by flo on 2016-11-01.
 */
public class SymTable<K,V> implements ISymTable<K,V> {
    private HashMap<K,V> st;
    public SymTable(){

        st=new HashMap<K,V>();
    }
    public Iterable<Map.Entry<K,V>> getAll(){
        return st.entrySet();
    }

    @Override
    public Set<K> keySet() {
        return st.keySet();
    }

    public void add(K key, V val){
        if(!st.containsKey(key))
            st.put(key,val);
    }
    public V getValue(K key) throws CustomException{
        V val=st.get(key);
        if(val==null)
            throw new CustomException("No such key");
        return val;
    }
    public boolean contains(K key){
        if(st.containsKey(key))
            return true;
        return false;
    }
    public void setValue(K key, V val){
        st.put(key,val);
    }
    public int size(){
        return st.size();
    }

    @Override
    public Collection<V> values() {
        return this.st.values();
    }

    @Override
    public ISymTable<K, V> copy() {
        SymTable<K, V> new_sym_table = new SymTable<K, V>();
        new_sym_table.setSt(new HashMap<K, V>(st));
        return new_sym_table;
    }


    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (K key: st.keySet())
        {
            buffer.append("(" + key + ", " + st.get(key) + ")");
        }
        buffer.append("}");
        return buffer.toString();
    }

    public HashMap<K, V> getSt() {
        return st;
    }

    public void setSt(HashMap<K, V> st) {
        this.st = st;
    }
}

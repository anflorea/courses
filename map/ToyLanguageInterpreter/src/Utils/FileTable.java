package Utils;

import Model.CustomException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by flo on 2016-11-11.
 */
public class FileTable<K,V> implements IFileTable<K,V> {
    private HashMap<K,V> ft;
    public FileTable(){

        ft=new HashMap<K,V>();
    }
    public Iterable<Map.Entry<K,V>> getAll(){
        return ft.entrySet();
    }

    public void add(K key, V val){
        if(!ft.containsKey(key))
            ft.put(key,val);
    }
    public V getValue(K key) throws CustomException {
        V val=ft.get(key);
        if(val==null)
            throw new CustomException("No such key");
        return val;
    }
    public boolean contains(K key){
        if(ft.containsKey(key))
            return true;
        return false;
    }
    public void setValue(K key, V val){
        ft.put(key,val);
    }
    public int size(){
        return ft.size();
    }
    public void delete(K key){
        ft.remove(key);
    }
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        for (K key: ft.keySet())
        {
            buffer.append("(" + key + ", " + ft.get(key) + ")");
        }
        buffer.append("}");
        return buffer.toString();
    }

    public Set<K> keySet() {
        return ft.keySet();
    }

}

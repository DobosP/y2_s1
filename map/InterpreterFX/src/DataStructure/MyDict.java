package DataStructure;

import ExceptionHandling.MyException;

import java.util.Hashtable;
import java.util.Map;

import ExceptionHandling.MyException;
public class MyDict<K, V> implements MyIntDict<K, V> {

    Map<K, V> hash;


    public MyDict(){
        hash = new Hashtable<K, V>();
    }

    @Override
    public boolean exists(K key) {
        return hash.containsKey(key);
    }

    @Override
    public void add(K key, V val) {
        hash.put(key, val);
    }

    @Override
    public void remove(K key) {
        if(hash.containsKey(key)) {
            hash.remove(key);
        }
    }

    @Override
    public V get(K key) throws MyException{
        if (hash.containsKey(key)) {
            return hash.get(key);
        }
        else {
            throw new MyException("Element not found");
        }
    }

    @Override
    public void update(K key, V val) throws MyException {
        if (hash.containsKey(key)) {
            hash.replace(key, val);
        }
        else {
            throw new MyException("Element not found");
        }
    }

    @Override
    public Map<K, V> getContent() {
        return hash;
    }

    @Override
    public void setContent(Map<K, V> _hash) {
        hash =_hash;
    }

    @Override
    public MyIntDict<K, V> clone() {
        MyDict<K, V> copy = new MyDict();
        for(K key: hash.keySet()) {
            copy.add(key, hash.get(key));
        }
        return copy;
    }

    @Override
    public String toString(){
        String printstr = new String();
        printstr += "SymTable";
        for(K key: hash.keySet())
            printstr += "\n" + key.toString() + "-->" + hash.get(key).toString();
        return printstr;
    }
}

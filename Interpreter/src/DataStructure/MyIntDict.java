package DataStructure;

import ExceptionHandling.MyException;

import java.util.Map;

public interface MyIntDict<K, V> {


    boolean exists(K key);
    void add(K key, V val);
    void remove(K key);
    V get(K key) throws MyException;
    void update(K key, V val) throws MyException;
    Map<K, V> getContent();
    void setContent(Map<K, V> _hash);

}

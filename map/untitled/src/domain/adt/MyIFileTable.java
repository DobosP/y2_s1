package domain.adt;

import exception.MyException;

public interface MyIFileTable<TKey,TValue> {
    void put(TKey key,TValue value);
    void remove(TKey key) throws MyException;
    TValue get(TKey key) throws MyException;
    boolean containsKey(TKey key) throws MyException;
    boolean containsValue(TValue value) throws MyException;
    String toString();
}

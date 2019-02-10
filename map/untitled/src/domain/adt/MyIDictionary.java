package domain.adt;

import exception.MyException;

import java.util.HashMap;
import java.util.List;

public interface MyIDictionary<T1,T2> {
    void add(T1 v1, T2 v2);
    void update(T1 v1, T2 v2);
    T2 lookup(T1 id) throws MyException;
    List<T1> getKeys();
    List<T2> getValues();
    boolean isDefined(String id);
    String toString();
    HashMap<T1,T2> getDictionary();
    void remove(T1 elem);
    HashMap<T1,T2> getContent();
    int get_size();
}

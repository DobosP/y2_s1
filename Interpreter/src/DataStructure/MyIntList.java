package DataStructure;

import ExceptionHandling.MyException;

public interface MyIntList<T> {

    void add(T val);
    T get(int index) throws MyException;
    void remove(int index) throws MyException;
    int size();

}

package DataStructure;

import ExceptionHandling.MyException;

import java.util.List;

public interface MyIntList<T> {

    void add(T val);
    T get(int index) throws MyException;
    void remove(int index) throws MyException;
    int size();
    List<T> getAll();
    void setList(List<T> new_list);

}

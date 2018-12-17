package DataStructure;


import ExceptionHandling.MyException;

import java.util.List;
import java.util.ArrayList;

public  class MyList<T> implements MyIntList<T> {

    List<T> list;

    public MyList(){
        list = new ArrayList<T>();
    }

    @Override
    public void add(T val) {
        list.add(val);
    }

    @Override
    public T get(int index) throws MyException {
        if(index < list.size() && index >= 0){
            return list.get(index);
        }
        else {
            throw new MyException("Index out of range!");
        }

    }

    @Override
    public void remove(int index) throws MyException {
        if(index < list.size() && index >= 0){
            list.remove(index);
        }
        else {
            throw new MyException("Index out of range!");
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString(){
        String printstr = new String();
        printstr += "Out";
        for(T e: list)
            printstr += "\n" + e.toString();
        return printstr;
    }
}

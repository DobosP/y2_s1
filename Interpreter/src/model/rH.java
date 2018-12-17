package model;

import DataStructure.IntHeap;
import DataStructure.MyIntDict;
import ExceptionHandling.MyException;

public class rH extends Expresion {
    String var_name;

    public rH(String _var_name){
        var_name = _var_name;
    }

    @Override
    public String toString(){
        return  "readH " + var_name;
    }


    @Override
    int eval(MyIntDict<String, Integer> tab, IntHeap heap) throws MyException {
        int address;
        try{
            address = tab.get(var_name);
        }
        catch (MyException e){
            throw e;
        }
        int val;
        try {
            val = heap.get(address);
        }
        catch (MyException e){
            throw e;
        }
        return val;

    }
}

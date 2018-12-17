package model;

import DataStructure.IntHeap;
import DataStructure.MyIntDict;
import ExceptionHandling.MyException;

public class VarExpresion extends Expresion {
    String id;

    public VarExpresion(String _id){
        id = _id;
    }

    @Override
    int eval(MyIntDict<String, Integer> tab, IntHeap heap) throws MyException {
        try {
            return tab.get(id);
        }
        catch (MyException ex){
            throw ex;
        }
    }
    @Override
    public String toString() {
        return id;
    }
}

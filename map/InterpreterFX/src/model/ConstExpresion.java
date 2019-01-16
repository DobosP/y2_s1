package model;

import DataStructure.IntHeap;
import DataStructure.MyIntDict;
import ExceptionHandling.MyException;

public class ConstExpresion extends Expresion{

    int val;

    public ConstExpresion(int value){
        val = value;
    }

    @Override
    int eval(MyIntDict<String, Integer> tab, IntHeap heap) throws MyException {
        return val;
    }

    @Override
    public String toString() {
        return Integer.toString(this.val);
    }
}

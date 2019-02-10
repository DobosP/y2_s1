package model;

import DataStructure.IntHeap;
import DataStructure.MyIntDict;
import ExceptionHandling.MyException;

public abstract class Expresion {
    abstract int eval(MyIntDict<String, Integer> tab, IntHeap heap) throws MyException;
}

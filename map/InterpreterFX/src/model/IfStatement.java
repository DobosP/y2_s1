package model;

import DataStructure.Heap;
import DataStructure.IntHeap;
import DataStructure.MyIntDict;
import DataStructure.MyIntStack;
import ExceptionHandling.MyException;

public class IfStatement implements  IntStatement{
    Expresion exp;
    IntStatement first;
    IntStatement second;

    public IfStatement(Expresion _exp,
                       IntStatement _first,
                       IntStatement _second){

        exp = _exp;
        first = _first;
        second = _second;
    }


    @Override
    public String toString() {
        return "IF(" + exp.toString() + ")THEN(" +
                first.toString() + ")ELSE(" + second.toString() + ")";
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyIntDict<String, Integer> tabel = state.getSymTable().top();
        MyIntStack<IntStatement> stack = state.getExecStack();
        IntHeap heap = state.getHeap();
        int val;
        try {
            val = exp.eval(tabel, heap);
        }
        catch (MyException e){
            throw e;
        }
        if(val != 0) {
            stack.push(first);
        }
        else{
            stack.push(second);
        }
        return null;
    }
}

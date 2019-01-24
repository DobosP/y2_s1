package model;

import DataStructure.IntHeap;
import DataStructure.MyIntDict;
import ExceptionHandling.MyException;

public class wH implements IntStatement {
    String var__name;
    Expresion exp;

    public wH(String _var_name, Expresion _exp){
        var__name = _var_name;
        exp = _exp;
    }

    @Override
    public String toString(){
        return "update " + var__name + "=" + exp.toString();
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyIntDict<String, Integer> symtable = state.getSymTable().top();
        IntHeap heap = state.getHeap();
        int address;
        try{
            address = symtable.get(var__name);
        }
        catch (MyException e){
            throw e;
        }
        int val = exp.eval(symtable, heap);
        try {
            heap.update(address, val);
        }
        catch (MyException e){
            throw e;
        }
        return null;
    }
}

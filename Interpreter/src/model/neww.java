package model;

import DataStructure.IntHeap;
import DataStructure.MyIntDict;
import ExceptionHandling.MyException;

public class neww implements IntStatement{

    String var_name;
    Expresion exp;

    public  neww(String _var_name, Expresion _exp){
        var_name = _var_name;
        exp = _exp;
    }

    @Override
    public String toString(){
        return "new " + var_name + '=' + exp.toString();
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyIntDict<String, Integer>  symTable = state.getSymTable();
        IntHeap heap = state.getHeap();
        int val;
        try{
            val = exp.eval(symTable, heap);
        }
        catch (MyException  e){
            throw e;
        }
        int adress = heap.add(val);
        symTable.add(var_name, adress);
        return null;
    }


}

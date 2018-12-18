package model;

import DataStructure.IntHeap;
import  DataStructure.MyIntDict;
import ExceptionHandling.MyException;

public class AssignStatement implements IntStatement  {

    String id;
    Expresion exp;

    public AssignStatement(String _id, Expresion _exp){
        id = _id;
        exp = _exp;
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyIntDict<String, Integer> tabel = state.getSymTable();
        IntHeap heap = state.getHeap();
        int val;

        try {

            val = exp.eval(tabel, heap);
            if (tabel.exists(id)) {
                tabel.update(id, val);
            } else {
                tabel.add(id, val);
            }
        }
        catch (MyException e){
            throw e;
        }
        return null;
    }

}

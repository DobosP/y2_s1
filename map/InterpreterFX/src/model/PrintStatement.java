package model;

import DataStructure.IntHeap;
import DataStructure.MyIntList;
import ExceptionHandling.MyException;

public class PrintStatement implements IntStatement {
    Expresion exp;

    public PrintStatement(Expresion _exp){
        this.exp = _exp;
    }


    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public ProgState execute(ProgState state) throws MyException{
        MyIntList<Integer> out = state.getOut();
        IntHeap heap = state.getHeap();
        try {
            out.add(exp.eval(state.getSymTable().top(), heap));
        }
        catch(MyException e) {
            throw e;
        }
        return null;
    }
}

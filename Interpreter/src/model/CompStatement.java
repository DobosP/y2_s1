package model;

import DataStructure.MyIntStack;
import ExceptionHandling.MyException;

public class CompStatement implements IntStatement {

    IntStatement first;
    IntStatement second;

    public CompStatement(IntStatement _first, IntStatement _second){
        first = _first;
        second = _second;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyIntStack<IntStatement> stack = state.getExecStack();
        stack.push(second);
        stack.push(first);
        return state;
    }

}

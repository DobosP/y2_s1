package model;

import DataStructure.MyIntStack;
import ExceptionHandling.MyException;

public class sleep implements  IntStatement {
    Integer number;

    public sleep(Integer _number){
        number = _number;

    }

    @Override
    public String toString(){
        return "sleep(" + Integer.toString(number) + ")";
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyIntStack<IntStatement> stack = state.getExecStack();

        if(number != 0){
            stack.push(new sleep(number - 1));
        }

        return null;
    }
}

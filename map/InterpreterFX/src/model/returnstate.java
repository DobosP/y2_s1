package model;

import ExceptionHandling.MyException;

public class returnstate implements IntStatement {

    public returnstate(){}

    @Override
    public String toString(){
        return "return";
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        state.getSymTable().pop();
        return null;
    }

}

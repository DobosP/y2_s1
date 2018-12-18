package model;

import DataStructure.MyIntStack;
import DataStructure.MyStack;
import ExceptionHandling.MyException;

public class forkStmt implements IntStatement {

    IntStatement stm;

    public forkStmt(IntStatement _state){
        stm = _state;
    }

    @Override
    public String toString(){
        return "fork(" + stm.toString() + ")";
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyIntStack<IntStatement> new_stack = new MyStack<IntStatement>();
        ProgState new_state = new ProgState(
                new_stack,
                state.symTable.clone(),
                state.getOut(),
                state.getFileTable(),
                state.getHeap(),
                state.getId() * 10,
                this.stm
        );

        return new_state;
    }
}

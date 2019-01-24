package model;

import DataStructure.MyIntDict;
import DataStructure.MyIntStack;
import DataStructure.MyStack;
import ExceptionHandling.MyException;
import sun.security.util.Length;

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
        MyIntStack<MyIntDict<String, Integer>> symTablestack = state.getSymTable();
        MyIntStack<MyIntDict<String, Integer>> symTablestackclone = new MyStack<>();

        for (int i = symTablestack.getStack().size() - 1; i >= 0; i--){
            symTablestackclone.push(symTablestack.getStack().get(i).clone());
        }


        ProgState new_state = new ProgState(
                new_stack,
                symTablestackclone,
                state.getOut(),
                state.getFileTable(),
                state.getHeap(),
                this.stm
        );

        return new_state;
    }
}

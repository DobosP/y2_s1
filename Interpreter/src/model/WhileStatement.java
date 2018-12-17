package model;

import DataStructure.IntHeap;
import DataStructure.MyIntDict;
import DataStructure.MyIntStack;
import ExceptionHandling.MyException;

import java.security.PublicKey;
import java.util.Stack;

public class WhileStatement implements IntStatement{
    IntStatement statement;
    Expresion exp;

    public WhileStatement(IntStatement _state, Expresion _exp){
        statement = _state;
        exp = _exp;
    }

    @Override
    public String toString(){
        return "while(" + exp.toString() + ") " + statement.toString();
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyIntDict<String, Integer> symt = state.getSymTable();
        IntHeap heap = state.getHeap();
        MyIntStack<IntStatement> stack = state.getExecStack();

        int val = exp.eval(symt, heap);
        if(val != 0){
            stack.push(this);
            stack.push(statement);
        }

        return state;
    }
}

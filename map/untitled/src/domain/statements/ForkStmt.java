package domain.statements;

import domain.PrgState;
import domain.adt.*;
import exception.MyException;

import java.io.BufferedReader;

public class ForkStmt implements IStmt{

    IStmt statement;

    public ForkStmt(IStmt statement)
    {
        this.statement=statement;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> newstack = new MyStack<IStmt>();
        //e posibil sa nu fie ok :/
        //MyIDictionary<String, Integer> newsymTable = state.getSymTable();
        MyIDictionary<String, Integer> newsymTable=new MyDictionary<String, Integer>();
        for(String i:state.getSymTable().getKeys())
        {
            newsymTable.add(i,state.getSymTable().lookup(i));
        }
        PrgState newprg = new PrgState(state.getId() * 10, newstack, newsymTable, state.getOut(), statement, state.getFileTable(), state.getHeap());
        return newprg;

    }

    public String toString()
    {
        return "fork("+statement.toString()+")";
    }



}

package domain;

import domain.adt.*;
import domain.statements.IStmt;
import exception.MyException;

import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String,Integer> symTable;
    MyIList<Integer> out;
    IStmt originalProgram;
    MyIDictionary<Integer,MyTuple<String, BufferedReader>> fileTable;
    MyIHeap<Integer,Integer> heap;
    int id;

    public PrgState(int id,MyIStack<IStmt> stack,MyIDictionary<String,Integer> symTable,MyIList<Integer> out,IStmt program,MyIDictionary<Integer,MyTuple<String, BufferedReader>> fileTable,MyIHeap<Integer,Integer> heap){
        this.id=id;
        this.exeStack=stack;
        this.symTable=symTable;
        this.out=out;
        this.originalProgram=program;
        this.fileTable=fileTable;
        this.heap=heap;
        exeStack.push(program);
    }

    public int getId() {return id;}

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<Integer,MyTuple<String, BufferedReader>> getFileTable() {return this.fileTable;}

    public MyIHeap<Integer,Integer> getHeap() {return this.heap;}

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Integer> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Integer> getOut() {
        return out;
    }

    public void setOut(MyIList<Integer> out) {
        this.out = out;
    }

    public void setHeap(MyIHeap<Integer,Integer> heap){this.heap=heap;}

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public String toString(){
        return "Id:\n"+id+"\nExeStack:\n"+exeStack.toString()+"Sym Table:\n"+symTable.toString()+"Print output:\n"+out.toString()+"File Table:\n"+fileTable.toString()+"Heap:\n"+heap.toString();
    }

    public boolean isNotCompleted()
    {
        if(exeStack.isEmpty()==true)
            return false;
        return true;
    }

    public PrgState oneStep() throws MyException
    {
        if(exeStack.isEmpty())
            throw new MyException("Done :)");
        IStmt  crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
}

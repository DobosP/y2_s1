package domain.statements;

import domain.PrgState;
import domain.adt.MyIStack;

public class CompStmt implements IStmt{
    IStmt first, second;

    public CompStmt(IStmt first,IStmt second){
        this.first=first;
        this.second=second;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    public String toString() {return "(" + first.toString()+ ", "+second.toString()+")";}
    public IStmt getFirst() {return this.first;}
    public IStmt getSecond() {return this.second;}
}

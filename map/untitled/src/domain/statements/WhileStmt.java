package domain.statements;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyIHeap;
import domain.adt.MyIStack;
import domain.expressions.Exp;
import domain.expressions.*;
import exception.MyException;

public class WhileStmt implements IStmt {

    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt)
    {
        this.exp=exp;
        this.stmt=stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String,Integer> symTable=state.getSymTable();
        MyIHeap<Integer,Integer> heap=state.getHeap();
        MyIStack<IStmt> exeStack=state.getExeStack();

        if(exp.eval(symTable,heap)!=0)
        {
            exeStack.push(new WhileStmt(exp,stmt));
            exeStack.push(stmt);
        }
        return null;

    }

    public String toString()
    {
        return "while("+exp.toString()+") "+stmt.toString();
    }
}

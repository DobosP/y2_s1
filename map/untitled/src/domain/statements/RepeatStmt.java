package domain.statements;

import domain.PrgState;
import domain.adt.MyIStack;
import domain.expressions.Exp;
import domain.expressions.notExp;
import exception.MyException;

public class RepeatStmt implements IStmt{
    Exp exp;
    IStmt stmt;

    public RepeatStmt(Exp exp, IStmt stmt){
        this.exp = exp;
        this.stmt = stmt;

    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIStack<IStmt> exeStack=state.getExeStack();

        exeStack.push(new WhileStmt(new notExp(exp),stmt));

        return null;

    }

    @Override
    public String toString(){
        return "(repeat " + stmt.toString() + "until" + exp.toString() + ")";
    }
}

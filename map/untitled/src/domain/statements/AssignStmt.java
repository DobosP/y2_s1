package domain.statements;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyIHeap;
import domain.expressions.Exp;

import exception.MyException;

public class AssignStmt implements IStmt{
    String id;
    Exp expression;

    public AssignStmt(String id,Exp expression)
    {
        this.id=id;
        this.expression=expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String,Integer> symTable=state.getSymTable();
        MyIHeap<Integer,Integer> heap=state.getHeap();
        int val=expression.eval(symTable,heap);
        if(symTable.isDefined(id))
            symTable.update(id,val);
        else
            symTable.add(id,val);
        return null;
    }

    public String toString() {
        return id + "=" + expression.toString();
    }

    public String getId() {return this.id;}
    public Exp getExpression() {return this.expression;}
}

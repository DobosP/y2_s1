package domain.statements;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyIHeap;
import domain.expressions.Exp;
import exception.MyException;

public class HeapAlocStmt implements IStmt{
    static int unique_key = 0;
    private String id;
    private Exp expression;

    public HeapAlocStmt(String id,Exp expression){this.expression=expression;
        this.id=id;}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIHeap<Integer,Integer> heap=state.getHeap();
        MyIDictionary<String,Integer> symTable=state.getSymTable();
        int val=expression.eval(symTable,heap);
        heap.add(++unique_key,val);
        if(symTable.isDefined(id))
            symTable.update(id,unique_key);
        else
            symTable.add(id,unique_key);

        return null;

    }

    public String toString()
    {
        return "new("+id+","+expression.toString()+")";
    }
}

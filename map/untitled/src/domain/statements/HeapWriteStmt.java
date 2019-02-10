package domain.statements;

import domain.PrgState;
import domain.adt.MyIDictionary;
import domain.adt.MyIHeap;
import domain.expressions.Exp;
import exception.MyException;

public class HeapWriteStmt implements IStmt{

    private String var;
    private Exp expression;

    public HeapWriteStmt(String var,Exp expression){this.var=var;this.expression=expression;}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        //System.out.println("in Heap Write----------------------------------------");
        MyIHeap<Integer,Integer> heap=state.getHeap();
        //System.out.println("in Heap Write--------2--------------------------------");
        MyIDictionary<String,Integer> symTable=state.getSymTable();
        //System.out.println("in Heap Write--------3--------------------------------");
        int val=expression.eval(symTable,heap);
        //System.out.println("in Heap Write--------4--------------------------------");
        int Hpoz=symTable.lookup(var);
        //System.out.println("duduudud----------dududududuudud--------");
        heap.update(Hpoz,val);
        //state.setHeap(heap);
        return null;
    }

    public String toString()
    {
        return "wH("+var+","+expression.toString()+")";
    }
}

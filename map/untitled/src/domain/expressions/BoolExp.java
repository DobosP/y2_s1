package domain.expressions;

import domain.adt.MyIDictionary;
import domain.adt.MyIHeap;
import exception.MyException;


public class BoolExp extends Exp{
    private Exp exp1;
    private Exp exp2;
    private String comp;

    public BoolExp(Exp exp1,Exp exp2,String comp)
    {
        this.exp1=exp1;
        this.exp2=exp2;
        this.comp=comp;
    }


    @Override
    public int eval(MyIDictionary<String, Integer> symTable, MyIHeap<Integer, Integer> heap) throws MyException {
        if(comp.equals("<"))
            return (exp1.eval(symTable,heap)<exp2.eval(symTable,heap))? 1:0;
        if(comp.equals(">"))
            return (exp1.eval(symTable,heap)>exp2.eval(symTable,heap))? 1:0;
        if(comp.equals("<="))
            return (exp1.eval(symTable,heap)<=exp2.eval(symTable,heap))? 1:0;
        if(comp.equals(">="))
            return (exp1.eval(symTable,heap)>=exp2.eval(symTable,heap))? 1:0;
        if(comp.equals("=="))
            return (exp1.eval(symTable,heap)==exp2.eval(symTable,heap))? 1:0;
        if(comp.equals("!="))
            return (exp1.eval(symTable,heap)!=exp2.eval(symTable,heap))? 1:0;

        throw new MyException("Invalid operator");
    }

    @Override
    public String toString() {
        return exp1.toString()+" "+comp+" "+exp2.toString();
    }
}

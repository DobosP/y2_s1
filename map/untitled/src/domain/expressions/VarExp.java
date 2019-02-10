package domain.expressions;

import domain.adt.MyIDictionary;
import domain.adt.MyIHeap;
import exception.MyException;

public class VarExp extends Exp{
    String id;

    public VarExp(String id) {this.id=id;}

    @Override
    public int eval(MyIDictionary<String,Integer> symTable, MyIHeap<Integer,Integer> heap) throws MyException {
        return symTable.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }

    public String getId() {return this.id;}
}

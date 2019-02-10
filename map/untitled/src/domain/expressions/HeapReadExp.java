package domain.expressions;

import domain.adt.MyIDictionary;
import domain.adt.MyIHeap;
import exception.MyException;

public class HeapReadExp extends Exp{

    String id;

    public HeapReadExp(String id){this.id=id;}

    @Override
    public int eval(MyIDictionary<String,Integer> symTable, MyIHeap<Integer,Integer> heap) throws MyException {
        int eval = symTable.lookup(id);
        try {
            return heap.lookup(eval);
        } catch (Exception e) {
            throw new MyException("Invalid element");
        }
    }

    @Override
    public String toString() {
        return "rH("+id+")";
    }
}

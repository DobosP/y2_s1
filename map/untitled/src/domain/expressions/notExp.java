package domain.expressions;

import domain.adt.MyIDictionary;
import domain.adt.MyIHeap;
import exception.MyException;

public class notExp extends Exp{

    Exp exp;

    public notExp(Exp exp){
        this.exp = exp;
    }



    @Override
    public int eval(MyIDictionary<String, Integer> symTable, MyIHeap<Integer, Integer> heap) throws MyException {

        if (exp.eval(symTable,heap) == 0) {
            return 1;
        }
        else {
            return 0;
        }

    }

    @Override
    public String toString() {
        return "(!" + exp.toString() + ")";
    }


}

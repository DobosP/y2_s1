package domain.expressions;

import domain.adt.MyIDictionary;
import domain.adt.MyIHeap;

public class ConstExp extends Exp {
    int number;

    public ConstExp(int number) {this.number=number;}

    @Override
    public int eval(MyIDictionary<String,Integer> symTable, MyIHeap<Integer,Integer> heap) {
        return number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public int getNumber() {return this.number;}
}

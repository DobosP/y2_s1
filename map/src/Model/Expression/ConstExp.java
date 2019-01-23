package Model.Expression;

import Model.DataStructure.MyIDictionary;

public class ConstExp extends Exp {
    int number;

    public ConstExp(final int number) {
        this.number = number;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> dictionary,
                    MyIDictionary<Integer, Integer> heapTable) {
        return number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}

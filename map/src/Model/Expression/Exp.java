package Model.Expression;

import Model.DataStructure.MyIDictionary;

public abstract class Exp {
    public abstract int eval(MyIDictionary<String, Integer> dictionary,
                             MyIDictionary<Integer, Integer> heapTable);
    public abstract String toString();
}
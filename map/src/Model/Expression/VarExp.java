package Model.Expression;

import Model.DataStructure.MyIDictionary;

public class VarExp extends Exp {

    String id;

    public VarExp(final String id) {
        this.id = id;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> dictionary,
                    MyIDictionary<Integer, Integer> heapTable) {
        return dictionary.get(id);
    }

    @Override
    public String toString() {
        return id;
    }
}

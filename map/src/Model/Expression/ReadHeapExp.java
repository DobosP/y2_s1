package Model.Expression;

import Model.DataStructure.MyIDictionary;

public class ReadHeapExp extends Exp {
    private final String varId;

    public ReadHeapExp(final String varId) {
        this.varId = varId;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> dictionary,
                    MyIDictionary<Integer, Integer> heapTable) {
        final Integer heapAddress = dictionary.get(this.varId);
        final Integer value = heapTable.get(heapAddress);
        return value;
    }

    @Override
    public String toString() {
        return varId;
    }
}

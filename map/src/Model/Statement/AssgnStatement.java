package Model.Statement;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIStack;
import Model.Expression.Exp;
import Model.PrgState;

public class AssgnStatement implements IStatement {
    String id;
    Exp exp;

    public AssgnStatement(final String id, final Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStatement> stck = state.getExeStack();
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        int val = exp.eval(symTable, heapTable);
        if (symTable.containsKey(id)) {
            symTable.replace(id, val);
        } else {
            symTable.put(id, val);
        }

        return null;
    }
}

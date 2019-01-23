package Model.Statement;

import Model.DataStructure.MyIDictionary;
import Model.Exception.FileAlreadyOpenedException;
import Model.Expression.Exp;
import Model.PrgState;

import java.io.FileNotFoundException;

public class WriteHeapStatement implements IStatement {

    private final String varId;
    private final Exp expression;

    public WriteHeapStatement(final String varId, final Exp expression) {
        this.varId = varId;
        this.expression = expression;
    }

    public String toString() {
        return "WriteHeap( " + varId + " " + expression.toString() + " )";
    }

    @Override
    public PrgState execute(PrgState state) throws FileAlreadyOpenedException, FileNotFoundException {
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        final Integer heapAddress = symTable.get(varId);
        if (heapTable.containsKey(heapAddress)) {
            heapTable.replace(heapAddress, expression.eval(symTable, heapTable));
        } else {
            heapTable.put(heapAddress, expression.eval(symTable, heapTable));
        }

        return null;
    }
}

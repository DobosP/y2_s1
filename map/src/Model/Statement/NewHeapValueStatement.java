package Model.Statement;

import Model.DataStructure.HeapAddressIncrementor;
import Model.DataStructure.MyIDictionary;
import Model.Exception.FileAlreadyOpenedException;
import Model.Expression.Exp;
import Model.PrgState;

import java.io.FileNotFoundException;

public class NewHeapValueStatement implements IStatement{
    private String varId;
    private Exp expression;

    public NewHeapValueStatement(final String varId, final Exp expression) {
        this.varId = varId;
        this.expression = expression;
    }

    public String toString() {
        return "NewHeapValue( " + varId + " " + expression.toString() + " )";
    }

    @Override
    public PrgState execute(PrgState state) throws FileAlreadyOpenedException, FileNotFoundException {
        final Integer address = HeapAddressIncrementor.getNewUniqueId();
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        heapTable.put(address, this.expression.eval(symTable, heapTable));

        if (symTable.containsKey(varId)) {
            symTable.replace(varId, address);
        } else {
            // TODO: THROW EXPRESSION
            symTable.put(varId, address);
        }

        return null;
    }
}

package Model.Statement;

import Model.DataStructure.*;
import Model.PrgState;

public class ForkStatement implements IStatement {
    IStatement statement;

    public ForkStatement(final IStatement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "(Fork Statement(" + statement.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStatement> forkedExeStack = new MyStack<IStatement>();
        forkedExeStack.push(statement);
        MyIDictionary<String, Integer> forkedSymTable = new MyDictionary<>();
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        symTable.keySet().forEach(key -> forkedSymTable.put(key, symTable.get(key)));
        MyIList<Integer> forkedOut = state.getOut();
        MyIDictionary<Integer, FileTableValue> forkedFileTable = state.getFileTable();
        MyIDictionary<Integer, Integer> forkedHeapTable = state.getHeapTable();
        int forkedId = state.getId() * 10;
        PrgState forkedPrgState = new PrgState(forkedExeStack, forkedSymTable, forkedOut, forkedFileTable, forkedHeapTable, forkedId);
        return forkedPrgState;
    }
}
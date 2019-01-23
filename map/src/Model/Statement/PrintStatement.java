package Model.Statement;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIList;
import Model.Expression.Exp;
import Model.PrgState;

public class PrintStatement implements IStatement {
    Exp exp;

    public PrintStatement(final Exp exp) {
        this.exp = exp;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) {
        MyIList<Integer> out = state.getOut();
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        out.add(exp.eval(symTable, heapTable));
        return null;
    }
}

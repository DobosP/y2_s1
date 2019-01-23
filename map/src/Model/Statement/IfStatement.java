package Model.Statement;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIStack;
import Model.Expression.Exp;
import Model.PrgState;

public class IfStatement implements IStatement {
    Exp exp;
    IStatement thenS;
    IStatement elseS;

    public IfStatement(final Exp exp, final IStatement thenS, final IStatement elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenS.toString() + ") ELSE(" + elseS.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack stck = state.getExeStack();
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        if (exp.eval(symTable, heapTable) != 0) {
            stck.push(thenS);
        } else {
            stck.push(elseS);
        }
        return null;
    }
}

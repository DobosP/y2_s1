package Model.Statement;

import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIStack;
import Model.Exception.FileAlreadyOpenedException;
import Model.Expression.Exp;
import Model.PrgState;

import java.io.FileNotFoundException;

public class WhileStatement implements IStatement {

    private final Exp exp;
    private final IStatement statement;

    public WhileStatement(final Exp exp, final IStatement statement) {
        this.exp = exp;
        this.statement = statement;
    }

    public String toString() {
        return "( While( " + exp.toString() + " do " + statement.toString() + " ) )";
    }

    @Override
    public PrgState execute(PrgState state) throws FileAlreadyOpenedException, FileNotFoundException {
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        if (exp.eval(symTable, heapTable) == 0) {
            return state;
        }

        MyIStack<IStatement> exeStack = state.getExeStack();
        exeStack.push(new WhileStatement(this.exp, this.statement));
        exeStack.push(this.statement);

        return null;
    }
}

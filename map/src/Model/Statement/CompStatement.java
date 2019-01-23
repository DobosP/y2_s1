package Model.Statement;

import Model.DataStructure.MyIStack;
import Model.PrgState;

public class CompStatement implements IStatement {

    IStatement frst;
    IStatement scnd;

    public CompStatement(final IStatement frst, final IStatement scnd) {
        this.frst = frst;
        this.scnd = scnd;
    }

    @Override
    public String toString() {
        return "(" + frst.toString() + ";" + scnd.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStatement> stck = state.getExeStack();
        stck.push(scnd);
        stck.push(frst);
        return null;
    }
}

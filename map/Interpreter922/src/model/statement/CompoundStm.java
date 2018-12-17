package model.statement;

import model.ProgState;
import util.IExecStack;

public class CompoundStm implements IStatement {
    private IStatement first, second;

    CompoundStm(AssignmentStm assignmentStm) {

    }

    @Override
    public ProgState execute(ProgState p) {
        IExecStack exec = p.getExecStack();
        exec.push(second);
        exec.push(first);
        return p;
    }
}

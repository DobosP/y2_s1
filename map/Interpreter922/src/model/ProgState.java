package model;

import model.statement.IStatement;
import util.IExecStack;
import util.IOutput;
import util.ISymTable;

public class ProgState {
    private IStatement program;
    private IExecStack stack;
    private ISymTable symTable;
    private IOutput output;

    public ISymTable getSymTable() {
        return symTable;
    }

    public IExecStack getExecStack() {
        return stack;
    }
}

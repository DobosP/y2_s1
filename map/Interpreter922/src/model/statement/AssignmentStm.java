package model.statement;

import model.ProgState;
import model.expression.IExpression;
import util.ISymTable;

public class AssignmentStm implements IStatement {
    private String var;
    private IExpression exp;

    AssignmentStm(String var, IExpression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgState execute(ProgState p) {
        ISymTable symTable = p.getSymTable();
        int result = exp.evaluate(symTable);
        symTable.add(var, result);
        return p;
    }
}

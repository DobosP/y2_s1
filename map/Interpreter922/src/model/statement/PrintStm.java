package model.statement;

import model.expression.IExpression;
import util.ISymTable;

public class PrintStm implements IExpression {
    private IExpression expression;

    @Override
    public int evaluate(ISymTable t) {
        return 0;
    }
}

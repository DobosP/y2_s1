package model.statement;

import model.expression.IExpression;
import util.ISymTable;

public class ConditionalStm implements IExpression {
    private IExpression exp;
    private IStatement then;
    private IStatement els;


    @Override
    public int evaluate(ISymTable t) {
        return 0;
    }
}

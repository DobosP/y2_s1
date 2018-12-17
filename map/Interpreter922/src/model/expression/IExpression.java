package model.expression;

import util.ISymTable;

public interface IExpression {
    public int evaluate(ISymTable t);
}

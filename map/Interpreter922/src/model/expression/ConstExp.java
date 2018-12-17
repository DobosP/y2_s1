package model.expression;

import util.ISymTable;

public class ConstExp implements IExpression {
    private int value;

    ConstExp(int value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int evaluate(ISymTable t) {
        return value;
    }
}

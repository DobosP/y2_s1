package model.expression;

import util.ISymTable;

public class VarExpr implements IExpression {
    private String name;

    VarExpr(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    @Override
    public int evaluate(ISymTable t) {
        if(t.find(name)) {
            return t.getValue(name);
        } else {
            throw new RuntimeException("synTable does not" +
                    " contain the variable");
        }
    }
}

package model.expression;

import util.ISymTable;

public class ArithExp implements IExpression {
    private char operator;
    private VarExpr operand1, operand2;

    ArithExp(char operator, VarExpr operand1,
             VarExpr operand2) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public String getValue() {
        return operand1.toString() + " " + operator + " "
                + operand2.toString();
    }

    @Override
    public int evaluate(ISymTable t) {
        int result1 = operand1.evaluate(t);
        int result2 = operand2.evaluate(t);
        switch (operator) {
            case '+':
                return result1 + result2;
            case '-':
                return result1 - result2;
            case '*':
                return result1 * result2;
            case '/': {
                if (result2 == 0) {
                    throw new ArithmeticException("Cannot " +
                            "divide by 0");
                } else {
                    return result1 / result2;
                }
            }
            default:
                throw new RuntimeException("Invalid " +
                        "operator");
        }
    }
}

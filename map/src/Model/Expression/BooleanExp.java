package Model.Expression;

import Model.DataStructure.MyIDictionary;

public class BooleanExp extends Exp {
    private final Exp exp1;
    private final Exp exp2;
    private String op;

    public BooleanExp(final Exp exp1, final Exp exp2, final String op) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.op = op;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> dictionary, MyIDictionary<Integer, Integer> heapTable) {
        boolean result = false;
        switch (op) {
            case "<": result = this.exp1.eval(dictionary, heapTable) < this.exp2.eval(dictionary, heapTable); break;
            case "<=": result = this.exp1.eval(dictionary, heapTable) <= this.exp2.eval(dictionary, heapTable); break;
            case ">": result = this.exp1.eval(dictionary, heapTable) > this.exp2.eval(dictionary, heapTable); break;
            case ">=": result = this.exp1.eval(dictionary, heapTable) >= this.exp2.eval(dictionary, heapTable); break;
            case "==": result = this.exp1.eval(dictionary, heapTable) == this.exp2.eval(dictionary, heapTable); break;
            case "!=": result = this.exp1.eval(dictionary, heapTable) != this.exp2.eval(dictionary, heapTable); break;
        }
        if (result) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public String toString() {
        return "BooleanExp( " + exp1.toString() + op + exp2.toString() + " )";
    }
}

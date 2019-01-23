package Model.Expression;

import Model.DataStructure.MyIDictionary;

public class ArithExp extends Exp {

    private Exp e1;
    private Exp e2;

    // 1 for +, 2 for -, 3 for *, 4 for /
    private int op;

    public ArithExp(final Exp e1, final Exp e2, final int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> dictionary,
                    MyIDictionary<Integer, Integer> heapTable) {
        int result = 0;
        switch (op) {
            case 1: result = e1.eval(dictionary, heapTable) + e2.eval(dictionary, heapTable); break;
            case 2: System.out.println(e2.eval(dictionary, heapTable));result = e1.eval(dictionary, heapTable) - e2.eval(dictionary, heapTable); break;
            case 3: result = e1.eval(dictionary, heapTable) * e2.eval(dictionary, heapTable); break;
            case 4: result = e1.eval(dictionary, heapTable) / e2.eval(dictionary, heapTable); break;
        }

        return result;
    }

    @Override
    public String toString() {
        String operation = "";
;
        return e1.toString() + " " + operation + " " + e2.toString();
    }
}

package view;

import model.expression.ArithExp;
import model.expression.ConstExp;
import model.expression.VarExpr;
import model.statement.AssignmentStm;
import model.statement.CompoundStm;
import model.statement.PrintStm;

public class View {
    public void showMenu() {
        VarExpr exp1 =
                new ArithExp('+', new ConstExp(2),
                        new ArithExp("*", new ConstExp(3),
                                new ConstExp(5)));
        CompoundStm st1 =
                new CompoundStm(
                        new AssignmentStm('v', new ConstExp(2)),
                        new PrintStm(new VarExpr('v')));
        CompoundStm st2 =
                new CompoundStm(new AssignmentStm('a', exp1),
                        new CompoundStm('b', exp3),
                        new PrintStm(new VarExpr('b')));
    }
}

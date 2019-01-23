package Model;

import Commands.ExitCommand;
import Commands.RunExampleCommand;
import Controller.Cntrl;
import Model.DataStructure.*;
import Model.Expression.ConstExp;
import Model.Expression.ReadHeapExp;
import Model.Expression.VarExp;
import Model.Statement.*;
import Repository.IRepo;
import Repository.InMemRepo;
import View.TextMenu;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    public Interpreter() {
        //        a=2-2;(If a Then v=2 Else v=3);Print(v) is represented as
//        IStatement exp = new CompStatement(new CompStatement(new AssgnStatement("y", new ConstExp(4)), new WhileStatement(new BooleanExp(new VarExp("y"), new ConstExp(0), ">="), new AssgnStatement(
//                "y", new ArithExp(new VarExp("y"), new ConstExp(1), 2)
//        ))), new CompStatement(new CompStatement(new NewHeapValueStatement("z",
//                                                                                        new ConstExp(5)),
//                                                             new WriteHeapStatement("z",
//                                                                                     new ConstExp(12))),
//                                           new CompStatement(new OpenFileStatement("x", "src/fisier.txt"),
//                                                           new CompStatement(new ReadFromFileStatement("x", "readValue"),
//                                                                                               new CompStatement(new AssgnStatement("a",
//                                                                                                                 new ArithExp(new ConstExp(2),
//                                                                                                                              new ConstExp(2), 2)),
//                new CompStatement(new CompStatement(new AssgnStatement("z",
//                                                                        new ConstExp(25)), new IfStatement(new VarExp("a"),
//                                                                                                                   new AssgnStatement("v",
//                                                                                                                                      new ConstExp(2)),
//                                                                                                                   new AssgnStatement("v",
//                                                                                                                                       new ConstExp(3)))),
//                                                    new PrintStatement(new ReadHeapExp("x"))))))));

//        v=10;new(a,22);
//        fork(fork(wH(a,30);v=32;print(v);print(rH(a))));
//        print(v);print(rH(a))

        IStatement exp = new CompStatement(new AssgnStatement("v", new ConstExp(10)),
                                            new CompStatement(new CompStatement(new NewHeapValueStatement("a", new ConstExp(22)),
                                                    new ForkStatement(new ForkStatement(
                                                            new CompStatement(new WriteHeapStatement("a", new ConstExp(30)), new CompStatement(new AssgnStatement("v", new ConstExp(32)),
                                                                    new CompStatement(new PrintStatement(new VarExp("v")), new PrintStatement(new ReadHeapExp("a"))))))
                                                    )), new CompStatement(new PrintStatement(new VarExp("v")), new PrintStatement(new ReadHeapExp("a")))));

        MyIStack<IStatement> exeStack = new MyStack<IStatement>();
        exeStack.push(exp);
        MyIDictionary<String, Integer> symTable = new MyDictionary<String, Integer>();
        MyIList<Integer> out = new MyList<Integer>();
        MyIDictionary<Integer, FileTableValue> fileTable = new MyFileTable<Integer, FileTableValue>();
        MyIDictionary<Integer, Integer> heapTable = new MyHeapTable<Integer, Integer>();
        PrgState state = new PrgState(exeStack, symTable, out, fileTable, heapTable, 1);

        List<PrgState> programList = new ArrayList<PrgState>();
        programList.add(state);

        IRepo repo = new InMemRepo(programList, "src/log_file");
        Cntrl cntrl = new Cntrl(repo);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExampleCommand("1", "Run an example", cntrl));
        menu.show();
    }

}
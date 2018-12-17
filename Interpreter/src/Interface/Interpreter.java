package Interface;

import DataStructure.*;
import controller.Controller;
import model.*;
import repository.IntRepository;
import repository.Repository;

import java.util.Scanner;

class Interpreter {
    public static ProgState createProgState(IntStatement state){

        MyIntStack<IntStatement> stack = new MyStack<IntStatement>();
        MyIntDict<String, Integer> dict = new MyDict<String, Integer>();
        MyIntList<Integer> list = new MyList<Integer>();
        IntFileTable filetable = new FileTable();
        IntHeap heap = new Heap();
        return new ProgState(stack, dict, list, filetable, heap, state);

    }

    public static void main(String[] args) {
        IntStatement st1 = new CompStatement(new AssignStatement("v",new ConstExpresion(2)), new PrintStatement(new
                VarExpresion("v")));
        IntStatement st2 = new CompStatement(new AssignStatement("a", new ArithExpresion(1,new ConstExpresion(2),new
                ArithExpresion(3 ,new ConstExpresion(3), new ConstExpresion(5)))),
                new CompStatement(new AssignStatement("b",new ArithExpresion(1 ,new VarExpresion("a"), new
                        ConstExpresion(1))), new PrintStatement(new VarExpresion("b"))));

        IntStatement st3 =  new CompStatement(new AssignStatement("a", new ArithExpresion(2,new ConstExpresion(2), new ConstExpresion(2))),
                new CompStatement(new IfStatement(new VarExpresion("a"),new AssignStatement("v",new ConstExpresion(2)), new
                        AssignStatement("v", new ConstExpresion(3))), new PrintStatement(new VarExpresion("v"))));

        IntStatement st4 = new CompStatement(new CompStatement(new openRFile("var_f", "test.in"),new CompStatement(new readFile("var_f", "var_c"),
                new PrintStatement(new VarExpresion("var_c")))),
                new CompStatement(new IfStatement(new VarExpresion("var_c"),
                        new CompStatement(new readFile("var_f", "var_c"),
                                new PrintStatement(new VarExpresion("var_c"))),
                        new PrintStatement(new ConstExpresion(0))), new CompStatement( new closeRFile("var_f"), new closeRFile("var_f"))));

        IntStatement st5 = new CompStatement(new CompStatement(new openRFile("var_f", "test.in"),new CompStatement(new readFile("var_f" + 2, "var_c"),
                new PrintStatement(new VarExpresion("var_c")))),
                new CompStatement(new IfStatement(new VarExpresion("var_c"),
                        new CompStatement(new readFile("var_f", "var_c"),
                                new PrintStatement(new VarExpresion("var_c"))),
                        new PrintStatement(new ConstExpresion(0))), new closeRFile("var_f")));

//        IntStatement st6a =new CompStatement(
//                new CompStatement( new AssignStatement("v", new ConstExpresion(10)),
//                        new neww("v", new ConstExpresion(22))),
//                new wH("a", new ConstExpresion(30)));


        IntStatement st6 = new CompStatement(new CompStatement(
                             new CompStatement( new AssignStatement("v", new ConstExpresion(10)),
                                new CompStatement(new neww("v", new ConstExpresion(20)),
                                     new neww("a", new ConstExpresion(30)))),
                                new wH("a", new ConstExpresion(30))),
                                new CompStatement(new PrintStatement(new VarExpresion("a")), new CompStatement(new PrintStatement(new rH("a")),
                                 new CompStatement(new  AssignStatement("a", new ConstExpresion(4)),
                                         new PrintStatement(new rH("a"))))));


        IntStatement st7 = new CompStatement(new AssignStatement("v",  new ConstExpresion(6)),
                new CompStatement(new WhileStatement(new CompStatement(new PrintStatement(new VarExpresion("v")),
                        new AssignStatement("v", new ArithExpresion(2,new VarExpresion("v"),
                                new ConstExpresion(1)))), new VarExpresion("v")), new PrintStatement(new VarExpresion("v"))));

        Scanner scan = new Scanner(System.in);
        System.out.println("Log file path:");
        String filen_name = scan.nextLine();
        IntRepository rep = new Repository(filen_name);
        Controller cont = new Controller(rep);
        cont.addPrgState(createProgState(st1));
        cont.addPrgState(createProgState(st2));
        cont.addPrgState(createProgState(st3));
        cont.addPrgState(createProgState(st4));
        cont.addPrgState(createProgState(st5));
        cont.addPrgState(createProgState(st6));
        cont.addPrgState(createProgState(st7));
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit!"));
        menu.addCommand(new AllStepsCommand("1", "Run all steps an example!", cont));
        menu.show();
   }

}
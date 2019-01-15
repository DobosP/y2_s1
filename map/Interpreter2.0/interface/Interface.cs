using System;
using models;
using DataStructure;
using Repository;
using Controller;

namespace Interface
{
    public class Interface
    {
          public static ProgState createProgState(IntStatement state){

        MyIntStack<IntStatement> stack = new MyStack<IntStatement>();
        MyIntDict<String, int> dict = new MyDict<String, int>();
        MyIntList<int> list = new MyList<int>();
        IntFileTable filetable = new FileTable();

        return new ProgState(stack, dict, list, filetable, state);

    }

        static void Main(string[] args)
        {
            IntStatement st1 = new CompStatement(new AssignStatement("a", new ArithExpresion(1,new ConstExpresion(2),new
                ArithExpresion(3 ,new ConstExpresion(3), new ConstExpresion(5)))),
                new CompStatement(new AssignStatement("b",new ArithExpresion(1 ,new VarExpresion("a"), new
                        ConstExpresion(1))), new PrintStatement(new VarExpresion("b"))));

            IntStatement st2 = new CompStatement(new CompStatement(new openRFile("var_f", "test.in"),new CompStatement(new readFile("var_f", "var_c"),
                new PrintStatement(new VarExpresion("var_c")))),
                new CompStatement(new IfStatement(new VarExpresion("var_c"),
                        new CompStatement(new readFile("var_f", "var_c"),
                                new PrintStatement(new VarExpresion("var_c"))),
                        new PrintStatement(new ConstExpresion(0))), new CompStatement( new closeRFile("var_f"), new closeRFile("var_f"))));


            IntStatement st3 = new CompStatement(new CompStatement(new openRFile("var_f", "test.in"),new CompStatement(new readFile("var_f", "var_c"),
                new PrintStatement(new VarExpresion("var_c")))),
                new closeRFile("var_f"));

            
            System.Console.WriteLine("Log file path:");
            String filen_name = System.Console.ReadLine();
            IntRepository rep = new Repository.Repository(filen_name);
            Controller.Controller cont = new Controller.Controller(rep);
            
            cont.addPrgState(createProgState(st1));
            cont.addPrgState(createProgState(st2));
            cont.addPrgState(createProgState(st3));
            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "Exit!"));
            menu.addCommand(new AllStepsCommand("1", "Run all steps an example!", cont));
            menu.show();
            
        } 
    }
}
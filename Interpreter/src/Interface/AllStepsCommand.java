package Interface;

import ExceptionHandling.MyException;
import controller.Controller;

import java.util.Scanner;

public class AllStepsCommand extends Command{
    private Controller ctr;
    public AllStepsCommand(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Index of Program :");
        String index = scan.nextLine();
        try {
            ctr.AllSteps(Integer.parseInt(index));
        }
        catch(MyException e){
            System.out.println(e.getMessage());
        }
    }
}

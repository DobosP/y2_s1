package View;

import Controller.Cntrl;

import java.util.Scanner;

public class View {
    private Cntrl cntrl;
    private Scanner scanner;

    public View(final Cntrl cntrl) {
        this.cntrl = cntrl;
        this.scanner = new Scanner(System.in);
    }

    public void startLoop() {
        while (true) {
            System.out.println("0 -> Exit\n 1 -> All step execution\n");
            int chosenOpt = scanner.nextInt();

            switch (chosenOpt) {
                case 0: return;
                case 1:
                    cntrl.allStep();
                    break;
//                case 2:
//                    System.out.println(cntrl.getCrrtProgram());
//                    break;

            }
        }
    }


}
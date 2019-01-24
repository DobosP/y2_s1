package application;

import DataStructure.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.*;
import repository.IntRepository;
import repository.Repository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class StartPageController implements Initializable {

    public Button execute_button;
    public ListView list_statemant;
    private IntProcTable progtable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        progtable = new ProcTable();

        IntStatement procst1 = new CompStatement(
                new AssignStatement("v", new ArithExpresion(1, new VarExpresion("a"), new VarExpresion("b"))),
                new PrintStatement(new VarExpresion("v"))
        );
        IntStatement procst2 = new CompStatement(
                new AssignStatement("v", new ArithExpresion(3, new VarExpresion("a"), new VarExpresion("b"))),
                new PrintStatement(new VarExpresion("v"))
        );

        List<String> var = new ArrayList<>();
        var.add("a");
        var.add("b");
        progtable.put("sum",new Pair<>(var,procst1));
        progtable.put("product",new Pair<>(var,procst2));

        IntStatement fork_st = new CompStatement(
                new CompStatement(
                        new AssignStatement("v", new ConstExpresion(10)),
                        new neww("a", new ConstExpresion(22))
                ),
                new CompStatement(

                        new forkStmt(new CompStatement(
                                new CompStatement(
                                        new wH("a", new ConstExpresion(10)),
                                        new AssignStatement("v", new ConstExpresion(32))
                                ),
                                new CompStatement(
                                        new PrintStatement(new VarExpresion("v")),
                                        new PrintStatement(new rH("a"))
                                )
                        )

                        ),
                        new forkStmt (new CompStatement(
                                new PrintStatement(new VarExpresion("v")),
                                new PrintStatement(new rH("a"))
                        ))
                )
        );

        IntStatement second_statemant = new CompStatement(new CompStatement(
                             new CompStatement( new AssignStatement("v", new ConstExpresion(10)),
                                new CompStatement(new neww("v", new ConstExpresion(20)),
                                     new neww("a", new ConstExpresion(30)))),
                                new wH("a", new ConstExpresion(30))),
                                new CompStatement(new PrintStatement(new VarExpresion("a")), new CompStatement(new PrintStatement(new rH("a")),
                                 new CompStatement(new  AssignStatement("a", new ConstExpresion(4)),
                                         new PrintStatement(new rH("a"))))));

        IntStatement st3 = new CompStatement(new CompStatement(new openRFile("var_f", "test.in"),new CompStatement(new readFile("var_f", "var_c"),
                new PrintStatement(new VarExpresion("var_c")))),
                new CompStatement(new IfStatement(new VarExpresion("var_c"),
                        new CompStatement(new readFile("var_f", "var_c"),
                                new PrintStatement(new VarExpresion("var_c"))),
                        new PrintStatement(new ConstExpresion(0))), new CompStatement( new closeRFile("var_f"), new closeRFile("var_f"))));




        IntStatement sleep_state = new CompStatement(
                new AssignStatement("v",new ConstExpresion(10) ),
                new CompStatement(
                        new forkStmt(
                              new CompStatement(
                                     new AssignStatement("v",new ArithExpresion( 2, new VarExpresion("v"), new ConstExpresion(1))),
                                      new CompStatement(
                                              new AssignStatement("v",new ArithExpresion( 2, new VarExpresion("v"), new ConstExpresion(1))),
                                              new PrintStatement( new VarExpresion("v") )
                                              )
                              )
                        ),
                        new CompStatement(
                                new sleep(10),
                                new PrintStatement(new ArithExpresion(3, new VarExpresion("v"), new ConstExpresion(10) ))
                        )
                )

        );

        ObservableList<IntStatement> items = FXCollections.observableArrayList(fork_st, sleep_state);

        list_statemant.setItems(items);


    }

    public void click_execute(ActionEvent actionEvent) throws Exception {

        Stage gui_stage = new Stage();
        Gui gui = new Gui((IntStatement) list_statemant.getSelectionModel().getSelectedItem(), progtable);
        gui.start(gui_stage);
        gui_stage.show();
        Stage curr_stage =  (Stage) execute_button.getScene().getWindow();
        curr_stage.close();
    }
}

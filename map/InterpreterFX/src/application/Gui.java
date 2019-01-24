package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import DataStructure.*;
import model.*;
import repository.IntRepository;
import repository.Repository;




public class Gui extends Application{

    private IntStatement statement;

    public  Gui(IntStatement _statement){
        statement = _statement;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Gui.fxml"));
        Parent root = loader.load();

        GuiController controller = loader.getController();
        controller.SetStatemant(this.statement);

        primaryStage.setTitle("Main Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

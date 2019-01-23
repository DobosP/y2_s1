package application;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import DataStructure.*;
import controller.Controller;
import model.*;
import repository.IntRepository;
import repository.Repository;


public class StartPage extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/application/StartPage.fxml"));
        primaryStage.setTitle("Available Programs");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

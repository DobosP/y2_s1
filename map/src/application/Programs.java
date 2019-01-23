package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
//import javafx.scene.layout.BorderPane;


public class Programs extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();


            // Read file fxml and draw interface.
//            Parent root = FXMLLoader.load(getClass()
//                    .getResource("/application/ProgramState.fxml"));
//
//            primaryStage.setTitle("My Application");
//            primaryStage.setScene(new Scene(root));
//            primaryStage.show();

            Parent root = FXMLLoader.load(getClass().getResource("/application/Programs.fxml"));
            primaryStage.setTitle("Available Programs");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
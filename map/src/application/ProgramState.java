package application;

import Model.Statement.IStatement;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
//import javafx.scene.layout.BorderPane;


public class ProgramState extends Application {

    private IStatement statement;

    public ProgramState(final IStatement statement) {
        this.statement = statement;
    }

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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ProgramState.fxml"));
            Parent root = loader.load();

            ProgramStateController controller = loader.getController();
            controller.setStatement(this.statement);
            primaryStage.setTitle("Program State");
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
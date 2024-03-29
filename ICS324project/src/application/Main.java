package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
    	// this is a comment
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
            Scene scene = new Scene(root, 800, 800);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(500);
            primaryStage.setMinWidth(850);
            primaryStage.setTitle("KCTDS");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
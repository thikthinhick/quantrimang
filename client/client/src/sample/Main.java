package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage guiStage;
    private static Controller controller;
    public static Stage getStage() {
        return guiStage;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        controller = new Controller();
        guiStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        Login login = fxmlLoader.getController();
        login.init(controller);
        primaryStage.setTitle("Client");
        Scene scene = new Scene(root, 350, 300);
        guiStage.setScene(scene);
        guiStage.show();
    }
    public static void changeScene(String name) {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource(name));
        try {
            root = fxmlLoader.load();
            if(name.equals("Connecting.fxml")) {
                Connecting a  = fxmlLoader.getController();
                a.init(controller);
            }
            else{
                Login a = fxmlLoader.getController();
                a.init(controller);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        guiStage.setScene(scene);
        guiStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
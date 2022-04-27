package Main;

import components.Server;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    public static FXMLLoader loader;
    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene( scene );
        primaryStage.setTitle("Server");
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        launch(args);
    }

}

package Main;

import components.Server;
import controller.CommandsClient;
import controller.SendFile;
import controller.managerFxml;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.managerFxml.loaderTable;


public class Controller implements Initializable {
    public static FXMLLoader loader = new FXMLLoader();
    @FXML
    public static Pane pane1 = new Pane();
    public Pane content;

    CommandsClient commandsClient = new CommandsClient();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loaderTable.setLocation(getClass().getResource("/fxml/Table.fxml"));
            pane1 = (Pane) loaderTable.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        content.getChildren().add(pane1);
    }
    public static void updateTable() {
        Platform.runLater(() -> {
            pane1.getChildren().removeAll();
            try {
                Pane fxml = (Pane) FXMLLoader.load((Controller.class.getResource("/fxml/Table.fxml")));
                pane1.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void AddUser(ActionEvent actionEvent) {
        try {
            loaderTable.setLocation(getClass().getResource("/fxml/Table.fxml"));
            pane1 = (Pane) loaderTable.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        content.getChildren().add(pane1);
    }

    public void DeleteUser(ActionEvent actionEvent) {
        pane1.getChildren().removeAll();
        try {
            Pane fxml = (Pane) FXMLLoader.load((getClass().getResource("/fxml/CameraView.fxml")));
            pane1.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adduser(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/AddUser.fxml"));
        SendFile sendFile = fxmlLoader.getController();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage newWindow = new Stage();
        newWindow.setTitle("AddUser");
        newWindow.setScene(scene);
        newWindow.show();
    }

    public void deleteuser(ActionEvent actionEvent) {
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void showHelp(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Help.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage newWindow = new Stage();
        newWindow.setTitle("About");
        newWindow.setScene(scene);
        newWindow.show();
    }
}
package controller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class managerFxml {
    public static FXMLLoader loaderTable = new FXMLLoader();
    public static void showAlert(String title) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText(title);
                alert.showAndWait();
            }
        });
    }
}

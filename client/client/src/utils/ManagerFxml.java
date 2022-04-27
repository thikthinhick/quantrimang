package utils;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sample.Chat;

import java.io.IOException;

public class ManagerFxml {
    private Stage newWindow;
    private FXMLLoader loaderChat;
    public ManagerFxml() {
        this.newWindow = new Stage();
    }
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
    public void showChat() {
        loaderChat = new FXMLLoader();
        loaderChat.setLocation(ManagerFxml.class.getResource("/sample/Chat.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loaderChat.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        newWindow = new Stage();
        newWindow.setTitle("Client-Chat");
        newWindow.setScene(scene);
        newWindow.show();
    }

    public FXMLLoader getLoaderChat() {
        return loaderChat;
    }
}

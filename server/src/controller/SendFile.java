package controller;

import components.ClientHandler;
import components.Server;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Commands;
import utils.FileSend;
import utils.Message;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SendFile implements Initializable {
    @FXML
    private TextArea ListFile;
    private ClientHandler client;
    final FileChooser fileChooser = new FileChooser();
    ArrayList<String> arr = new ArrayList<>();
    public void ButtonChooseFile(ActionEvent actionEvent) {
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());
        printLog(files);
    }
    public void init(ClientHandler client) {
        this.client = client;
    }
    private void printLog(List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            ListFile.appendText(file.getAbsolutePath() + "\n");
            arr.add(file.getAbsolutePath());
        }
    }
    public void ButtonSendFile(ActionEvent actionEvent) {
        for(int i = 0; i < arr.size(); i++) {
            try {
                File x = new File(arr.get(i));
                byte[] bytes = Files.readAllBytes(x.toPath());
                String name = x.getName();
                this.client.getDos().writeObject(new Message(21, new FileSend(name, bytes)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        managerFxml.showAlert("Gửi file thành công!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

package controller;

import components.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Client;

import java.awt.event.*;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class controllerTable implements Initializable, KeyListener, MouseListener, MouseMotionListener {
    @FXML
    private TableView<Client> table;

    @FXML
    private TableColumn<Client, Integer> idColumn;

    @FXML
    private TableColumn<Client, String> userNameColumn;

    @FXML
    private TableColumn<Client, String> hostNameColumn;

    @FXML
    private TableColumn<Client, Integer> portColumn;

    @FXML
    private TableColumn<Client, String> descriptionColumn;

    @FXML
    private TableColumn<Client, MenuButton> actionColumn;

    @FXML
    private TableColumn<Client, Date> dateColumn;

    private ObservableList<Client> clientList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void update() {
        clientList = FXCollections.observableArrayList();
        int i = 0;
        for(ClientHandler ch : Server.ar) {
            i++;
            clientList.add( new Client(  i, ch.getNameClient(), ch.getS().getPort(), ch.getS().getInetAddress().toString(), new Date(), ch.getUserName(), ch));
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("userName"));
        hostNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("hostName"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<Client, MenuButton>("action"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Client, Date>("date"));
        portColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("port"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("Description"));
        table.setItems(clientList);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("click");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("hello");
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
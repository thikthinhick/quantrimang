package controller;

import connectDatabase.ConnectMysql;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class AddUser {
    public TextField username;
    public TextField password;

    public void createUser(ActionEvent actionEvent) {
        String id = UUID.randomUUID().toString();
        String sql = "insert into userclient (username, password, iduser) values ('" + username.getText() + "','" + password.getText() + "','" + id + "')";
        Connection conn = ConnectMysql.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }
}

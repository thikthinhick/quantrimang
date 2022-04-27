package controller;

import components.ClientHandler;
import connectDatabase.ConnectMysql;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import utils.Client;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SelectAccess{
    @FXML
    private CheckBox phonggiamdoc;
    @FXML
    private CheckBox phongmarketing;
    @FXML
    private CheckBox phongkinhdoanh;
    @FXML
    private CheckBox phongbaove;
    @FXML
    private CheckBox phongsanxuat;
    private String idUser;
    public void init(String idUser) {
        this.idUser = idUser;
        Connection conn = ConnectMysql.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "select * from access where iduser = '" + this.idUser + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                phongkinhdoanh.setSelected(rs.getBoolean(2));
                phonggiamdoc.setSelected(rs.getBoolean(3));
                phongmarketing.setSelected(rs.getBoolean(4));
                phongbaove.setSelected(rs.getBoolean(5));
                phongsanxuat.setSelected(rs.getBoolean(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void submit(ActionEvent actionEvent) {
        String sql = "update access set phongkinhdoanh = " + phongkinhdoanh.isSelected() +
                ", phonggiamdoc = " + phonggiamdoc.isSelected() + ", phongmarketing = " +
                phongmarketing.isSelected() + ", phongbaove = " + phongbaove.isSelected()+ ", phongsanxuat = " +
                phongsanxuat.isSelected() + " where iduser = '" + idUser + "'";
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

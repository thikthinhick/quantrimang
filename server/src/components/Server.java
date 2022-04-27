package components;

//import Main.Controller;

import Main.Main;
import Main.Controller;
import connectDatabase.ConnectMysql;
import controller.controllerTable;
import controller.managerFxml;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import utils.Message;
import utils.User;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.net.*;
 

public class Server
{
    public static Vector<ClientHandler> ar = new Vector<>();

    static int i = 0;

    public Server () throws IOException
    {
        ServerSocket ss = new ServerSocket(1234);
        final Socket[] s = {null};
        Thread ConnectClient = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {   ObjectInputStream ois = null;
                    ObjectOutputStream oos = null;
                    try {
                        s[0] = ss.accept();
                        ois = new ObjectInputStream(s[0].getInputStream());
                        oos = new ObjectOutputStream(s[0].getOutputStream());
                        Message message = (Message) ois.readObject();
                        String data = (String) message.getData();
                        String username = data.split("/")[0];
                        String password = data.split("/")[1];
                        String computerName = data.split("/")[2];
                        Connection conn = ConnectMysql.getConnection();
                        Statement stmt = null;
                        String sql1 = "select iduser, username from userclient where username = '" + username + "' and password = '" + password +"'";
                        String sql2 = "update userclient set ip = '" + s[0].getInetAddress().toString().split("/")[1] + "' where username = '" + username + "'";
                        try {
                            stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(sql1);
                            if(rs.next()) {
                                String IdUser = rs.getString("iduser");
                                String UserName = rs.getString("username");
                                stmt.executeUpdate(sql2);
                                ClientHandler newClientHandler = new ClientHandler(s[0], computerName, ois, oos, IdUser, UserName);
                                oos.writeObject(new Message(1, true));
                                Thread t = new Thread(newClientHandler);
                                ar.add(newClientHandler);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        controllerTable controller = managerFxml.loaderTable.getController();
                                        controller.update();
                                    }
                                });
                                t.start();
                                i++;
                            }
                            else{
                                oos.writeObject(new Message(1, false));
                            }
                            rs.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        ConnectClient.start();
    }
}
 
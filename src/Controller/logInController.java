package Controller;

import Model.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logInController {

    @FXML
    private Button button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label wronglogin;

    private Connection conn = null;

    @FXML
    void logUser(ActionEvent event) throws SQLException, IOException {
        String user=username.getText();
        String pass=password.getText();
        conn = DBConnection.createDBConnection();
        boolean res = checkUser(user,pass);
        System.out.println(res);
        if(res){
            Stage stage;
            Scene scene ;
            Parent root;
            root= FXMLLoader.load(getClass().getResource("../View/dashboard.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

            System.out.println(" true");

        }
        else {
            wronglogin.setText(" wrong pass or username");
        }
    }

    public boolean checkUser(String userName, String pass) throws SQLException {
        PreparedStatement statement = null;
        try {

            String query = "SELECT username,password from admin where username=? and password=?";
            statement = this.conn.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();

        } finally {
            if (null != statement) {
                statement.close();
            }
            if (null != this.conn) {
                this.conn.close();
            }


        }
        return false;
    }
}
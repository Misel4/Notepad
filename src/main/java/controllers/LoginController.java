package controllers;

import daos.ConnectionDAO;
import daos.LoginDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.Utils;

public class LoginController {

    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public AnchorPane anchorPane;
    public Button registerButton;

    public void initialize(){

        ConnectionDAO.connectToDatabase();

        anchorPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                if (LoginDAO.userExists(usernameTextField.getText(),passwordTextField.getText())){
                    ConnectionDAO.setCurrentUser(usernameTextField.getText());
                    ((Stage)anchorPane.getScene().getWindow()).close();
                    Utils.showForm("MainView.fxml","Notepad - " + ConnectionDAO.getCurrentUser());
                }
                else new Alert(Alert.AlertType.WARNING,"login failed").showAndWait();
            }
        });

        registerButton.setOnAction(event -> {
            ((Stage)anchorPane.getScene().getWindow()).close();
            Utils.showForm("Register.fxml","Register");
        });

    }
}

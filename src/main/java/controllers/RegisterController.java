package controllers;

import daos.RegisterDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.Utils;

public class RegisterController {

    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public PasswordField repeatPasswordTextField;
    public AnchorPane anchorPane;
    public TextField emailTextField;
    public Button loginButton;


    public void initialize(){

        anchorPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                String username = usernameTextField.getText();

                if (username.isEmpty() || passwordTextField.getText().isEmpty() || repeatPasswordTextField.getText().isEmpty()){
                    new Alert(Alert.AlertType.WARNING,"Please fill in all fields").showAndWait();
                    return;
                }
                if (!passwordTextField.getText().equals(repeatPasswordTextField.getText())){
                    new Alert(Alert.AlertType.WARNING,"Passwords do not match").showAndWait();
                    return;
                }

                if (RegisterDAO.usernameExists(username)){
                    new Alert(Alert.AlertType.ERROR,"Username already exists").showAndWait();
                    return;
                }

                RegisterDAO.insertUser(username,passwordTextField.getText(),emailTextField.getText());
                ((Stage)anchorPane.getScene().getWindow()).close(); // close current window
                Utils.showForm("Login.fxml","Login");
            }
        });

        loginButton.setOnAction(event -> {
            ((Stage)anchorPane.getScene().getWindow()).close(); // close current window
            Utils.showForm("Login.fxml","Login");
        });
    }
}


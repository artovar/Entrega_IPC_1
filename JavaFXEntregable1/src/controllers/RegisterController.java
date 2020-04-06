/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class RegisterController implements Initializable {

    @FXML
    private Button registerButton;
    @FXML
    private TextField nameText;
    @FXML
    private TextField surnameText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField loginText;
    @FXML
    private TextField creditText;
    @FXML
    private TextField svcText;
    @FXML
    private PasswordField passText;
    @FXML
    private RadioButton image1;
    @FXML
    private RadioButton image2;
    @FXML
    private RadioButton image3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registerButton(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class PerfilController implements Initializable {

    @FXML
    private Label nombreLabel;
    @FXML
    private Label telefonoLabel;
    @FXML
    private Label tarjetaLabel;
    private Member user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void data(Member mem){
        user = mem;
        nombreLabel.setText(user.getName() + " " + user.getSurname());
        telefonoLabel.setText(user.getTelephone());
        tarjetaLabel.setText("*********" + user.getCreditCard().substring(8));
    }
}

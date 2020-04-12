/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DBAcess.ClubDBAccess;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Booking;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class reservasController implements Initializable {

    @FXML
    private Button filtrarButton;
    @FXML
    private Label pistaLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ListView<?> listaPista;
    @FXML
    private Button reservarButton;

    private String pista;
    private int inicioDia = 9 * 60;
    
            
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    

    @FXML
    private void filtrarReservas(ActionEvent event) {
        
    }

    @FXML
    private void reservarPista(ActionEvent event) {
    }
    
}

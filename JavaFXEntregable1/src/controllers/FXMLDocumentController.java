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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import model.Member;

/**
 *
 * @author aaren
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private Button PistasButtonFXID;
    @FXML
    private Button MisReservasButtonFXID;
    @FXML
    private Button DisponibilidadButtonFXID;
    @FXML
    private Tab PistasTabFXID;
    @FXML
    private Tab ReservasTabFXID;
    @FXML
    private Tab DisponibilidadTabFXID;
    
    
    private String username;
    
    @FXML
    private Label usernameLabelFXID;
    @FXML
    private ListView<?> ListViewReservasFXID;
    @FXML
    private TableView<?> TableViewDisponibilidadFXID;
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
    }    

    @FXML
    private void selecPistas(ActionEvent event) {
        
        PistasTabFXID.closableProperty().set(true);
    }

    @FXML
    private void selecResevas(ActionEvent event) {
        ReservasTabFXID.closableProperty().set(true);
        
    }

    @FXML
    private void selecDisponibilidad(ActionEvent event) {
        ReservasTabFXID.closableProperty().set(true);
    }
    
    public void initMember(Member men,Boolean registered) {
        
        username = men.getLogin();
        usernameLabelFXID.setText(username);
        
        if(registered) {
        
        //Pues aqui habilitaresmos los tabs de mis reservas y de hacer reservas
        
        
        }
        else{
        //Aqui dejamos deshabilitadas las opciones puesto que entra como invitado
        usernameLabelFXID.setText("Invitado");
        }
        
        
        
    }
    
}

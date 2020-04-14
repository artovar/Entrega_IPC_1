/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DBAcess.ClubDBAccess;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Booking;
import model.Member;


/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class PagarController implements Initializable {

    @FXML
    private Button pagarButton;
    private Member miembro;
    private Booking reserva;
    @FXML
    private TextField creditText;
    @FXML
    private TextField svcText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hacerPago(ActionEvent event) {
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        if(!((creditText.getText().length()) == 12) || !((svcText.getText().length()) == 3)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el pago");
            alert.setContentText("Rellene de forma correcta los campos" );
           
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK");
            } else {
            System.out.println("CANCEL");
            }
        }
        else{
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Pago realizado");
           alert.setHeaderText("¿Desea añadir los datos a su cuenta?" ); 
           alert.setContentText("Si lo haces no tendras que volver a pagar manualmente y se realizaran los pagos de todas tus reservas actuales");
           
           Optional<ButtonType> result = alert.showAndWait();
           if (result.isPresent() && result.get() == ButtonType.OK){
               ArrayList<Booking> aux = clubDBAcess.getUserBookings(miembro.getLogin());
               for(int i = 0; i < aux.size(); i++){
                   clubDBAcess.getBookings().remove(aux.get(i));
                   aux.get(i).setPaid(Boolean.TRUE);
                   clubDBAcess.getBookings().add(aux.get(i));
               }
               miembro.setCreditCard(creditText.getText());
               miembro.setSvc(svcText.getText());
           } else {
               clubDBAcess.getBookings().remove(reserva);
               reserva.setPaid(Boolean.TRUE);
               clubDBAcess.getBookings().add(reserva);
           }
           clubDBAcess.saveDB();
           ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
    }
    
    public void obtainData(Member mem,Booking b){
        miembro = mem;
        reserva = b;
    }
}

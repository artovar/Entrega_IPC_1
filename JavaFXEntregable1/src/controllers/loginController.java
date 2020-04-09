/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DBAcess.ClubDBAccess;
import model.Member;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fernando
 */
public class loginController implements Initializable {

    @FXML
    private TextField loginText;
    @FXML
    private Button logInButton;
    @FXML
    private Label registerLabel;
    @FXML
    private Label reservasLabel;
    @FXML
    private PasswordField passText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void entrarMain(ActionEvent event) {
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        if(clubDBAcess.getMemberByCredentials(loginText.getText(), passText.getText()) != null){
            System.out.println("funciona"); //cambiar por cargador de ventana main
        }
         else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Error al iniciar sesion");
            alert.setTitle("Error");
            alert.setContentText("Parece que el Login y contraseña que has puesto no son correctos");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.equals(ButtonType.OK)){System.out.println("ok");}
            else{System.out.println("cancel");}}
    }

    @FXML
     private void entrarRegistro(MouseEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Registro");
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    private void entrarReservasSinRegistro(MouseEvent event) {
    }
    
}

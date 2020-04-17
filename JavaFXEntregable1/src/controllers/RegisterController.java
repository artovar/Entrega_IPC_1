/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DBAcess.ClubDBAccess;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import model.Member;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private ToggleGroup Radio;
    @FXML
    private RadioButton Radio1;
    @FXML
    private ImageView Image1;
    @FXML
    private RadioButton Radio2;
    @FXML
    private ImageView Image2;
    @FXML
    private RadioButton Radio3;
    @FXML
    private ImageView Image3;
    
    
    
     
   
    

    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       phoneText.textProperty().addListener(new ChangeListener<String>(){
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
               if(!newValue.matches("\\d*")) {
                   phoneText.setText(newValue.replaceAll("[^\\d]", ""));
               }
           }
       });
       
       svcText.textProperty().addListener(new ChangeListener<String>(){
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
               if(!newValue.matches("\\d*")) {
                   svcText.setText(newValue.replaceAll("[^\\d]", ""));
               }
           }
       });
       
       creditText.textProperty().addListener(new ChangeListener<String>(){
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
               if(!newValue.matches("\\d*")) {
                   creditText.setText(newValue.replaceAll("[^\\d]", ""));
               }
           }
       });
       
    }    

    @FXML
    private void registerButton(ActionEvent event) {
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        String aux = wrongField();
        if (!(aux == "")) {
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error en el registro");
           alert.setHeaderText("Vaya parece que hemos tenido un error al registrarte...");
           alert.setContentText("Rellene de forma correcta los siguientes campos " +aux );
           
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK");
            } else {
            System.out.println("CANCEL");
}
        }
        else{
            clubDBAcess.getMembers().add(new Member(nameText.getText(), surnameText.getText(), phoneText.getText(), loginText.getText(), passText.getText(), creditText.getText(), svcText.getText(), selectedImage()  ));
            clubDBAcess.saveDB();
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
        
    }
    
    private void sinLetrasPhone(){
        String aux=null;
        if(phoneText.getText().matches("[0-9]*")){
            aux=phoneText.getText();
        }
        else{aux=null;}
        phoneText.setText(aux);
    }
    
    private void cambio(ObservableValue<? extends String> observable, String oldValue, String newValue){
        if(!newValue.matches("\\d*")){
            phoneText.setText(newValue.replaceAll("[^\\d]",""));
        }
    }
    private String wrongField(){
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        String wrong = "";
        if(nameText.getText().isEmpty()){
            wrong += " Nombre ";
        }
        if(surnameText.getText().isEmpty() ){
            wrong += " Apellidos ";
        }
        if( !((phoneText.getText().length()) == 9)){
            wrong += " Telefono ";
        }
        if(loginText.getText().isEmpty() || loginText.getText().contains(" ")){
            wrong += " Login ";
        }
        if(!((creditText.getText().length()) == 12) && !((creditText.getText().length()) == 0) ){
            wrong += " Tarjeta crédito ";
        }
        if(!((svcText.getText().length()) == 3) && !((svcText.getText().length()) == 0) ){
            wrong += " SVC ";
        }
        if(passText.getText().isEmpty() || !containsNumber(passText) || ((passText.getText().length()) < 6)){
            wrong += " Password ";
        }
        if(selectedImage() == null){
            wrong += " Foto de Perfil ";
        }
        if(clubDBAcess.existsLogin(loginText.getText()) ){
            wrong += "El nombre que has elegido ya esta en uso";
        }
        return wrong;
    }
    
    private boolean containsNumber(PasswordField pass){
        if(pass.getText().contains("0")){ return true;}
        else if(pass.getText().contains("1")){ return true;}
        else if(pass.getText().contains("2")){ return true;}
        else if(pass.getText().contains("3")){ return true;}
        else if(pass.getText().contains("4")){ return true;}
        else if(pass.getText().contains("5")){ return true;}
        else if(pass.getText().contains("6")){ return true;}
        else if(pass.getText().contains("7")){ return true;}
        else if(pass.getText().contains("8")){ return true;}
        else if(pass.getText().contains("9")){ return true;}
        else{return false;}
    }
    
    
    private Image selectedImage(){
        
        if(Radio1.isSelected()){ return Image1.getImage();}
        else if(Radio2.isSelected()){return Image2.getImage();}
        else if(Radio3.isSelected()){return Image3.getImage();}
        else{return null;}
        
    }

    
    
   
}   


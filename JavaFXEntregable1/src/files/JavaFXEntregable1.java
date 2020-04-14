/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import DBAcess.ClubDBAccess;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Booking;

/**
 *
 * @author aaren
 */
public class JavaFXEntregable1 extends Application {
    private ClubDBAccess clubDBAccess;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        

        stage.setOnCloseRequest((event) -> {
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(clubDBAccess.getClubName());
            alert.setHeaderText("Guardado de datos");
            alert.setContentText("La aplicación guardará los datos proporcionados antes de cerrarse");
            alert.show();
            clubDBAccess.saveDB();
            
        });
        
        ArrayList<Booking> reservasUser = clubDBAccess.getBookings();
        for(int i = 0; i < reservasUser.size() ; i++){
            if(reservasUser.get(i).getMadeForDay().isBefore(LocalDate.now())){
                clubDBAccess.getBookings().remove(reservasUser.get(i));
                reservasUser.remove(i);
            }
        }



    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

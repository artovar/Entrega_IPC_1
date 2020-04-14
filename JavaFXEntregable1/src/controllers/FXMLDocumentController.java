/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DBAcess.ClubDBAccess;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;







import model.Court;

import model.Booking;




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
    private ListView<Booking> ListViewReservasFXID;
    @FXML
    private TableView<?> TableViewDisponibilidadFXID;
    @FXML
    private Button pista1;
    @FXML
    private Button pista2;
    @FXML
    private Button pista3;
    @FXML
    private Button pista4;
    @FXML
    private TabPane TabPaneFXID;
    
    
    private Member user;
    private ObservableList<Booking> datosReservas = null;
    @FXML
    private TableColumn<?, ?> horaColumFXID;
    @FXML
    private TableColumn<Booking, String> pista1ColumFXID;
    @FXML
    private TableColumn<Booking, String> pista2ColumFXID;
    @FXML
    private TableColumn<Booking, String> pista3ColumFXID;
    @FXML
    private TableColumn<Booking, String> pista4ColumFXID;
    @FXML
    private TableView<Booking> pista1Table;
    @FXML
    private TableView<Booking> pista2Table;
    @FXML
    private TableView<Booking> pista3Table;
    @FXML
    private TableView<Booking> pista4table;

    @FXML
    private Button eliminarButton;

    private Member member;
    @FXML
    private Button actualizarButtonFXID;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
 
        //GESTION TABLA DISPONIBILIDAD

    }   
    
    @FXML
    private void eliminarReserva(ActionEvent event) {
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        Booking newBooking = ListViewReservasFXID.getSelectionModel().getSelectedItem();
        if(newBooking.isOlderForDay(LocalDate.now())){
            clubDBAcess.getBookings().remove(newBooking);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Reserva eliminada");
                alert.setHeaderText("La reserva seleccionada ha sido eliminada");
            
           
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                TabPaneFXID.getSelectionModel().select(PistasTabFXID);
                clubDBAcess.saveDB();
                } else {
                TabPaneFXID.getSelectionModel().select(PistasTabFXID);
                clubDBAcess.saveDB();
                }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Imposible eliminar");
            alert.setHeaderText("La reserva seleccionada no se puede eliminar");
            alert.setContentText("No puedes eliminar esta reserva, pues quedan menos de 24 horas");
            
           
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK");
            } else {
            System.out.println("CANCEL");
            }   
        }
    }

    @FXML
    private void mostrarReservas(Event event) {
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        ArrayList<Booking> reservasUser = clubDBAcess.getUserBookings(username);
        for(int i = 0; i < reservasUser.size() ; i++){
            if(reservasUser.get(i).getMadeForDay().isBefore(LocalDate.now())){
                clubDBAcess.getBookings().remove(reservasUser.get(i));
                reservasUser.remove(i);
            }
        }
        //LISTADO DE RESERVAS DEL USUARIO
        datosReservas = FXCollections.observableArrayList(reservasUser);
        ListViewReservasFXID.setCellFactory(c -> new UserBookings());
        ListViewReservasFXID.setItems(datosReservas);
        clubDBAcess.saveDB();
    }

    @FXML
    private void selecPistas(ActionEvent event) {
        
        TabPaneFXID.getSelectionModel().select(PistasTabFXID);
        
    }

    @FXML
    private void selecResevas(ActionEvent event) {
        TabPaneFXID.getSelectionModel().select(ReservasTabFXID);
    }

    @FXML
    private void selecDisponibilidad(ActionEvent event) {
        TabPaneFXID.getSelectionModel().select(DisponibilidadTabFXID);
        
    }
    
    public void initMember(Member men,Boolean registered) {
   
        user = men;
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

    @FXML
    private void reservar(ActionEvent event) throws Exception {
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        Court pista;
        if(event.getSource().equals(pista1)){
            pista = clubDBAcess.getCourt("Pista 1");
            FXMLLoader reserva = new FXMLLoader(getClass().getResource("/fxml/reservas.fxml"));
            Parent root = (Parent) reserva.load();
            reservasController cont = reserva.<reservasController>getController();
            cont.getData(username, pista, user);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro");
            stage.setScene(new Scene(root));  
            stage.show();
        }
        if(event.getSource().equals(pista2)){
            pista = clubDBAcess.getCourt("Pista 2");
            FXMLLoader reserva = new FXMLLoader(getClass().getResource("/fxml/reservas.fxml"));
            Parent root = (Parent) reserva.load();
            reservasController cont = reserva.<reservasController>getController();
            cont.getData(username, pista, user);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro");
            stage.setScene(new Scene(root));  
            stage.show();
        }
        if(event.getSource().equals(pista3)){
            pista = clubDBAcess.getCourt("Pista 3");
            FXMLLoader reserva = new FXMLLoader(getClass().getResource("/fxml/reservas.fxml"));
            Parent root = (Parent) reserva.load();
            reservasController cont = reserva.<reservasController>getController();
            cont.getData(username, pista, user);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro");
            stage.setScene(new Scene(root));  
            stage.show();
        }
        if(event.getSource().equals(pista4)){
            pista = clubDBAcess.getCourt("pista 4");
            FXMLLoader reserva = new FXMLLoader(getClass().getResource("/fxml/reservas.fxml"));
            Parent root = (Parent) reserva.load();
            reservasController cont = reserva.<reservasController>getController();
            cont.getData(username, pista, user);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro");
            stage.setScene(new Scene(root));  
            stage.show();
        }
    }

    
    private ArrayList<Booking> ordenarReservas(ArrayList<Booking> desordenado) {
        
        
    
    
    
    
    
    
    
    }
    
    
    
    
    
    
    
    
    @FXML
    private void actualizarDisponiblidad(ActionEvent event) {
        
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();

        ArrayList<Booking> pista1 = clubDBAcess.getCourtBookings("Pista 1", LocalDate.now()); //Lista reservas Pista 1
        ArrayList<Booking> disponibilidadPista1 = new ArrayList<>(); //Lista a mostrar con huecos

        ArrayList<Booking> pista2 = clubDBAcess.getCourtBookings("Pista 2", LocalDate.now()); //Lista reservas Pista 2
        ArrayList<Booking> disponibilidadPista2 = new ArrayList<>(); //Lista a mostrar con huecos
        
        ArrayList<Booking> pista3 = clubDBAcess.getCourtBookings("Pista 3", LocalDate.now()); //Lista reservas Pista 3
        ArrayList<Booking> disponibilidadPista3 = new ArrayList<>(); //Lista a mostrar con huecos
        
        ArrayList<Booking> pista4 = clubDBAcess.getCourtBookings("Pista 4", LocalDate.now()); //Lista reservas Pista 4
        ArrayList<Booking> disponibilidadPista4 = new ArrayList<>(); //Lista a mostrar con huecos
        
        int auxHour = 21;
        int auxMin = 0;
        Member disp = new Member(null,null,null,"DISPONIBLE",null,null,null,null);
        
        for (int i =0; i <=8;i++) {
            if(i<pista1.size()){
                if(auxHour != pista1.get(i).getFromTime().getHour() || auxMin != pista1.get(i).getFromTime().getHour()) {
                    disponibilidadPista1.add(new Booking(null,null,null,false,null,disp));
                } else{ disponibilidadPista1.add(pista1.get(i));    }
            } else{disponibilidadPista1.add(new Booking(null,null,null,false,null,disp));}
            
            if(i<pista2.size()){

                if(auxHour != pista2.get(i).getFromTime().getHour() || auxMin != pista2.get(i).getFromTime().getHour()) {
                    disponibilidadPista2.add(new Booking(null,null,null,false,null,disp));
                }else{  disponibilidadPista2.add(pista2.get(i));     }
            } else{disponibilidadPista2.add(new Booking(null,null,null,false,null,disp));}
                
            if(i<pista3.size()){
                if(auxHour != pista3.get(i).getFromTime().getHour() || auxMin != pista3.get(i).getFromTime().getHour()) {
                    disponibilidadPista3.add(new Booking(null,null,null,false,null,disp));
                }else{  disponibilidadPista3.add(pista2.get(i));    }
            } else{disponibilidadPista3.add(new Booking(null,null,null,false,null,disp));}

            if(i<pista4.size()){
                if(auxHour != pista4.get(i).getFromTime().getHour() || auxMin != pista4.get(i).getFromTime().getHour()) {
                    disponibilidadPista4.add(new Booking(null,null,null,false,null,disp));
                }else{  disponibilidadPista4.add(pista4.get(i));    }
            } else{disponibilidadPista4.add(new Booking(null,null,null,false,null,disp));}
 
            if((i+1) % 2 == 0) {    auxHour -= 2;auxMin =0; }
            else if((i+1)%2==1) {   auxHour -=1 ; auxMin = 30;  } 
        }
        
        pista1ColumFXID.setCellValueFactory(cellData1 -> new SimpleStringProperty(cellData1.getValue().getMember().getLogin()));
        pista2ColumFXID.setCellValueFactory(cellData2 -> new SimpleStringProperty(cellData2.getValue().getMember().getLogin()));
        pista3ColumFXID.setCellValueFactory(cellData3 -> new SimpleStringProperty(cellData3.getValue().getMember().getLogin()));
        pista4ColumFXID.setCellValueFactory(cellData4 -> new SimpleStringProperty(cellData4.getValue().getMember().getLogin()));

        pista1Table.setItems(FXCollections.observableArrayList(disponibilidadPista1));
        pista2Table.setItems(FXCollections.observableArrayList(disponibilidadPista2));
        pista3Table.setItems(FXCollections.observableArrayList(disponibilidadPista3));
        pista4table.setItems(FXCollections.observableArrayList(disponibilidadPista4));

    }
    
}
//Clase para el listado de reservas del usuario
    class UserBookings extends ListCell<Booking> {
        
        @Override
        protected void updateItem(Booking item, boolean empty) {
            
            
            super.updateItem(item, empty);
            if(item == null || empty) setText(null);
            else setText( item.getMadeForDay()+
                        " | " + item.getFromTime() + " | pagado: " + item.getPaid() + " | " + item.getCourt().getName());
        }
        
        
    } 
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
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
<<<<<<< HEAD


=======
>>>>>>> f7d9e8d5133c90e3b905bacd9785232ac9ea4d93
import javafx.util.converter.LocalTimeStringConverter;


import model.Court;

import model.Booking;
<<<<<<< HEAD


=======
>>>>>>> f7d9e8d5133c90e3b905bacd9785232ac9ea4d93
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
<<<<<<< HEAD
    @FXML
    private Button eliminarButton;

    @FXML
    private void eliminarReserva(ActionEvent event) {
    }
=======
>>>>>>> f7d9e8d5133c90e3b905bacd9785232ac9ea4d93
    
    
    
    
    
    
    //Clase para el listado de reservas del usuario
    class UserBookings extends ListCell<Booking> {
        
        @Override
        protected void updateItem(Booking item, boolean empty) {
            
            super.updateItem(item, empty);
            if(item == null || empty) setText(null);
            else setText(item.getBookingDate() + " | " + item.getMadeForDay()+
                        " | " + item.getFromTime() + " | " + item.getPaid() + " | " + item.getCourt());
        }
    } 
    
    
    private Member member;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        
        ArrayList<Court> courts = clubDBAcess.getCourts();
        
        //LISTADO DE RESERVAS DEL USUARIO
        datosReservas = FXCollections.observableArrayList(clubDBAcess.getUserBookings(username));
        ListViewReservasFXID.setCellFactory(c -> new UserBookings());
        ListViewReservasFXID.setItems(datosReservas);

        //GESTION TABLA DISPONIBILIDAD
        
        /*
        ObservableList<Booking> pista1 = FXCollections.observableArrayList(clubDBAcess.getCourtBookings(courts.get(0).getName(), LocalDate.now()));
        
        int auxHour = 9;
        int auxMin = 0;
        Member disp = new Member(null,null,null,"Disponible",null,null,null,null);
        for (int i =0; i <8;i++) {
            
            if(auxHour != pista1.get(i).getFromTime().getHour() || auxMin != pista1.get(i).getFromTime().getHour() ) {
                pista1.set(i, new Booking(null, null, null, false, courts.get(0), disp));
            }
                
             
        }
        
         
        
        pista1ColumFXID.setCellValueFactory(cellData1 -> new SimpleStringProperty(cellData1.getValue().getMember().getLogin()));
        pista2ColumFXID.setCellValueFactory(cellData2 -> new SimpleStringProperty(cellData2.getValue().getMember().getLogin()));
        pista3ColumFXID.setCellValueFactory(cellData3 -> new SimpleStringProperty(cellData3.getValue().getMember().getLogin()));
        pista4ColumFXID.setCellValueFactory(cellData4 -> new SimpleStringProperty(cellData4.getValue().getMember().getLogin()));
        
        pista1Table.setItems(FXCollections.observableArrayList(clubDBAcess.getCourtBookings("1", LocalDate.now())));
        pista2Table.setItems(FXCollections.observableArrayList(clubDBAcess.getCourtBookings("2", LocalDate.now())));
        pista3Table.setItems(FXCollections.observableArrayList(clubDBAcess.getCourtBookings("3", LocalDate.now())));
        pista4table.setItems(FXCollections.observableArrayList(clubDBAcess.getCourtBookings("4", LocalDate.now())));
        */
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
<<<<<<< HEAD


        member = men;


        member = men;

=======
        member = men;
>>>>>>> f7d9e8d5133c90e3b905bacd9785232ac9ea4d93
        
        user = men;
        
        
<<<<<<< HEAD




=======
>>>>>>> f7d9e8d5133c90e3b905bacd9785232ac9ea4d93
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
            pista = clubDBAcess.getCourt("pista 1");
            FXMLLoader reserva = new FXMLLoader(getClass().getResource("/fxml/reservas.fxml"));
            Parent root = (Parent) reserva.load();
            reservasController cont = reserva.<reservasController>getController();
            cont.getData(username, pista, member);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro");
            stage.setScene(new Scene(root));  
            stage.show();
        }
        if(event.getSource().equals(pista2)){
            pista = clubDBAcess.getCourt("pista 2");
            FXMLLoader reserva = new FXMLLoader(getClass().getResource("/fxml/reservas.fxml"));
            Parent root = (Parent) reserva.load();
            reservasController cont = reserva.<reservasController>getController();
            cont.getData(username, pista, member);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro");
            stage.setScene(new Scene(root));  
            stage.show();
        }
        if(event.getSource().equals(pista3)){
            pista = clubDBAcess.getCourt("pista 3");
            FXMLLoader reserva = new FXMLLoader(getClass().getResource("/fxml/reservas.fxml"));
            Parent root = (Parent) reserva.load();
            reservasController cont = reserva.<reservasController>getController();
            cont.getData(username, pista, member);
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
            cont.getData(username, pista, member);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registro");
            stage.setScene(new Scene(root));  
            stage.show();
        }
    }
    
}

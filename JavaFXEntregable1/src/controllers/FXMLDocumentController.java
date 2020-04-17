/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DBAcess.ClubDBAccess;
import clases.FormattedTableCellFactory;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Booking;
import model.Court;
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
    private TableView<String> TableViewDisponibilidadFXID;
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
    private TableColumn<String, String> horaColumFXID;
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

    private Boolean registrado;
    @FXML
    private Button pagarButton;

    @FXML
    private Button actualizarButtonFXID;
    private  ClubDBAccess clubDBAcess;
    @FXML
    private AnchorPane pistasPane;
    @FXML
    private HBox PIstasBox;
    @FXML
    private AnchorPane ReservasPane;
    @FXML
    private VBox ReservasBox;
    @FXML
    private AnchorPane DispoPane;
    @FXML
    private VBox DispoBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
        
                
        mostrarReservas();
        //GESTION TABLA DISPONIBILIDAD
        ArrayList<String> horaList = new ArrayList<>();
        horaList.add("9:00");horaList.add("10:30");
        horaList.add("12:00");horaList.add("13:30");
        horaList.add("15:00");horaList.add("16:30");
        horaList.add("18:00");horaList.add("19:30");
        horaList.add("21:00");
        horaColumFXID.setCellValueFactory(cellData0 -> new SimpleStringProperty(cellData0.getValue()));
        TableViewDisponibilidadFXID.setItems(FXCollections.observableArrayList(horaList));
        actualizarDisponiblidad();
    }
    
    @FXML
    private void actualizarDisponiblidad() {
        
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        ArrayList<Booking> pista1 = clubDBAcess.getCourtBookings("Pista 1", LocalDate.now()); //Lista reservas Pista 1
        ArrayList<Booking> pista2 = clubDBAcess.getCourtBookings("Pista 2", LocalDate.now()); //Lista reservas Pista 2
        ArrayList<Booking> pista3 = clubDBAcess.getCourtBookings("Pista 3", LocalDate.now()); //Lista reservas Pista 3
        ArrayList<Booking> pista4 = clubDBAcess.getCourtBookings("Pista 4", LocalDate.now()); //Lista reservas Pista 4
        Member disp = new Member(null,null,null,"DISPONIBLE",null,null,null,null);
        ArrayList<Booking> disponibilidadPista1 = new ArrayList<>();
        ArrayList<Booking> disponibilidadPista2 = new ArrayList<>();
        ArrayList<Booking> disponibilidadPista3 = new ArrayList<>();
        ArrayList<Booking> disponibilidadPista4 = new ArrayList<>();
        for (int i=0;i<=8;i++) {
          disponibilidadPista1.add(new Booking(null,null,null,false,null,disp));
          disponibilidadPista2.add(new Booking(null,null,null,false,null,disp));
          disponibilidadPista3.add(new Booking(null,null,null,false,null,disp));
          disponibilidadPista4.add(new Booking(null,null,null,false,null,disp));
        }
        disponibilidadPista1 = ordenarReservas(disponibilidadPista1, pista1);
        disponibilidadPista2 = ordenarReservas(disponibilidadPista2, pista2);
        disponibilidadPista3 = ordenarReservas(disponibilidadPista3, pista3);
        disponibilidadPista4 = ordenarReservas(disponibilidadPista4, pista4);
        pista1ColumFXID.setCellFactory(new FormattedTableCellFactory());
        pista1ColumFXID.setCellValueFactory(cellData1 -> new SimpleStringProperty(cellData1.getValue().getMember().getLogin()));
        pista1ColumFXID.setCellFactory(new FormattedTableCellFactory());
        
        pista2ColumFXID.setCellFactory(new FormattedTableCellFactory());
        pista2ColumFXID.setCellValueFactory(cellData2 -> new SimpleStringProperty(cellData2.getValue().getMember().getLogin()));
        pista2ColumFXID.setCellFactory(new FormattedTableCellFactory());
        
        pista3ColumFXID.setCellFactory(new FormattedTableCellFactory());
        pista3ColumFXID.setCellValueFactory(cellData3 -> new SimpleStringProperty(cellData3.getValue().getMember().getLogin()));
        pista3ColumFXID.setCellFactory(new FormattedTableCellFactory());
        
        pista4ColumFXID.setCellFactory(new FormattedTableCellFactory());
        pista4ColumFXID.setCellValueFactory(cellData4 -> new SimpleStringProperty(cellData4.getValue().getMember().getLogin()));
        pista4ColumFXID.setCellFactory(new FormattedTableCellFactory());
        
        pista1Table.setItems(FXCollections.observableArrayList(disponibilidadPista1));
        pista2Table.setItems(FXCollections.observableArrayList(disponibilidadPista2));
        pista3Table.setItems(FXCollections.observableArrayList(disponibilidadPista3));
        pista4table.setItems(FXCollections.observableArrayList(disponibilidadPista4));
        
        TableViewDisponibilidadFXID.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pista1Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pista2Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pista3Table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pista4table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
    }
    
    
    @FXML
    private void mostrarReservas() {
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        //LISTADO DE RESERVAS DEL USUARIO
        ArrayList<Booking> reservasUser = clubDBAcess.getUserBookings(username);
        datosReservas = FXCollections.observableArrayList(reservasUser);
        ListViewReservasFXID.setCellFactory(c -> new UserBookings());
        ListViewReservasFXID.setItems(datosReservas);
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
            } else {System.out.println("CANCEL");}   
        }
        mostrarReservas();
    }

    private void mostrarReservas(Event event) {
        mostrarReservas();
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
        actualizarDisponiblidad();
        TabPaneFXID.getSelectionModel().select(DisponibilidadTabFXID); 
    }
    
    public void initMember(Member men,Boolean registered) {
        if(men != null){
            user = men;
            member = men;
            username = men.getLogin();
            usernameLabelFXID.setText(username);
            if(!user.getCreditCard().equals("") && !user.getSvc().equals("")){
                pagarButton.setDisable(true);
            }
        }else{usernameLabelFXID.setText("Invitado"); }
        registrado = registered;
        System.out.print(registrado);
        if(!registrado){
            usernameLabelFXID.setText("Invitado");
            TabPaneFXID.getSelectionModel().select(DisponibilidadTabFXID);
            PistasButtonFXID.setDisable(true);
            PistasButtonFXID.setVisible(false); 
            MisReservasButtonFXID.setVisible(false);
            MisReservasButtonFXID.setDisable(true);
            PistasTabFXID.setDisable(true);
            ReservasTabFXID.setDisable(true);
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

    @FXML
    private void pagarReserva(ActionEvent event) throws Exception{
        
        
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        Booking b = ListViewReservasFXID.getSelectionModel().getSelectedItem();
        if(b != null){
            if(!b.getPaid()){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/pagar.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                PagarController pagarController = fxmlLoader.<PagarController>getController();
                pagarController.obtainData(member, b);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Pago");
                stage.setScene(new Scene(root1));  
                stage.showAndWait();
                
                ArrayList<Booking> reservasUser = clubDBAcess.getUserBookings(username);
                datosReservas = FXCollections.observableArrayList(reservasUser);
                ListViewReservasFXID.setCellFactory(c -> new UserBookings());
                ListViewReservasFXID.setItems(datosReservas);
                clubDBAcess.saveDB();
            }else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Esta reserva ya esta pagada");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.equals(ButtonType.OK)){System.out.println("ok");}
                else{System.out.println("cancel");}
            }
                
            
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No hay reserva seleccionada");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.equals(ButtonType.OK)){System.out.println("ok");}
            else{System.out.println("cancel");}
        }
    }
    
    private int conversorMinutos (LocalTime t){
        int aux = 0;
        aux = t.getHour()*60 + t.getMinute();;
        return aux;
    }
    
    private ArrayList<Booking> ordenarReservas(ArrayList<Booking> ordenado, ArrayList<Booking> desordenado) {
        int aux;
        while(!desordenado.isEmpty()){
            aux = (conversorMinutos(desordenado.get(0).getFromTime())-9*60)/90;
            ordenado.set(aux, desordenado.get(0));
            desordenado.remove(0);
        }
        return ordenado;
    }

    private void actualizarDisponiblidad(ActionEvent event) {
       actualizarDisponiblidad();
    } 

    @FXML
    private void darkMode(ActionEvent event) {
        
        
        
        
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

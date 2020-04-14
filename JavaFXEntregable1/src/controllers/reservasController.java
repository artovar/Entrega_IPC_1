/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DBAcess.ClubDBAccess;
import model.Court;
import model.Member;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
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
    private ListView<Booking> listaPista;
    @FXML
    private Button reservarButton;
    private String user;
    private Court court;
    private String inicioDia = "09:00:00";
    private Member mem;
    
            
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listaPista.setCellFactory(c -> new BookingListCell());
       reservarButton.setDisable(true);
       datePicker.setValue(LocalDate.now());
       listaPista.focusedProperty().addListener(new ChangeListener<Boolean>()
				{	@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						if (listaPista.isFocused()) {
							reservarButton.setDisable(false);
							
						}
						
					}
			
				});
       
       datePicker.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                    public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0 );
                }
            };
       });
        
    }    
    public void getData (String username, Court pista, Member member){
        user = username;
        court = pista;
        mem = member;
        pistaLabel.setText(pista.getName());
    }
    @FXML
    private void filtrarReservas(ActionEvent event) {
        if(datePicker.getValue() != null){
            filtrarButton.setDisable(false);
        }
        
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        LocalDate fecha = datePicker.getValue();
        ArrayList<Booking> reservas = new ArrayList<Booking>();
        int k = 0;
        while( k  < clubDBAcess.getClubBookingSlots()){
            reservas.add(new Booking( null, datePicker.getValue(), LocalTime.parse(inicioDia).plusMinutes(k*90),false, court, null ));
            k++;
        }
        ArrayList<Booking> efectuadasDia = new ArrayList<Booking>();
        ArrayList<Booking> efectuadasTotal =clubDBAcess.getBookings();
        int i = 0;
        while( i < efectuadasTotal.size()){
            if(efectuadasTotal.get(i).getMadeForDay().equals(datePicker.getValue())){
                efectuadasDia.add(efectuadasTotal.get(i));
            }
            i++;
        }
        int j = 0;
        while(j < efectuadasDia.size()){
          int aux =  (conversorMinutos(efectuadasDia.get(j).getFromTime()) -9*60)/90;
          reservas.remove(aux);
          reservas.add(aux, efectuadasDia.get(j));
          j++;
        }
        ObservableList datos = FXCollections.observableArrayList(reservas);
        listaPista.setItems(datos);
        System.out.print(mem);
        
    }

    @FXML
    private void reservarPista(ActionEvent event) {
        ClubDBAccess clubDBAcess;
        clubDBAcess = ClubDBAccess.getSingletonClubDBAccess();
        Booking newBooking = listaPista.getSelectionModel().getSelectedItem();
        if(newBooking.getMember() != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en la reserva");
            alert.setHeaderText("Vaya parece que hemos tenido un error al efectuar tu reserva...");
            alert.setContentText("El horario que has seleccionado ya estaba reservado, por favor, selecciona otro");
           
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("OK");
            } else {
            System.out.println("CANCEL");
            }
        }else{
            
            newBooking.setBookingDate(LocalDateTime.now());
            newBooking.setMember(mem);
            if (mem.getCreditCard().equals("") || mem.getSvc().equals("")){newBooking.setPaid(Boolean.FALSE);}
            else{ newBooking.setPaid(Boolean.TRUE);}
            clubDBAcess.getBookings().add(newBooking);
            clubDBAcess.saveDB();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("reserva");
            alert.setContentText("La reserva ha sido efectuada con éxito");
             Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            } else {
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            }
        }
    }
    private int conversorMinutos (LocalTime t){
        int aux = 0;
        aux += t.getHour()*60;
        aux += t.getMinute();
        return aux;
    }
    
    
}
class BookingListCell extends ListCell<Booking>{
    @Override
    protected void updateItem(Booking item, boolean empty)
    { super.updateItem(item, empty); // Obligatoria esta llamada
    if (item==null || empty) setText(null);
    else if(item.getMember()!= null){setText(item.getCourt().getName() + " ," + item.getFromTime() + ",reservado por: " + item.getMember().getLogin());}
    else setText(item.getCourt().getName() + " ," + item.getFromTime());
    }
}

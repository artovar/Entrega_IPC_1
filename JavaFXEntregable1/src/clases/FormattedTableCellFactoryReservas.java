/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author aaren
 */
public class FormattedTableCellFactoryReservas<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    public FormattedTableCellFactoryReservas() {
    }

    @Override
    public TableCell<S, T> call(TableColumn<S, T> p) {
        TableCell<S, T> cell = new TableCell<S, T>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                // CSS Styles
                String disponibleStyle = "disponibleStyle";
                String ocupadoPorOtro = "ocupadoStyle";
                String defaultTableStyle = "horasStyle";
                String cssStyle = "";

                model.Booking inboundBean = null;
                if( getTableRow() != null ) {
                    try{
                    inboundBean = (model.Booking) getTableRow().getItem();
                    }
                    catch(Exception e) {
                    inboundBean = null;
                    }
                }

                //Remove all previously assigned CSS styles from the cell.
                getStyleClass().remove(disponibleStyle);
                getStyleClass().remove(ocupadoPorOtro);
                getStyleClass().remove(defaultTableStyle);

                super.updateItem((T) item, empty);

                //Determine how to format the cell based on the status of the container.
                if( inboundBean == null ) {
                    cssStyle = defaultTableStyle;
                } else if( "DISPONIBLE".equals(inboundBean.getMember().getLogin()) ) {
                    cssStyle = disponibleStyle;
                }
                else {
                    cssStyle = ocupadoPorOtro;
                }
                

                //Set the CSS style on the cell and set the cell's text.
                getStyleClass().add(cssStyle);
                if( item != null ) {
                    setText( item.toString()  );
                } else {
                    setText( "" );
                }
            }
        };
        return cell;
    }
}

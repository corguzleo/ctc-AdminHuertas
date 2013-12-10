/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import mx.com.ctc.aztec.control.HuertaAcciones;
import mx.com.ctc.aztec.model.Arbol;
import mx.com.ctc.aztec.model.Huerta;

/**
 *
 * @author OSCAR
 */
public class HuertaController implements Initializable    {
    private Main application;
    private HuertaAcciones ha = new HuertaAcciones();
    private ObservableList<Huerta> huertas;
    
    @FXML 
    private MenuController menuController;
    
    @FXML
    ComboBox huertasListas;
    
    @FXML
    TableView huertasTabla;
    
    public void setApp(Main application){
        this.application = application;
        menuController.setApp(application);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crearTabla();
    }
    
    private void crearTabla(){
        huertas = FXCollections.observableArrayList();
        for(Huerta h: ha.getHuertasApp()){
            huertas.add(h);
        }
        
        huertasTabla.setEditable(true);
        TableColumn idHuerta =  new TableColumn("Id");
        idHuerta.setMinWidth(100);
        idHuerta.setCellValueFactory(
                    new PropertyValueFactory<Huerta, String>("Id")
                );
        idHuerta.setVisible(false);
        
        TableColumn nameHuerta =  new TableColumn("Nombre");
        nameHuerta.setMinWidth(200);
        nameHuerta.setCellValueFactory(
                    new PropertyValueFactory<Huerta, String>("huerta")
                );
        TableColumn productorHuerta =  new TableColumn("Productor");
        productorHuerta.setMinWidth(200);
        productorHuerta.setCellValueFactory(
                    new PropertyValueFactory<Huerta, String>("productor")
                );
        TableColumn ubicacionHuerta =  new TableColumn("Ubicacion");
        ubicacionHuerta.setMinWidth(200);
        ubicacionHuerta.setCellValueFactory(
                    new PropertyValueFactory<Huerta, String>("ubicacion")
                );
        
       TableColumn col_action = new TableColumn<>("Accion");
       col_action.setSortable(false);
       col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Arbol, Boolean>,
                ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Arbol, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        col_action.setCellFactory(
                new Callback<TableColumn<Arbol, Boolean>, TableCell<Arbol, Boolean>>() {
 
            @Override
            public TableCell<Arbol, Boolean> call(TableColumn<Arbol, Boolean> p) {
               return new ButtonCell();
            }
        });
        
        huertasTabla.setItems(huertas);
        huertasTabla.getColumns().clear();
        huertasTabla.getColumns().addAll(nameHuerta,productorHuerta,ubicacionHuerta,col_action);
        
        huertasTabla.setOnMouseClicked(new EventHandler<MouseEvent>(){
         @Override
            public void handle(MouseEvent t) {
               Huerta huerta = (Huerta)huertasTabla.getSelectionModel().getSelectedItem();
               if(huerta != null){
                    Main.huertaSeleccionada = huerta;
                    application.gotoArboles();
               }
            }
            
        }); 
    }
    
    private class ButtonCell extends TableCell<Arbol, Boolean> {
        final Button cellButton = new Button("Borrar");
         
        ButtonCell(){
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    int selectdIndex = getTableRow().getIndex();
                    //System.out.println("selectdIndex: " + huertas.get(selectdIndex).getId());
                    deleteDatosHuerta(huertas.get(selectdIndex).getId());
                }
            });
        }
 
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }
     
    private void deleteDatosHuerta(String idHuerta){
        DialogResponse response = Dialogs.showConfirmDialog(application.stage, "", "Se eliminara toda la información", "Aztec");
        if(response.equals(DialogResponse.YES)){
            ha.deleteAllHuertaApp(idHuerta);
            List<Huerta> zonas = ha.getZonasApp(idHuerta);
            for(Huerta zona: zonas){
                ha.deleteAllHuertaApp(zona.getId());
            }
            
            crearTabla();
        }
        
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mx.com.ctc.aztec.utils.Util;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import mx.com.ctc.aztec.control.ArbolAcciones;
import mx.com.ctc.aztec.control.AsesoriaTecnicaAcciones;
import mx.com.ctc.aztec.control.HuertaAcciones;
import mx.com.ctc.aztec.model.Arbol;
import mx.com.ctc.aztec.model.AsesoriaTecnica;
import mx.com.ctc.aztec.model.Huerta;
import mx.com.ctc.aztec.utils.Constantes;

/**
 * FXML Controller class
 *
 * @author OSCAR
 */
public class ArbolController  implements Initializable {
   private Main application;
   private HuertaAcciones ha = new HuertaAcciones();
   private ArbolAcciones aa = new ArbolAcciones();
   private AsesoriaTecnicaAcciones atc = new AsesoriaTecnicaAcciones();
   private ObservableList<Arbol> arboles ;
   private boolean bEditable = true;
   private boolean bEditarSinGuardar = false;
   public static  Arbol arbolSelected;
   public static  boolean bNuevo;
   
   @FXML 
   private MenuController menuController;
   @FXML
   TextField huertaNombre;
   @FXML
   TextField productorHuerta;
   @FXML
   ComboBox comboZonas;
   @FXML
   Label labelCombo;
   @FXML
   Label labelMensaje;
   @FXML
   TableView arbolesTabla;
   @FXML
   Button botonGuardar;
   @FXML
   TextField txtPlaca;
   @FXML
   Button botonNuevoArbol;
   @FXML
   Button botonAsesoria;
   @FXML
   Button botonRevision;
   
   @FXML
   ComboBox cmbVariedad;
   @FXML
   ComboBox cmbFechaMes;
   @FXML
   ComboBox cmbFechaAnio;
   @FXML
   ComboBox cmbAnioProduccion;
   @FXML
   CheckBox checkCopiar;
   
   
   final static ObservableList<String> listaVariedades = FXCollections.observableArrayList("Hass", "Criollo", "Fuerte","Méndez","Jimenez","Otra variedad aguacate");
   final static ObservableList<String> listaEstatus = FXCollections.observableArrayList("Sano","Enfermo");
   final static ObservableList<String> listaTipos = FXCollections.observableArrayList("Árbol", "Hueco", "Replantado");
   final static ObservableList<String> listaMeses = FXCollections.observableArrayList("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
   final static ObservableList<String> listaAnios = FXCollections.observableArrayList("1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020");
   
   private class EditingCell extends TableCell<Arbol, String>{
       private TextField textField;
       
       public EditingCell() {
       }
       
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }
        
        @Override
        public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(null);
        }
        
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
        
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean arg1, Boolean arg2) {
                        if (!arg2) {
                            commitEdit(textField.getText());
                        }
                }
            });
        }
        
         private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
   }
   
   private class ButtonCell extends TableCell<Arbol, Boolean> {
        final Button cellButton = new Button("Guardar");
         
        ButtonCell(){
             
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
                @Override
                public void handle(ActionEvent t) {
                    guardarCambios();
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
   
   private class StringCell extends TableCell<Arbol, String> {
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? null : getString());
            setGraphic(null);
        }
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
   
   private class MyEventHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent t) {
           //if(ArbolController.arbolSelected != null && bEditable){
             application.gotoArbolEditar();
            //}else{
              // labelMensaje.setText("La huerta no tine una asesoria técnica ligada");
           //}
        }
    }
   
   public void setApp(Main application){
        this.application = application;
        menuController.setApp(application);
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       labelMensaje.setAlignment(Pos.CENTER);
       labelMensaje.setText("");
       comboZonas.setVisible(false);
       labelCombo.setVisible(false);
       huertaNombre.setText(Main.huertaSeleccionada.getHuerta());
       productorHuerta.setText(Main.huertaSeleccionada.getProductor());
       
       ObservableList<Util.HuertaCombo> lista = FXCollections.observableArrayList();
       for(Huerta h : ha.getZonasApp(Main.huertaSeleccionada.getId())){
           lista.add(new Util.HuertaCombo(h.getId(),h.getHuerta()) );
       }
       if(lista.size() > 0){
           labelCombo.setVisible(true);
           comboZonas.setVisible(true);
           comboZonas.setItems(lista);
       }
       
       comboZonas.getSelectionModel().selectedItemProperty().addListener(
               new ChangeListener<Util.HuertaCombo>() 
           {

                @Override
                public void changed(ObservableValue<? extends Util.HuertaCombo> ov, Util.HuertaCombo t, Util.HuertaCombo t1) {
                        if(t1 != null){
                            //System.out.println(t1.getId());
                            Huerta h = ha.getHuertaApp(t1.getId());
                            Main.huertaSeleccionada = h;
                            application.gotoArboles();
                        }
                }
               
           }
               
          );   
        

         arboles = FXCollections.observableArrayList();
         for(Arbol arbol: aa.getArbolesApp(Main.huertaSeleccionada.getId())){
             arboles.add(arbol);
         }
         
         if(Main.huertaSeleccionada.getTipoRegistro().equals(Constantes.RECORD_PRINCIPAL_ZONAS)){
             botonNuevoArbol.setDisable(true);
         }else{
             botonNuevoArbol.setDisable(false);
         }
         
         cmbVariedad.getItems().setAll(listaVariedades);
         cmbFechaMes.getItems().setAll(listaMeses);
         cmbFechaAnio.getItems().setAll(listaAnios);
         cmbAnioProduccion.getItems().setAll(listaAnios);
             
         configurarTabla();
       
    }    
    
     private void configurarTabla(){
          arbolesTabla.setEditable(true);
          AsesoriaTecnica  at = atc.getAsesoriaAPPxHuerta(Main.huertaSeleccionada.getId());
          if(Util.isNull(at) && !Util.isNull(Main.huertaSeleccionada.getIdHuerta())){
             at = atc.getAsesoriaAPPxHuerta((Main.huertaSeleccionada.getIdHuerta()));
          }
          if(Util.isNull(at)){
              bEditable = false;
              //arbolesTabla.setEditable(false);
          }else{
              bEditable = true;
          }
          Callback<TableColumn, TableCell> cellFactory =
          new Callback<TableColumn, TableCell>() {
              @Override
              public TableCell call(TableColumn p) {
                 return new EditingCell();
              }
        };
        
        Callback<TableColumn, TableCell> stringCellFactory =
            new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                StringCell cell = new StringCell();
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
                return cell;
            }
        };
        
       TableColumn idArbol =  new TableColumn("Id");
       idArbol.setMinWidth(100);
       idArbol.setCellValueFactory(
                    new PropertyValueFactory<Arbol, String>("id")
                );
       idArbol.setVisible(false);
       
       TableColumn placa =  new TableColumn("Placa");
       placa.setMinWidth(100);
       placa.setCellFactory(stringCellFactory);
       placa.setCellValueFactory(
                    new PropertyValueFactory<Arbol, String>("placa")
        );
       placa.setOnEditCommit(null);
       
       TableColumn tipo =  new TableColumn("Tipo");
       tipo.setMinWidth(100);
       tipo.setCellValueFactory(
                    new PropertyValueFactory<Arbol, String>("tipo")
       );
       tipo.setCellFactory(ComboBoxTableCell.<String, String>forTableColumn(listaTipos));
       tipo.setOnEditCommit(
                new EventHandler<CellEditEvent<Arbol, String>>() {
                    @Override
                    public void handle(CellEditEvent<Arbol, String> t) {
                        if(!t.getNewValue().equals(t.getOldValue())){
                            labelMensaje.setText("");
                            arbolSelected.setModificado("true");
                            bEditarSinGuardar = true;
                        }
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setTipo(t.getNewValue());
                    }
                }
         );
       
       
      
       TableColumn fechaPlantacion =  new TableColumn("Fecha de plantación");
       TableColumn mesPlantacion = new TableColumn("Mes");
       TableColumn anioPlantacion = new TableColumn("Año");  
       fechaPlantacion.getColumns().addAll(mesPlantacion,anioPlantacion);
       
       mesPlantacion.setMinWidth(120);
       mesPlantacion.setCellValueFactory(
                    new PropertyValueFactory<Arbol, String>("mesPlantacion")
       );
       mesPlantacion.setCellFactory(ComboBoxTableCell.<String, String>forTableColumn(listaMeses));
       mesPlantacion.setOnEditCommit(
                new EventHandler<CellEditEvent<Arbol, String>>() {
                    @Override
                    public void handle(CellEditEvent<Arbol, String> t) {
                        if(!t.getNewValue().equals(t.getOldValue())){
                            labelMensaje.setText("");
                            arbolSelected.setModificado("true");
                            bEditarSinGuardar = true;
                        }
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setMesPlantacion(t.getNewValue());
                    }
                } 
       );
       
       anioPlantacion.setMinWidth(120);
       anioPlantacion.setCellValueFactory(
                    new PropertyValueFactory<Arbol, String>("anioPlantacion")
       );
       anioPlantacion.setCellFactory(ComboBoxTableCell.<String, String>forTableColumn(listaAnios));
       anioPlantacion.setOnEditCommit(
                new EventHandler<CellEditEvent<Arbol, String>>() {
                    @Override
                    public void handle(CellEditEvent<Arbol, String> t) {
                        if(!t.getNewValue().equals(t.getOldValue())){
                            labelMensaje.setText("");
                            arbolSelected.setModificado("true");
                            bEditarSinGuardar = true;
                        }
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setAnioPlantacion(t.getNewValue());
                    }
                } 
       );
       
       /*fechaPlantacion.setMinWidth(150);
       fechaPlantacion.setCellValueFactory(
                    new PropertyValueFactory<Arbol, String>("fechaPlantacion")
       );
       fechaPlantacion.setCellFactory(cellFactory);
       fechaPlantacion.setOnEditCommit(
                new EventHandler<CellEditEvent<Arbol, String>>() {
                    @Override
                    public void handle(CellEditEvent<Arbol, String> t) {
                        if(!t.getNewValue().equals(t.getOldValue())){
                            labelMensaje.setText("");
                            arbolSelected.setModificado("true");
                            bEditarSinGuardar = true;
                        }
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setFechaPlantacion(t.getNewValue());
                    }
                } 
       );*/
       
       final TableColumn produccion =  new TableColumn("Producción estimada");
       produccion.setMinWidth(100);
       produccion.setCellValueFactory(
                    new PropertyValueFactory<Huerta, String>("produccionEstimada")
        );
       produccion.setCellFactory(cellFactory);
       produccion.setOnEditCommit(
                new EventHandler<CellEditEvent<Arbol, String>>() {
                    @Override
                    public void handle(CellEditEvent<Arbol, String> t) {
                        if(!t.getNewValue().equals(t.getOldValue())){
                            labelMensaje.setText("");
                            arbolSelected.setModificado("true");
                            arbolSelected.setModificadoProduccion("true");
                            bEditarSinGuardar = true;
                        }
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setProduccionEstimada(t.getNewValue());
                    }
                } 
       );
       
       TableColumn anioProduccion =  new TableColumn("Año producción");
       anioProduccion.setMinWidth(100);
       anioProduccion.setCellValueFactory(
                    new PropertyValueFactory<Arbol, String>("anioProduccion")
        );
       anioProduccion.setCellFactory(ComboBoxTableCell.<String, String>forTableColumn(listaAnios));
       anioProduccion.setOnEditCommit(
                new EventHandler<CellEditEvent<Arbol, String>>() {
                    @Override
                    public void handle(CellEditEvent<Arbol, String> t) {
                        if(!t.getNewValue().equals(t.getOldValue())){
                            labelMensaje.setText("");
                            arbolSelected.setModificado("true");
                            arbolSelected.setModificadoProduccion("true");
                            bEditarSinGuardar = true;
                        }
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setAnioProduccion(t.getNewValue());
                    }
                } 
       );
       
       TableColumn variedad =  new TableColumn("Variedad");
       variedad.setMinWidth(100);
       variedad.setCellValueFactory(
                    new PropertyValueFactory<Arbol, String>("variedad")
        );
       variedad.setCellFactory(ComboBoxTableCell.<String, String>forTableColumn(listaVariedades));
       variedad.setOnEditCommit(
                new EventHandler<CellEditEvent<Arbol, String>>() {
                    @Override
                    public void handle(CellEditEvent<Arbol, String> t) {
                        
                        if(!t.getNewValue().equals(t.getOldValue())){
                            labelMensaje.setText("");
                            arbolSelected.setModificado("true");
                            bEditarSinGuardar = true;
                            
                        }
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setVariedad(t.getNewValue());
                    }
                }
         );
 
       
       TableColumn estado =  new TableColumn("Estatus");
       estado.setMinWidth(100);
       estado.setCellValueFactory(
                    new PropertyValueFactory<Arbol, String>("estatus")
        );
       estado.setCellFactory(ComboBoxTableCell.<String, String>forTableColumn(listaEstatus));
       estado.setOnEditCommit(
                new EventHandler<CellEditEvent<Arbol, String>>() {
                   
                    @Override
                    public void handle(CellEditEvent<Arbol, String> t) {
                        if(!t.getNewValue().equals(t.getOldValue())){
                            labelMensaje.setText("");
                            arbolSelected.setModificado("true");
                            bEditarSinGuardar = true;
                        }
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).setEstatus(t.getNewValue());
                        
                    }
                    
                }
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
        
        arbolesTabla.setItems(arboles);
        arbolesTabla.getColumns().clear();
        arbolesTabla.getColumns().addAll(col_action,idArbol,placa,tipo,variedad,fechaPlantacion,estado,produccion,anioProduccion);
        
        arbolesTabla.setOnMousePressed(new EventHandler<MouseEvent>(){
         @Override
            public void handle(MouseEvent t) {
               labelMensaje.setText("");
               Arbol arbol = (Arbol)arbolesTabla.getSelectionModel().getSelectedItem();
               
               if(arbol != ArbolController.arbolSelected  && !Util.isNull(ArbolController.arbolSelected) ){
                   if(ArbolController.arbolSelected.getModificado().equals("true") && bEditarSinGuardar){
                         Dialogs.showWarningDialog(application.stage, "","Se realizarón cambios que no han sido guardados", "Aztec");
                   }
               }
               if(arbol != null){
                    ArbolController.arbolSelected = arbol;
                    bNuevo = false;
               }
               
               /*if(!bEditable){
                   labelMensaje.setText("La huerta no tine una asesoria técnica ligada");
               }*/
            }
        }); 
     }
     private void guardarCambios() {
        String respuesta = "";
        if(arbolSelected != null){
           respuesta = aa.saveArbolAPP(ArbolController.arbolSelected);
           if(respuesta.equals(Constantes.MSG_EXITO_GUARDADO)){
                labelMensaje.setText(respuesta);
                //Dialogs.showInformationDialog(application.stage, respuesta, "", "Aztec");
                Huerta huerta = Main.huertaSeleccionada;
                huerta.setModificado("true");
                ha.saveHuertaApp(huerta);
                bEditarSinGuardar = false;
           }else{
                Dialogs.showWarningDialog(application.stage, respuesta, "", "Aztec");
           }
        }
     }
     
     public void buscarPlaca(ActionEvent event) {
         if(!Util.isNull(txtPlaca.getText())){
              arboles = FXCollections.observableArrayList();
              for(Arbol arbol: aa.getArbolesXPlaca(txtPlaca.getText(),Main.huertaSeleccionada.getId(),Main.huertaSeleccionada.getIdHuerta())){
                arboles.add(arbol);
                }
                    configurarTabla();
                }
     }
     
     public void crearAsesoriaTecnica(ActionEvent event) {
         application.gotoAsesoriaTecnica();
     }
     
     public void crearArbolNuevo(ActionEvent event) {
         this.arbolSelected = null;
         this.bNuevo = true;
         application.gotoArbolEditar();
     }
     
     public void crearEditarHuerta(ActionEvent event){
        if(bEditable){
         ArbolController.arbolSelected = null;
         this.bNuevo = false;
         application.gotoHuertaEditar();
        }else{
            Dialogs.showErrorDialog(application.stage, "La huerta no tine una asesoria técnica ligada", "", "Aztec");
            //labelMensaje.setText("La huerta no tine una asesoria técnica ligada");
        }
     }
}

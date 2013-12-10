/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import mx.com.ctc.aztec.control.DignosticoAcciones;
import mx.com.ctc.aztec.control.RevisionAcciones;
import mx.com.ctc.aztec.model.Diagnostico;
import mx.com.ctc.aztec.model.Revision;
import mx.com.ctc.aztec.utils.Util;

/**
 * FXML Controller class
 *
 * @author OSCAR
 */
public class UltimosDiagnosticosController implements Initializable {

    private ObservableList<Diagnostico> diagnosticos ;
    private ObservableList<RevisionDiagnostico> revisionDiagnosticos ;
    private DignosticoAcciones da = new DignosticoAcciones();
    private RevisionAcciones ra = new RevisionAcciones();
    private Main application;
    private ArbolEditarController arbolEditarcontroller;
    public  List<RevisionDiagnostico> revisionesSeleccionados = new ArrayList();
    @FXML
    TableView diagnosticosTabla;
    @FXML
    AnchorPane panelPrincipal;
    
     public void setApp(Main application){
        this.application = application;
    }
    public void setArbolEditar(ArbolEditarController arbolEditarcontroller){
        this.arbolEditarcontroller = arbolEditarcontroller;
    }
    private class AddPersonCell extends TableCell<RevisionDiagnostico, Boolean> {
          final Button addButton = new Button("Agegar");
          final StackPane paddedButton = new StackPane();
          final DoubleProperty buttonY = new SimpleDoubleProperty();
          
          AddPersonCell(final TableView table) {
                paddedButton.setPadding(new Insets(3));
                paddedButton.getChildren().add(addButton);
                addButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override 
                    public void handle(MouseEvent mouseEvent) {
                         buttonY.set(mouseEvent.getScreenY());
                     }
                 });
                 addButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                    //showAddPersonDialog(stage, table, buttonY.get());
                    table.getSelectionModel().select(getTableRow().getIndex());
                    RevisionDiagnostico d = (RevisionDiagnostico) table.getSelectionModel().getTableView().getItems().get(getTableRow().getIndex());
                    
                    if(revisionesSeleccionados.isEmpty()){
                        /*revisionesSeleccionados.add(d);
                        Dialogs.showInformationDialog(application.stage, "Diagnostico agreagado", "", "Aztec");*/
                        DialogResponse response = Dialogs.showConfirmDialog(application.stage,"¿Esta seguro de continuar?", "Se agregará la revisón al árbol", "Aztec",DialogOptions.YES_NO);
                        if(response.equals(DialogResponse.YES)){
                            revisionesSeleccionados.add(d);
                        }
                    }else{
                        Dialogs.showWarningDialog(application.stage, "Ya se ha asignado un diagnóstico", "", "Aztec");
                    }
                    
                    
                   // System.out.print(d.getId() + "," + d.getName());
                   // System.out.println(table.getSelectionModel().select(getTableRow().getIndex());
                    }
                 });
           }
          
           @Override protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    setGraphic(paddedButton);
                }
}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           createTable();

    }   
    public void createTable(){
       diagnosticos = FXCollections.observableArrayList();
       revisionDiagnosticos = FXCollections.observableArrayList();
       Revision r;

       for(Diagnostico d: da.getLastDiagnoticos(Main.huertaSeleccionada.getId())){
           diagnosticos.add(d);
           if(d.getIdRevision().length() <= 5){
                r = ra.getRevision(d.getIdRevision(),"");
           }else{
                 r = ra.getRevision("",d.getIdRevision());
           }
           //Revision r = ra.getRevision(d.getIdRevision());
           revisionDiagnosticos.add(new RevisionDiagnostico(r,d));
       }
       diagnosticosTabla.setEditable(true);
       TableColumn idDiagnostico =  new TableColumn("Id");
       idDiagnostico.setMinWidth(100);
       idDiagnostico.setCellValueFactory(
                    new PropertyValueFactory<RevisionDiagnostico, String>("id")
                );
       TableColumn sintomas =  new TableColumn("Sintomas");
       sintomas.setMinWidth(100);
       sintomas.setCellValueFactory(
                    new PropertyValueFactory<RevisionDiagnostico, String>("revisiones")
                );
       
       TableColumn diagnostico =  new TableColumn("Diagnóstico");
       diagnostico.setMinWidth(100);
       diagnostico.setCellValueFactory(
                    new PropertyValueFactory<RevisionDiagnostico, String>("name")
        );
       
       TableColumn sitio =  new TableColumn("Sitio afección");
       sitio.setMinWidth(100);
       sitio.setCellValueFactory(
                    new PropertyValueFactory<RevisionDiagnostico, String>("sitioAfeccion")
       );
       
       TableColumn grado =  new TableColumn("Grado infección");
       grado.setMinWidth(100);
       grado.setCellValueFactory(
                    new PropertyValueFactory<RevisionDiagnostico, String>("gradoAfeccion")
       );
       
       TableColumn actionCol = new TableColumn("Accion");
       actionCol.setMinWidth(100);
       actionCol.setSortable(false);
       
       actionCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RevisionDiagnostico, Boolean>, ObservableValue<Boolean>>() {
           @Override 
           public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<RevisionDiagnostico, Boolean> features) {
                    return new SimpleBooleanProperty(features.getValue() != null);
           }
        });
       
        actionCol.setCellFactory(new Callback<TableColumn<RevisionDiagnostico, Boolean>, TableCell<RevisionDiagnostico, Boolean>>() {
        @Override public TableCell<RevisionDiagnostico, Boolean> call(TableColumn<RevisionDiagnostico, Boolean> personBooleanTableColumn) {
                return new AddPersonCell(diagnosticosTabla);
            }
         });
        
 
       diagnosticosTabla.setItems(revisionDiagnosticos);
       diagnosticosTabla.getColumns().clear();
       diagnosticosTabla.getColumns().addAll(idDiagnostico,sintomas,diagnostico,sitio,grado,actionCol);
    }
    public class RevisionDiagnostico{
        Diagnostico diagnostico;
        Revision revision;
        String id;
        String name;
        String revisiones;
        String sitioAfeccion;
        String gradoAfeccion;
        
        public RevisionDiagnostico(Revision revision,Diagnostico diagnostico){
            this.diagnostico = diagnostico;
            this.revision =  revision;
        }

        public String getId() {
            return diagnostico.getId();
        }

        public void setId(String id) {
            this.id = id;
        }
        
        
        public String getName() {
            return diagnostico.getDiagnostico();
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSitioAfeccion() {
            return diagnostico.getSitioAfeccion();
        }

        public void setSitioAfectacion(String sitioAfectacion) {
            this.sitioAfeccion = sitioAfectacion;
        }

        public String getGradoAfeccion() {
            return diagnostico.getGradoAfeccion();
        }

        public void setGradoAfeccion(String gradoAfeccion) {
            this.gradoAfeccion = gradoAfeccion;
        }
        
        
        
        public String getRevisiones(){
            String cadenaRevisiones = "";
            if(revision.getAmarillo().equals("true")){
                    cadenaRevisiones = cadenaRevisiones + "Amarillo,";
            }
            if(revision.getAgallasHojas().equals("true")){
                    cadenaRevisiones = cadenaRevisiones + "Agallas en hojas,";
            }
            if(revision.getBrotesDanados().equals("true")){
                    cadenaRevisiones = cadenaRevisiones + "Brotes dañados,";
            }
            if(revision.getCaidaFrutos().equals("true")){
                    cadenaRevisiones = cadenaRevisiones + "Caida de frutos,";
            }
            if(revision.getCaidaHojas().equals("true")){
                    cadenaRevisiones = cadenaRevisiones + "Caida de hojas,";
            }
            if(revision.getCaidaRamas().equals("true")){
                    cadenaRevisiones = cadenaRevisiones + "Caida de ramas,";
            }if(revision.getCortezaDanada().equals("true")){
                    cadenaRevisiones = cadenaRevisiones + "Corteza dañada,";
            }if(revision.getDefoliacion().equals("true")){
                    cadenaRevisiones = cadenaRevisiones + "Defolación,";
            }if(revision.getFloracionRetrasada().equals("true")){
                    cadenaRevisiones = cadenaRevisiones + "Floración retrasada,";
            }if(revision.getFrutoMalformacion().equals("true")){
                cadenaRevisiones = cadenaRevisiones + "Fruto con malformación,";
            }if(revision.getGranizada().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Granizada,";
            }if(revision.getHelada().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Helada,";
            }if(revision.getHojaChica().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Hoja chica,";
            }if(revision.getHojasCloroticas().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Hojas cloríticas,";
            }if(revision.getHojasManchadas().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Hojas manchadas,";
            }if(revision.getHojasSecas().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Hojas secas,";
            }if(revision.getHojasVerdeAmarillento().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Hojas verde amarillento,";
            }if(revision.getHojasVerdesOscuro().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Hojas verde oscuro,";
            }if(revision.getHueco().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Hojas hueco,";
            }if(revision.getHuesoBarrenado().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Hueso barrenado,";
            }if(revision.getLesionesSol().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Lesiones por sol,";
            }if(revision.getManchasCorchosas().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Manchas corchosas,";
            }if(revision.getMarcasHojasFrutosTallos().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Manchas en hojas/frutos/tallos,";
            }if(revision.getNecrosis().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Necrosis,";
            }if(revision.getOtro().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Otro,";
            }if(revision.getOxidacionHojas().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Oxidación en hojas,";
            }if(revision.getPigmeo().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Pigmeo,";
            }if(revision.getPocaHoja().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Poca hoja,";
            }if(revision.getPocoAmarre().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Poco amarre,";
            }if(revision.getPudricionFruto().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Pudrición del fruto,";
            }if(revision.getPudricionRadicular().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Pudrición radicular,";
            }if(revision.getSeco().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Seco,";
            }if(revision.getTumores().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Tumores,";
            }if(revision.getIncendio().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Incendio,";
            }if(revision.getFaltaAgua().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Falta de agua,";
            }if(revision.getCenizaVolcanica().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Ceniza volcánica,";
            }if(revision.getPlaga().equals("true")){
                 cadenaRevisiones = cadenaRevisiones + "Plaga,";
            }
            if(!Util.isNull(cadenaRevisiones)){
                cadenaRevisiones = cadenaRevisiones.substring(0,cadenaRevisiones.length() - 1);
            }
            return cadenaRevisiones;
        }
        
        public void setRevisiones(String revisiones){
            this.revisiones = revisiones;
        }

        public Diagnostico getDiagnostico() {
            return diagnostico;
        }

        public void setDiagnostico(Diagnostico diagnostico) {
            this.diagnostico = diagnostico;
        }

        public Revision getRevision() {
            return revision;
        }

        public void setRevision(Revision revision) {
            this.revision = revision;
        }
        
        
    }
}

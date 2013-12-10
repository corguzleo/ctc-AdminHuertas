/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import mx.com.ctc.aztec.dao.DiagnosticoCatalogoDAO;
import mx.com.ctc.aztec.model.AgenteCausal;
import mx.com.ctc.aztec.model.DiagnosticoCatalogo;
import mx.com.ctc.aztec.utils.Util;

/**
 * FXML Controller class
 *
 * @author OSCAR
 */
public class DiagnosticoController implements Initializable {
   
    @FXML
    TextField txtTipo;
    @FXML
    ComboBox cmbSubtipo;
    @FXML
    ListView listSitio;
    @FXML
    ComboBox cmbGrado;
    @FXML
    ComboBox cmbDiagostico;
    @FXML
    ComboBox cmbAgente;
    @FXML
    CheckBox chkAfectacion;
    @FXML
    CheckBox chkDanio;
    @FXML
    CheckBox chkFollaje;
    @FXML
    CheckBox chkRaiz;
    @FXML
    CheckBox chkTronco;
    @FXML
    CheckBox chkFlor;
    
             
    private Main application;
    public static String tipoDiagnostico = "";
    DiagnosticoCatalogoDAO dao =  new DiagnosticoCatalogoDAO();
    final static ObservableList<String> listaSubtipoArbol = FXCollections.observableArrayList("Enfermedad","Deficiencia");
    final static ObservableList<String> listaSubtipoHuerta = FXCollections.observableArrayList("Plaga","Daño");
    final static ObservableList<String> listaGrado = FXCollections.observableArrayList("Leve","Medio","Alto");
    final static ObservableList<String> listaSitio = FXCollections.observableArrayList("Afectación al fruto","Daño al árbol","Follaje","Raíz","Tronco","Flor");
    /**
     * Initializes the controller class.
     */
    public void setApp(Main application){
        this.application = application;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbDiagostico.getItems().clear();
        cmbAgente.getItems().clear();
        cmbGrado.getItems().setAll(listaGrado);
   
        //listSitio.getItems().setAll(listaSitio);
        //listSitio.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        if(!Util.isNull(ArbolController.arbolSelected) || ArbolController.bNuevo){
            txtTipo.setText("Árbol");
            cmbSubtipo.getItems().setAll(listaSubtipoArbol);
        }else{   
            cmbSubtipo.getItems().setAll(listaSubtipoHuerta);
            txtTipo.setText("Huerta");
            cmbAgente.setEditable(false);
        }  
        
        cmbSubtipo.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
               if(!Util.isNull(t1)){
                   ObservableList<DiagnosticoCatalogo> listaDiagnosticos = FXCollections.observableArrayList(dao.selectDiagnostico(t1));
                   cmbDiagostico.getItems().setAll(listaDiagnosticos);
                   
               }
            }
        
        });
        
        cmbDiagostico.valueProperty().addListener(new ChangeListener<DiagnosticoCatalogo>(){
            @Override
            public void changed(ObservableValue<? extends DiagnosticoCatalogo> ov, DiagnosticoCatalogo t, DiagnosticoCatalogo t1) {
                //System.out.println(t + "," + t1);
                 if(!Util.isNull(t1)){
                     ObservableList<AgenteCausal> listaDiagnosticos = FXCollections.observableArrayList(dao.selectAgenteCausal(t1.getDiagnostico()));
                     cmbAgente.getItems().setAll(listaDiagnosticos);
                 }
            }
        });
    } 
    
}

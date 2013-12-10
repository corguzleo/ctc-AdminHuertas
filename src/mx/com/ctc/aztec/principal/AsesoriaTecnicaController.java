/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mx.com.ctc.aztec.control.AsesoriaTecnicaAcciones;
import mx.com.ctc.aztec.control.HuertaAcciones;
import mx.com.ctc.aztec.control.RevisionAcciones;
import mx.com.ctc.aztec.model.AsesoriaTecnica;
import mx.com.ctc.aztec.model.Huerta;
import mx.com.ctc.aztec.model.Revision;
import mx.com.ctc.aztec.service.AsesoriaTecnicaServicio;
import mx.com.ctc.aztec.utils.Constantes;
import mx.com.ctc.aztec.utils.Util;

/**
 * FXML Controller class
 *
 * @author OSCAR
 */

public class AsesoriaTecnicaController implements Initializable {
    private Main application;
    private AsesoriaTecnicaServicio ats =  new AsesoriaTecnicaServicio();
    private AsesoriaTecnicaAcciones ata = new AsesoriaTecnicaAcciones();
    private RevisionAcciones ra = new RevisionAcciones();
    private  AsesoriaTecnica asesoria;
    private HuertaAcciones ha = new HuertaAcciones();
    final static ObservableList<String> listaSINO = FXCollections.observableArrayList("Si", "No");
    
    @FXML 
    private MenuController menuController;
    @FXML
    TextField txtAsesoriaTecnica;
    @FXML
    TextField txtHuerta;
    @FXML
    TextField txtFolio;
    @FXML
    TextField txtFechaAsesoria;
    @FXML
    ComboBox cmbInsectosBeneficos;
    @FXML
    ComboBox cmbInstalaciones;
    @FXML
    ComboBox cmbMaleza;
    @FXML
    ComboBox cmbBasura;
    @FXML
    TextArea txtComentariosLimpieza;
    @FXML
    TextArea txtObservacionesAsesor;
    @FXML
    Label lbMensaje;
    
     
    public void setApp(Main application){
        this.application = application;
        menuController.setApp(application);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //lbMensaje.setText("");
        //lbMensaje.setAlignment(Pos.CENTER);
        cmbInsectosBeneficos.getItems().setAll(listaSINO);
        cmbInstalaciones.getItems().setAll(listaSINO);
        cmbMaleza.getItems().setAll(listaSINO);
        cmbBasura.getItems().setAll(listaSINO);
        txtHuerta.setText(Main.huertaSeleccionada.getHuerta());
        
        asesoria = ata.getAsesoriaAPPxHuerta(Main.huertaSeleccionada.getId());
        cmbInsectosBeneficos.getSelectionModel().select("No");
        cmbInstalaciones.getSelectionModel().select("No");
        cmbMaleza.getSelectionModel().select("No");
        cmbBasura.getSelectionModel().select("No");
        txtFechaAsesoria.setText(Util.toDaySimple());
        if(asesoria != null ){
            txtAsesoriaTecnica.setText(asesoria.getId() != null ? asesoria.getId() :  asesoria.getIdTemporal());
            txtFolio.setText(asesoria.getFolio());
            txtFechaAsesoria.setText(asesoria.getFechaAsesoria());
            cmbInsectosBeneficos.getSelectionModel().select(asesoria.getInsectosBeneficos());
            cmbInstalaciones.getSelectionModel().select(asesoria.getInstalaciones());
            cmbMaleza.getSelectionModel().select(asesoria.getMaleza());
            cmbBasura.getSelectionModel().select(asesoria.getBasura());
            txtComentariosLimpieza.setText(asesoria.getComentariosLimpieza());
            txtObservacionesAsesor.setText(asesoria.getObservacionesAsesor());
        }
    }
    
    public void regresar(ActionEvent event) {
         application.gotoArboles();
    }
    
    public void guardar(ActionEvent event) {
          String mensaje;
          String insectos = !Util.isNull(cmbInsectosBeneficos.getSelectionModel().getSelectedItem()) ?  cmbInsectosBeneficos.getSelectionModel().getSelectedItem().toString() : "";
          String instalaciones = !Util.isNull(cmbInstalaciones.getSelectionModel().getSelectedItem()) ?  cmbInstalaciones.getSelectionModel().getSelectedItem().toString() : "";
          String maleza = !Util.isNull(cmbMaleza.getSelectionModel().getSelectedItem()) ?  cmbMaleza.getSelectionModel().getSelectedItem().toString() : "";
          String basura = !Util.isNull(cmbBasura.getSelectionModel().getSelectedItem()) ?  cmbBasura.getSelectionModel().getSelectedItem().toString() : "";
          
          if(Util.isNull(asesoria)){
            mensaje =  ats.insert("", "", Main.huertaSeleccionada.getId(), Main.huertaSeleccionada.getHuerta(),"", txtFechaAsesoria.getText(),txtFolio.getText(), insectos, instalaciones, maleza, basura, txtComentariosLimpieza.getText(), txtObservacionesAsesor.getText());
            AsesoriaTecnica ultima = ata.getAsesoriaAPPxHuerta(Main.huertaSeleccionada.getId());
            List<Revision> revisiones = ra.getRevisonesAppHuerta(Main.huertaSeleccionada.getId());
            for(Revision revision:revisiones){
                revision.setIdAsesoriaTecnica(!Util.isNull(ultima.getId()) ? ultima.getId() : ultima.getIdTemporal());
                ra.saveRevisonAPP(revision);
            }
          }else{
            asesoria.setFolio(txtFolio.getText());
            asesoria.setInsectosBeneficos(insectos);
            asesoria.setMaleza(maleza);
            asesoria.setBasura(basura);
            asesoria.setComentariosLimpieza(txtComentariosLimpieza.getText());
            asesoria.setObservacionesAsesor(txtObservacionesAsesor.getText());
            asesoria.setFechaAsesoria(txtFechaAsesoria.getText());
            
            mensaje = ats.updateAsesoria(asesoria);
          }
          if(mensaje.equals(Constantes.MSG_EXITO_GUARDADO)){
            Huerta huerta = Main.huertaSeleccionada;
            huerta.setModificado("true");
            ha.saveHuertaApp(huerta);
            Dialogs.showInformationDialog(application.stage, mensaje, "", "Aztec");
           }else{
              Dialogs.showErrorDialog(application.stage, mensaje, "", "Aztec");
          }
          
    }
}

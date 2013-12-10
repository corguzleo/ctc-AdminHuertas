/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import com.sforce.soap.enterprise.sobject.Arbol__c;
import com.sforce.soap.enterprise.sobject.Huerta__c;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import mx.com.ctc.aztec.control.ArbolAcciones;
import mx.com.ctc.aztec.control.AsesoriaTecnicaAcciones;
import mx.com.ctc.aztec.control.HuertaAcciones;
import mx.com.ctc.aztec.control.RevisionAcciones;
import mx.com.ctc.aztec.control.DignosticoAcciones;
import mx.com.ctc.aztec.model.AsesoriaTecnica;
import mx.com.ctc.aztec.model.Huerta;
import mx.com.ctc.aztec.model.Revision;
import mx.com.ctc.aztec.utils.Constantes;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author OSCAR
 */
public class HuertaDescargaController implements Initializable {
    private Main application;
    private HuertaAcciones ha = new HuertaAcciones();
    private ArbolAcciones aa = new ArbolAcciones();
    private RevisionAcciones ra = new RevisionAcciones();
    private AsesoriaTecnicaAcciones ata = new  AsesoriaTecnicaAcciones();
    private DignosticoAcciones da = new DignosticoAcciones();
    private ObservableList<HuertaWrapper> huertasWrapper ;
    private List<Huerta__c> huertasDescarga = new ArrayList<>();
    private List<Huerta__c> zonasDescarga = new ArrayList<>();
    private Huerta huertaSelected;
    private Task copyWorker;
    static Logger loggerHDC =  LoggerFactory.getLogger(HuertaDescargaController.class);
    @FXML 
    private MenuController menuController;
    @FXML
    ListView huertaLista;
    @FXML
    Label  labelMensaje;
    @FXML
    Button btnAccion;
    @FXML
    ProgressBar bar;
  
    public void setApp(Main application){
        this.application = application;
        menuController.setApp(application);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        huertasWrapper = FXCollections.observableArrayList();
        labelMensaje.setText("");
        labelMensaje.setAlignment(Pos.CENTER);
         for(Huerta__c huerta: ha.getHuertasSF()){
             huertasWrapper.add(new HuertaWrapper(false, huerta));
         }

        //huertaLista.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        huertaLista.setItems(huertasWrapper);
        
        huertaLista.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<HuertaWrapper>() {

            @Override
            public void changed(ObservableValue<? extends HuertaWrapper> ov, HuertaWrapper t, HuertaWrapper t1) {
                huertaSelected = ha.getHuertaApp(t1.getHuerta().getId());
                labelMensaje.setText("");
                btnAccion.setText("Descargar");
                if(!Util.isNull(huertaSelected)){
                    if(!Util.isNull(huertaSelected.getModificado())){
                         if(huertaSelected.getModificado().equals("true")){
                            Dialogs.showWarningDialog(application.stage, "","La huerta tiene cambios previos.\nSe eliminaran los datos cargados." , "Aztec");
                            //labelMensaje.setText("La huerta tiene cambios.\nSe eliminaran los datos cargados.");
                            btnAccion.setText("Aceptar");
                         }
                    }else{
                        labelMensaje.setText("");
                        btnAccion.setText("Descargar");
                    }
                }
            }
        });
        bar.setVisible(false);
        btnAccion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Dialogs.showInformationDialog(application.stage, "","Presione 'Ok' para comenzar" , "Aztec");
                bar.setVisible(true);
                btnAccion.setDisable(true);
                copyWorker = createWorker();
                bar.progressProperty().unbind();
                bar.progressProperty().bind(copyWorker.progressProperty());
                copyWorker.messageProperty().addListener(new ChangeListener<String>() {
                            @Override
                            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                //System.out.println(newValue);
                                descargarHuertas();
                            }
                 });
                new Thread(copyWorker).start();
            }
        });
        
    } 
    
    public void descargarHuertas(){
      
        loggerHDC.debug("Comenzando carga de huerta");
        String sRespuesta = Constantes.MSG_EXITO_GUARDADO;
        //labelMensaje.setText("Comenzando carga");
        //btnAccion.setDisable(true);
        for(Object i : huertaLista.getSelectionModel().getSelectedIndices()){
             Integer indice = (Integer)i;
             huertasDescarga.add(huertasWrapper.get(indice).getHuerta());
             btnAccion.setDisable(false);
         }

         for(Huerta__c h:huertasDescarga){
             try{
                borrarDatos(h.getId());
                ha.insertHuertaAPP(h);
             }catch(Exception e){
                 sRespuesta = "Error en la descarga. Consulte a su administrador";
                 //labelMensaje.setText(sRespuesta);
                 Dialogs.showErrorDialog(application.stage, sRespuesta);
                 loggerHDC.error("Error en la carga de la huerta principal:" +   e.getMessage() + "," + e.getLocalizedMessage());
                 btnAccion.setDisable(false);
             } 
         }
         if(sRespuesta.equals(Constantes.MSG_EXITO_GUARDADO)){
            for(Huerta__c h:huertasDescarga){
               try{
                  for(Arbol__c arbol: aa.getArbolesSF(h.getId())){
                      aa.insertArbolAPP(arbol);
                  }
                }catch(Exception e){
                    sRespuesta = "Error en la descarga. Consulte a su administrador";
                    //labelMensaje.setText(sRespuesta);
                     Dialogs.showErrorDialog(application.stage, sRespuesta);
                    loggerHDC.error("Error en la carga de arboles de la huerta principal:" +  e.getMessage());
                    btnAccion.setDisable(false);
                }   
            }
         }
         if(sRespuesta.equals(Constantes.MSG_EXITO_GUARDADO)){
            for(Huerta__c h:huertasDescarga){
               for(Huerta__c sh:ha.getZonasSF(h.getId())){
                    zonasDescarga.add(sh);
                    try{
                        borrarDatos(sh.getId());
                        ha.insertHuertaAPP(sh);
                   }catch(Exception e){
                       sRespuesta = "Error en la descarga. Consulte a su administrador";
                       //labelMensaje.setText(sRespuesta);
                        Dialogs.showErrorDialog(application.stage, sRespuesta);
                       loggerHDC.error("Error insertar subhuertas:" +   e.getMessage() + "," + e.getLocalizedMessage());
                       btnAccion.setDisable(false);
                   }
               }
            }
         }
         
        if(sRespuesta.equals(Constantes.MSG_EXITO_GUARDADO)){
            for(Huerta__c h:zonasDescarga ){
                try{
                  for(Arbol__c arbol: aa.getArbolesSF(h.getId())){
                      aa.insertArbolAPP(arbol);
                  }
                }catch(Exception e){
                    huertasDescarga.clear();
                    sRespuesta = "Error en la descarga. Consulte a su administrador";
                    //labelMensaje.setText(sRespuesta);
                    Dialogs.showErrorDialog(application.stage, sRespuesta);
                    loggerHDC.error("Error en la carga de arboles de la subhuertas:" +  e.getMessage() + "," + e.getLocalizedMessage());
                    btnAccion.setDisable(false);
                }   
            }
        }
         
         if(sRespuesta.equals(Constantes.MSG_EXITO_GUARDADO)){
            Dialogs.showInformationDialog(application.stage, "","Carga finalizada", "Aztec");
            loggerHDC.debug("Carga finalizada");
            huertasDescarga.clear();
            zonasDescarga.clear();
            btnAccion.setDisable(false);
            bar.setVisible(false);
         }
         
    }
   
    public void borrarDatos(String idHuerta){
        if(!Util.isNull(idHuerta)){
            ha.deleteHuertaApp(idHuerta);
            AsesoriaTecnica asesoria = ata.getAsesoriaAPPxHuerta(idHuerta);
            if(asesoria != null){
                ata.deleteAsesoriaAPP(asesoria);
            }
            Revision revision = ra.getRevisionAPPxHuerta(idHuerta);
            if(!Util.isNull(revision)){
                ra.deleteRevisionApp(idHuerta);
            }
            da.deleteDiagnosticosXHuerta(idHuerta);
            aa.deleteArbolesXHuerta(idHuerta);
        }
    }
    
     public Task createWorker() {
        return new Task() {
          @Override
          protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    updateMessage("1000 milliseconds");
                    updateProgress(i + 1, 10);
                }
                
                return true;
            }
        };
    }
   
    public static class HuertaWrapper{
 
        private Huerta__c huerta;
        private Boolean selecionada;
        
        @Override
        public String toString(){
            String name;
            if(huerta.getHuertaParent__c() != null){
                name = huerta.getName() + "(Subhuerta)";
            }else{
                 name = huerta.getName() + "(Principal)";
            }
            return name;
        }

        public HuertaWrapper(Boolean selecionada, Huerta__c huerta) {
            this.selecionada = selecionada;
            this.huerta = huerta;
        }
        
        public Huerta__c getHuerta() {
            return huerta;
        }

        public void setHuerta(Huerta__c huerta) {
            this.huerta = huerta;
        }

        public Boolean getSelecionada() {
            return selecionada;
        }

        public void setSelecionada(Boolean selecionada) {
            this.selecionada = selecionada;
        }
    }
}

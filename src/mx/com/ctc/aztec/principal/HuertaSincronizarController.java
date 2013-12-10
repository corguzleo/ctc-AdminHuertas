/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

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
import mx.com.ctc.aztec.control.DignosticoAcciones;
import mx.com.ctc.aztec.control.HuertaAcciones;
import mx.com.ctc.aztec.control.RevisionAcciones;
import mx.com.ctc.aztec.model.Arbol;
import mx.com.ctc.aztec.model.AsesoriaTecnica;
import mx.com.ctc.aztec.model.Diagnostico;
import mx.com.ctc.aztec.model.Huerta;
import mx.com.ctc.aztec.model.Revision;
import mx.com.ctc.aztec.utils.Constantes;
import mx.com.ctc.aztec.utils.Respuesta;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class HuertaSincronizarController implements Initializable{
    
    private Main application;
    private HuertaAcciones ha = new HuertaAcciones();
    private ArbolAcciones aa = new ArbolAcciones();
    private RevisionAcciones ra = new RevisionAcciones();
    private AsesoriaTecnicaAcciones ata = new  AsesoriaTecnicaAcciones();
    private DignosticoAcciones da = new DignosticoAcciones();
    private ObservableList<HuertaWrapper> huertasWrapper ;
    private List<Huerta> huertasSincronizar = new ArrayList<>();
    private List<Huerta__c> zonasDescarga = new ArrayList<>();
    private Huerta huertaSelected;
    private Task copyWorker;
    private List<HuertaWrapper> wrappersSelected = new ArrayList();
    static Logger loggerHDC =  LoggerFactory.getLogger(HuertaAcciones.class);
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
       huertaLista.setEditable(true);
       huertasWrapper = FXCollections.observableArrayList();
       for(Huerta huerta: ha.getHuertasSincronizar()){
             huertasWrapper.add(new HuertaWrapper(false,huerta));
       }
        //huertaLista.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        if(huertasWrapper != null && !huertasWrapper.isEmpty()){
            huertaLista.setItems(huertasWrapper);
        }
        
       huertaLista.getSelectionModel().selectedItemProperty().addListener(
           new ChangeListener<HuertaWrapper>() {
           @Override
           public void changed(ObservableValue<? extends HuertaWrapper> ov, HuertaWrapper t, HuertaWrapper t1) {
               if(!Util.isNull(t1)){
                huertaSelected = ha.getHuertaApp(t1.getHuerta().getId());
               }
           } 
         });
        bar.setVisible(false);
        btnAccion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                
                for(Object i : huertaLista.getSelectionModel().getSelectedIndices()){
                    Integer indice = (Integer)i;
                    huertasSincronizar.add(huertasWrapper.get(indice).getHuerta());
                    wrappersSelected.add(huertasWrapper.get(indice));
                     //Agregamos la subhuerta , si es una huerta principal
                    List<Huerta> subhuertas = ha.getZonasApp(huertasWrapper.get(indice).getHuerta().getId());
                    if(!subhuertas.isEmpty()){
                        huertasSincronizar.addAll(subhuertas);
                       for(HuertaWrapper hw:huertasWrapper){
                           for(Huerta subhuerta:subhuertas){
                               if(subhuerta.getHuerta().equals(hw.getHuerta().getHuerta())){
                                   wrappersSelected.add(hw);
                               }
                           }
                       }
                    }
                }
                if(huertasSincronizar.isEmpty()){
                    bar.setVisible(false);
                    Dialogs.showWarningDialog(application.stage, "","Seleccione una huerta" , "Aztec");
                }else{
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
                                    sincronizarHuertas();
                                }
                     });
                    new Thread(copyWorker).start();
                }
            }
        });
    }
 
    
    public void sincronizarHuertas(){
         Respuesta respuesta = new Respuesta();
         respuesta.setMensaje("");
         

         for(Huerta huerta: huertasSincronizar){
             loggerHDC.debug("Inicio de sincronización: " + huerta.getHuerta() );
             AsesoriaTecnica at = ata.getAsesoriaAPPxHuerta(huerta.getId());
            
             if(Util.isNull(at) && !Util.isNull(huerta.getIdHuerta())){
                at = ata.getAsesoriaAPPxHuerta((huerta.getIdHuerta()));
             }
             if(!Util.isNull(at)){
                 respuesta = ata.insertAsesoriaSF(at);
                 at.setId(respuesta.getId());
                 if(respuesta.getMensaje().equals(Constantes.MSG_EXITO_GUARDADO)){
                    List<Arbol> arboles = aa.getArbolesModificadosApp(huerta.getId());
                    respuesta = aa.updateArbolesSF(arboles,at);
                    if(respuesta.getMensaje().contains(Constantes.MSG_EXITO_GUARDADO)){
                        List<Revision> revisiones = ra.getRevisionesArboles(respuesta.getIds());
                       
                        if(ra.getRevisionHuerta(huerta.getId()) != null){  
                                revisiones.add(ra.getRevisionHuerta(huerta.getId()));
                        }
                        if(!revisiones.isEmpty()){
                            respuesta = ra.insertRevisionSF(revisiones,at);
                        }
                        if(respuesta.getMensaje().contains(Constantes.MSG_EXITO_GUARDADO)){
                            if(!respuesta.getIds().isEmpty()){
                                List<Diagnostico> diagnosticos = da.getDiagnosticosRevisiones(respuesta.getIds());
                                respuesta = da.insertDiagnosticosSF(diagnosticos);
                            }
                             if(respuesta.getMensaje().contains(Constantes.MSG_EXITO_GUARDADO)){
                                    huerta.setModificado("false");
                                    ha.saveHuertaApp(huerta);
                             }
                        }
                    }
                 }
             }else{
                  List<Arbol> arboles = aa.getArbolesModificadosApp(huerta.getId());
                  respuesta = aa.updateArbolesSF(arboles,at);
                  if(respuesta.getMensaje().contains(Constantes.MSG_EXITO_GUARDADO)){
                                    huerta.setModificado("false");
                                    ha.saveHuertaApp(huerta);
                  }
             }
            
             loggerHDC.debug("Fin de sincronización: " + huerta.getHuerta() );
             
         }
         btnAccion.setDisable(false);
         if(respuesta.getMensaje().contains(Constantes.MSG_EXITO_GUARDADO)){
             huertaLista.getItems().removeAll(wrappersSelected);
             btnAccion.setDisable(false);
             bar.setVisible(false);
             Dialogs.showInformationDialog(application.stage, "",respuesta.getMensaje() , "Aztec");
         }else if(!respuesta.getMensaje().equals("")){
              Dialogs.showErrorDialog(application.stage, "",respuesta.getMensaje() , "Aztec");
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
        private Huerta huerta;
        private Boolean selecionada;
        
        @Override
        public String toString(){
           String name;
           if(huerta.getIdHuerta() != null){
                name = huerta.getHuerta()+ "(Subhuerta)";
            }else{
                 name = huerta.getHuerta() + "(Principal)";
            }
            return name;
        }
        
        public HuertaWrapper(Boolean selecionada, Huerta huerta) {
            this.selecionada = selecionada;
            this.huerta = huerta;
        }
        
        public Huerta getHuerta() {
            return huerta;
        }

        public void setHuerta(Huerta huerta) {
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

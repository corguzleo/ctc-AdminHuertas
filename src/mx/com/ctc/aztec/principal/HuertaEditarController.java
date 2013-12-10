/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mx.com.ctc.aztec.control.ArbolAcciones;
import mx.com.ctc.aztec.control.AsesoriaTecnicaAcciones;
import mx.com.ctc.aztec.control.RevisionAcciones;
import mx.com.ctc.aztec.control.DignosticoAcciones;
import mx.com.ctc.aztec.control.HuertaAcciones;
import mx.com.ctc.aztec.dao.DiagnosticoCatalogoDAO;
import mx.com.ctc.aztec.model.AgenteCausal;
import mx.com.ctc.aztec.model.Arbol;
import mx.com.ctc.aztec.model.AsesoriaTecnica;
import mx.com.ctc.aztec.model.Diagnostico;
import mx.com.ctc.aztec.model.DiagnosticoCatalogo;
import mx.com.ctc.aztec.model.Huerta;
import mx.com.ctc.aztec.model.Revision;
import mx.com.ctc.aztec.utils.Util;
import static mx.com.ctc.aztec.principal.DiagnosticoController.listaSubtipoArbol;
import static mx.com.ctc.aztec.principal.DiagnosticoController.listaSubtipoHuerta;
import static mx.com.ctc.aztec.principal.DiagnosticoController.listaSitio;
import mx.com.ctc.aztec.principal.UltimosDiagnosticosController.RevisionDiagnostico;
import mx.com.ctc.aztec.utils.Constantes;

/**
 * FXML Controller class
 *
 * @author OSCAR
 */
public class HuertaEditarController implements Initializable {
    private Main application;
    private ArbolAcciones aa = new ArbolAcciones();
    private RevisionAcciones ra = new RevisionAcciones();
    private AsesoriaTecnicaAcciones ata = new  AsesoriaTecnicaAcciones();
    private DignosticoAcciones da = new DignosticoAcciones();
    private HuertaAcciones ha =  new HuertaAcciones();
    private Revision revisionSelected;
    private Diagnostico diagnosticoSelected ;
    private DiagnosticoCatalogoDAO dao =  new DiagnosticoCatalogoDAO();
    
    @FXML 
    private MenuController menuController;
    @FXML 
    private DiagnosticoController diagnosticoController;
    @FXML
    private RevisionController revisionController;
    @FXML
    ScrollPane scroll;
    @FXML
    AnchorPane pane;
    @FXML
    TextField  txtArbol;
    @FXML
    TextField  txtHuerta;
    @FXML
    TextField  txtFila;
    @FXML
    TextField  txtColumna;
    @FXML
    TextField  txtClave;
    @FXML
    TextField  txtQR;
    @FXML
    TextField  txtPlaca;
    @FXML
    TextField  txtLatitud;
    @FXML
    TextField  txtLongitud;
    @FXML
    TextField txtAltitud;
    @FXML
    ComboBox cmbTipo;
    @FXML
    ComboBox cmbVariedad;
    @FXML
    ComboBox cmbEstatus;
    @FXML
    TextField txtPlantacion;
    @FXML
    TextField txtEstacion;
    @FXML
    CheckBox chkInjertado;
    @FXML
    TextArea txtNotas;
    @FXML
    TextField txtProduccion;
    @FXML
    TextField txtVivero;
    @FXML
    CheckBox chkReplantado;
    @FXML
    TextField txtUltimoDiagnostico;
    @FXML
    Label labelMensaje;
    
    @FXML
    Tab tabArbol;
    @FXML
    Tab tabRevision;
    
    
    public void setApp(Main application){
        this.application = application;
        menuController.setApp(application);
        diagnosticoController.setApp(application);
        revisionController.setApp(application);
        //ultimosDiagnosticosController.setApp(application);
        //ultimosDiagnosticosController.setArbolEditar(this);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //labelMensaje.setAlignment(Pos.CENTER);
        //labelMensaje.setText("");
        ArbolController.arbolSelected = null;
        llenarDatosRevision();
        llenarDatosDiagnostico();
        tabRevision.setOnSelectionChanged(tabRevisionHandler);
    }    

     
     
     private void llenarDatosRevision(){
         List<Revision> revisiones =  new ArrayList();
         revisiones = ra.getRevisonesAppHuerta(Main.huertaSeleccionada.getId());
         if (revisiones.size() > 0){
             tabRevision.setText("Última revisión");
             revisionSelected = revisiones.get(0);
             revisionController.agallasHojas.selectedProperty().set(Boolean.valueOf(revisionSelected.getAgallasHojas().toLowerCase()));
             revisionController.amarillo.selectedProperty().set(Boolean.valueOf(revisionSelected.getAmarillo().toLowerCase()));
             revisionController.brotosDaniados.selectedProperty().set(Boolean.valueOf(revisionSelected.getBrotesDanados().toLowerCase()));
             revisionController.caidaFruto.selectedProperty().set(Boolean.valueOf(revisionSelected.getCaidaFrutos()));
             revisionController.caidaHojas.selectedProperty().set(Boolean.valueOf(revisionSelected.getCaidaHojas()));
             revisionController.caidaRamas.selectedProperty().set(Boolean.valueOf(revisionSelected.getCaidaRamas()));
             revisionController.cortezaDaniada.selectedProperty().set(Boolean.valueOf(revisionSelected.getCortezaDanada()));
             revisionController.defolacion.selectedProperty().set(Boolean.valueOf(revisionSelected.getDefoliacion()));
             revisionController.floracionRetrasada.selectedProperty().set(Boolean.valueOf(revisionSelected.getFloracionRetrasada()));
             revisionController.frutoMalformado.selectedProperty().set(Boolean.valueOf(revisionSelected.getFrutoMalformacion()));
             revisionController.granizada.selectedProperty().set(Boolean.valueOf(revisionSelected.getGranizada()));
             revisionController.helada.selectedProperty().set(Boolean.valueOf(revisionSelected.getHelada()));
             revisionController.hojaAmarilla.selectedProperty().set(Boolean.valueOf(revisionSelected.getHojasAmarillas()));
             revisionController.hojaChica.selectedProperty().set(Boolean.valueOf(revisionSelected.getHojaChica()));
             revisionController.hojaChina.selectedProperty().set(Boolean.valueOf(revisionSelected.getHojasChinas()));
             revisionController.hojaCloritica.selectedProperty().set(Boolean.valueOf(revisionSelected.getHojasCloroticas()));
             revisionController.hojaManchada.selectedProperty().set(Boolean.valueOf(revisionSelected.getHojasManchadas()));
             revisionController.hojaSeca.selectedProperty().set(Boolean.valueOf(revisionSelected.getHojasSecas()));
             revisionController.hojaVerdeAmaillento.selectedProperty().set(Boolean.valueOf(revisionSelected.getHojasVerdeAmarillento()));
             revisionController.hojaVerdeOscuro.selectedProperty().set(Boolean.valueOf(revisionSelected.getHojasVerdesOscuro()));
             revisionController.hueco.selectedProperty().set(Boolean.valueOf(revisionSelected.getHueco()));
             revisionController.huesoBarrenado.selectedProperty().set(Boolean.valueOf(revisionSelected.getHuesoBarrenado()));
             revisionController.lesionesSol.selectedProperty().set(Boolean.valueOf(revisionSelected.getLesionesSol()));
             revisionController.manchasCorchosas.selectedProperty().set(Boolean.valueOf(revisionSelected.getManchasCorchosas()));
             revisionController.marcasHoja.selectedProperty().set(Boolean.valueOf(revisionSelected.getMarcasHojasFrutosTallos()));
             revisionController.necrosis.selectedProperty().set(Boolean.valueOf(revisionSelected.getNecrosis()));
             revisionController.otro.selectedProperty().set(Boolean.valueOf(revisionSelected.getOtro()));
             revisionController.oxidacionHojas.selectedProperty().set(Boolean.valueOf(revisionSelected.getOxidacionHojas()));
             revisionController.pigmeo.selectedProperty().set(Boolean.valueOf(revisionSelected.getPigmeo()));
             revisionController.pocaHoja.selectedProperty().set(Boolean.valueOf(revisionSelected.getPocaHoja()));
             revisionController.pocoAmarre.selectedProperty().set(Boolean.valueOf(revisionSelected.getPocoAmarre()));
             revisionController.pudricionFruto.selectedProperty().set(Boolean.valueOf(revisionSelected.getPudricionFruto()));
             revisionController.pudricionRadicular.selectedProperty().set(Boolean.valueOf(revisionSelected.getPudricionRadicular()));
             revisionController.seco.selectedProperty().set(Boolean.valueOf(revisionSelected.getSeco()));
             revisionController.tumores.selectedProperty().set(Boolean.valueOf(revisionSelected.getTumores()));
             
             revisionController.cenizaVolcanica.selectedProperty().set(Boolean.valueOf(revisionSelected.getCenizaVolcanica()));
             revisionController.faltaAgua.selectedProperty().set(Boolean.valueOf(revisionSelected.getFaltaAgua()));
             revisionController.incendio.selectedProperty().set(Boolean.valueOf(revisionSelected.getIncendio()));
             revisionController.plaga.selectedProperty().set(Boolean.valueOf(revisionSelected.getPlaga()));
         }else{
             tabRevision.setText("Nueva revisión");
             //ultimosDiagnosticosController.panelPrincipal.setVisible(true);
         }
     }
     
     
    private void llenarDatosDiagnostico(){
         if(revisionSelected != null){
            String idRevision = Util.isNull(revisionSelected.getId()) ? revisionSelected.getIdTemporal(): revisionSelected.getId();
            diagnosticoSelected = da.getDiagnostico(idRevision);
            if(diagnosticoSelected != null){
                diagnosticoController.txtTipo.setText(diagnosticoSelected.getTipo());
                if(diagnosticoSelected.getTipo().equals("Árbol")){
                   diagnosticoController.cmbSubtipo.getItems().setAll(listaSubtipoArbol);
                }else{
                   diagnosticoController.cmbSubtipo.getItems().setAll(listaSubtipoHuerta);
                }
                diagnosticoController.cmbGrado.getSelectionModel().select(diagnosticoSelected.getGradoAfeccion());
                diagnosticoController.cmbSubtipo.getSelectionModel().select(diagnosticoSelected.getSubtipo());
                
                ObservableList<DiagnosticoCatalogo> listaDiagnosticos = FXCollections.observableArrayList(dao.selectDiagnostico(diagnosticoSelected.getSubtipo()));
                diagnosticoController.cmbDiagostico.getItems().setAll(listaDiagnosticos);
                DiagnosticoCatalogo dc = new DiagnosticoCatalogo("",diagnosticoSelected.getSubtipo(),diagnosticoSelected.getDiagnostico());
                diagnosticoController.cmbDiagostico.getSelectionModel().select(dc);
                
                ObservableList<AgenteCausal> listaCausas = FXCollections.observableArrayList(dao.selectAgenteCausal(diagnosticoSelected.getDiagnostico()));
                diagnosticoController.cmbAgente.getItems().setAll(listaCausas);
                AgenteCausal ac = new AgenteCausal("",diagnosticoSelected.getDiagnostico(),diagnosticoSelected.getAgenteCausal());
                diagnosticoController.cmbAgente.getSelectionModel().select(ac);
                
                setSitioAfectacion(diagnosticoSelected.getSitioAfeccion());
                /*String[] sitiosSeleccionados = diagnosticoSelected.getSitioAfeccion().split(",");
                int[] indices = new int[listaSitio.size()];
                int firsSelected = -1;
                int index = 0;
                for(Integer i = 0;i < indices.length;i++ ){
                    for(Integer j=0; j < sitiosSeleccionados.length; j++){
                        if(listaSitio.get(i).toString().equals(sitiosSeleccionados[j]) && firsSelected == -1){
                            firsSelected = i;
                        }else if(listaSitio.get(i).toString().equals(sitiosSeleccionados[j])  && firsSelected > -1){
                            indices[index] = i;
                            index++;
                        }
                    }
                }
                diagnosticoController.listSitio.getSelectionModel().selectIndices(firsSelected, indices);*/
            }
         }
     }
     
    public void regresarListaArboles(ActionEvent event) {
         ArbolController.arbolSelected = null;
         application.gotoArboles();
     }
     
   
     
    public void guardarRevisionDiagnostico(ActionEvent event){
        if(diagnosticoSelected == null){
            insertar();
        }else{
            actualizar();
        }
        //ArbolController.arbolSelected.setModificado("true");
        //aa.saveArbolAPP(ArbolController.arbolSelected);
     }
     
    private Boolean getValueFromCheck(CheckBox cb){
         return cb.selectedProperty().get();
     }
    
    private void insertar(){
         String respuesta = "";
         respuesta = revisarDiagnsotico();
         if(Util.isNull(respuesta)){
            AsesoriaTecnica  at = ata.getAsesoriaAPPxHuerta(Main.huertaSeleccionada.getId());
            if(Util.isNull(at) && !Util.isNull(Main.huertaSeleccionada.getIdHuerta())){
             at = ata.getAsesoriaAPPxHuerta((Main.huertaSeleccionada.getIdHuerta()));
            }
            respuesta = ra.insertRevision("", "","", Main.huertaSeleccionada.getId(), at.getIdTemporal(), 
                                           getValueFromCheck(revisionController.tumores),getValueFromCheck(revisionController.seco), getValueFromCheck(revisionController.pudricionRadicular),
                                           getValueFromCheck(revisionController.pudricionFruto), getValueFromCheck(revisionController.pocoAmarre), getValueFromCheck(revisionController.pocaHoja), 
                                           getValueFromCheck(revisionController.pigmeo), getValueFromCheck(revisionController.oxidacionHojas), getValueFromCheck(revisionController.otro),
                                           getValueFromCheck(revisionController.necrosis), getValueFromCheck(revisionController.marcasHoja), getValueFromCheck(revisionController.manchasCorchosas), 
                                           getValueFromCheck(revisionController.lesionesSol), getValueFromCheck(revisionController.huesoBarrenado), getValueFromCheck(revisionController.hueco), 
                                           getValueFromCheck(revisionController.hojaVerdeOscuro), getValueFromCheck(revisionController.hojaVerdeAmaillento), getValueFromCheck(revisionController.hojaSeca),
                                           getValueFromCheck(revisionController.hojaManchada), getValueFromCheck(revisionController.hojaCloritica), getValueFromCheck(revisionController.hojaChica), 
                                           getValueFromCheck(revisionController.helada), getValueFromCheck(revisionController.granizada), getValueFromCheck(revisionController.frutoMalformado), getValueFromCheck(revisionController.floracionRetrasada),
                                           getValueFromCheck(revisionController.defolacion), getValueFromCheck(revisionController.cortezaDaniada), getValueFromCheck(revisionController.caidaRamas), 
                                           getValueFromCheck(revisionController.caidaHojas), getValueFromCheck(revisionController.caidaFruto), getValueFromCheck(revisionController.brotosDaniados),
                                           getValueFromCheck(revisionController.amarillo), getValueFromCheck(revisionController.agallasHojas),getValueFromCheck(revisionController.hojaChina),getValueFromCheck(revisionController.hojaAmarilla),
                                           getValueFromCheck(revisionController.cenizaVolcanica),getValueFromCheck(revisionController.faltaAgua),getValueFromCheck(revisionController.incendio),getValueFromCheck(revisionController.plaga));

            if(!Util.isNull(ra.idRevision)){
                //System.out.println(ra.idRevision);
                String diagnostico = diagnosticoController.cmbDiagostico.getSelectionModel().getSelectedItem().toString();
                String agenteCausal = !Util.isNull(diagnosticoController.cmbAgente.getSelectionModel().getSelectedItem()) ? diagnosticoController.cmbAgente.getSelectionModel().getSelectedItem().toString() : "";
                String gradoAfeccion = !Util.isNull(diagnosticoController.cmbGrado.getSelectionModel().getSelectedItem()) ? diagnosticoController.cmbGrado.getSelectionModel().getSelectedItem().toString() : "";
                
                String subtipo = diagnosticoController.cmbSubtipo.getSelectionModel().getSelectedItem().toString();
                /*
                 String sitioAfeccion = "";
                 for(Object i : diagnosticoController.listSitio.getSelectionModel().getSelectedIndices()){
                   Integer indice = (Integer)i;
                   sitioAfeccion = sitioAfeccion + DiagnosticoController.listaSitio.get(indice) + "," ;
                }
                sitioAfeccion = sitioAfeccion.substring(0,sitioAfeccion.length()-1);*/
                String tipo = "Árbol";
                if(Util.isNull(ArbolController.arbolSelected)){
                    tipo = "Huerta";
                }
                
                respuesta = da.insertDiagnostico(String.valueOf(ra.idRevision), "", diagnostico, agenteCausal, gradoAfeccion, getSitioAfectacion(),Main.huertaSeleccionada.getId(),"M",tipo,subtipo);
            }else{
               respuesta = "Error, consulte a su administrador: " + respuesta;
            }
         
         }
         if(respuesta.equals(Constantes.MSG_EXITO_GUARDADO)){
            Huerta huerta = Main.huertaSeleccionada;
            huerta.setModificado("true");
            ha.saveHuertaApp(huerta);
            Dialogs.showInformationDialog(application.stage, respuesta, "", "Aztec");
         }else{
            Dialogs.showWarningDialog(application.stage, respuesta, "", "Aztec");
         }
         //labelMensaje.setText(respuesta);
    }
     
    private void actualizar(){
        String mensaje;
        revisionSelected.setTumores(getValueFromCheck(revisionController.tumores).toString());
        revisionSelected.setSeco(getValueFromCheck(revisionController.seco).toString());
        revisionSelected.setPudricionRadicular( getValueFromCheck(revisionController.pudricionRadicular).toString());
        revisionSelected.setPudricionFruto(getValueFromCheck(revisionController.pudricionFruto).toString());
        revisionSelected.setPocoAmarre(getValueFromCheck(revisionController.pocoAmarre).toString());
        revisionSelected.setPocaHoja(getValueFromCheck(revisionController.pocaHoja).toString());
        revisionSelected.setPigmeo(getValueFromCheck(revisionController.pigmeo).toString());
        revisionSelected.setOxidacionHojas(getValueFromCheck(revisionController.oxidacionHojas).toString());
        revisionSelected.setOtro(getValueFromCheck(revisionController.otro).toString());
        revisionSelected.setNecrosis(getValueFromCheck(revisionController.necrosis).toString());
        revisionSelected.setMarcasHojasFrutosTallos(getValueFromCheck(revisionController.marcasHoja).toString());
        revisionSelected.setManchasCorchosas( getValueFromCheck(revisionController.manchasCorchosas).toString());
        revisionSelected.setLesionesSol(getValueFromCheck(revisionController.lesionesSol).toString());
        revisionSelected.setHuesoBarrenado(getValueFromCheck(revisionController.huesoBarrenado).toString());
        revisionSelected.setHueco(getValueFromCheck(revisionController.hueco).toString());
        revisionSelected.setHojasVerdesOscuro(getValueFromCheck(revisionController.hojaVerdeOscuro).toString());
        revisionSelected.setHojasVerdeAmarillento(getValueFromCheck(revisionController.hojaVerdeAmaillento).toString());
        revisionSelected.setHojasSecas(getValueFromCheck(revisionController.hojaSeca).toString());
        revisionSelected.setHojasManchadas( getValueFromCheck(revisionController.hojaManchada).toString());
        revisionSelected.setHojasCloroticas(getValueFromCheck(revisionController.hojaCloritica).toString());
        revisionSelected.setHojaChica(getValueFromCheck(revisionController.hojaChica).toString());
        revisionSelected.setGranizada(getValueFromCheck(revisionController.granizada).toString());
        revisionSelected.setFrutoMalformacion( getValueFromCheck(revisionController.frutoMalformado).toString());
        revisionSelected.setFloracionRetrasada(getValueFromCheck(revisionController.floracionRetrasada).toString());
        revisionSelected.setHelada( getValueFromCheck(revisionController.helada).toString());
        revisionSelected.setCortezaDanada(getValueFromCheck(revisionController.cortezaDaniada).toString());
        revisionSelected.setCaidaRamas(getValueFromCheck(revisionController.caidaRamas).toString());
        revisionSelected.setCaidaFrutos( getValueFromCheck(revisionController.caidaFruto).toString());
        revisionSelected.setBrotesDanados( getValueFromCheck(revisionController.brotosDaniados).toString());
        revisionSelected.setAmarillo(getValueFromCheck(revisionController.amarillo).toString());
        revisionSelected.setAgallasHojas( getValueFromCheck(revisionController.agallasHojas).toString());
        revisionSelected.setHojasChinas(getValueFromCheck(revisionController.hojaChina).toString());
        revisionSelected.setHojasAmarillas(getValueFromCheck(revisionController.hojaAmarilla).toString());
        
        revisionSelected.setCenizaVolcanica(getValueFromCheck(revisionController.cenizaVolcanica).toString());
        revisionSelected.setFaltaAgua(getValueFromCheck(revisionController.faltaAgua).toString());
        revisionSelected.setIncendio(getValueFromCheck(revisionController.incendio).toString());
        revisionSelected.setPlaga(getValueFromCheck(revisionController.plaga).toString());
 
        mensaje = ra.saveRevisonAPP(revisionSelected);
        if(!Util.isNull(mensaje)){
              String diagnostico = diagnosticoController.cmbDiagostico.getSelectionModel().getSelectedItem().toString();
              String agenteCausal = !Util.isNull(diagnosticoController.cmbAgente.getSelectionModel().getSelectedItem()) ? diagnosticoController.cmbAgente.getSelectionModel().getSelectedItem().toString() : "";
              String gradoAfeccion = !Util.isNull(diagnosticoController.cmbGrado.getSelectionModel().getSelectedItem()) ? diagnosticoController.cmbGrado.getSelectionModel().getSelectedItem().toString() : "";
             
              String subtipo = diagnosticoController.cmbSubtipo.getSelectionModel().getSelectedItem().toString();
              /*String sitioAfeccion = "";
              for(Object i : diagnosticoController.listSitio.getSelectionModel().getSelectedIndices()){
                   Integer indice = (Integer)i;
                   sitioAfeccion = sitioAfeccion + DiagnosticoController.listaSitio.get(indice) + "," ;
                }
              sitioAfeccion = sitioAfeccion.substring(0,sitioAfeccion.length()-1);*/
              
              diagnosticoSelected.setDiagnostico(diagnostico);
              diagnosticoSelected.setAgenteCausal(agenteCausal);
              diagnosticoSelected.setGradoAfeccion(gradoAfeccion);
              diagnosticoSelected.setSitioAfeccion(getSitioAfectacion());
              diagnosticoSelected.setSubtipo(subtipo);
              diagnosticoSelected.setTipoInsercion("M");
              
              mensaje = da.savaDiagnostioco(diagnosticoSelected);
        }
        if(mensaje.equals(Constantes.MSG_EXITO_GUARDADO)){
            Huerta huerta = Main.huertaSeleccionada;
            huerta.setModificado("true");
            ha.saveHuertaApp(huerta);
            Dialogs.showInformationDialog(application.stage, mensaje, "", "Aztec");
         }else{
            Dialogs.showWarningDialog(application.stage, mensaje, "", "Aztec");
        }
        //labelMensaje.setText(mensaje);
    }
    private String revisarDiagnsotico(){
            String respuesta = "";
            if(Util.isNull(diagnosticoController.cmbSubtipo.getSelectionModel().getSelectedItem())){
                respuesta = respuesta + "Seleccione un subtipo.";
            }
            if(Util.isNull(diagnosticoController.cmbDiagostico.getSelectionModel().getSelectedItem())){
                 respuesta = respuesta +  "Seleccione un diagnóstico.";
            }
            /*if(diagnosticoController.listSitio.getSelectionModel().getSelectedIndex() < 0){
                respuesta = respuesta + "Seleccione un sitio de afección.";
            }*/
            return respuesta;
     }
     
  
    
    private EventHandler<Event> tabRevisionHandler = 
            new EventHandler<Event>(){
 
        @Override
        public void handle(Event t) {
             //labelMensaje.setText("");
        }
    };
    
     private String getSitioAfectacion(){
        String respuesta = "";
        if(getValueFromCheck(diagnosticoController.chkAfectacion)){
            respuesta = respuesta  + "Afectación al fruto;";
        }
        if(getValueFromCheck(diagnosticoController.chkDanio)){
            respuesta = respuesta  + "Daño al árbol;";
        }
        if(getValueFromCheck(diagnosticoController.chkFollaje)){
            respuesta = respuesta  + "Follaje;";
        }
        if(getValueFromCheck(diagnosticoController.chkRaiz)){
            respuesta = respuesta  + "Raíz;";
        }
        if(getValueFromCheck(diagnosticoController.chkTronco)){
            respuesta = respuesta  + "Tronco;";
        }
        if(getValueFromCheck(diagnosticoController.chkFlor)){
            respuesta = respuesta  + "Flor;";
        }
        respuesta = respuesta.length() > 0 ? respuesta.substring(0,respuesta.length()-1) : respuesta;
        return respuesta;
    }
    
    private void setSitioAfectacion(String sitiosAfeccion){
        
         String[] sitiosSeleccionados = sitiosAfeccion.split(";");
         for(String sitio:sitiosSeleccionados){
             if(sitio.equals("Afectación al fruto")){
                 diagnosticoController.chkAfectacion.selectedProperty().set(true);
             }
             if(sitio.equals("Daño al árbol")){
                 diagnosticoController.chkDanio.selectedProperty().set(true);
             }
             if(sitio.equals("Follaje")){
                 diagnosticoController.chkFollaje.selectedProperty().set(true);
             }
             if(sitio.equals("Raíz")){
                 diagnosticoController.chkRaiz.selectedProperty().set(true);
             }
             if(sitio.equals("Tronco")){
                 diagnosticoController.chkTronco.selectedProperty().set(true);
             }
             if(sitio.equals("Flor")){
                 diagnosticoController.chkFlor.selectedProperty().set(true);
             }
         }
    }
             
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author OSCAR
 */
public class RevisionController implements Initializable {
     private Main application;
     @FXML
     CheckBox agallasHojas;
     @FXML
     CheckBox amarillo;
     @FXML
     CheckBox brotosDaniados;
     @FXML
     CheckBox caidaFruto;
     @FXML
     CheckBox caidaHojas;
     @FXML
     CheckBox caidaRamas;
     @FXML
     CheckBox cortezaDaniada;
     @FXML
     CheckBox defolacion;
     @FXML
     CheckBox floracionRetrasada;
     @FXML
     CheckBox frutoMalformado;
     @FXML
     CheckBox granizada;
     @FXML
     CheckBox helada;
     @FXML
     CheckBox hojaChica;
     @FXML
     CheckBox hojaAmarilla;
     @FXML
     CheckBox hojaChina; 
     @FXML
     CheckBox hojaCloritica;
     @FXML
     CheckBox hojaManchada;
     @FXML
     CheckBox hojaSeca;
     @FXML
     CheckBox hojaVerdeAmaillento;
     @FXML
     CheckBox hojaVerdeOscuro;
     @FXML
     CheckBox hueco;
     @FXML
     CheckBox huesoBarrenado;
     @FXML
     CheckBox lesionesSol;
     @FXML
     CheckBox manchasCorchosas;
     @FXML
     CheckBox marcasHoja;
     @FXML
     CheckBox necrosis;
     @FXML
     CheckBox otro;
     @FXML
     CheckBox oxidacionHojas;
     @FXML
     CheckBox pigmeo;
     @FXML
     CheckBox pocaHoja;
     @FXML
     CheckBox pocoAmarre;
     @FXML
     CheckBox pudricionFruto;
     @FXML
     CheckBox pudricionRadicular;
     @FXML
     CheckBox seco;
     @FXML
     CheckBox tumores;
     
     @FXML
     CheckBox cenizaVolcanica;
     @FXML
     CheckBox faltaAgua;
     @FXML
     CheckBox incendio;
     @FXML
     CheckBox plaga;
     
     
     public void setApp(Main application){
        this.application = application;
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if(ArbolController.arbolSelected != null || ArbolController.bNuevo){
           helada.setVisible(false);
           granizada.setVisible(false);
           cenizaVolcanica.setVisible(false);
           faltaAgua.setVisible(false);
           incendio.setVisible(false);
           plaga.setVisible(false);
           agallasHojas.setVisible(true);
           amarillo.setVisible(true);
           brotosDaniados.setVisible(true);
           caidaFruto.setVisible(true);
           caidaHojas.setVisible(true);
           caidaRamas.setVisible(true);
           cortezaDaniada.setVisible(true);
           defolacion.setVisible(true);
           floracionRetrasada.setVisible(true);
           frutoMalformado.setVisible(true);
           seco.setVisible(true);
           tumores.setVisible(true);
           hojaChica.setVisible(true);
           hojaAmarilla.setVisible(true);
           hojaChina.setVisible(true);
           hojaCloritica.setVisible(true);
           hojaManchada.setVisible(true);
           hojaSeca.setVisible(true);
           hojaVerdeAmaillento.setVisible(true);
           hojaVerdeOscuro.setVisible(true);
           hueco.setVisible(true);
           huesoBarrenado.setVisible(true);
           lesionesSol.setVisible(true);
           manchasCorchosas.setVisible(true);
           marcasHoja.setVisible(true);
           necrosis.setVisible(true);
           otro.setVisible(true);
           oxidacionHojas.setVisible(true);
           pigmeo.setVisible(true);
           pocaHoja.setVisible(true);
           pocoAmarre.setVisible(true);
           pudricionFruto.setVisible(true);
           pudricionRadicular.setVisible(true);
       }else{
           helada.setVisible(true);
           granizada.setVisible(true);
           cenizaVolcanica.setVisible(true);
           faltaAgua.setVisible(true);
           incendio.setVisible(true);
           plaga.setVisible(true);
           agallasHojas.setVisible(false);
           amarillo.setVisible(false);
           brotosDaniados.setVisible(false);
           caidaFruto.setVisible(false);
           caidaHojas.setVisible(false);
           caidaRamas.setVisible(false);
           cortezaDaniada.setVisible(false);
           defolacion.setVisible(false);
           floracionRetrasada.setVisible(false);
           frutoMalformado.setVisible(false);
           seco.setVisible(false);
           tumores.setVisible(false);
           hojaChica.setVisible(false);
           hojaAmarilla.setVisible(false);
           hojaChina.setVisible(false);
           hojaCloritica.setVisible(false);
           hojaManchada.setVisible(false);
           hojaSeca.setVisible(false);
           hojaVerdeAmaillento.setVisible(false);
           hojaVerdeOscuro.setVisible(false);
           hueco.setVisible(false);
           huesoBarrenado.setVisible(false);
           lesionesSol.setVisible(false);
           manchasCorchosas.setVisible(false);
           marcasHoja.setVisible(false);
           necrosis.setVisible(false);
           otro.setVisible(false);
           oxidacionHojas.setVisible(false);
           pigmeo.setVisible(false);
           pocaHoja.setVisible(false);
           pocoAmarre.setVisible(false);
           pudricionFruto.setVisible(false);
           pudricionRadicular.setVisible(false);
       }
    }    
}

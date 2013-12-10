/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import mx.com.ctc.aztec.utils.Constantes;


/**
 * FXML Controller class
 *
 * @author OSCAR
 */
public class MenuController extends AnchorPane implements Initializable {
    /*private MenuItem exit = new MenuItem("Salir");
    private MenuItem huertas = new MenuItem("Huertas");
    private MenuItem arboles = new MenuItem("Arboles");
    private MenuItem descargaHuertas = new MenuItem("Descarga huertas");
    private MenuItem sincronizar = new MenuItem("Sincronizar");*/
    public static String menuPresionado = "";
    private Main application;
    /**
     * Initializes the controller class.
     */
    @FXML
    MenuBar menuPrincipal;
    
    @FXML
    MenuItem exit;
    @FXML
    MenuItem huertas;
    @FXML
    MenuItem arboles;
    @FXML
    MenuItem descargaHuertas;
    @FXML
    MenuItem sincronizar;
    
    public void setApp(Main application){
        this.application = application;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* Menu menu1 =  new Menu("Principal");
        menu1.getItems().addAll(arboles,huertas,exit);
        Menu menu2 =  new Menu("Salesforce");
        menu2.getItems().addAll(descargaHuertas,sincronizar);
        menuPrincipal.getMenus().addAll(menu1,menu2);
       */
        exit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    System.exit(0);
                }
         });
        
        huertas.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                   application.gotoHuertas();
                }
         });
        arboles.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t){
                    if(Main.huertaSeleccionada != null){
                        application.gotoArboles();
                    }
                }
         });
        descargaHuertas.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                   menuPresionado = Constantes.MENU_DESCARGA_HUERTAS;
                   application.gotoLoginSalesforce();
                }
         });
        sincronizar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                   menuPresionado = Constantes.MENU_SINCRONIZAR;
                   application.gotoLoginSalesforce();
                }
         });
    }    
    
    
}

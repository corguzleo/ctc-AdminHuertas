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
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mx.com.ctc.aztec.service.LoginServicio;
import mx.com.ctc.aztec.service.UserServicio;
import mx.com.ctc.aztec.utils.Constantes;

/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author OSCAR
 */
public class SalesforceController extends AnchorPane implements Initializable {
    private Main application;
    private Task copyWorker;
    private LoginServicio loginService = new LoginServicio();
    
    @FXML 
    private MenuController menuController;
    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;
    @FXML
    PasswordField token;
    @FXML
    ComboBox cmbAmbiente;
    @FXML
    CheckBox chkGuardar;
    @FXML
    ProgressBar bar;
    
    public void setApp(Main application){
        this.application = application;
        menuController.setApp(application);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bar.setVisible(false);
        ObservableList<String> listaVariedades = FXCollections.observableArrayList("Sandbox","Producción");
        cmbAmbiente.getItems().clear();
        cmbAmbiente.getItems().addAll(listaVariedades);
        cmbAmbiente.getSelectionModel().select("Producción");
        errorMessage.setText("");
        errorMessage.setAlignment(Pos.CENTER);
        userId.setPromptText("Usuario");
        password.setPromptText("Contraseña");
        token.setPromptText("Token");
        chkGuardar.setSelected(Boolean.valueOf(UserServicio.usuarioConectado.getGuardarsf()));
        userId.setText(UserServicio.usuarioConectado.getUsersf());
        password.setText(UserServicio.usuarioConectado.getPasswordsf());
        token.setText(UserServicio.usuarioConectado.getToken());
        
        login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                errorMessage.setText("");
                bar.setVisible(true);
                login.setDisable(true);
                copyWorker = createWorker();
                bar.progressProperty().unbind();
                        bar.progressProperty().bind(copyWorker.progressProperty());
                        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
                                    @Override
                                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                                        //System.out.println(newValue);
                                        processLogin();
                                    }
                         });
                new Thread(copyWorker).start();
            }
        });

    }
    
    
    public void processLogin() {
        
       if (application != null){
        
            try{
                guardarDatosSalesforce();
                loginService.login(userId.getText().trim(), password.getText().trim(),token.getText().trim(),cmbAmbiente.getSelectionModel().getSelectedItem().toString());                if(MenuController.menuPresionado.equals(Constantes.MENU_DESCARGA_HUERTAS)){
                    //System.out.println("Descargar huertas");
                    application.gotoDescargaHuertas();
                }else if(MenuController.menuPresionado.equals(Constantes.MENU_SINCRONIZAR)){
                    //System.out.println("sincronziar huertas");
                    application.gotoSincronizarHuertas();
                }
            }catch(Exception e){
                bar.setVisible(false);
                login.setDisable(false);
                errorMessage.setText(e.getMessage());
            }
        }
    }
    
    
    
    private void guardarDatosSalesforce(){
       
        if(UserServicio.usuarioConectado != null){
            if(chkGuardar.selectedProperty().get()){
                    UserServicio.usuarioConectado.setUsersf(userId.getText());
                    UserServicio.usuarioConectado.setPasswordsf(password.getText());
                    UserServicio.usuarioConectado.setToken(token.getText());
                    UserServicio.usuarioConectado.setGuardarsf("true");
            }else{
                    UserServicio.usuarioConectado.setUsersf("");
                    UserServicio.usuarioConectado.setPasswordsf("");
                    UserServicio.usuarioConectado.setToken("");
                    UserServicio.usuarioConectado.setGuardarsf("false");
            }
            loginService.actulizarUsuario(UserServicio.usuarioConectado);
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
     
}

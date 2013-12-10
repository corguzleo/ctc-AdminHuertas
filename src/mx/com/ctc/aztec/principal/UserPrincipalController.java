/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import mx.com.ctc.aztec.dao.ArbolDAO;
import mx.com.ctc.aztec.service.UserServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class UserPrincipalController extends AnchorPane implements Initializable{
    private UserServicio us = new UserServicio();
    private Main application;
    private Task copyWorker;
    static Logger logUser=  LoggerFactory.getLogger(UserPrincipalController.class);
    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;
    @FXML
    ProgressBar bar;
    
    public void setApp(Main application){
        this.application = application;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         errorMessage.setText("");
         userId.setPromptText("Usuario");
         password.setPromptText("Contraseña");
         bar.setVisible(false);
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
    
    private void processLogin(){
        if (application == null){
            errorMessage.setText("Error en la creación de la aplicación");
        } else {
            try{
               //logUser.error("Error: " + userId.getText().trim() +","+ password.getText().trim());
               us.Login(userId.getText().trim(), password.getText().trim());
               application.gotoHuertas();
            }catch(Exception e){
                bar.setVisible(false);
                login.setDisable(false);
                logUser.error("Error: " + e.getMessage());
                errorMessage.setText(e.getMessage());
            }
        }
    }
    
    public void processLoginKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            processLogin();
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

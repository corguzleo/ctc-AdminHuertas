/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.principal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mx.com.ctc.aztec.dao.Conexion;
import mx.com.ctc.aztec.model.Huerta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class Main extends Application {
    public Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 800.0;
    private final double MINIMUM_WINDOW_HEIGHT = 600.0;
    static Logger logger =  LoggerFactory.getLogger(Main.class.getName());
    public static Huerta huertaSeleccionada;
    @Override
    public void start(Stage primaryStage) {
            prepareLogging();
            Conexion conexionDBAPP  = new Conexion();
            conexionDBAPP.crearTablas();
            stage = primaryStage;
            stage.setTitle("Aztec huertas");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            gotoLoginPrincipal();
            primaryStage.setResizable(true);
            primaryStage.show();
    }
       
      public void gotoLoginPrincipal() {
            try {
               UserPrincipalController login = (UserPrincipalController) replaceSceneContent("userPrincipal.fxml",600.0,400.0);
               login.setApp(this);
            } catch (Exception ex) {
                logger.error(ex.getLocalizedMessage());
            }
       }
      public void gotoLoginSalesforce() {
            try {
               SalesforceController login = (SalesforceController) replaceSceneContent("salesforce.fxml",800.0,600.0);
               login.setApp(this);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
       }
       public void gotoHuertas() {
            try {
               HuertaController huerta = (HuertaController) replaceSceneContent("huerta.fxml",800.0,600.0);
               huerta.setApp(this);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }
       
       public void gotoDescargaHuertas(){
           try {
               HuertaDescargaController huerta = (HuertaDescargaController) replaceSceneContent("huertaDescarga.fxml",800.0,600.0);
               huerta.setApp(this);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
       }
       public void gotoSincronizarHuertas(){
           try {
               HuertaSincronizarController huerta = (HuertaSincronizarController) replaceSceneContent("huertaSincronizar.fxml",800.0,600.0);
               huerta.setApp(this);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
       }
       
       public void gotoArboles() {
            try {
               ArbolController arbol = (ArbolController) replaceSceneContent("arbol.fxml",800.0,600.0);
               arbol.setApp(this);
            } catch (Exception ex) {
                logger.error(ex.getMessage() + "," + ex.getLocalizedMessage());
            }
        }
       
       public void gotoArbolEditar() {
            try {
               ArbolEditarController arbolEditar;
               arbolEditar = (ArbolEditarController) replaceSceneContent("arbolEditarTab.fxml",800.0,600.0);
               arbolEditar.setApp(this);
            } catch (Exception ex) {
                logger.error(ex.getMessage() + "," + ex.getLocalizedMessage());
            }
        }
       
       public void gotoHuertaEditar(){
           try {
               HuertaEditarController huertaEditar;
               huertaEditar = (HuertaEditarController) replaceSceneContent("huertaEditarTab.fxml",800.0,600.0);
               huertaEditar.setApp(this);
            } catch (Exception ex) {
                logger.error(ex.getMessage() + "," + ex.getLocalizedMessage());
            }
       }
       
       public void gotoAsesoriaTecnica(){
            try {
               AsesoriaTecnicaController asesoria = (AsesoriaTecnicaController) replaceSceneContent("asesoriaTecnica.fxml",800.0,600.0);
               asesoria.setApp(this);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
       }
       
       
      
      private Initializable replaceSceneContent(String fxml, Double width, Double height) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
       
        width = stage.getWidth();
        height= stage.getHeight();

        Scene scene = new Scene(page, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }
    
     
    public static void prepareLogging() {
        /* File loggingConfigurationFile = new File("mylogging.properties");
         if(!loggingConfigurationFile.exists()) {
                 Writer output = null;
                try {
                    output = new BufferedWriter(new FileWriter(loggingConfigurationFile));
                    Properties logConf = new Properties();
                    logConf.setProperty("handlers",
           "java.util.logging.FileHandler,"+
           "java.util.logging.ConsoleHandler");
        logConf.setProperty(".level", "INFO");
        logConf.setProperty(
           "java.util.logging.ConsoleHandler.level",
           "INFO");
        logConf.setProperty(
           "java.util.logging.ConsoleHandler.formatter",
           "java.util.logging.SimpleFormatter");
        logConf.setProperty(
           "java.util.logging.FileHandler.level",
           "ALL");
        logConf.setProperty(
           "java.util.logging.FileHandler.pattern",
           "log/application.log");
        logConf.setProperty(
           "java.util.logging.FileHandler.limit",
           "50000");
        logConf.setProperty(
           "java.util.logging.FileHandler.count", "1");
        logConf.setProperty(
           "java.util.logging.FileHandler.formatter",
           "java.util.logging.XMLFormatter");
        logConf.store(output, "Generated");
                }catch(Exception ex){
                    logger.warn("Logging configuration file not created", ex);
                }
                finally {
                    try {
                        output.close();
                     }
                     catch (IOException ex) {
                        logger.warn(
                           "Problems to save " +
                           "the logging configuration file in the disc",
                           ex);
                     }
                }
        
         }
         
         Properties prop = System.getProperties();
         prop.setProperty("java.util.logging.config.file","mylogging.properties");
         **/
        
        File logDir = new File("log");
    if(!logDir.exists()) {
       logger.info("Creating the logging directory");
       logDir.mkdir();
    }
        
       InputStream  is = null;
        try { 
            is = Main.class.getClassLoader().getResourceAsStream("mylogging.properties");
            LogManager manager = LogManager.getLogManager();
            manager.readConfiguration(is);
        } catch (Exception ex) {
           System.out.println(ex);
        }finally{
            try {
                is.close();
            } catch (Exception ex) {
                 System.out.println(ex);
            }
        }
        
    } 
}

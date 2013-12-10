/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.control;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectorConfig;
import mx.com.ctc.aztec.utils.AztecException;
import mx.com.ctc.aztec.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class ConexionSF {
    public EnterpriseConnection connection;
    public static  String user;
    public static  String pwd;
    public static String ambiente;
    public static String token;
    static Logger logger =  LoggerFactory.getLogger(ConexionSF.class);
    public static final String END_POINT_PRODUCCION = "https://login.salesforce.com/services/Soap/c/29.0";
    public static final String END_POINT_SANDBOX = "https://test.salesforce.com/services/Soap/c/29.0";
    
    public ConexionSF(){
    
    }
   
    public void conectar() throws AztecException{
        try{
            ConnectorConfig config = new ConnectorConfig();
	    config.setUsername(user);
	    config.setPassword(pwd + token);
            //config.setSessionId(user);
            // connection.getSessionHeader().getSessionId();
            if(ambiente.equals("Sandbox")){
                config.setAuthEndpoint(END_POINT_SANDBOX);
            }else{
                config.setAuthEndpoint(END_POINT_PRODUCCION);
            }
            connection = Connector.newConnection(config);

        }catch(Exception e){
            logger.error(e.getLocalizedMessage() + ", " +e.getMessage());
            throw new AztecException(Constantes.MSG_ERROR_USUARIO);
        }
    }
    
}

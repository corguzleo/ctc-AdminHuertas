/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.dao;

import java.sql.PreparedStatement;
import mx.com.ctc.aztec.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class UsuarioDAO extends Conexion{
    private String sqry = "";
    static Logger loggerUsuario =  LoggerFactory.getLogger(RevisionDAO.class);
    
        public void update(Usuario usuario){
            conectar();
            try{
                sqry = "UPDATE Usuario SET User = ?, Password = ? , UserSF = ?, PasswordSF = ?, Token  = ? , GuardarSF = ? " +
                        "WHERE Id = ?";
                PreparedStatement prep = conexion.prepareStatement(sqry);
                prep.setString(1,usuario.getUser());
                prep.setString(2,usuario.getPassword());
                prep.setString(3,usuario.getUsersf());
                prep.setString(4,usuario.getPasswordsf());
                prep.setString(5,usuario.getToken());
                prep.setString(6,usuario.getGuardarsf());
                prep.setString(7,usuario.getId());
                   
                conexion.setAutoCommit(false);
                prep.executeUpdate();
                conexion.setAutoCommit(true);
                
                
            }catch(Exception e){
             loggerUsuario.error(e.getLocalizedMessage() + "," + e.getMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerUsuario.error(e.getLocalizedMessage() + "," + e.getMessage());
             }  
         }
        }
}

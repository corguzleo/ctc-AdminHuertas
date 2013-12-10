/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.service;

import java.sql.ResultSet;
import mx.com.ctc.aztec.dao.Conexion;
import mx.com.ctc.aztec.model.Usuario;
import mx.com.ctc.aztec.utils.AztecException;
import mx.com.ctc.aztec.utils.Constantes;

/**
 *
 * @author OSCAR
 */
public class UserServicio extends Conexion{
    String sqry = "";
    boolean conectado = false;
    static public Usuario usuarioConectado;
    public void Login(String user, String pwd) throws AztecException{
        conectar();
        try{
            sqry = "SELECT * FROM Usuario WHERE User = '" + user + "' AND Password = '" + pwd  +"'";
            ResultSet rs = statement.executeQuery(sqry);
            while ( rs.next() ) {
                usuarioConectado = new Usuario(rs.getString("Id"),rs.getString("user"),rs.getString("password"),rs.getString("usersf"),
                                                rs.getString("passwordsf"),rs.getString("token"),rs.getString("guardarsf"));
                conectado = true;
            }
            if(!conectado){
                throw new AztecException(Constantes.MSG_ERROR_USUARIO);
            }
            
        }catch(Exception e){
            throw new AztecException(e.getMessage());
        }
        
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.service;

import mx.com.ctc.aztec.control.ConexionSF;
import mx.com.ctc.aztec.dao.UsuarioDAO;
import mx.com.ctc.aztec.model.Usuario;
import mx.com.ctc.aztec.utils.AztecException;

/**
 *
 * @author OSCAR
 */
public class LoginServicio extends ConexionSF{
    private UsuarioDAO dao = new UsuarioDAO();
    public  void login(String user, String pwd,String token, String ambiente) throws AztecException{
        ConexionSF.user = user;
        ConexionSF.pwd = pwd;
        ConexionSF.token = token;
        ConexionSF.ambiente = ambiente;
        conectar();
    }
    
    public void actulizarUsuario(Usuario usuario){
        dao.update(usuario);
    }
}

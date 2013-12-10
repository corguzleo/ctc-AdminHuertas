/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.model;

/**
 *
 * @author OSCAR
 */
public class Usuario {
   
    private String id;
    private String user;
    private String password;
    private String usersf;
    private String passwordsf;
    private String token;
    private String guardarsf;

    public Usuario(){
    
    }
    
    public Usuario(String id, String user, String password, String usersf,String passwordsf, String token, String guardarsf) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.usersf = usersf;
        this.passwordsf = passwordsf;
        this.token = token;
        this.guardarsf = guardarsf;
    }
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordsf() {
        return passwordsf;
    }

    public void setPasswordsf(String passwordsf) {
        this.passwordsf = passwordsf;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGuardarsf() {
        return guardarsf;
    }

    public void setGuardarsf(String guardarsf) {
        this.guardarsf = guardarsf;
    }

    public String getUsersf() {
        return usersf;
    }

    public void setUsersf(String usersf) {
        this.usersf = usersf;
    }

}

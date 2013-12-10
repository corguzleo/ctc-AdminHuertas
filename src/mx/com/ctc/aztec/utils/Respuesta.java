/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OSCAR
 */
public class Respuesta {
    private String mensaje;
    private  String id;
    private List<String> ids;
    
    public Respuesta(){
        this.mensaje = "";
        this.id = "";
        this.ids = new ArrayList();
    }

    public Respuesta(String mensaje, String id) {
        this.mensaje = mensaje;
        this.id = id;
        this.ids = new ArrayList();
    }
    
    public Respuesta(String mensaje, String id, List<String> ids) {
        this.mensaje = mensaje;
        this.id = id;
        this.ids = ids;
    }
    
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
    
    
}

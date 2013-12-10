/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.model;

import java.util.Date;

/**
 *
 * @author OSCAR
 */
public class ArbolDiagnostico {
    private Integer id;
    private String idArbol;
    private String IdDiagnostico;
    private Date Fecha;

    public ArbolDiagnostico(Integer id, String idArbol, String IdDiagnostico, Date Fecha) {
        this.id = id;
        this.idArbol = idArbol;
        this.IdDiagnostico = IdDiagnostico;
        this.Fecha = Fecha;
    }

    
    public ArbolDiagnostico(String idArbol, String IdDiagnostico, Date Fecha) {
        this.idArbol = idArbol;
        this.IdDiagnostico = IdDiagnostico;
        this.Fecha = Fecha;
    }
    
  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdArbol() {
        return idArbol;
    }

    public void setIdArbol(String idArbol) {
        this.idArbol = idArbol;
    }

    public String getIdDiagnostico() {
        return IdDiagnostico;
    }

    public void setIdDiagnostico(String IdDiagnostico) {
        this.IdDiagnostico = IdDiagnostico;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    
    
}

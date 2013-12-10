/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.model;

/**
 *
 * @author OSCAR
 */
public class DiagnosticoCatalogo {
    private String id;
    private String subtipo;
    private String diagnostico;

    public DiagnosticoCatalogo(String id, String subtipo, String diagnostico) {
        this.id = id;
        this.subtipo = subtipo;
        this.diagnostico = diagnostico;
    }
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    @Override
    public String toString(){
        return this.getDiagnostico();
    }
    
    
}

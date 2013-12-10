/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.model;

/**
 *
 * @author OSCAR
 */
public class Diagnostico {
    private String id;
    private String idTemporal;
    private String idRevision;
    private String idArbol;
    private String idHuerta;
    private String diagnostico;
    private String agenteCausal;
    private String gradoAfeccion;
    private String sitioAfeccion;
    private String tipoInsercion;
    private String tipo;
    private String subtipo;
            

    public Diagnostico(String id, String idRevision, String idArbol, String diagnostico, String agenteCausal, String gradoAfeccion, String sitioAfeccion,String idHuerta, String tipoInsercion,String tipo,String subtipo,String idTemporal) {
        this.id = id;
        this.idRevision = idRevision;
        this.idArbol = idArbol;
        this.diagnostico = diagnostico;
        this.agenteCausal = agenteCausal;
        this.gradoAfeccion = gradoAfeccion;
        this.sitioAfeccion = sitioAfeccion;
        this.idHuerta = idHuerta;
        this.tipoInsercion = tipoInsercion;
        this.tipo = tipo;
        this.subtipo = subtipo;
        this.idTemporal = idTemporal;
    }

    public Diagnostico() {
       
    }
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(String idRevision) {
        this.idRevision = idRevision;
    }

    public String getIdArbol() {
        return idArbol;
    }

    public void setIdArbol(String idArbol) {
        this.idArbol = idArbol;
    }

    public String getAgenteCausal() {
        return agenteCausal;
    }

    public void setAgenteCausal(String agenteCausal) {
        this.agenteCausal = agenteCausal;
    }

    
    public String getGradoAfeccion() {
        return gradoAfeccion;
    }

    public void setGradoAfeccion(String gradoAfeccion) {
        this.gradoAfeccion = gradoAfeccion;
    }

    public String getSitioAfeccion() {
        return sitioAfeccion;
    }

    public void setSitioAfeccion(String sitioAfeccion) {
        this.sitioAfeccion = sitioAfeccion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getIdHuerta() {
        return idHuerta;
    }

    public void setIdHuerta(String idHuerta) {
        this.idHuerta = idHuerta;
    }

    public String getTipoInsercion() {
        return tipoInsercion;
    }

    public void setTipoInsercion(String tipoInsercion) {
        this.tipoInsercion = tipoInsercion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(String idTemporal) {
        this.idTemporal = idTemporal;
    }
    
    

}

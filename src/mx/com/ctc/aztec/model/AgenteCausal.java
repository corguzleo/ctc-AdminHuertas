/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.model;

/**
 *
 * @author OSCAR
 */
public class AgenteCausal {
   private String id;
   private String diagnostico;
   private String agente;

    public AgenteCausal(String id, String diagnostico, String agente) {
        this.id = id;
        this.diagnostico = diagnostico;
        this.agente = agente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }
   
    @Override
    public String toString(){
      return this.agente;
    }
}

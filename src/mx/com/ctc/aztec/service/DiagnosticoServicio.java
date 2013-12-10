/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.service;

import java.util.List;
import mx.com.ctc.aztec.dao.DiagnosticoDAO;
import mx.com.ctc.aztec.model.Diagnostico;

/**
 *
 * @author OSCAR
 */
public class DiagnosticoServicio {
    private DiagnosticoDAO dao = new DiagnosticoDAO();
    public Integer insert(Diagnostico diagnostico){
        return dao.insert(diagnostico);
    }
    
    public Integer insert(String idRevision, String idArbol, String diagnsotico,String agenteCausal,String gradoAfeccion, String sitioAfeccion,String idHuerta,String tipoInsercion,String tipo,String subtipo){
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdArbol(idArbol);
        diagnostico.setDiagnostico(diagnsotico);
        diagnostico.setIdRevision(idRevision);
        diagnostico.setAgenteCausal(agenteCausal);
        diagnostico.setGradoAfeccion(gradoAfeccion);
        diagnostico.setSitioAfeccion(sitioAfeccion);
        diagnostico.setIdHuerta(idHuerta);
        diagnostico.setTipoInsercion(tipoInsercion);
        diagnostico.setTipo(tipo);
        diagnostico.setSubtipo(subtipo);
        return dao.insert(diagnostico);
    }
    public void save(Diagnostico diagnostico){
        dao.update(diagnostico);
    }
    
    public List<Diagnostico> getUltimosDiagnosticos(String idHuerta){
        String sQuery = "WHERE IdHuerta = '" + idHuerta + "' AND TipoInsercion = 'M' AND Tipo = 'Árbol' ORDER BY fechaModificacion DESC LIMIT 5  ";
        return dao.select(sQuery);
    }
    
    public List<Diagnostico> getDiagnosticoByRevision(String idRevision){
        String sQuery = "WHERE IdRevision = '" + idRevision + "'";
        return dao.select(sQuery);
    }
    
    public List<Diagnostico> select(String sQuery){
        return dao.select(sQuery);
    }
    
    public void deleteDiagnostico(String sQuery){
        dao.delete(sQuery);
    }
}

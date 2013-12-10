/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.control;

import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.Diagnostico__c;
import com.sforce.ws.ConnectionException;
import java.util.ArrayList;
import java.util.List;
import mx.com.ctc.aztec.model.Diagnostico;
import mx.com.ctc.aztec.service.DiagnosticoServicio;
import mx.com.ctc.aztec.utils.AztecException;
import mx.com.ctc.aztec.utils.Constantes;
import mx.com.ctc.aztec.utils.Respuesta;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class DignosticoAcciones extends ConexionSF{
    private DiagnosticoServicio ds = new DiagnosticoServicio();
    public String idDiagnostico = null;
    static Logger loggerDA =  LoggerFactory.getLogger(HuertaAcciones.class);
    
    public Respuesta insertDiagnosticosSF(List<Diagnostico> diagnosticos){
        Respuesta respuesta = new Respuesta(Constantes.MSG_EXITO_GUARDADO,"");
        Diagnostico__c[] records = new Diagnostico__c[diagnosticos.size()];
        try{
            conectar();
            Integer i = 0;
            for(Diagnostico diagnostico:diagnosticos){
                Diagnostico__c diagnosticoSF = new Diagnostico__c();
                if(diagnostico.getTipo().equals("Árbol")){
                    diagnosticoSF.setArbolParent__c(diagnostico.getIdArbol());
                }else if(diagnostico.getTipo().equals("Huerta")){
                    diagnosticoSF.setHuertaParent__c(diagnostico.getIdHuerta());
                }
                diagnosticoSF.setRevisionParent__c(diagnostico.getIdRevision());
                
                if(!Util.isNull(diagnostico.getId())){
                    diagnosticoSF.setId(diagnostico.getId());
                }
                
                diagnosticoSF.setTipo__c(diagnostico.getTipo());
                diagnosticoSF.setSubTipo__c(diagnostico.getSubtipo());
                diagnosticoSF.setNombreComunEnfermedad__c(diagnostico.getDiagnostico());
                diagnosticoSF.setAgenteCausal__c(diagnostico.getAgenteCausal());
                diagnosticoSF.setGradoInfeccion__c(diagnostico.getGradoAfeccion());
                diagnosticoSF.setSitioAfectacion__c(diagnostico.getSitioAfeccion());
                records[i] = diagnosticoSF;
                i++;
            }
            UpsertResult[] saveResults = connection.upsert("Id", records);
            Integer j = 0;
            for (UpsertResult result : saveResults) {
                  if(result.getSuccess()){
                       respuesta.getIds().add(result.getId());
                       if(result.isCreated()){
                           diagnosticos.get(j).setId(result.getId());
                           savaDiagnostioco( diagnosticos.get(j));
                       }
                  }else{
                      respuesta.setMensaje("Error, revisar registro");
                      loggerDA.error("ERROR creando registro:" + result.getErrors()[0].getMessage());
                  }
                  j++;
            }
        }catch(AztecException | ConnectionException e){
             loggerDA.error(e.getLocalizedMessage() + "," + e.getMessage());
        } 
        return respuesta;
    }
    public String insertDiagnostio(Diagnostico diagnostico){
        String respuesta = Constantes.MSG_EXITO_GUARDADO;
        if(Util.isNull(diagnostico.getIdRevision())){
            respuesta = "No se encuentra  idRevision";
        }else if(Util.isNull(diagnostico.getIdArbol())){
            respuesta = "No se encuenta idArbol";
        }else if(Util.isNull(diagnostico.getDiagnostico())){
            respuesta = "No se encuentra diagnostico";
        }else if(Util.isNull(diagnostico.getSitioAfeccion())){
            respuesta = "No se encuentra sitio de afección";
        }else if(Util.isNull(diagnostico.getGradoAfeccion())){
            respuesta = "No se encuentra grado de afección";
        }else{
            ds.insert(diagnostico);
        }
        return respuesta;
    }
    
    public String insertDiagnostico(String idRevision, String idArbol, String diagnostico,String agenteCausal,String gradoAfeccion, String sitioAfeccion,String idHuerta,String tipoInsercion,String tipo,String subtipo){
        String respuesta = Constantes.MSG_EXITO_GUARDADO;
        if(Util.isNull(idRevision)){
            respuesta = "No se encuentra  idRevision";
        }else if(Util.isNull(diagnostico)){
            respuesta = "No se encuentra diagnostico";
        }else if(Util.isNull(sitioAfeccion)){
            respuesta = "No se encuentra sitio de afección";
        }else if(Util.isNull(gradoAfeccion)){
            respuesta = "No se encuentra grado de afección";
        }else{
            ds.insert(idRevision, idArbol, diagnostico, agenteCausal, gradoAfeccion, sitioAfeccion,idHuerta,tipoInsercion,tipo,subtipo);
        }
        return respuesta;
    }
    
    public List<Diagnostico> getLastDiagnoticos(String idHuerta){
       return ds.getUltimosDiagnosticos(idHuerta);
    }
    
    public Diagnostico getDiagnostico(String idRevision){
       Diagnostico d = null;
       if(ds.getDiagnosticoByRevision(idRevision).size() > 0){
            d = ds.getDiagnosticoByRevision(idRevision).get(0);
       }
       return d;
    }
    
    public List<Diagnostico> getDiagnosticosRevisiones(List<String> idRevisiones){
            List<Diagnostico> diagnosticos = new ArrayList<>();
            if(!idRevisiones.isEmpty()){
                String ids = "";
                for(String id:idRevisiones){
                    ids = ids + "'"+ id + "',";
                }
                ids = ids.substring(0,ids.length()-1);
                String sQuery = " WHERE IdRevision IN (" + ids +")";
                diagnosticos = ds.select(sQuery);
            }
            return diagnosticos;
    }
    
    public String savaDiagnostioco(Diagnostico diagnostico){
        String respuesta = Constantes.MSG_EXITO_GUARDADO;
        if(Util.isNull(diagnostico.getIdRevision())){
            respuesta = "No se encuentra  idRevision";
        }else if(Util.isNull(diagnostico.getDiagnostico())){
            respuesta = "No se encuentra diagnostico";
        }else if(Util.isNull(diagnostico.getSitioAfeccion())){
            respuesta = "No se encuentra sitio de afección";
        }else if(Util.isNull(diagnostico.getGradoAfeccion())){
            respuesta = "No se encuentra grado de afección";
        }else{
            ds.save(diagnostico);
        }
        return respuesta;
    }
    
    public void deleteDiagnosticosXHuerta(String idHuerta){
        String sQuery = " IdHuerta = '" + idHuerta +"'";
        ds.deleteDiagnostico(sQuery);
    }
}

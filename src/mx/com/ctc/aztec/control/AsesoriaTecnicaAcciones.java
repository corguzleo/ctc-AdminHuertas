/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.control;

import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.AsesoriaTecnica__c;
import com.sforce.ws.ConnectionException;
import java.util.Calendar;
import mx.com.ctc.aztec.model.AsesoriaTecnica;
import mx.com.ctc.aztec.service.AsesoriaTecnicaServicio;
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
public class AsesoriaTecnicaAcciones extends ConexionSF{
    AsesoriaTecnicaServicio as = new AsesoriaTecnicaServicio();
    static Logger loggerATA =  LoggerFactory.getLogger(AsesoriaTecnicaAcciones.class);
    
    public AsesoriaTecnica getAsesoriaAPPxHuerta (String idHuerta){
        AsesoriaTecnica asesoria = null;
        for(AsesoriaTecnica at : as.selectAsesoriaHuerta(idHuerta)){
            asesoria = at;
        }
        return asesoria;
    }
    
    public void deleteAsesoriaAPP(AsesoriaTecnica at){
        as.deleteAsesoria(at);
    }
    
    public Respuesta insertAsesoriaSF(AsesoriaTecnica at){
        Respuesta respuesta = new Respuesta();
        String mensaje = Constantes.MSG_EXITO_GUARDADO;
        respuesta = new Respuesta(mensaje,"");
        AsesoriaTecnica__c[] records = new AsesoriaTecnica__c[1];
        try{
            conectar();
            SaveResult[] saveResults; 
            AsesoriaTecnica__c asesoria = new AsesoriaTecnica__c();
            asesoria.setHuertaParent__c(at.getIdHuerta());
            asesoria.setFolio__c(at.getFolio());
            asesoria.setInsectosBeneficosDetectados__c(at.getInsectosBeneficos());
            asesoria.setBasura__c(at.getBasura());
            asesoria.setComentariosLimpieza__c(at.getComentariosLimpieza());
            asesoria.setObservacionesAsesorTecnico__c(at.getObservacionesAsesor());
            Calendar fecha = Util.stringToCalendar(at.getFechaAsesoria());
            asesoria.setFechaAsesorTecnica__c(fecha);
            if(!Util.isNull(at.getId())){
                asesoria.setId(at.getId());
            }
            records[0] = asesoria;
            if(Util.isNull(at.getId())){
               saveResults = connection.create(records);
            }else{
                saveResults = connection.update(records);
            }
            for (int i=0; i< saveResults.length; i++) {
                 if (saveResults[i].isSuccess()) {
                     respuesta.setId(saveResults[i].getId());
                     at.setId(saveResults[i].getId());
                     as.updateAsesoria(at);
                 }else{
                    mensaje = "Error, revisar registro.";
                    com.sforce.soap.enterprise.Error[] errors = saveResults[i].getErrors();
                    for (int j=0; j< errors.length; j++) {
                    loggerATA.error("ERROR creando registro: " + errors[j].getMessage());
                    }
                 }
            }
        }catch(AztecException | NumberFormatException | ConnectionException e){
            mensaje = "Error, revisar registro.";
            loggerATA.error(e.getMessage() + "," + e.getLocalizedMessage());
           
        }
        respuesta.setMensaje(mensaje);
        return respuesta;
    }
}

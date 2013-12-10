    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.control;

import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.Revision__c;
import com.sforce.ws.ConnectionException;
import java.util.ArrayList;
import java.util.List;
import mx.com.ctc.aztec.model.AsesoriaTecnica;
import mx.com.ctc.aztec.model.Diagnostico;
import mx.com.ctc.aztec.model.Revision;
import mx.com.ctc.aztec.service.RevisionServicio;
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
public class RevisionAcciones extends ConexionSF{
    private RevisionServicio rs = new RevisionServicio();
    private DignosticoAcciones da = new DignosticoAcciones();
    private String str = "";
    static Logger loggerRA =  LoggerFactory.getLogger(HuertaAcciones.class);
    public Integer idRevision = null;
    public Revision__c getRevisionSF (String idRevision){
        Revision__c revision = null;
        str = "SELECT Id, OwnerId, IsDeleted, Name, RecordTypeId, CreatedDate, CreatedById, LastModifiedDate, LastModifiedById, " +
                         " SystemModstamp, PocaHoja__c, Amarillo__c, Granizada__c, Helada__c, HojaChica__c, HojasManchadasPigmentadas__c, Otro__c, " +
                         " Pigmeo__c, Seco__c, Arbol__c, Huerta__c, AsesoriaTecnica__c, Hueco__c, FechaDiagnostico__c, FechaRevisionFormula__c, AgallasHojas__c," +
                         " BrotesDanados__c, CaidaFruto__c, CaidaHojas__c, CaidaRamas__c, CortezaDanada__c, Defoliacion__c, FloracionRetrasada__c, " +
                         " FrutoMalformacion__c, HojasAmarillas__c, HojasChinas__c, HojasCloroticas__c, HojasSecas__c, HojasVerdeAmarillento__c, "+
                         " HojasVerdeOscuro__c, HuesoBarrenado__c, LesionesSol__c, ManchasCorchosas__c, MarcasHojasFrutosTallos__c, Necrosis__c, OxidacionHojas__c, " +
                         " PocoAmarre__c, PudricionFruto__c, PudricionRadicular__c, Tumores__c FROM Revision__c " +
                         " WHERE Id = '" + idRevision + "'";   
         try{
            conectar();
            if(connection != null){
                 QueryResult queryResults = connection.query(str);
                 if (queryResults.getSize() > 0) {
                     for (int i = 0; i < queryResults.getRecords().length; i++) {
                          revision = (Revision__c)queryResults.getRecords()[i];
                          
                     }
                 }
            }
        }catch(AztecException | ConnectionException e){
            loggerRA.error(e.getMessage());
        }
        return revision;
    } 
    public Respuesta insertRevisionSF(List<Revision> revisiones, AsesoriaTecnica asesoria){
        Respuesta respuesta = new Respuesta(Constantes.MSG_EXITO_GUARDADO,"");
        Revision__c[] records = new Revision__c[revisiones.size()];
        try{
             conectar();
             Integer i = 0;
             for(Revision revision:revisiones){
                 Revision__c revisionSF = new Revision__c();
                 revisionSF.setAsesoriaTecnicaParent__c(asesoria.getId());
                 if(!Util.isNull(revision.getIdArbol())){
                        revisionSF.setArbolParent__c(revision.getIdArbol());
                 }else{
                    revisionSF.setHuertaParent__c(revision.getIdHuerta());
                 }
        
                 if(!Util.isNull(revision.getId())){
                    revisionSF.setId(revision.getId());
                 }
                 revisionSF.setAgallasHojas__c(Boolean.parseBoolean(revision.getAgallasHojas()));
                 revisionSF.setBrotesDanados__c(Boolean.parseBoolean(revision.getBrotesDanados()));
                 revisionSF.setCaidaFruto__c(Boolean.parseBoolean(revision.getCaidaFrutos()));
                 revisionSF.setCaidaHojas__c(Boolean.parseBoolean(revision.getCaidaHojas()));
                 revisionSF.setCaidaRamas__c(Boolean.parseBoolean(revision.getCaidaRamas()));
                 revisionSF.setCortezaDanada__c(Boolean.parseBoolean(revision.getCortezaDanada()));
                 revisionSF.setDefoliacion__c(Boolean.parseBoolean(revision.getDefoliacion()));
                 revisionSF.setFloracionRetrasada__c(Boolean.parseBoolean(revision.getFloracionRetrasada()));
                 revisionSF.setFrutoMalformacion__c(Boolean.parseBoolean(revision.getFrutoMalformacion()));
                 revisionSF.setGranizada__c(Boolean.parseBoolean(revision.getGranizada()));
                 revisionSF.setHelada__c(Boolean.parseBoolean(revision.getHelada()));
                 revisionSF.setHojaChica__c(Boolean.parseBoolean(revision.getHojaChica()));
                 revisionSF.setHojasAmarillas__c(Boolean.parseBoolean(revision.getHojasAmarillas()));
                 revisionSF.setHojasChinas__c(Boolean.parseBoolean(revision.getHojasChinas()));
                 revisionSF.setHojasCloroticas__c(Boolean.parseBoolean(revision.getHojasCloroticas()));
                 revisionSF.setHojasManchadasPigmentadas__c(Boolean.parseBoolean(revision.getHojasManchadas()));
                 revisionSF.setHojasSecas__c(Boolean.parseBoolean(revision.getHojasSecas()));
                 revisionSF.setHojasVerdeAmarillento__c(Boolean.parseBoolean(revision.getHojasVerdeAmarillento()));
                 revisionSF.setHojasVerdeOscuro__c(Boolean.parseBoolean(revision.getHojasVerdesOscuro()));
                 revisionSF.setHueco__c(Boolean.parseBoolean(revision.getHueco()));
                 revisionSF.setHuesoBarrenado__c(Boolean.parseBoolean(revision.getHuesoBarrenado()));
                 revisionSF.setLesionesSol__c(Boolean.parseBoolean(revision.getLesionesSol()));
                 revisionSF.setManchasCorchosas__c(Boolean.parseBoolean(revision.getManchasCorchosas()));
                 revisionSF.setMarcasHojasFrutosTallos__c(Boolean.parseBoolean(revision.getMarcasHojasFrutosTallos()));
                 revisionSF.setNecrosis__c(Boolean.parseBoolean(revision.getNecrosis()));
                 revisionSF.setOtro__c(Boolean.parseBoolean(revision.getOtro()));
                 revisionSF.setOxidacionHojas__c(Boolean.parseBoolean(revision.getOxidacionHojas()));
                 revisionSF.setPigmeo__c(Boolean.parseBoolean(revision.getPigmeo()));
                 revisionSF.setPocaHoja__c(Boolean.parseBoolean(revision.getPocaHoja()));
                 revisionSF.setPocoAmarre__c(Boolean.parseBoolean(revision.getPocoAmarre()));
                 revisionSF.setPudricionFruto__c(Boolean.parseBoolean(revision.getPudricionFruto()));
                 revisionSF.setPudricionRadicular__c(Boolean.parseBoolean(revision.getPudricionRadicular()));
                 revisionSF.setSeco__c(Boolean.parseBoolean(revision.getSeco()));
                 revisionSF.setTumores__c(Boolean.parseBoolean(revision.getTumores()));
                 
                 revisionSF.setCenizaVolcanica__c(Boolean.parseBoolean(revision.getCenizaVolcanica()));
                 revisionSF.setFaltaAgua__c(Boolean.parseBoolean(revision.getFaltaAgua()));
                 revisionSF.setIncendio__c(Boolean.parseBoolean(revision.getIncendio()));
                 revisionSF.setPlaga__c(Boolean.parseBoolean(revision.getPlaga()));
                 
                 records[i] = revisionSF;
                 i++;
             }
            UpsertResult[] saveResults = connection.upsert("Id", records);
            Integer j = 0;
            for (UpsertResult result : saveResults) {
                if(result.getSuccess()){
                  respuesta.getIds().add(result.getId());
                  revisiones.get(j).setId(result.getId());
                  saveRevisonAPP(revisiones.get(j));
                  Diagnostico diagnostico = da.getDiagnostico(revisiones.get(j).getIdTemporal());
                  if(result.isCreated()){
                    diagnostico.setIdRevision(result.getId());
                    da.savaDiagnostioco(diagnostico);
                  }
                }else{
                    loggerRA.error("ERROR creando registro:" + result.getErrors()[0].getMessage());
                    respuesta.setMensaje("Error, revisar registro");
                }
                j++;
            }
        }catch(AztecException | ConnectionException e){
            loggerRA.error(e.getLocalizedMessage() + "," + e.getMessage());
        }
         return respuesta;
    }
    
    
    public void  insertRevisionApp(Revision__c revision){
        try{
            if(!Util.isNull(revision)){
                rs.insert(revision.getId(), "", revision.getArbolParent__c(), revision.getHuertaParent__c(),revision.getAsesoriaTecnicaParent__c(), revision.getTumores__c().toString(),revision.getSeco__c().toString(),
                        revision.getPudricionRadicular__c().toString(), revision.getPudricionFruto__c().toString(),revision.getPocoAmarre__c().toString(), revision.getPocaHoja__c().toString(),
                        revision.getPigmeo__c().toString(), revision.getOxidacionHojas__c().toString(),revision.getOtro__c().toString(), revision.getNecrosis__c().toString(), revision.getMarcasHojasFrutosTallos__c().toString(), 
                        revision.getManchasCorchosas__c().toString(), revision.getLesionesSol__c().toString(), revision.getHuesoBarrenado__c().toString(), revision.getHueco__c().toString(), revision.getHojasVerdeOscuro__c().toString(), 
                        revision.getHojasVerdeAmarillento__c().toString(), revision.getHojasSecas__c().toString(), revision.getHojasSecas__c().toString(), revision.getHojasCloroticas__c().toString(), revision.getHojaChica__c().toString(), 
                        revision.getHelada__c().toString(),revision.getGranizada__c().toString(), revision.getFrutoMalformacion__c().toString(), revision.getFloracionRetrasada__c().toString(), revision.getDefoliacion__c().toString(), 
                        revision.getCortezaDanada__c().toString(),revision.getCaidaRamas__c().toString(), revision.getCaidaHojas__c().toString(), revision.getCaidaFruto__c().toString(), revision.getBrotesDanados__c().toString(),
                        revision.getAmarillo__c().toString(), revision.getAgallasHojas__c().toString(),revision.getHojasChinas__c().toString(),revision.getHojasAmarillas__c().toString(),
                        revision.getCenizaVolcanica__c().toString(),revision.getFaltaAgua__c().toString(),revision.getIncendio__c().toString(),revision.getPlaga__c().toString());
            }
        }catch(Exception e){
             loggerRA.error(e.getMessage());
        }
    }
    
    public List<Revision> getRevisonesApp(String idArbol){
         return rs.selectAll(idArbol);
    }
    
    public List<Revision> getRevisonesAppHuerta(String idHuerta){
        String sQuery = "IdHuerta = '" + idHuerta + "' AND idArbol = '' ";
        return rs.select(sQuery);
    }
     
     
    public String saveRevisonAPP (Revision revision){
          String respuesta;
           if(Util.isNull(revision.getIdHuerta())){
                respuesta = "No se encuentra idHuerta";
            }else if(Util.isNull(revision.getIdAsesoriaTecnica())){
                respuesta = "No se encuentra idAsesoriaTecnica";
            }else{
                respuesta =  rs.update(revision);
            }
          return respuesta;
    }
    
    public String insertRevision(Revision revision){
        String respuesta = "";
        
            if(Util.isNull(revision.getIdHuerta())){
                respuesta = "No se encuentra idHuerta";
            }else if(Util.isNull(revision.getIdAsesoriaTecnica())){
                respuesta = "No se encuentra idAsesoriaTecnica";
            }else{
                respuesta = Constantes.MSG_EXITO_GUARDADO;
                idRevision = rs.insert(revision);
            }
        return respuesta;
    }
    
    public String insertRevision(String id, String idTemporal, String idArbol, String idHuerta,String idAsesoriaTecnica, Boolean tumores, Boolean seco, Boolean pudricionRadicular, Boolean pudricionFruto, Boolean pocoAmarre, Boolean pocaHoja,
                       Boolean pigmeo, Boolean oxidacionHojas, Boolean otro, Boolean necrosis, Boolean marcasHojasFrutosTallos, Boolean manchasCorchosas, Boolean lesionesSol, Boolean huesoBarrenado, Boolean hueco, Boolean hojasVerdesOscuro,
                       Boolean hojasVerdeAmarillento, Boolean hojasSecas, Boolean hojasManchadas, Boolean hojasCloroticas, Boolean hojaChica, Boolean helada, Boolean granizada, Boolean frutoMalformacion, Boolean floracionRetrasada, Boolean defoliacion, 
                       Boolean cortezaDanada, Boolean caidaRamas, Boolean caidaHojas, Boolean caidaFrutos, Boolean brotesDanados, Boolean amarillo, Boolean agallasHojas,Boolean hojasChinas,Boolean hojasAmarillas,
                       Boolean ceniza, Boolean faltaAgua, Boolean incendio, Boolean plaga){
    
        String respuesta = "";
            if(Util.isNull(idHuerta)){
                respuesta = "No se encuentra idHuerta";
            }else if(Util.isNull(idAsesoriaTecnica)){
                respuesta = "No se encuentra idAsesoriaTecnica";
            }else{
                respuesta = Constantes.MSG_EXITO_GUARDADO;
                idRevision = rs.insert(id, idTemporal, idArbol, idHuerta, idAsesoriaTecnica, String.valueOf(tumores), String.valueOf(seco),String.valueOf(pudricionRadicular), String.valueOf(pudricionFruto),
                            String.valueOf(pocoAmarre), String.valueOf(pocaHoja),String.valueOf(pigmeo), String.valueOf(oxidacionHojas), String.valueOf(otro),String.valueOf(necrosis),
                            String.valueOf(marcasHojasFrutosTallos), String.valueOf(manchasCorchosas),String.valueOf(lesionesSol), String.valueOf(huesoBarrenado),
                            String.valueOf(hueco), String.valueOf(hojasVerdesOscuro),String.valueOf(hojasVerdeAmarillento), String.valueOf(hojasSecas), String.valueOf(hojasManchadas), String.valueOf(hojasCloroticas), 
                            String.valueOf(hojaChica), String.valueOf(helada), String.valueOf(granizada), String.valueOf(frutoMalformacion), String.valueOf(floracionRetrasada), 
                            String.valueOf(defoliacion), String.valueOf(cortezaDanada), String.valueOf(caidaRamas), String.valueOf(caidaHojas), String.valueOf(caidaFrutos),String.valueOf(brotesDanados), 
                            String.valueOf(amarillo), String.valueOf(agallasHojas),String.valueOf(hojasChinas),String.valueOf(hojasAmarillas),
                            String.valueOf(ceniza), String.valueOf(faltaAgua), String.valueOf(incendio), String.valueOf(plaga));
            }
        return respuesta;
    }
    
    public Revision getRevision(String idTemporal,String id){
        List<Revision> revisiones;
        Revision revision = null;
        String sQuery = !Util.isNull(idTemporal) ? "IdTemporal = '" +idTemporal + "'" : "Id = '" +id + "'";
        revisiones = rs.select(sQuery);
        if(revisiones != null){
            if(!revisiones.isEmpty()){
                revision = revisiones.get(0);
            }
        }
        return revision;
    }
    
    public Revision getRevisionAPPxHuerta (String idHuerta){
        List<Revision> revisiones;
        Revision revision = null;
        revisiones = rs.select("idHuerta = '" +idHuerta + "'");
        if(revisiones != null){
            if(!revisiones.isEmpty()){
                revision = revisiones.get(0);
            }
        }
        return revision;
    }
    
    public Revision getRevisionHuerta (String idHuerta){
        List<Revision> revisiones;
        Revision revision = null;
        revisiones = rs.select("idHuerta = '" +idHuerta + "' AND idArbol =''");
        if(revisiones != null){
            if(!revisiones.isEmpty()){
                revision = revisiones.get(0);
            }
        }
        return revision;
    }
    
    public List<Revision> getRevisionesArboles(List<String> idArboles){
            List<Revision> revisiones = new ArrayList<>();
            if(!idArboles.isEmpty()){
                String ids = "";
                for(String id:idArboles){
                    ids = ids + "'"+ id + "',";
                }
                ids = ids.substring(0,ids.length()-1);
                String sQuery = "IdArbol IN (" + ids +")";
                revisiones = rs.select(sQuery);
            }
            return revisiones;
    }
    
    public void deleteRevisionApp(Revision revision){
        rs.delete(revision);
    }
    
     public void deleteRevisionApp(String idHuerta){
         rs.delete(idHuerta);
     }
}

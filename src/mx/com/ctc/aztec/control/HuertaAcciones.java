/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.control;

import java.util.ArrayList;
import java.util.List;
import mx.com.ctc.aztec.model.Huerta;
import mx.com.ctc.aztec.service.HuertaServicio;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Huerta__c;
import com.sforce.ws.ConnectionException;
import mx.com.ctc.aztec.utils.AztecException;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class HuertaAcciones extends ConexionSF{
    private HuertaServicio hs = new HuertaServicio();
    private String str = null;
    static Logger loggerHA =  LoggerFactory.getLogger(HuertaAcciones.class);
    
  
    public HuertaAcciones(){
        
    }
    
     /*
     * Metodos para interactuar con la base de datos
     * de Salesforce
     */
    public List<Huerta__c> getHuertasSF(){
        
        List<Huerta__c> huertas = new ArrayList<>();
        str = "SELECT Id,Name,HuertaParent__c,ProductorParent__c,ProductorParent__r.Name,MunicipioParent__c,MunicipioParent__r.Name,Estado__c, RecordType.Name " +
               " FROM Huerta__c ORDER BY Name ASC";
        try{
            conectar();
            if(connection != null){
                 QueryResult queryResults = connection.query(str);
                 if (queryResults.getSize() > 0) {
                     for (int i = 0; i < queryResults.getRecords().length; i++) {
                          Huerta__c huertaSF = (Huerta__c)queryResults.getRecords()[i];
                          huertas.add(huertaSF);
                     }
                 }
            }
        }catch(AztecException | ConnectionException e){
            loggerHA.error(e.getMessage());
        }
        return huertas;
    }
    
    public List<Huerta__c> getZonasSF(String huertaId){
         List<Huerta__c> huertas = new ArrayList<>();
         str = "SELECT Id,Name,HuertaParent__c,ProductorParent__c,ProductorParent__r.Name,MunicipioParent__c,MunicipioParent__r.Name,Estado__c, RecordType.Name" +
               " FROM Huerta__c WHERE HuertaParent__c = '" + huertaId + "' ORDER BY Name ASC";
        try{
            if(connection != null){
                 QueryResult queryResults = connection.query(str);
                 if (queryResults.getSize() > 0) {
                     for (int i = 0; i < queryResults.getRecords().length; i++) {
                          Huerta__c huertaSF = (Huerta__c)queryResults.getRecords()[i];
                          huertas.add(huertaSF);
                     }
                 }
            }
        }catch(Exception e){
            loggerHA.error(e.getLocalizedMessage());
        }
        return huertas;
    }
    
    public Huerta__c getHuertaSF(String id) throws AztecException{
        Huerta__c huerta = null;
         str = "SELECT Id,Name,HuertaParent__c,ProductorParent__c,ProductorParent__r.Name,MunicipioParent__c,MunicipioParent__r.Name,Estado__c, RecordType.Name" +
               " FROM Huerta__c WHERE Id = '" + id + "' ORDER BY Name ASC";
        //System.out.println("STR " + str);
        try{
            if(connection != null){
                 QueryResult queryResults = connection.query(str);
                  if (queryResults.getSize() > 0) {
                       for (int i = 0; i < queryResults.getRecords().length; i++) {
                          huerta = (Huerta__c)queryResults.getRecords()[i];
                      
                     }
                  }
            }
        }catch(Exception e){
            loggerHA.error(e.getMessage());
            throw new  AztecException(e.getMessage());
        }
        return huerta;
    }
    
    /*
     * Metodos para interactuar con la base de datos
     * local
     */
    
    public void insertHuertaAPP(Huerta__c huerta) throws AztecException{
      try{
        String productor = Util.isNull( huerta.getProductorParent__r()) ? "" :  huerta.getProductorParent__r().getName();
        String municipio = Util.isNull(huerta.getMunicipioParent__r()) ? "" : huerta.getMunicipioParent__r().getName();
        //hs.delete(huerta.getId());
        hs.insert(huerta.getId(), huerta.getHuertaParent__c(),huerta.getName(), productor ,huerta.getEstado__c() +"," + municipio, "","",huerta.getRecordType().getName());
      }catch(Exception e){
        loggerHA.error(e.getLocalizedMessage());
        throw new  AztecException(e.getLocalizedMessage());
      }
    }
    
    public List<Huerta> getHuertasApp(){
        return  hs.selectAll();
    }
    public List<Huerta> getHuertasSincronizar(){
        return hs.selectAllSincronizar();
    }
    public List<Huerta> getZonasApp(String idHuerta){
        return hs.selectAllZonas(idHuerta);
    }
    
    public Huerta getHuertaApp(String idHuerta){
        return hs.selectHuerta(idHuerta);
    }
    
    public void saveHuertaApp(Huerta huerta){
        hs.saveHuerta(huerta);
    }
    
    public void deleteHuertaApp(String id){
        hs.delete(id);
    }
    
    public void deleteAllHuertaApp(String id){
        hs.deleteAll(id);
    }
}

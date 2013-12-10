/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.control;

import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.UpsertResult;
import com.sforce.soap.enterprise.sobject.Arbol__c;
import com.sforce.soap.enterprise.sobject.ProduccionEstimada__c;
import com.sforce.ws.ConnectionException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static mx.com.ctc.aztec.control.HuertaAcciones.loggerHA;
import mx.com.ctc.aztec.model.Arbol;
import mx.com.ctc.aztec.model.AsesoriaTecnica;
import mx.com.ctc.aztec.model.Huerta;
import mx.com.ctc.aztec.service.ArbolServicio;
import mx.com.ctc.aztec.service.HuertaServicio;
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
public class ArbolAcciones extends ConexionSF{
     private ArbolServicio as = new ArbolServicio();
     private HuertaServicio hs = new HuertaServicio();
     private String str = "";
     static Logger loggerAA =  LoggerFactory.getLogger(HuertaAcciones.class);
      
     public List<Arbol__c> getArbolesSF (String idHuerta){
         List<Arbol__c> arboles = new ArrayList();
         str = " SELECT Id, IsDeleted, Name, CreatedDate, CreatedById, LastModifiedDate, LastModifiedById, SystemModstamp, LastActivityDate, LastViewedDate, LastReferencedDate, " +
                       " Ano_de_Plantacion__c, Ubicacion_Fila__c, Notas__c, Clave_Unica__c, Variedad_Estimado_producci_n__c, HuertaParent__c, Ubicacion_Columna__c, CodigoArbol__c, " +
                       " CodigoVariedad__c, CoordenadaZ__c, FechaPlantacion__c, FueReplantado__c, NumeroArbol__c, Variedad__c, NumeroEstacion__c, QR__c, Placa__c, Tipo__c, ViveroParent__c,ViveroParent__r.Name," +
                       " Estatus__c, UltimaRevision__c, InjertadoFuerte__c, ProductividadImagen__c, Punto__Latitude__s, Punto__Longitude__s, UltimaProduccionEstimada__c, " +
                       " Insertado__c, Produccion__c, ProduccionRango__c, Huerta_Principal__c, ProduccionAnio__c " +
                 " FROM Arbol__c WHERE HuertaParent__c = '" + idHuerta+ "'";
          try{
            conectar();
            if(connection != null){
                 QueryResult queryResults = connection.query(str);
                 if (queryResults.getSize() > 0) {
                     for (int i = 0; i < queryResults.getRecords().length; i++) {
                          Arbol__c arbolSF = (Arbol__c)queryResults.getRecords()[i];
                          arboles.add(arbolSF);
                     }
                 }
            }
        }catch(AztecException | ConnectionException e){
            loggerAA.error(e.getMessage());
        }
        return arboles;        
     }
     
     public Arbol__c getArbolSF (String idArbol){
         Arbol__c arbol = new Arbol__c();
         List<Arbol__c> arboles = new ArrayList();
         str = "SELECT Id, IsDeleted, Name, CreatedDate, CreatedById, LastModifiedDate, LastModifiedById, SystemModstamp, LastActivityDate, LastViewedDate, LastReferencedDate, " +
                       " Ano_de_Plantacion__c, Ubicacion_Fila__c, Notas__c, Clave_Unica__c, Variedad_Estimado_producci_n__c, HuertaParent__c, Ubicacion_Columna__c, CodigoArbol__c, " +
                       " CodigoVariedad__c, CoordenadaZ__c, FechaPlantacion__c, FueReplantado__c, NumeroArbol__c, Variedad__c, NumeroEstacion__c, QR__c, Placa__c, Tipo__c, ViveroParent__c,ViveroParent__r.Name," +
                       " Estatus__c, UltimaRevision__c, InjertadoFuerte__c, ProductividadImagen__c, Punto__Latitude__s, Punto__Longitude__s, UltimaProduccionEstimada__c, " +
                       " Insertado__c, Produccion__c, ProduccionRango__c, Huerta_Principal__c, ProduccionAnio__c " +
                 " FROM Arbol__c WHERE Id = '" + idArbol+ "'";
          try{
            conectar();
            if(connection != null){
                 QueryResult queryResults = connection.query(str);
                 if (queryResults.getSize() > 0) {
                     for (int i = 0; i < queryResults.getRecords().length; i++) {
                          Arbol__c arbolSF = (Arbol__c)queryResults.getRecords()[i];
                          arboles.add(arbolSF);
                     }
                 }
            }
        }catch(AztecException | ConnectionException e){
            loggerAA.error(e.getMessage());
        }
        if(arboles.size() > 0){
            arbol = arboles.get(0);
        }
        return arbol;        
     }
     
     /*
     * Metodos para interactuar con la base de datos
     * local
     */
     public void insertArbolAPP(Arbol__c arbol) throws AztecException{
      try{
        //as.delete(arbol.getId());
        as.insert(  arbol.getId(),
                    arbol.getUltimaRevision__c(),
                    arbol.getName(),
                    arbol.getPlaca__c(),
                    arbol.getEstatus__c(),
                    String.valueOf(arbol.getFueReplantado__c()),
                    Util.isNull(arbol.getViveroParent__r()) ? "" : arbol.getViveroParent__r().getName(),
                    String.valueOf(arbol.getProduccion__c()),
                    arbol.getNotas__c(),
                    String.valueOf(arbol.getNumeroEstacion__c()),
                    Util.calendarToString(arbol.getFechaPlantacion__c()),
                    arbol.getVariedad__c(),
                    arbol.getTipo__c(),
                    String.valueOf(arbol.getCoordenadaZ__c()),
                    String.valueOf(arbol.getPunto__Latitude__s()),
                    String.valueOf(arbol.getPunto__Longitude__s()),
                    arbol.getQR__c(),
                    arbol.getClave_Unica__c(),
                    arbol.getUbicacion_Fila__c(),
                    String.valueOf(arbol.getUbicacion_Columna__c()),
                    arbol.getHuertaParent__c(),
                    String.valueOf(arbol.getInjertadoFuerte__c()),
                    "","false","","false",arbol.getProduccionAnio__c(),String.valueOf(arbol.getInsertado__c())
                );
      }catch(Exception e){
        loggerHA.error(e.getMessage() + "," + e.getLocalizedMessage());
        throw new  AztecException(e.getLocalizedMessage());
      }
     }
     
     public Respuesta updateArbolesSF(List<Arbol> arboles, AsesoriaTecnica asesoria){
         Respuesta respuesta = new Respuesta(Constantes.MSG_EXITO_GUARDADO,"");
         Arbol__c[] records = new Arbol__c[arboles.size()];
         try{
             conectar();
             Integer i = 0;
             for(Arbol arbol:arboles){
                 Arbol__c arbolSF = new Arbol__c();
                 if(!Util.isNull(arbol.getId())){
                      arbolSF.setId(arbol.getId());
                 }
                 arbolSF.setHuertaParent__c(arbol.getIdHuerta());
                 arbolSF.setUbicacion_Columna__c(Double.valueOf(arbol.getUbicacionColumna()));
                 arbolSF.setUbicacion_Fila__c(arbol.getUbicacionFila());
                 //arbolSF.setCoordenadaX__c(Double.valueOf(arbol.getLatitud()));
                 //arbolSF.setCoordenadaY__c(Double.valueOf(arbol.getLongitud()));
                 arbolSF.setPunto__Latitude__s(Double.valueOf(Util.isNull(arbol.getLatitud()) ? "0.0" : arbol.getLatitud()));
                 arbolSF.setPunto__Longitude__s(Double.valueOf(Util.isNull(arbol.getLongitud()) ? "0.0" : arbol.getLongitud()));
                 Double cordenadaZ = Double.parseDouble(Util.isNull(arbol.getElevacion()) ? "0.0" :  arbol.getElevacion() );
                 arbolSF.setCoordenadaZ__c(cordenadaZ);
                 arbolSF.setTipo__c(arbol.getTipo());
                 arbolSF.setVariedad__c(arbol.getVariedad());
                 Calendar fecha =  Util.stringToCalendar(arbol.getFechaPlantacion());
                 arbolSF.setFechaPlantacion__c(fecha);
                 arbolSF.setNumeroEstacion__c(Util.isNull(arbol.getNumeroEstacion()) ? "":arbol.getNumeroEstacion());
                 arbolSF.setInjertadoFuerte__c(Boolean.parseBoolean(arbol.getInjertado()));
                 arbolSF.setNotas__c(arbol.getNotas());
                 arbolSF.setFueReplantado__c(Boolean.parseBoolean(arbol.getReplantado()));
                 arbolSF.setEstatus__c(arbol.getEstatus());
                 Double insertado = Double.parseDouble(Util.isNull(arbol.getInsertado()) ? "0.0" :  arbol.getInsertado() );
                 arbolSF.setInsertado__c(insertado);
                 records[i] = arbolSF;
                 i++;
             }
            UpsertResult[] saveResults = connection.upsert("Id",records);
             for (int j=0; j< saveResults.length; j++) {
                 if (saveResults[j].isSuccess()) {
                     arboles.get(j).setMensaje("");
                     arboles.get(j).setModificado("false");
                     respuesta.getIds().add((saveResults[j].getId()));
                     Double produccion = Double.parseDouble(!Util.isNull(arboles.get(j).getProduccionEstimada()) ? arboles.get(j).getProduccionEstimada(): "0.0" );
                     if(produccion > 0.0 &&  arboles.get(j).getModificadoProduccion().equals("true")){
                         arboles.get(j).setModificadoProduccion("false");
                         insertProduccion(saveResults[j].getId(),produccion,asesoria,arboles.get(j).getAnioProduccion());
                     }
                     if(saveResults[j].isCreated()){
                         arboles.get(j).setId(saveResults[j].getId());
                     }
                     Arbol__c arbol = getArbolSF( arboles.get(j).getId());
                     
                     arboles.get(j).setPlaca(arbol.getPlaca__c());
                     arboles.get(j).setQr(arbol.getQR__c());
                     arboles.get(j).setClaveUnica(arbol.getClave_Unica__c());
                     saveArbolAPPSinValidar(arboles.get(j));
                 }else{
                    respuesta.setMensaje("Error, revisar registro");
                    com.sforce.soap.enterprise.Error[] errors = saveResults[j].getErrors();
                    for (int k=0; k< errors.length; k++) {
                        arboles.get(j).setMensaje(errors[k].getMessage());
                        saveArbolAPP(arboles.get(j));
                        loggerAA.error("ERROR creando registro: " + errors[k].getMessage());
                    }
                 }
            }
         
         }catch(AztecException | NumberFormatException | ConnectionException e){
             respuesta.setMensaje("ERROR sincronizando registros");
             loggerAA.error(e.getLocalizedMessage() + "," + e.getMessage());
         }
         return respuesta;
     }
     
     public List<Arbol> getArbolesModificadosApp(String idHuerta){
         String sQuery = "Modificado = 'true' AND idHuerta = '" + idHuerta +"'";
         return as.select(sQuery);
     }
     
     public List<Arbol>   getArbolesApp(String idHuerta){
         return as.selectAll(idHuerta);
     }
     public List<Arbol> getArbolesXPlaca(String sPlaca,String idHuerta,String idPrincipal){
         String sQuery  ="";
         List<Huerta> subhuertas =  hs.selectAllZonas(idHuerta);
         if(!Util.isNull(idPrincipal) || subhuertas.isEmpty()){
            if(Util.isNull(sPlaca) || sPlaca.equals("*")){
               sQuery = " IdHuerta = '" + idHuerta +"'";
            }else{
               sQuery = " placa LIKE '%" + sPlaca +"%' AND IdHuerta = '" + idHuerta +"'";
            } 
         }else{
            if(Util.isNull(sPlaca) || sPlaca.equals("*")){
               sQuery = " IdHuerta IN (select  id FROM Huerta WHERE idHuerta = '" + idHuerta +"')";
            }else{
               sQuery = " placa LIKE '%" + sPlaca +"%' AND IdHuerta IN (select  id FROM Huerta WHERE idHuerta = '" + idHuerta +"')";
            }
         }
         return as.select(sQuery);
     }
     
     public String saveArbolAPP (Arbol arbol){
         String mensaje = Constantes.MSG_EXITO_GUARDADO;
         if(!Util.isNull(arbol.getAnioPlantacion()) && !Util.isNull(arbol.getMesPlantacion()) ){
            arbol.setFechaPlantacion("01/" + Util.monthToNumber(arbol.getMesPlantacion()) + "/" +arbol.getAnioPlantacion());
         }
         
         if(Util.isNull(arbol.getLatitud())){
             mensaje = "No se encuentra latitud";
         }else if(Util.isNull(arbol.getLongitud())){
             mensaje = "No se encuentra longitud";
         }else if(Util.isNull(arbol.getTipo())){
             mensaje = "No se encuentra tipo";
         }else if(Util.isNull(arbol.getIdHuerta())){
              mensaje = "No se encuentra huerta";
         }else if(!Util.isDecimal(arbol.getLatitud())){
             mensaje = "Latitud no tiene formato adecuado";
         }else if(!Util.isDecimal(arbol.getLongitud())){
             mensaje = "Longitud no tiene formato adecuado";
         }else if(!Util.isNull(arbol.getUbicacionColumna()) && !Util.isDecimal(arbol.getUbicacionColumna())){
             mensaje = "Columna no tiene formato adecuado";
         }else if(!Util.isNull(arbol.getProduccionEstimada()) && !Util.isDecimal(arbol.getProduccionEstimada())){
            mensaje = "Producción no es un número";
         }else if((!Util.isNull(arbol.getProduccionEstimada()) && Integer.valueOf(arbol.getProduccionEstimada()) > 0)&& Util.isNull(arbol.getAnioProduccion())){
             mensaje = "No se encuentra año de producción";
         }else if(!Util.isNull(arbol.getInsertado()) && !Util.isDecimal(arbol.getInsertado())){
             mensaje = "Insertado no es un número";
         }else{
           mensaje = as.update(arbol);
         }
         return mensaje;
     }
     
     public void saveArbolAPPSinValidar(Arbol arbol){
        if(!Util.isNull(arbol.getAnioPlantacion()) && !Util.isNull(arbol.getMesPlantacion()) ){
            arbol.setFechaPlantacion("01/" + Util.monthToNumber(arbol.getMesPlantacion()) + "/" +arbol.getAnioPlantacion());
         }
        as.update(arbol);
     }
     
     public String insertArbolAPP (Arbol arbol){
         
         String mensaje = Constantes.MSG_EXITO_GUARDADO;
         if(!Util.isNull(arbol.getAnioPlantacion()) && !Util.isNull(arbol.getMesPlantacion()) ){
            arbol.setFechaPlantacion("01/" + Util.monthToNumber(arbol.getMesPlantacion()) + "/" +arbol.getAnioPlantacion());
         }
         
         if(Util.isNull(arbol.getTipo())){
             mensaje = "No se encuentra tipo";
         }else if(Util.isNull(arbol.getIdHuerta())){
              mensaje = "No se encuentra huerta";
         }else if(!Util.isNull(arbol.getLatitud()) && !Util.isDecimal(arbol.getLatitud())){
             mensaje = "Latitud no tiene formato adecuado";
         }else if(!Util.isNull(arbol.getLongitud()) && !Util.isDecimal(arbol.getLongitud())){
             mensaje = "Longitud no tiene formato adecuado";
         }else if(!Util.isNull(arbol.getUbicacionColumna()) && !Util.isDecimal(arbol.getUbicacionColumna())){
             mensaje = "Columna no tiene formato adecuado";
         }else if(!Util.isNull(arbol.getProduccionEstimada()) && !Util.isDecimal(arbol.getProduccionEstimada())){
            mensaje = "Producción no es un número";
         }else if((!Util.isNull(arbol.getProduccionEstimada()) && Integer.valueOf(arbol.getProduccionEstimada()) > 0)&& Util.isNull(arbol.getAnioProduccion())){
             mensaje = "No se encuentra año de producción";
         }else if(!Util.isNull(arbol.getInsertado()) && !Util.isDecimal(arbol.getInsertado())){
             mensaje = "Insertado no es un número";
         }else{
            as.insert(arbol);
         }
         return mensaje;
     }
     
     public void deleteArbolesXHuerta(String idHuerta){
         String sQuery = " idHuerta = '" + idHuerta +"'";
         as.delete(sQuery);
     }
     
     
     public void insertProduccion(String idArbol,Double cantidad,AsesoriaTecnica asesoria,String anio){
         ProduccionEstimada__c produccion = new ProduccionEstimada__c();
         produccion.setArbolParent__c(idArbol);
         produccion.setProduccion__c(cantidad);
         if(!Util.isNull(asesoria)){
            produccion.setAsesoriaTecnicaParent__c(asesoria.getId());
            //produccion.setAnio__c(asesoria.getFechaAsesoria().substring(asesoria.getFechaAsesoria().lastIndexOf("/") + 1,asesoria.getFechaAsesoria().length()));
         }
         produccion.setAnio__c(anio);
         ProduccionEstimada__c[] records = new ProduccionEstimada__c[1];
         records[0] = produccion;
         try{
             SaveResult[] saveResults = connection.create(records);
             for (int j=0; j< saveResults.length; j++) {
                  if (saveResults[j].isSuccess()) {
                      
                  }else{ 
                      com.sforce.soap.enterprise.Error[] errors = saveResults[j].getErrors();
                      for (int k=0; k< errors.length; k++) {
                        loggerAA.error("ERROR creando produccion: " + errors[k].getMessage());
                    }
                  }
             }
         }catch(Exception e){
             loggerAA.debug(e.getMessage());
         }
         
         
     }
     
}

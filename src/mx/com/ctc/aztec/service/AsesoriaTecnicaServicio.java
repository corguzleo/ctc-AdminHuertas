/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.service;

import java.util.List;
import mx.com.ctc.aztec.dao.AsesoriaTecnicaDAO;
import mx.com.ctc.aztec.model.AsesoriaTecnica;
import mx.com.ctc.aztec.utils.Constantes;
import mx.com.ctc.aztec.utils.Util;

/**
 *
 * @author OSCAR
 */
public class AsesoriaTecnicaServicio {
     private AsesoriaTecnicaDAO dao = new AsesoriaTecnicaDAO();
     
     
     public String insert(String Id, String IdTemporal,String IdHuerta, String Huerta, String Asesoria, String FechaAsesoria, String Folio, String InsectosBeneficos, 
                        String instalaciones, String maleza, String basura, String comentariosLimpieza, String observacionesAsesor){
            AsesoriaTecnica at = new AsesoriaTecnica();
            String mensaje = Constantes.MSG_EXITO_GUARDADO;
            
           if(Util.isNull(Folio)){
               mensaje =  "Debe ingresar un Folio";
           }else if(Util.isNull(FechaAsesoria)){
               mensaje =  "Debe ingresar un Fecha de asesoria";
           }else if(!Util.isDate(FechaAsesoria, "dd/mm/YYYY")){
               mensaje = "Fecha asesoria no tiene formato adecuado: dd/mm/YYYY";
           }else if(Folio.length() > 8){
                    mensaje =  "El folio debe ser menor a 8 caracteres.";
           }else{
                at.setId(Id);
                at.setIdTemporal(IdTemporal);
                at.setIdHuerta(IdHuerta);
                at.setAsesoria(Asesoria);
                at.setFechaAsesoria(FechaAsesoria);
                at.setFolio(Folio);
                at.setInsectosBeneficos(InsectosBeneficos);
                at.setInstalaciones(instalaciones);
                at.setMaleza(maleza);
                at.setBasura(basura);
                at.setComentariosLimpieza(comentariosLimpieza);
                at.setObservacionesAsesor(observacionesAsesor);
                at.setHuerta(Huerta);
                dao.insert(at);  
           }
           return mensaje;
     }
     
     public List<AsesoriaTecnica> selectAsesoriaHuerta(String id){
         String sWhere = "idHuerta = '" + id + "'";
         return dao.select(sWhere);
     }
     
     public void deleteAsesoria(AsesoriaTecnica at){
         dao.delete(at);
     }
     
     public String updateAsesoria(AsesoriaTecnica at){
         String mensaje = Constantes.MSG_EXITO_GUARDADO;
         if(Util.isNull(at.getFolio())){
               mensaje =  "Debe ingresar un Folio";
           }else if(Util.isNull(at.getFechaAsesoria())){
               mensaje =  "Debe ingresar un Fecha de asesoria";
           }else if(!Util.isDate(at.getFechaAsesoria(), "dd/mm/YYYY")){
               mensaje = "Fecha asesoria no tiene formato adecuado: dd/mm/YYYY";
           }else{
                dao.update(at);
           }
         return mensaje;
     }
}

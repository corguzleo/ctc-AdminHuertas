/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.service;

import java.util.List;
import mx.com.ctc.aztec.dao.HuertaDAO;
import mx.com.ctc.aztec.model.Huerta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class HuertaServicio {
    private HuertaDAO dao = new HuertaDAO();
    static Logger loggerHS =  LoggerFactory.getLogger(HuertaDAO.class);
    
    public void insert(String id, String Idhuerta, String huerta, String productor,String ubicacion,String idAsesoriaTecnica,String fechaAsesoriaTecnica,String tipoRegistro){
        Huerta h = new Huerta();
        if(!id.trim().equals("") && !huerta.trim().equals("")){
             h.setId(id);
             h.setIdHuerta(Idhuerta);
             h.setHuerta(huerta);
             h.setProductor(productor);
             h.setUbicacion(ubicacion);
             h.setIdAsesoriaTecnica(idAsesoriaTecnica);
             h.setFechaAsesoriaTecnica(fechaAsesoriaTecnica);
             h.setModificado("false");
             h.setTipoRegistro(tipoRegistro);
             dao.insert(h);
        }else{
            loggerHS.warn("La huerta no contiene un Id valido: " + id);
        }
    }
    
    public void deleteAll(String id){
        dao.deleteAllHuerta(id);
    }
    
    public void delete(String id){
        dao.delete(id,false);
    }
    
    public List<Huerta> selectAll(){
        return dao.selectAll();
    }
    
    public List<Huerta> selectAllZonas(String idHuerta){
        String sWhere = " IdHuerta = '" + idHuerta + "'";
        return dao.select(sWhere);
    }
    
    public Huerta selectHuerta(String id){
         String sWhere = " Id = '" + id + "'";
         Huerta huerta = null;
         if(dao.select(sWhere) != null){
             if(!dao.select(sWhere).isEmpty()){
                huerta = dao.select(sWhere).get(0);
             }
         }
         
         return huerta;
    }
    
    public List<Huerta> selectAllSincronizar(){
        String sQuery = " Modificado = 'true'";
        return dao.select(sQuery);
    }
    
    public void saveHuerta(Huerta huerta){
        dao.update(huerta);
    }
    
    public void deleteHuerta(String id){
        dao.delete(id, Boolean.FALSE);
    }
           
}

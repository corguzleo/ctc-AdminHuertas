/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.dao;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.com.ctc.aztec.model.AsesoriaTecnica;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class AsesoriaTecnicaDAO extends Conexion{
    private String sqry = "";
    static Logger loggerAsesoria =  LoggerFactory.getLogger(AsesoriaTecnicaDAO.class);
    
     public void insert(AsesoriaTecnica asesoria){
         conectar();
         try{
              
             sqry = "INSERT INTO AsesoriaTecnica (id,idTemporal, idHuerta, huerta,asesoria, fechaAsesoria,folio,insectosBeneficos,instalaciones,maleza,basura,comentariosLimpieza,observacionesAsesor) " + 
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement prep = conexion.prepareStatement(sqry);
             prep.setString(1, asesoria.getId());
             //prep.setString(2, asesoria.getIdTemporal());
             prep.setString(3, asesoria.getIdHuerta());
             prep.setString(4, asesoria.getHuerta());
             prep.setString(5, asesoria.getAsesoria());
             prep.setString(6, asesoria.getFechaAsesoria());
             prep.setString(7, asesoria.getFolio());
             prep.setString(8, asesoria.getInsectosBeneficos());
             prep.setString(9, asesoria.getInstalaciones());
             prep.setString(10, asesoria.getMaleza());
             prep.setString(11, asesoria.getBasura());
             prep.setString(12, asesoria.getComentariosLimpieza());
             prep.setString(13, asesoria.getObservacionesAsesor());
       
             
             conexion.setAutoCommit(false);
             prep.executeUpdate();
             conexion.setAutoCommit(true);
             
          
         }catch(Exception e){
             loggerAsesoria.error(e.getLocalizedMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerAsesoria.error(e.getLocalizedMessage());
             }  
         }
    }
    
    public void delete(AsesoriaTecnica asesoria){
        conectar();
         try{
             if(!Util.isNull(asesoria)){
               if(!Util.isNull(asesoria.getId())){
                  sqry = "DELETE FROM AsesoriaTecnica WHERE Id = '" + asesoria.getId() + "'";
               }else if(!Util.isNull(asesoria.getIdTemporal())){
                  sqry = "DELETE FROM AsesoriaTecnica WHERE IdTemporal = '" + asesoria.getIdTemporal() + "'";
               }
               statement.executeUpdate(sqry);
             }
         }catch(Exception e){
             loggerAsesoria.error(e.getLocalizedMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerAsesoria.error(e.getLocalizedMessage());
             }  
         }
    }
    
    public void update(AsesoriaTecnica asesoria){
        conectar();
        String strWhere = "";
        try{
             sqry = "UPDATE AsesoriaTecnica SET idHuerta = ?, huerta = ?,asesoria = ?, fechaAsesoria = ?, " +
                     "folio = ?,insectosBeneficos = ?,instalaciones = ?,maleza = ?,basura = ?,comentariosLimpieza = ?,observacionesAsesor = ? ,Id = ?";
             
            
             
            
             if(!Util.isNull(asesoria.getIdTemporal())){
                strWhere = " WHERE idTemporal = ?";
             }
            
             sqry = sqry + strWhere;
             PreparedStatement prep = conexion.prepareStatement(sqry);
             
             prep.setString(1, asesoria.getIdHuerta());
             prep.setString(2, asesoria.getHuerta());
             prep.setString(3, asesoria.getAsesoria());
             prep.setString(4, asesoria.getFechaAsesoria());
             prep.setString(5, asesoria.getFolio());
             prep.setString(6, asesoria.getInsectosBeneficos());
             prep.setString(7, asesoria.getInstalaciones());
             prep.setString(8, asesoria.getMaleza());
             prep.setString(9, asesoria.getBasura());
             prep.setString(10, asesoria.getComentariosLimpieza());
             prep.setString(11, asesoria.getObservacionesAsesor());
             prep.setString(12, asesoria.getId());
            
             if(!Util.isNull(asesoria.getIdTemporal())){
                 prep.setString(13, asesoria.getIdTemporal());
             }
             conexion.setAutoCommit(false);
             prep.executeUpdate();
             conexion.setAutoCommit(true);
            
        }catch(Exception e){
             loggerAsesoria.error(e.getMessage()  + "," + e.getLocalizedMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerAsesoria.error(e.getMessage()  + "," + e.getLocalizedMessage());
             }  
         }
    
    }
    
    public List<AsesoriaTecnica> selectAll(){
        List<AsesoriaTecnica> asesorias =  new ArrayList<AsesoriaTecnica>();
        conectar();
          try{
                sqry = "SELECT * FROM AsesoriaTecnica";
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                        AsesoriaTecnica asesoria = new AsesoriaTecnica(rs.getString("id"),rs.getString("idTemporal"), rs.getString("idHuerta"), rs.getString("huerta"),
                                                                   rs.getString("asesoria"), rs.getString("fechaAsesoria"),rs.getString("folio"),rs.getString("insectosBeneficos"),
                                                                   rs.getString("instalaciones"),rs.getString("maleza"),rs.getString("basura"),
                                                                   rs.getString("comentariosLimpieza"),rs.getString("observacionesAsesor"));
                        asesorias.add(asesoria);
                        
                }
            
        }catch(Exception e){
               loggerAsesoria.error(e.getLocalizedMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerAsesoria.error(e.getLocalizedMessage());
             }  
         }
        
        return asesorias;
    
    }
    
     public List<AsesoriaTecnica> select(String sWhere){
        List<AsesoriaTecnica> asesorias =  new ArrayList();
        conectar();
          try{
                sqry = "SELECT * FROM AsesoriaTecnica WHERE " + sWhere;
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                        AsesoriaTecnica asesoria = new AsesoriaTecnica(rs.getString("id"),rs.getString("idTemporal"), rs.getString("idHuerta"), rs.getString("huerta"),
                                                                   rs.getString("asesoria"), rs.getString("fechaAsesoria"),rs.getString("folio"),rs.getString("insectosBeneficos"),
                                                                   rs.getString("instalaciones"),rs.getString("maleza"),rs.getString("basura"),
                                                                   rs.getString("comentariosLimpieza"),rs.getString("observacionesAsesor"));
                        asesorias.add(asesoria);
                        
                }
            
        }catch(Exception e){
               loggerAsesoria.error(e.getLocalizedMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerAsesoria.error(e.getLocalizedMessage());
             }  
         }
        
        return asesorias;
    
    }
}

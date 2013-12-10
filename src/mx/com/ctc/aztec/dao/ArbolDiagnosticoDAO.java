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
import mx.com.ctc.aztec.model.ArbolDiagnostico;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class ArbolDiagnosticoDAO extends Conexion {
     private String sqry = "";
     static Logger loggerAsesoria =  LoggerFactory.getLogger(ArbolDiagnosticoDAO.class);
     
     public Integer insert(ArbolDiagnostico arbol){
         conectar();
         Integer index = 0;
         try{
              
             sqry = "INSERT INTO ArbolDiagnostico (idArbol, idDiagnostico, Fecha) " + 
                     "VALUES (?,?,?)";
             PreparedStatement prep = conexion.prepareStatement(sqry);
             prep.setString(1, arbol.getIdArbol());
             prep.setString(2, arbol.getIdDiagnostico());
             prep.setDate(3, (Date) arbol.getFecha());
        
             conexion.setAutoCommit(false);
             index = prep.executeUpdate();
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
         return index;
    }
     
    public void delete(ArbolDiagnostico arbol){
        conectar();
         try{
             sqry = "DELETE FROM ArbolDiagnostico WHERE Id = '" + arbol.getId() + "'";
             statement.executeUpdate(sqry);
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
    
    public void update(ArbolDiagnostico arbol){
        conectar();
        try{
             sqry = "UPDATE ArbolDiagnostico SET idArbol = ?, idDiagnostico = ?, Fecha = ? " +
                    "WHERE Id = ?";
             PreparedStatement prep = conexion.prepareStatement(sqry);
         
            
             prep.setString(1, arbol.getIdArbol());
             prep.setString(2, arbol.getIdDiagnostico());
             prep.setDate(3, (Date) arbol.getFecha());
             prep.setInt(4, arbol.getId());
             
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
    
     public List<ArbolDiagnostico> selectAll(){
        List<ArbolDiagnostico> arbolDiagnosticos =  new ArrayList<ArbolDiagnostico>();
        conectar();
          try{
                sqry = "SELECT * FROM ArbolDiagnostico";
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                     ArbolDiagnostico arbolDiagnostico;
                     arbolDiagnostico = new ArbolDiagnostico(rs.getInt("Id"),
                                                           rs.getString("idArbol"),rs.getString("idDiagnostico"), Util.strToDate(rs.getString("Fecha"), "YYYY-mm-dd"));
                     arbolDiagnosticos.add(arbolDiagnostico);
                        
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
        
        return arbolDiagnosticos;
    }
    
     public List<ArbolDiagnostico> select(String sWhere){
        List<ArbolDiagnostico> arbolDiagnosticos =  new ArrayList<ArbolDiagnostico>();
        conectar();
          try{
                sqry = "SELECT * FROM ArbolDiagnostico WHERE " + sWhere;
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                     ArbolDiagnostico arbolDiagnostico;
                     arbolDiagnostico = new ArbolDiagnostico(rs.getInt("Id"),
                                                           rs.getString("idArbol"),rs.getString("idDiagnostico"), Util.strToDate(rs.getString("Fecha"), "YYYY-mm-dd"));
                     arbolDiagnosticos.add(arbolDiagnostico);
                        
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
        
        return arbolDiagnosticos;
    
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.com.ctc.aztec.model.Huerta;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class HuertaDAO extends Conexion{
    private String sqry = "";
    static Logger loggerHuerta =  LoggerFactory.getLogger(HuertaDAO.class);
    
    public void insert(Huerta huerta){
         conectar();
         try{
              
             sqry = "INSERT INTO Huerta (id,IdHuerta,Huerta,Productor,Ubicacion,idAsesoriaTecnica,FechaAsesoriaTecnica,Modificado,TipoRegistro) values (?,?,?,?,?,?,?,?,?)";
             PreparedStatement prep = conexion.prepareStatement(sqry);
             prep.setString(1, huerta.getId());
             prep.setString(2, huerta.getIdHuerta());
             prep.setString(3, huerta.getHuerta());
             prep.setString(4, huerta.getProductor());
             prep.setString(5, huerta.getUbicacion());
             prep.setString(6, huerta.getIdAsesoriaTecnica());
             prep.setString(7, huerta.getFechaAsesoriaTecnica());
             prep.setString(8, huerta.getModificado());
             prep.setString(9, huerta.getTipoRegistro());
             conexion.setAutoCommit(false);
             prep.executeUpdate();
             conexion.setAutoCommit(true);
             
          
         }catch(Exception e){
             loggerHuerta.error(e.getMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerHuerta.error(e.getMessage());
             }  
         }
    }
   
    public  void deleteAllHuerta(String id){
         conectar();
         try{
          
            sqry = "DELETE FROM Huerta WHERE Id = '" + id + "'; ";
            sqry = sqry + "DELETE FROM Arbol WHERE IdHuerta = '" + id + "';";
            sqry = sqry + "DELETE FROM AsesoriaTecnica WHERE IdHuerta = '" + id + "';";
            sqry = sqry + "DELETE FROM Revision WHERE IdHuerta = '" + id + "';";
            sqry = sqry + "DELETE FROM Diagnostico WHERE IdHuerta = '" + id + "';";
           
            statement.executeUpdate(sqry);
         }catch(Exception e){
             loggerHuerta.error(e.getLocalizedMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerHuerta.error(e.getLocalizedMessage());
             }  
         }
    }
    
    
    public  void delete(String Id, Boolean bTodo){
         conectar();
         try{
             
             if(bTodo){
                sqry = "DELETE FROM Huerta";
             }else{
                sqry = "DELETE FROM Huerta WHERE Id = '" + Id + "'";
             }
             statement.executeUpdate(sqry);
         }catch(Exception e){
             loggerHuerta.error(e.getLocalizedMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerHuerta.error(e.getLocalizedMessage());
             }  
         }
    }
    
    public void update(Huerta huerta){
        conectar();
        try{
             sqry = "UPDATE Huerta SET IdHuerta = ?, Huerta = ?,Productor = ?,Ubicacion = ?,idAsesoriaTecnica = ?,FechaAsesoriaTecnica = ?, " +
                     "Modificado = ?, FechaModificado = ? WHERE Id = ?";
             PreparedStatement prep = conexion.prepareStatement(sqry);
             prep.setString(1, huerta.getIdHuerta());
             prep.setString(2, huerta.getHuerta());
             prep.setString(3, huerta.getProductor());
             prep.setString(4, huerta.getUbicacion());
             prep.setString(5, huerta.getIdAsesoriaTecnica());
             prep.setString(6, huerta.getFechaAsesoriaTecnica());
             prep.setString(7, huerta.getModificado());
             prep.setString(8, Util.toDay());
             prep.setString(9, huerta.getId());
             conexion.setAutoCommit(false);
             prep.executeUpdate();
             conexion.setAutoCommit(true);
            
        }catch(Exception e){
             loggerHuerta.error(e.getLocalizedMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerHuerta.error(e.getLocalizedMessage());
             }  
         }
    }
    
    public List<Huerta> selectAll(){
        List<Huerta> huertas =  new ArrayList();
        conectar();
          try{
                sqry = "SELECT * FROM HUERTA";
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                        Huerta huerta = new Huerta(rs.getString("Id"),rs.getString("IdHuerta"),
                                                   rs.getString("Huerta"),
                                                    rs.getString("Productor"),
                                                    rs.getString("Ubicacion"),
                                                       rs.getString("IdAsesoriaTecnica"),rs.getString("FechaAsesoriaTecnica"),rs.getString("Modificado"),rs.getString("FechaModificado"),
                                                       rs.getString("tipoRegistro"));
                        huertas.add(huerta);
                        
                }
            
        }catch(Exception e){
             loggerHuerta.error(e.getLocalizedMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerHuerta.error(e.getLocalizedMessage());
             }  
         }
        
        return huertas;
    
    }
    
    public List<Huerta> select(String sWhere){
        List<Huerta> huertas =  new ArrayList();
        conectar();
          try{
                sqry = "SELECT * FROM HUERTA WHERE " + sWhere;
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                        Huerta huerta = new Huerta(rs.getString("Id"),rs.getString("IdHuerta"),
                                                   rs.getString("Huerta"),
                                                    rs.getString("Productor"),
                                                    rs.getString("Ubicacion"),
                                                    rs.getString("IdAsesoriaTecnica"),rs.getString("FechaAsesoriaTecnica"),rs.getString("Modificado"),rs.getString("FechaModificado"),
                                                    rs.getString("tipoRegistro"));
                        huertas.add(huerta);
                        
                }
            
        }catch(Exception e){
             loggerHuerta.error(e.getMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerHuerta.error(e.getMessage());
             }  
         }
        
        return huertas;
    
    }
    
   
}

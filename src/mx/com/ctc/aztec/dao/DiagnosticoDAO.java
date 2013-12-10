/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.com.ctc.aztec.model.Diagnostico;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class DiagnosticoDAO extends Conexion{
    private String sqry = "";
    private Integer idDiagnostico;
    static Logger loggerDiagnostico =  LoggerFactory.getLogger(DiagnosticoDAO.class);
    
     public Integer insert(Diagnostico diagnostico){
         conectar();
         try{
              
             sqry = "INSERT INTO Diagnostico (idRevision, idArbol, diagnostico, agenteCausal, gradoAfeccion, sitioAfeccion,idHuerta,fechaModificacion,tipoInsercion,tipo,subtipo) " + 
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement prep = conexion.prepareStatement(sqry);
             //prep.setString(1, diagnostico.getId());
             prep.setString(1, diagnostico.getIdRevision());
             prep.setString(2, diagnostico.getIdArbol());
             prep.setString(3, diagnostico.getDiagnostico());
             prep.setString(4, diagnostico.getAgenteCausal());
             prep.setString(5, diagnostico.getGradoAfeccion());
             prep.setString(6, diagnostico.getSitioAfeccion());
             prep.setString(7, diagnostico.getIdHuerta());
             prep.setString(8, Util.toDay());
             prep.setString(9, diagnostico.getTipoInsercion());
             prep.setString(10, diagnostico.getTipo());
             prep.setString(11, diagnostico.getSubtipo());
       
             
             conexion.setAutoCommit(false);
             idDiagnostico = prep.executeUpdate();
             conexion.setAutoCommit(true);
             
          
         }catch(Exception e){
             loggerDiagnostico.error(e.getLocalizedMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerDiagnostico.error(e.getLocalizedMessage());
             }  
             return idDiagnostico;
         }
    }
     
    public void delete(Diagnostico diagnostico){
        conectar();
         try{
             sqry = "DELETE FROM Diagnostico WHERE Id = '" + diagnostico.getId() + "'";
             statement.executeUpdate(sqry);
         }catch(Exception e){
             loggerDiagnostico.error(e.getLocalizedMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerDiagnostico.error(e.getLocalizedMessage());
             }  
         }
    }
    
    public void delete(String sQuery){
        conectar();
         try{
             sqry = "DELETE FROM Diagnostico WHERE " + sQuery;
             statement.executeUpdate(sqry);
         }catch(Exception e){
             loggerDiagnostico.error(e.getLocalizedMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerDiagnostico.error(e.getLocalizedMessage());
             }  
         }
    }
    
    
    
     public void update(Diagnostico diagnostico){
        conectar();
        try{
             sqry = "UPDATE Diagnostico SET idRevision = ?, idArbol = ?, diagnostico = ?, agenteCausal = ?, gradoAfeccion = ?, sitioAfeccion = ?, " +
                    "idHuerta = ? ,fechaModificacion = ?, tipoInsercion = ? ,tipo = ?, subtipo = ?, id = ? WHERE IdTemporal = ?";
             PreparedStatement prep = conexion.prepareStatement(sqry);
         
             prep.setString(1, diagnostico.getIdRevision());
             prep.setString(2, diagnostico.getIdArbol());
             prep.setString(3, diagnostico.getDiagnostico());
             prep.setString(4, diagnostico.getAgenteCausal());
             prep.setString(5, diagnostico.getGradoAfeccion());
             prep.setString(6, diagnostico.getSitioAfeccion());
             prep.setString(7, diagnostico.getIdHuerta());
             prep.setString(8, Util.toDay());
             prep.setString(9, diagnostico.getTipoInsercion());
             prep.setString(10, diagnostico.getTipo());
             prep.setString(11, diagnostico.getSubtipo());
             prep.setString(12, diagnostico.getId());
             prep.setString(13, diagnostico.getIdTemporal());
             
             conexion.setAutoCommit(false);
             prep.executeUpdate();
             conexion.setAutoCommit(true);
            
        }catch(Exception e){
             loggerDiagnostico.error(e.getLocalizedMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerDiagnostico.error(e.getLocalizedMessage());
             }  
         }
    }
    
     public List<Diagnostico> selectAll(){
        List<Diagnostico> diagnosticos =  new ArrayList<Diagnostico>();
        conectar();
          try{
                sqry = "SELECT * FROM Diagnostico";
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                        Diagnostico diagnostico = new Diagnostico(rs.getString("Id"), rs.getString("IdRevision"),rs.getString("IdArbol"), rs.getString("Diagnostico"),
                                                    rs.getString("AgenteCausal"),rs.getString("GradoAfeccion"),rs.getString("SitioAfeccion"),rs.getString("idHuerta"),
                                                    rs.getString("TipoInsercion"),rs.getString("Tipo"),rs.getString("Subtipo"),rs.getString("idTemporal"));
                        diagnosticos.add(diagnostico);
                        
                }
            
        }catch(Exception e){
               loggerDiagnostico.error(e.getLocalizedMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerDiagnostico.error(e.getLocalizedMessage());
             }  
         }
        
        return diagnosticos;
    
    }
     
     public List<Diagnostico> select(String sWhere){
        List<Diagnostico> diagnosticos =  new ArrayList<Diagnostico>();
        conectar();
          try{
                sqry = "SELECT * FROM Diagnostico " + sWhere;
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                        Diagnostico diagnostico = new Diagnostico(rs.getString("Id"), rs.getString("IdRevision"),rs.getString("IdArbol"), rs.getString("Diagnostico"),
                                                    rs.getString("AgenteCausal"),rs.getString("GradoAfeccion"),rs.getString("SitioAfeccion"),rs.getString("idHuerta"),
                                rs.getString("TipoInsercion"),rs.getString("Tipo"),rs.getString("Subtipo"),rs.getString("idTemporal"));
                        diagnosticos.add(diagnostico);
                        
                }
            
        }catch(Exception e){
               loggerDiagnostico.error(e.getLocalizedMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerDiagnostico.error(e.getLocalizedMessage());
             }  
         }
        
        return diagnosticos;
    
    }
}

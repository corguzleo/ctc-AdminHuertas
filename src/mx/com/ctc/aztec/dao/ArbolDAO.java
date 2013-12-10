/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static mx.com.ctc.aztec.dao.ArbolDiagnosticoDAO.loggerAsesoria;
import mx.com.ctc.aztec.model.Arbol;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class ArbolDAO extends Conexion {
    private String sqry = "";
    static Logger loggerArbol=  LoggerFactory.getLogger(ArbolDAO.class);
    
    public void insert(Arbol arbol){
         conectar();
         try{
              
             sqry = "INSERT INTO Arbol (id,idUltimaRevision,Arbol,Placa,Estatus,Replantado,Vivero,ProduccionEstimada,Notas, NumeroEstacion, FechaPlantacion, "
                        + " variedad, tipo, elevacion, latitud, longitud, qr, claveUnica, ubicacionFila, ubicacionColumna, idHuerta,InjertadoFuerte,modificado,mensaje,modificadoProduccion,AnioProduccion,Insertado) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement prep = conexion.prepareStatement(sqry);
             prep.setString(1, arbol.getId());
             prep.setString(2, arbol.getIdUltimaRevision());
             prep.setString(3, arbol.getArbol());
             prep.setString(4, arbol.getPlaca());
             prep.setString(5, arbol.getEstatus());
             prep.setString(6, arbol.getReplantado());
             prep.setString(7, arbol.getVivero());
             prep.setString(8, arbol.getProduccionEstimada());
             prep.setString(9, arbol.getNotas());
             prep.setString(10, arbol.getNumeroEstacion());
             prep.setString(11, arbol.getFechaPlantacion());
             
             prep.setString(12, arbol.getVariedad());
             prep.setString(13, arbol.getTipo());
             prep.setString(14, arbol.getElevacion());
             prep.setString(15, arbol.getLatitud());
             prep.setString(16, arbol.getLongitud());
             prep.setString(17, arbol.getQr());
             prep.setString(18, arbol.getClaveUnica());
             prep.setString(19, arbol.getUbicacionFila());
             prep.setString(20, arbol.getUbicacionColumna());
             prep.setString(21, arbol.getIdHuerta());
             prep.setString(22, arbol.getInjertado());
             prep.setString(23, arbol.getModificado());
             prep.setString(24, arbol.getMensaje());
             prep.setString(25, arbol.getModificadoProduccion());
             prep.setString(26, arbol.getAnioProduccion());
             prep.setString(27, arbol.getInsertado());
             
             //prep.setString(23, arbol.getIdTemporal());
             conexion.setAutoCommit(false);
             prep.executeUpdate();
             conexion.setAutoCommit(true);
             
          
         }catch(Exception e){
             loggerArbol.error(e.getMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerArbol.error(e.getMessage());
             }  
         }
    }
    
    public void delete(String sQuery){
        conectar();
         try{
             sqry = "DELETE FROM Arbol WHERE " + sQuery;
             statement.executeUpdate(sqry);
         }catch(Exception e){
             loggerArbol.error(e.getMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerArbol.error(e.getMessage());
             }  
         }
    }
    
     public void delete4Huerta(String idHuerta){
         conectar();
         try{
             sqry = "DELETE FROM Arbol WHERE IdHuerta = '" + idHuerta + "'";
             statement.executeUpdate(sqry);
         }catch(Exception e){
             loggerArbol.error(e.getMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerArbol.error(e.getMessage());
             }  
         }
      }
    
    public void update(Arbol arbol){
        conectar();
        try{
             sqry = "UPDATE Arbol SET idUltimaRevision = ?, Arbol = ?, Placa = ?, Estatus = ?,Replantado = ?,Vivero = ?, ProduccionEstimada = ?,Notas = ?, NumeroEstacion = ?, FechaPlantacion = ?, "
                        + " variedad = ?, tipo = ?, elevacion = ?, latitud = ?, longitud = ?, qr = ?, claveUnica = ?, ubicacionFila = ?, ubicacionColumna = ?, idHuerta = ?, InjertadoFuerte = ?, " +
                     " modificado = ?, mensaje = ?, modificadoProduccion = ? , id =? ,AnioProduccion = ?, Insertado = ? WHERE idTemporal = ?";
             PreparedStatement prep = conexion.prepareStatement(sqry);
         
         
             prep.setString(1, arbol.getIdUltimaRevision());
             prep.setString(2, arbol.getArbol());
             prep.setString(3, arbol.getPlaca());
             prep.setString(4, arbol.getEstatus());
             prep.setString(5, arbol.getReplantado());
             prep.setString(6, arbol.getVivero());
             prep.setString(7, arbol.getProduccionEstimada());
             prep.setString(8, arbol.getNotas());
             prep.setString(9, arbol.getNumeroEstacion());
             prep.setString(10, arbol.getFechaPlantacion());
             
             prep.setString(11, arbol.getVariedad());
             prep.setString(12, arbol.getTipo());
             prep.setString(13, arbol.getElevacion());
             prep.setString(14, arbol.getLatitud());
             prep.setString(15, arbol.getLongitud());
             prep.setString(16, arbol.getQr());
             prep.setString(17, arbol.getClaveUnica());
             prep.setString(18, arbol.getUbicacionFila());
             prep.setString(19, arbol.getUbicacionColumna());
             prep.setString(20, arbol.getIdHuerta());
             prep.setString(21, arbol.getInjertado());
             prep.setString(22, arbol.getModificado());
             prep.setString(23, arbol.getMensaje());
             prep.setString(24, arbol.getModificadoProduccion());
             prep.setString(25, arbol.getId());
             prep.setString(26, arbol.getAnioProduccion());
             prep.setString(27, arbol.getInsertado());
             prep.setString(28, arbol.getIdTemporal());
             
             conexion.setAutoCommit(false);
             prep.executeUpdate();
             conexion.setAutoCommit(true);
            
        }catch(Exception e){
             loggerArbol.error(e.getMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerArbol.error(e.getMessage());
             }  
         }
    }
    
    public List<Arbol> selectAll(){
        List<Arbol> arboles =  new ArrayList();
        conectar();
          try{
                sqry = "SELECT * FROM Arbol";
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                     Arbol arbol;
                     arbol = new Arbol(rs.getString("Id"),rs.getString("idUltimaRevision"),rs.getString("Arbol"),rs.getString("Placa"),rs.getString("Estatus"),rs.getString("Replantado"),rs.getString("Vivero"),rs.getString("ProduccionEstimada"),rs.getString("Notas"), rs.getString("NumeroEstacion"), rs.getString("FechaPlantacion"),
                                       rs.getString("variedad"),rs.getString("tipo"), rs.getString("elevacion"), rs.getString("latitud"), rs.getString("longitud"), rs.getString("qr"), rs.getString("ClaveUnica"), rs.getString("ubicacionFila"), rs.getString("ubicacionColumna"), rs.getString("idHuerta"),
                                       rs.getString("InjertadoFuerta"),rs.getString("IdTemporal"),rs.getString("Modificado"),rs.getString("Mensaje"),rs.getString("ModificadoProduccion"),rs.getString("AnioProduccion"),rs.getString("Insertado"));
                     arboles.add(arbol);
                        
                }
            
        }catch(Exception e){
               loggerAsesoria.error(e.getMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerAsesoria.error(e.getMessage());
             }  
         }
        
        return arboles;
    }
    
    public List<Arbol> select(String sWhere){
        List<Arbol> arboles =  new ArrayList();
        conectar();
          try{
                sqry = "SELECT * FROM Arbol WHERE " + sWhere;
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                     Arbol arbol;
                     arbol = new Arbol(rs.getString("Id"),rs.getString("idUltimaRevision"),rs.getString("Arbol"),rs.getString("Placa"),rs.getString("Estatus"),rs.getString("Replantado"),rs.getString("Vivero"),rs.getString("ProduccionEstimada"),rs.getString("Notas"), rs.getString("NumeroEstacion"), rs.getString("FechaPlantacion"),
                                       rs.getString("variedad"),rs.getString("tipo"), rs.getString("elevacion"), rs.getString("latitud"), rs.getString("longitud"), rs.getString("qr"), rs.getString("ClaveUnica"), rs.getString("ubicacionFila"), rs.getString("ubicacionColumna"), rs.getString("idHuerta"),
                                       rs.getString("InjertadoFuerte"),rs.getString("IdTemporal"),rs.getString("Modificado"),rs.getString("Mensaje"),rs.getString("ModificadoProduccion"),rs.getString("AnioProduccion"),rs.getString("Insertado"));
                     if(!Util.isNull(arbol.getFechaPlantacion())){
                        //arbol.setMesPlantacion(ruta);
                        Integer mes = Integer.parseInt(arbol.getFechaPlantacion().split("/")[1]);
                        arbol.setMesPlantacion(Util.numberToMonth(mes));
                        arbol.setAnioPlantacion(arbol.getFechaPlantacion().split("/")[2]);
                     }
                     
                     arboles.add(arbol);
                        
                }
            
        }catch(Exception e){
               loggerAsesoria.error(e.getMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerAsesoria.error(e.getMessage());
             }  
         }
        
        return arboles;
    }
    
       
}

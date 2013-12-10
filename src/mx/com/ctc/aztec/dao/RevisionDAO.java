/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static mx.com.ctc.aztec.dao.HuertaDAO.loggerHuerta;
import mx.com.ctc.aztec.model.Revision;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class RevisionDAO extends Conexion{
    private String sqry = "";
    static Logger loggerRevision =  LoggerFactory.getLogger(RevisionDAO.class);
    
    public Integer insert(Revision revision){
         conectar();
         Integer idInsercion = null;
         try{
              
             sqry = "INSERT INTO Revision (Id, idTemporal, idArbol, idHuerta,idAsesoriaTecnica, Tumores, Seco, PudricionRadicular,  PudricionFruto, PocoAmarre, PocaHoja,"+
                                            "Pigmeo, OxidacionHojas, Otro, Necrosis, MarcasHojasFrutosTallos, ManchasCorchosas, LesionesSol, HuesoBarrenado, Hueco, HojasVerdeOscuro,"+
                                            "HojasVerdeAmarillento, HojasSecas, HojasManchadas, HojasCloroticaS, HojaChica, Helada, Granizada, FrutoMalformacion,FloracionRetrasada, Defoliacion," +
                                            "CortezaDanada, CaidaRamas, CaidaHojas, CaidaFrutos, BrotesDanados,Amarillo, AgallasHojas,HojasChinas,HojasAmarillas, CenizaVolcanica,FaltaAgua,Incendio,Plaga) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement prep = conexion.prepareStatement(sqry);
             prep.setString(1, revision.getId());
             //prep.setString(2, revision.getIdTemporal());
             prep.setString(3, revision.getIdArbol());
             prep.setString(4, revision.getIdHuerta());
             prep.setString(5, revision.getIdAsesoriaTecnica());
             prep.setString(6, revision.getTumores());
             prep.setString(7, revision.getSeco());
             prep.setString(8, revision.getPudricionRadicular());
             prep.setString(9, revision.getPudricionFruto());
             prep.setString(10, revision.getPocoAmarre());
             prep.setString(11, revision.getPocaHoja());
             
             prep.setString(12, revision.getPigmeo());
             prep.setString(13, revision.getOxidacionHojas());
             prep.setString(14, revision.getOtro());
             prep.setString(15, revision.getNecrosis());
             prep.setString(16, revision.getMarcasHojasFrutosTallos());
             prep.setString(17, revision.getManchasCorchosas());
             prep.setString(18, revision.getLesionesSol());
             prep.setString(19, revision.getHuesoBarrenado());
             prep.setString(20, revision.getHueco());
             prep.setString(21, revision.getHojasVerdesOscuro());
             
             prep.setString(22, revision.getHojasVerdeAmarillento());
             prep.setString(23, revision.getHojasSecas());
             prep.setString(24, revision.getHojasManchadas());
             prep.setString(25, revision.getHojasCloroticas());
             prep.setString(26, revision.getHojaChica());
             prep.setString(27, revision.getHelada());
             prep.setString(28, revision.getGranizada());
             prep.setString(29, revision.getFrutoMalformacion());
             prep.setString(30, revision.getFloracionRetrasada());
             prep.setString(31, revision.getDefoliacion());
            
             prep.setString(32, revision.getCortezaDanada());
             prep.setString(33, revision.getCaidaRamas());
             prep.setString(34, revision.getCaidaHojas());
             prep.setString(35, revision.getCaidaFrutos());
             prep.setString(36, revision.getBrotesDanados());
             prep.setString(37, revision.getAmarillo());
             prep.setString(38, revision.getAgallasHojas());
             prep.setString(39, revision.getHojasChinas());
             prep.setString(40, revision.getHojasAmarillas());
             
             prep.setString(41, revision.getCenizaVolcanica());
             prep.setString(42, revision.getFaltaAgua());
             prep.setString(43, revision.getIncendio());
             prep.setString(44, revision.getPlaga());
             
             
             
             
             conexion.setAutoCommit(false);
             prep.executeUpdate();
             conexion.setAutoCommit(true);
              
             
               if (prep.getGeneratedKeys().next()) {
                        idInsercion = prep.getGeneratedKeys().getInt(1);
                 } 
          
         }catch(Exception e){
             loggerRevision.error(e.getLocalizedMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerRevision.error(e.getLocalizedMessage());
             }  
             return idInsercion;
         }
    }
    
    public void delete(Revision revision){
        conectar();
         try{
             if(!Util.isNull(revision.getId())){
                sqry = "DELETE FROM Revision WHERE Id = '" + revision.getId() + "'";
             }else if(!Util.isNull(revision.getIdTemporal())){
                sqry = "DELETE FROM Revision WHERE IdTemporal = '" + revision.getIdTemporal() + "'";
             }
             statement.executeUpdate(sqry);
         }catch(Exception e){
             loggerRevision.error(e.getLocalizedMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerHuerta.error(e.getLocalizedMessage());
             }  
         }
    }
    
     public void deleteByHuerta(String idHuerta){
        conectar();
         try{
            
             sqry = "DELETE FROM Revision WHERE IdHuerta = '" + idHuerta + "'";
             
             statement.executeUpdate(sqry);
         }catch(Exception e){
             loggerRevision.error(e.getLocalizedMessage());
         }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerHuerta.error(e.getLocalizedMessage());
             }  
         }
    }
     
     
    public void update(Revision revision){
        conectar();
        String strWhere  = "";
        try{
             sqry = "UPDATE Revision SET Id = ? , idTemporal = ?, idArbol = ?,idHuerta = ?, idAsesoriaTecnica = ?, Tumores = ?, Seco = ?, PudricionRadicular = ?,  PudricionFruto = ?, PocoAmarre = ?, PocaHoja = ?,"+
                                            "Pigmeo = ?, OxidacionHojas = ?, Otro = ?, Necrosis = ?, MarcasHojasFrutosTallos = ?, ManchasCorchosas = ?, LesionesSol = ?, HuesoBarrenado = ?, Hueco = ?, HojasVerdeOscuro = ?,"+
                                            "HojasVerdeAmarillento = ?, HojasSecas = ?, HojasManchadas = ?, HojasCloroticas = ?, HojaChica = ?, Helada = ?, Granizada = ?, FrutoMalformacion = ?,FloracionRetrasada = ?, Defoliacion = ?," +
                                            "CortezaDanada = ?, CaidaRamas = ?, CaidaHojas = ?, CaidaFrutos = ?, BrotesDanados = ?,Amarillo = ?, AgallasHojas = ? ,HojasChinas  = ?,HojasAmarillas = ? , " +
                                            "CenizaVolcanica = ?, FaltaAgua = ?, Incendio = ?, Plaga = ? ";
             
             PreparedStatement prep;
            
            
             strWhere = "WHERE idTemporal = ?";
             
             sqry = sqry + strWhere;
             prep = conexion.prepareStatement(sqry);
             
             prep.setString(1, revision.getId());
             prep.setString(2, revision.getIdTemporal());
             prep.setString(3, revision.getIdArbol());
             prep.setString(4, revision.getIdHuerta());
             prep.setString(5, revision.getIdAsesoriaTecnica());
             prep.setString(6, revision.getTumores());
             prep.setString(7, revision.getSeco());
             prep.setString(8, revision.getPudricionRadicular());
             prep.setString(9, revision.getPudricionFruto());
             prep.setString(10, revision.getPocoAmarre());
             prep.setString(11, revision.getPocaHoja());
             
             prep.setString(12, revision.getPigmeo());
             prep.setString(13, revision.getOxidacionHojas());
             prep.setString(14, revision.getOtro());
             prep.setString(15, revision.getNecrosis());
             prep.setString(16, revision.getMarcasHojasFrutosTallos());
             prep.setString(17, revision.getManchasCorchosas());
             prep.setString(18, revision.getLesionesSol());
             prep.setString(19, revision.getHuesoBarrenado());
             prep.setString(20, revision.getHueco());
             prep.setString(21, revision.getHojasVerdesOscuro());
             
             prep.setString(22, revision.getHojasVerdeAmarillento());
             prep.setString(23, revision.getHojasSecas());
             prep.setString(24, revision.getHojasManchadas());
             prep.setString(25, revision.getHojasCloroticas());
             prep.setString(26, revision.getHojaChica());
             prep.setString(27, revision.getHelada());
             prep.setString(28, revision.getGranizada());
             prep.setString(29, revision.getFrutoMalformacion());
             prep.setString(30, revision.getFloracionRetrasada());
             prep.setString(31, revision.getDefoliacion());
            
             prep.setString(32, revision.getCortezaDanada());
             prep.setString(33, revision.getCaidaRamas());
             prep.setString(34, revision.getCaidaHojas());
             prep.setString(35, revision.getCaidaFrutos());
             prep.setString(36, revision.getBrotesDanados());
             prep.setString(37, revision.getAmarillo());
             prep.setString(38, revision.getAgallasHojas());
             prep.setString(39, revision.getHojasChinas());
             prep.setString(40, revision.getHojasAmarillas());
             
             prep.setString(41, revision.getCenizaVolcanica());
             prep.setString(42, revision.getFaltaAgua());
             prep.setString(43, revision.getIncendio());
             prep.setString(44, revision.getPlaga());
             
            
             prep.setString(45, revision.getIdTemporal());
      
             conexion.setAutoCommit(false);
             prep.executeUpdate();
             conexion.setAutoCommit(true);
            
        }catch(Exception e){
             loggerHuerta.error(e.getLocalizedMessage() + "," + e.getMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                loggerHuerta.error(e.getLocalizedMessage() + "," + e.getMessage());
             }  
         }
    }
    
      public List<Revision> selectAll(){
        List<Revision> revisiones =  new ArrayList<Revision>();
        conectar();
          try{
                sqry = "SELECT * FROM Revision";
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                        Revision revision = new Revision(rs.getString("Id"), rs.getString("IdTemporal"),rs.getString("IdArbol"),rs.getString("IdHuerta"),rs.getString("IdAsesoriaTecnica"),rs.getString("Tumores"),rs.getString("Seco"), rs.getString("pudricionRadicular"),rs.getString("pudricionFruto"),rs.getString("pocoAmarre"),rs.getString("pocaHoja"),
                                                         rs.getString("Pigmeo"),rs.getString("OxidacionHojas"), rs.getString("Otro"), rs.getString("Necrosis"), rs.getString("MarcasHojasFrutosTallos"), rs.getString("ManchasCorchosas"), rs.getString("LesionesSol"), rs.getString("HuesoBarrenado"), rs.getString("Hueco"), rs.getString("HojasVerdeOscuro"),
                                                         rs.getString("HojasVerdeAmarillento"), rs.getString("HojasSecas"), rs.getString("HojasManchadas"), rs.getString("HojasCloroticas"), rs.getString("HojaChica"), rs.getString("Helada"), rs.getString("Granizada"), rs.getString("FrutoMalformacion"),rs.getString("FloracionRetrasada"), rs.getString("Defoliacion"),
                                                         rs.getString("CortezaDanada"),rs.getString("CaidaRamas"), rs.getString("CaidaHojas"), rs.getString("CaidaFrutos"), rs.getString("BrotesDanados"),rs.getString("Amarillo"), rs.getString("AgallasHojas"),rs.getString("HojasChinas"),rs.getString("HojasAmarillas"),
                                                         rs.getString("CenizaVolcanica"),rs.getString("FaltaAgua"),rs.getString("Incendio"),rs.getString("Plaga"));
                        revisiones.add(revision);
                        
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
        
        return revisiones;
    
    }
    
     public List<Revision> select(String sWhere){
        List<Revision> revisiones =  new ArrayList<Revision>();
        conectar();
          try{
                sqry = "SELECT * FROM Revision WHERE " + sWhere;
                ResultSet rs = statement.executeQuery(sqry);
                while ( rs.next() ) {
                        Revision revision = new Revision(rs.getString("Id"), rs.getString("IdTemporal"),rs.getString("IdArbol"),rs.getString("IdHuerta"),rs.getString("IdAsesoriaTecnica"),rs.getString("Tumores"),rs.getString("Seco"), rs.getString("pudricionRadicular"),rs.getString("pudricionFruto"),rs.getString("pocoAmarre"),rs.getString("pocaHoja"),
                                                         rs.getString("Pigmeo"),rs.getString("OxidacionHojas"), rs.getString("Otro"), rs.getString("Necrosis"), rs.getString("MarcasHojasFrutosTallos"), rs.getString("ManchasCorchosas"), rs.getString("LesionesSol"), rs.getString("HuesoBarrenado"), rs.getString("Hueco"), rs.getString("HojasVerdeOscuro"),
                                                         rs.getString("HojasVerdeAmarillento"), rs.getString("HojasSecas"), rs.getString("HojasManchadas"), rs.getString("HojasCloroticas"), rs.getString("HojaChica"), rs.getString("Helada"), rs.getString("Granizada"), rs.getString("FrutoMalformacion"),rs.getString("FloracionRetrasada"), rs.getString("Defoliacion"),
                                                         rs.getString("CortezaDanada"),rs.getString("CaidaRamas"), rs.getString("CaidaHojas"), rs.getString("CaidaFrutos"), rs.getString("BrotesDanados"),rs.getString("Amarillo"), rs.getString("AgallasHojas"),rs.getString("HojasChinas"),rs.getString("HojasAmarillas"),
                                                         rs.getString("CenizaVolcanica"),rs.getString("FaltaAgua"),rs.getString("Incendio"),rs.getString("Plaga"));
                        revisiones.add(revision);
                        
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
        
        return revisiones;
    
    }
}

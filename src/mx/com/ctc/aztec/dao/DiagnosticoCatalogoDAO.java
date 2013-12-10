    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.com.ctc.aztec.model.AgenteCausal;
import mx.com.ctc.aztec.model.DiagnosticoCatalogo;

/**
 *
 * @author OSCAR
 */
public class DiagnosticoCatalogoDAO  extends Conexion{
    String str = "";
    public List<DiagnosticoCatalogo> selectDiagnostico(String subtipo){
         conectar();
         List<DiagnosticoCatalogo> diagnosticos = new ArrayList();
         str = "SELECT id, Subtipo,Diagnostico FROM DiagnosticoCatalogo WHERE subtipo = '" + subtipo + "'";
         try{
             ResultSet rs = statement.executeQuery(str);
             while ( rs.next() ) {
                  DiagnosticoCatalogo diagnostico =  new DiagnosticoCatalogo(rs.getString("Id"),rs.getString("Subtipo"),rs.getString("Diagnostico"));
                  diagnosticos.add(diagnostico);
             }
         }catch(Exception e){
         
         }
        return diagnosticos;
    }
    
    public List<AgenteCausal> selectAgenteCausal(String diagnostico){
         conectar();
         List<AgenteCausal> agentes = new ArrayList();
         str = "SELECT id,Diagnostico,Agente FROM AgenteCausalCatalogo WHERE Diagnostico = '" + diagnostico + "'";
        // System.out.println(str);
         try{
             ResultSet rs = statement.executeQuery(str);
             while ( rs.next() ) {
                  AgenteCausal agente =  new AgenteCausal(rs.getString("Id"),rs.getString("Diagnostico"),rs.getString("Agente"));
                  agentes.add(agente);
             }
         }catch(Exception e){
         
         }
        return agentes;
    }
}

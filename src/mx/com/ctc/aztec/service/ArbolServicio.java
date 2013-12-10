/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.service;


import java.util.List;
import mx.com.ctc.aztec.dao.ArbolDAO;
import mx.com.ctc.aztec.model.Arbol;
import mx.com.ctc.aztec.utils.Constantes;
import mx.com.ctc.aztec.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class ArbolServicio {
    private ArbolDAO dao = new ArbolDAO();
    static Logger loggerAS =  LoggerFactory.getLogger(ArbolDAO.class);
    
    public void insert(String id, String idUltimaRevision, String name, String placa, String estatus, String replantado, String vivero, 
                String produccionEstimada, String notas, String numeroEstacion, String fechaPlantacion, String variedad, 
                    String tipo, String elevacion, String latitud, String longitud, String qr, String claveUnica, String ubicacionFila, 
                    String ubicacionColumna, String idHuerta,String injertado,String idTemporal,String modificado, String mensaje,String modificadoProduccion,String anioProduccion,String insertado)
    {
        Arbol arbol = new Arbol();
        //String respuesta = Constantes.MSG_EXITO_GUARDADO;
        if(!id.trim().equals("") && !idHuerta.trim().equals("")){
            arbol.setId(id);
            arbol.setIdHuerta(idHuerta);
            arbol.setIdUltimaRevision(idUltimaRevision);
            arbol.setArbol(name);
            arbol.setPlaca(placa);
            arbol.setEstatus(estatus);
            arbol.setReplantado(replantado);
            arbol.setVivero(vivero);
            arbol.setProduccionEstimada(produccionEstimada);
            arbol.setNotas(notas);
            arbol.setNumeroEstacion(numeroEstacion);
            arbol.setFechaPlantacion(fechaPlantacion);
            arbol.setVariedad(variedad);
            arbol.setTipo(tipo);
            arbol.setElevacion(elevacion);
            arbol.setLatitud(latitud);
            arbol.setLongitud(longitud);
            arbol.setQr(qr);
            arbol.setClaveUnica(claveUnica);
            arbol.setUbicacionFila(ubicacionFila);
            arbol.setUbicacionColumna(ubicacionColumna);
            arbol.setInjertado(injertado);
            arbol.setIdTemporal(idTemporal);
            arbol.setModificado(modificado);
            arbol.setMensaje(mensaje);
            arbol.setModificadoProduccion(modificadoProduccion);
            arbol.setAnioProduccion(anioProduccion);
            arbol.setInsertado(insertado);
            dao.insert(arbol);
        }
        
    }
    
    public void delete(String id){
          dao.delete(id);
    }
    
    public String update(Arbol arbol){
        String  mensaje = Constantes.MSG_EXITO_GUARDADO;
        dao.update(arbol);
        return mensaje;
    }
    
    public List<Arbol> selectAll(String huertaId){
        String str = " IdHuerta = '" + huertaId + "' ORDER BY idHuerta,Placa DESC";
        return dao.select(str);
    }
    
    public List<Arbol> select(String sQuery){
        return dao.select(sQuery);
    }
    
    public void insert(Arbol arbol){
        dao.insert(arbol);
    }
}

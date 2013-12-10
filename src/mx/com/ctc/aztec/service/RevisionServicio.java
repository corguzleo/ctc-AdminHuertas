/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.service;

import java.util.List;
import mx.com.ctc.aztec.dao.ArbolDAO;
import mx.com.ctc.aztec.dao.RevisionDAO;
import mx.com.ctc.aztec.model.Revision;
import mx.com.ctc.aztec.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class RevisionServicio {
    private RevisionDAO dao = new RevisionDAO();
    static Logger loggerRS =  LoggerFactory.getLogger(ArbolDAO.class);
    
    public Integer insert(Revision revision){
     return dao.insert(revision);
    }
    
    public Integer insert(String id, String idTemporal, String idArbol, String idHuerta,String idAsesoriaTecnica, String tumores, String seco, String pudricionRadicular, String pudricionFruto, String pocoAmarre, String pocaHoja,
                       String pigmeo, String oxidacionHojas, String otro, String necrosis, String marcasHojasFrutosTallos, String manchasCorchosas, String lesionesSol, String huesoBarrenado, String hueco, String hojasVerdesOscuro,
                       String hojasVerdeAmarillento, String hojasSecas, String hojasManchadas, String hojasCloroticas, String hojaChica, String helada, String granizada, String frutoMalformacion, String floracionRetrasada, String defoliacion, 
                       String cortezaDanada, String caidaRamas, String caidaHojas, String caidaFrutos, String brotesDanados, String amarillo, String agallasHojas, String hojasChinas, String hojasAmarillas,
                       String cenicaVolcanica, String faltaAgua, String incendio, String plaga) {
        Revision r =  new Revision();
        r.setId(id);
        r.setIdTemporal(idTemporal);
        r.setIdArbol(idArbol);
        r.setIdHuerta(idHuerta);
        r.setIdAsesoriaTecnica(idAsesoriaTecnica);
        r.setTumores(tumores);
        r.setSeco(seco);
        r.setPudricionRadicular(pudricionRadicular);
        r.setPudricionFruto(pudricionFruto);
        r.setPocoAmarre(pocoAmarre);
        r.setPocaHoja(pocaHoja);
        r.setPigmeo(pigmeo);
        r.setOxidacionHojas(oxidacionHojas);
        r.setOtro(otro);
        r.setNecrosis(necrosis);
        r.setMarcasHojasFrutosTallos(marcasHojasFrutosTallos);
        r.setManchasCorchosas(manchasCorchosas);
        r.setLesionesSol(seco);
        r.setHuesoBarrenado(huesoBarrenado);
        r.setHueco(hueco);
        r.setHojasVerdesOscuro(hojasVerdesOscuro);
        r.setHojasVerdeAmarillento(hojasVerdeAmarillento);
        r.setHojasSecas(hojasSecas);
        r.setHojasManchadas(hojasManchadas);
        r.setHojasCloroticas(hojasCloroticas);
        r.setHojaChica(hojaChica);
        r.setHelada(helada);
        r.setGranizada(granizada);
        r.setFrutoMalformacion(frutoMalformacion);
        r.setFloracionRetrasada(floracionRetrasada);
        r.setDefoliacion(defoliacion);
        r.setCortezaDanada(cortezaDanada);
        r.setCaidaRamas(caidaRamas);
        r.setCaidaHojas(caidaHojas);
        r.setCaidaFrutos(caidaFrutos);
        r.setBrotesDanados(brotesDanados);
        r.setAmarillo(amarillo);
        r.setAgallasHojas(agallasHojas);
        r.setHojasChinas(hojasChinas);
        r.setHojasAmarillas(hojasAmarillas);
        r.setCenizaVolcanica(cenicaVolcanica);
        r.setFaltaAgua(faltaAgua);
        r.setIncendio(incendio);
        r.setPlaga(plaga);
        
        return dao.insert(r);
    }
    
    public void delete(Revision revision){
        dao.delete(revision);
    }
    public void delete(String idHuerta){
        dao.deleteByHuerta(idHuerta);
    }
    
    public String update(Revision revision){
        String mensaje = Constantes.MSG_EXITO_GUARDADO;
       
        dao.update(revision);
        
        return mensaje;
    }
    
    public List<Revision> selectAll(String arbolId){
        String str = " IdArbol = '" + arbolId + "'";
        return dao.select(str);
    }
    
    
    public List<Revision> select(String sWhere){
        return dao.select(sWhere);
    }
    
    
}

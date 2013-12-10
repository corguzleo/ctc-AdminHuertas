/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.model;

/**
 *
 * @author OSCAR
 */
public class Revision {
    private String id;
    private String idTemporal;
    private String idArbol;
    private String idHuerta;
    private String idAsesoriaTecnica; 
    private String tumores;
    private String seco;
    private String pudricionRadicular;
    private String pudricionFruto;
    private String pocoAmarre;
    private String pocaHoja;
    private String pigmeo;
    private String oxidacionHojas;
    private String otro;
    private String necrosis;
    private String marcasHojasFrutosTallos;
    private String manchasCorchosas;
    private String lesionesSol;
    private String huesoBarrenado;
    private String hueco;
    private String hojasVerdesOscuro;
    private String hojasVerdeAmarillento;
    private String hojasSecas;
    private String hojasManchadas;
    private String hojasCloroticas;
    private String hojasChinas;
    private String hojasAmarillas;
    private String hojaChica;
    private String helada;
    private String granizada;
    private String frutoMalformacion;
    private String floracionRetrasada;
    private String defoliacion;
    private String cortezaDanada;
    private String caidaRamas;
    private String caidaHojas;
    private String caidaFrutos;
    private String brotesDanados;
    private String amarillo;
    private String agallasHojas;
    
    private String cenizaVolcanica;
    private String faltaAgua;
    private String incendio;
    private String plaga;
    
    public Revision(){
    }
    public Revision(String id, String idTemporal, String idArbol, String idHuerta,String idAsesoriaTecnica, String tumores, String seco, String pudricionRadicular, String pudricionFruto, String pocoAmarre, String pocaHoja, String pigmeo, String oxidacionHojas, String otro, String necrosis, String marcasHojasFrutosTallos, String manchasCorchosas, String lesionesSol, String huesoBarrenado, String hueco, String hojasVerdesOscuro, String hojasVerdeAmarillento, String hojasSecas, String hojasManchadas, String hojasCloroticaS, String hojaChica, String helada, String granizada, String frutoMalformacion, String floracionRetrasada, String defoliacion, String cortezaDanada, String caidaRamas, String caidaHojas, String caidaFrutos, String brotesDanados, 
                   String amarillo, String agallasHojas,String hojasChinas,String hojasAmarillas,String cenizaVolcanica, String faltaAgua,String incendio, String plaga) {
        this.id = id;
        this.idTemporal = idTemporal;
        this.idArbol = idArbol;
        this.idHuerta = idHuerta;
        this.idAsesoriaTecnica = idAsesoriaTecnica;
        this.tumores = tumores;
        this.seco = seco;
        this.pudricionRadicular = pudricionRadicular;
        this.pudricionFruto = pudricionFruto;
        this.pocoAmarre = pocoAmarre;
        this.pocaHoja = pocaHoja;
        this.pigmeo = pigmeo;
        this.oxidacionHojas = oxidacionHojas;
        this.otro = otro;
        this.necrosis = necrosis;
        this.marcasHojasFrutosTallos = marcasHojasFrutosTallos;
        this.manchasCorchosas = manchasCorchosas;
        this.lesionesSol = lesionesSol;
        this.huesoBarrenado = huesoBarrenado;
        this.hueco = hueco;
        this.hojasVerdesOscuro = hojasVerdesOscuro;
        this.hojasVerdeAmarillento = hojasVerdeAmarillento;
        this.hojasSecas = hojasSecas;
        this.hojasManchadas = hojasManchadas;
        this.hojasCloroticas = hojasCloroticaS;
        this.hojaChica = hojaChica;
        this.helada = helada;
        this.granizada = granizada;
        this.frutoMalformacion = frutoMalformacion;
        this.floracionRetrasada = floracionRetrasada;
        this.defoliacion = defoliacion;
        this.cortezaDanada = cortezaDanada;
        this.caidaRamas = caidaRamas;
        this.caidaHojas = caidaHojas;
        this.caidaFrutos = caidaFrutos;
        this.brotesDanados = brotesDanados;
        this.amarillo = amarillo;
        this.agallasHojas = agallasHojas;
        this.hojasChinas = hojasChinas;
        this.hojasAmarillas = hojasAmarillas;
        
        this.cenizaVolcanica = cenizaVolcanica;
        this.faltaAgua = faltaAgua;
        this.incendio = incendio;
        this.plaga = plaga;
    }

    public String getIdHuerta() {
        return idHuerta;
    }

    public void setIdHuerta(String idHuerta) {
        this.idHuerta = idHuerta;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(String idTemporal) {
        this.idTemporal = idTemporal;
    }

    public String getIdArbol() {
        return idArbol;
    }

    public void setIdArbol(String idArbol) {
        this.idArbol = idArbol;
    }

    public String getIdAsesoriaTecnica() {
        return idAsesoriaTecnica;
    }

    public void setIdAsesoriaTecnica(String idAsesoriaTecnica) {
        this.idAsesoriaTecnica = idAsesoriaTecnica;
    }

    public String getTumores() {
        return tumores;
    }

    public void setTumores(String tumores) {
        this.tumores = tumores;
    }

    public String getSeco() {
        return seco;
    }

    public void setSeco(String seco) {
        this.seco = seco;
    }

    public String getPudricionRadicular() {
        return pudricionRadicular;
    }

    public void setPudricionRadicular(String pudricionRadicular) {
        this.pudricionRadicular = pudricionRadicular;
    }

    public String getPudricionFruto() {
        return pudricionFruto;
    }

    public void setPudricionFruto(String pudricionFruto) {
        this.pudricionFruto = pudricionFruto;
    }

    public String getPocoAmarre() {
        return pocoAmarre;
    }

    public void setPocoAmarre(String pocoAmarre) {
        this.pocoAmarre = pocoAmarre;
    }

    public String getPocaHoja() {
        return pocaHoja;
    }

    public void setPocaHoja(String pocaHoja) {
        this.pocaHoja = pocaHoja;
    }

    public String getPigmeo() {
        return pigmeo;
    }

    public void setPigmeo(String pigmeo) {
        this.pigmeo = pigmeo;
    }

    public String getOxidacionHojas() {
        return oxidacionHojas;
    }

    public void setOxidacionHojas(String oxidacionHojas) {
        this.oxidacionHojas = oxidacionHojas;
    }

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public String getNecrosis() {
        return necrosis;
    }

    public void setNecrosis(String necrosis) {
        this.necrosis = necrosis;
    }

    public String getMarcasHojasFrutosTallos() {
        return marcasHojasFrutosTallos;
    }

    public void setMarcasHojasFrutosTallos(String marcasHojasFrutosTallos) {
        this.marcasHojasFrutosTallos = marcasHojasFrutosTallos;
    }

    public String getManchasCorchosas() {
        return manchasCorchosas;
    }

    public void setManchasCorchosas(String manchasCorchosas) {
        this.manchasCorchosas = manchasCorchosas;
    }

    public String getLesionesSol() {
        return lesionesSol;
    }

    public void setLesionesSol(String lesionesSol) {
        this.lesionesSol = lesionesSol;
    }

    public String getHuesoBarrenado() {
        return huesoBarrenado;
    }

    public void setHuesoBarrenado(String huesoBarrenado) {
        this.huesoBarrenado = huesoBarrenado;
    }

    public String getHueco() {
        return hueco;
    }

    public void setHueco(String hueco) {
        this.hueco = hueco;
    }

    public String getHojasVerdesOscuro() {
        return hojasVerdesOscuro;
    }

    public void setHojasVerdesOscuro(String hojasVerdesOscuro) {
        this.hojasVerdesOscuro = hojasVerdesOscuro;
    }

    public String getHojasVerdeAmarillento() {
        return hojasVerdeAmarillento;
    }

    public void setHojasVerdeAmarillento(String hojasVerdeAmarillento) {
        this.hojasVerdeAmarillento = hojasVerdeAmarillento;
    }

    public String getHojasSecas() {
        return hojasSecas;
    }

    public void setHojasSecas(String hojasSecas) {
        this.hojasSecas = hojasSecas;
    }

    public String getHojasManchadas() {
        return hojasManchadas;
    }

    public void setHojasManchadas(String hojasManchadas) {
        this.hojasManchadas = hojasManchadas;
    }

    public String getHojasCloroticas() {
        return hojasCloroticas;
    }

    public void setHojasCloroticas(String hojasCloroticaS) {
        this.hojasCloroticas = hojasCloroticaS;
    }

    public String getHojaChica() {
        return hojaChica;
    }

    public void setHojaChica(String hojaChica) {
        this.hojaChica = hojaChica;
    }

    public String getHelada() {
        return helada;
    }

    public void setHelada(String helada) {
        this.helada = helada;
    }

    public String getGranizada() {
        return granizada;
    }

    public void setGranizada(String granizada) {
        this.granizada = granizada;
    }

    public String getFrutoMalformacion() {
        return frutoMalformacion;
    }

    public void setFrutoMalformacion(String frutoMalformacion) {
        this.frutoMalformacion = frutoMalformacion;
    }

    public String getFloracionRetrasada() {
        return floracionRetrasada;
    }

    public void setFloracionRetrasada(String floracionRetrasada) {
        this.floracionRetrasada = floracionRetrasada;
    }

    public String getDefoliacion() {
        return defoliacion;
    }

    public void setDefoliacion(String defoliacion) {
        this.defoliacion = defoliacion;
    }

    public String getCortezaDanada() {
        return cortezaDanada;
    }

    public void setCortezaDanada(String cortezaDanada) {
        this.cortezaDanada = cortezaDanada;
    }

    public String getCaidaRamas() {
        return caidaRamas;
    }

    public void setCaidaRamas(String caidaRamas) {
        this.caidaRamas = caidaRamas;
    }

    public String getCaidaHojas() {
        return caidaHojas;
    }

    public void setCaidaHojas(String caidaHojas) {
        this.caidaHojas = caidaHojas;
    }

    public String getCaidaFrutos() {
        return caidaFrutos;
    }

    public void setCaidaFrutos(String caidaFrutos) {
        this.caidaFrutos = caidaFrutos;
    }

    public String getBrotesDanados() {
        return brotesDanados;
    }

    public void setBrotesDanados(String brotesDanados) {
        this.brotesDanados = brotesDanados;
    }

    public String getAmarillo() {
        return amarillo;
    }

    public void setAmarillo(String amarillo) {
        this.amarillo = amarillo;
    }

    public String getAgallasHojas() {
        return agallasHojas;
    }

    public void setAgallasHojas(String agallasHojas) {
        this.agallasHojas = agallasHojas;
    }

    public String getHojasChinas() {
        return hojasChinas;
    }

    public void setHojasChinas(String hojasChinas) {
        this.hojasChinas = hojasChinas;
    }

    public String getHojasAmarillas() {
        return hojasAmarillas;
    }

    public void setHojasAmarillas(String hojasAmarillas) {
        this.hojasAmarillas = hojasAmarillas;
    }

    public String getCenizaVolcanica() {
        return cenizaVolcanica;
    }

    public void setCenizaVolcanica(String cenizaVolcanica) {
        this.cenizaVolcanica = cenizaVolcanica;
    }

    public String getFaltaAgua() {
        return faltaAgua;
    }

    public void setFaltaAgua(String faltaAgua) {
        this.faltaAgua = faltaAgua;
    }

    public String getIncendio() {
        return incendio;
    }

    public void setIncendio(String incendio) {
        this.incendio = incendio;
    }

    public String getPlaga() {
        return plaga;
    }

    public void setPlaga(String plaga) {
        this.plaga = plaga;
    }
    
    
}

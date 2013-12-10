/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.model;


/**
 *
 * @author OSCAR
 */
public class Arbol {
    private String id;
    private String idTemporal;
    private String idUltimaRevision;
    private String arbol;
    private String estatus;
    private String replantado;
    private String injertado;
    private String vivero;
    private String produccionEstimada;
    private String notas;
    private String numeroEstacion;
    private String fechaPlantacion;
    private String variedad;
    private String tipo;
    private String elevacion;
    private String latitud;
    private String longitud;
    private String qr;
    private String claveUnica;
    private String ubicacionFila;
    private String ubicacionColumna;
    private String idHuerta;
    private String placa;
    private String mensaje;
    private String modificado;
    private String modificadoProduccion;
    private String anioProduccion;
    private String insertado;
    private String mesPlantacion;
    private String anioPlantacion;
    
    public Arbol(){
    }

    public Arbol(String id, String idUltimaRevision, String arbol, String placa, String estatus, String replantado, String vivero, 
                String produccionEstimada, String notas, String numeroEstacion, String fechaPlantacion, String variedad, 
                    String tipo, String elevacion, String latitud, String longitud, String qr, String claveUnica, String ubicacionFila, 
                    String ubicacionColumna, String idHuerta,String injertado,String idTemporal,String modificado,String mensaje,String modificadoProduccion,String anioProduccion,String insertado) {
        this.id = id;
        this.idUltimaRevision = idUltimaRevision;
        this.arbol = arbol;
        this.placa = placa;
        this.estatus = estatus;
        this.replantado = replantado;
        this.vivero = vivero;
        this.produccionEstimada = produccionEstimada;
        this.notas = notas;
        this.numeroEstacion = numeroEstacion;
        this.fechaPlantacion = fechaPlantacion;
        this.variedad = variedad;
        this.tipo = tipo;
        this.elevacion = elevacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.qr = qr;
        this.claveUnica = claveUnica;
        this.ubicacionFila = ubicacionFila;
        this.ubicacionColumna = ubicacionColumna;
        this.idHuerta = idHuerta;
        this.injertado = injertado;
        this.idTemporal = idTemporal;
        this.modificado = modificado;
        this.mensaje = mensaje;
        this.modificadoProduccion = modificadoProduccion;
        this.anioProduccion = anioProduccion;
        this.insertado = insertado;
    }

    public String getModificadoProduccion() {
        return modificadoProduccion;
    }

    public void setModificadoProduccion(String modificadoProduccion) {
        this.modificadoProduccion = modificadoProduccion;
    }
    
    

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getModificado() {
        return modificado;
    }

    public void setModificado(String modificado) {
        this.modificado = modificado;
    }
    
    public String getInjertado() {
        return injertado;
    }

    public void setInjertado(String injertado) {
        this.injertado = injertado;
    }
   
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getIdUltimaRevision() {
        return idUltimaRevision;
    }

    public void setIdUltimaRevision(String idUltimaRevision) {
        this.idUltimaRevision = idUltimaRevision;
    }

    public String getEstatus() {
        if(estatus == null){
            return "";
        }else{
            return estatus;
        }
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getReplantado() {
        return replantado;
    }

    public void setReplantado(String replantado) {
        this.replantado = replantado;
    }

    public String getVivero() {
        return vivero;
    }

    public void setVivero(String vivero) {
        this.vivero = vivero;
    }

    public String getProduccionEstimada() {
        return produccionEstimada;
    }

    public void setProduccionEstimada(String produccionEstimada) {
        this.produccionEstimada = produccionEstimada;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getNumeroEstacion() {
        if(numeroEstacion == null || numeroEstacion.equals("null")){
            return "";
        }else{
            return numeroEstacion;
        }
    }

    public void setNumeroEstacion(String numeroEstacion) {
        this.numeroEstacion = numeroEstacion;
    }

    public String getFechaPlantacion() {
        return fechaPlantacion;
    }

    public void setFechaPlantacion(String fechaPlantacion) {
        this.fechaPlantacion = fechaPlantacion;
    }

    public String getVariedad() {
        if(variedad == null){
            return "";
        }else{
            return variedad;
        }
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getTipo() {
        if(tipo == null){
            return "";
        }else{
            return tipo;
        }
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getElevacion() {
       if(elevacion == null || elevacion.equals("null")){
            return "";
        }else{
            return elevacion;
        }
    }

    public void setElevacion(String elevacion) {
        this.elevacion = elevacion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getClaveUnica() {
        return claveUnica;
    }

    public void setClaveUnica(String claveUnica) {
        this.claveUnica = claveUnica;
    }

    public String getUbicacionFila() {
        return ubicacionFila;
    }

    public void setUbicacionFila(String ubicacionFila) {
        this.ubicacionFila = ubicacionFila;
    }

    public String getUbicacionColumna() {
        return ubicacionColumna;
    }

    public void setUbicacionColumna(String ubicacionColumna) {
        this.ubicacionColumna = ubicacionColumna;
    }

    public String getIdHuerta() {
        return idHuerta;
    }

    public void setIdHuerta(String idHuerta) {
        this.idHuerta = idHuerta;
    }

    public String getArbol() {
        return arbol;
    }

    public void setArbol(String Arbol) {
        this.arbol = Arbol;
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

    public String getAnioProduccion() {
        if(anioProduccion == null){
            return "";
        }else{
            return anioProduccion;
        }
    }

    public void setAnioProduccion(String anioProduccion) {
        this.anioProduccion = anioProduccion;
    }

    public String getInsertado() {
        if(insertado == null || insertado.equals("null")){
            return "";
        }else{
            return insertado;
        }
    }

    public void setInsertado(String insertado) {
        this.insertado = insertado;
    }

    public String getMesPlantacion() {
        if(mesPlantacion == null){
            return "";
        }else{
            return mesPlantacion;
        }
    }

    public void setMesPlantacion(String mesPlantacion) {
        this.mesPlantacion = mesPlantacion;
    }

    public String getAnioPlantacion() {  
        if(anioPlantacion == null){
            return "";
        }else{
            return anioPlantacion;
        }
    }

    public void setAnioPlantacion(String anioPlantacion) {
        this.anioPlantacion = anioPlantacion;
    }
    
    

}

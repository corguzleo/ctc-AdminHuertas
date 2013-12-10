/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.model;

import java.util.Date;

/**
 *
 * @author OSCAR
 */
public class AsesoriaTecnica {
    private String id;
    private String idTemporal;
    private String idHuerta;
    private String Huerta;
    private String Asesoria;
    private String FechaAsesoria;
    private String Folio;
    private String insectosBeneficos;
    private String instalaciones;
    private String maleza;
    private String basura;
    private String comentariosLimpieza;
    private String observacionesAsesor;
    
    public AsesoriaTecnica(){
    }
    
    public AsesoriaTecnica(String id, String idTemporal, String idHuerta, String Huerta, String Asesoria, String FechaAsesoria, String Folio, String insectosBeneficos, String instalaciones, String maleza, String basura, String comentariosLimpieza, String observacionesAsesor) {
        this.id = id;
        this.idTemporal = idTemporal;
        this.idHuerta = idHuerta;
        this.Huerta = Huerta;
        this.Asesoria = Asesoria;
        this.FechaAsesoria = FechaAsesoria;
        this.Folio = Folio;
        this.insectosBeneficos = insectosBeneficos;
        this.instalaciones = instalaciones;
        this.maleza = maleza;
        this.basura = basura;
        this.comentariosLimpieza = comentariosLimpieza;
        this.observacionesAsesor = observacionesAsesor;
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

    public String getIdHuerta() {
        return idHuerta;
    }

    public void setIdHuerta(String idHuerta) {
        this.idHuerta = idHuerta;
    }

    public String getHuerta() {
        return Huerta;
    }

    public void setHuerta(String Huerta) {
        this.Huerta = Huerta;
    }

    public String getAsesoria() {
        return Asesoria;
    }

    public void setAsesoria(String Asesoria) {
        this.Asesoria = Asesoria;
    }

    public String getFechaAsesoria() {
        return FechaAsesoria;
    }

    public void setFechaAsesoria(String FechaAsesoria) {
        this.FechaAsesoria = FechaAsesoria;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String Folio) {
        this.Folio = Folio;
    }

    public String getInsectosBeneficos() {
        return insectosBeneficos;
    }

    public void setInsectosBeneficos(String insectosBeneficos) {
        this.insectosBeneficos = insectosBeneficos;
    }

    public String getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(String instalaciones) {
        this.instalaciones = instalaciones;
    }

    public String getMaleza() {
        return maleza;
    }

    public void setMaleza(String maleza) {
        this.maleza = maleza;
    }

    public String getBasura() {
        return basura;
    }

    public void setBasura(String basura) {
        this.basura = basura;
    }

    public String getComentariosLimpieza() {
        return comentariosLimpieza;
    }

    public void setComentariosLimpieza(String comentariosLimpieza) {
        this.comentariosLimpieza = comentariosLimpieza;
    }

    public String getObservacionesAsesor() {
        return observacionesAsesor;
    }

    public void setObservacionesAsesor(String observacionesAsesor) {
        this.observacionesAsesor = observacionesAsesor;
    }

    
}

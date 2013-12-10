    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.model;

/**
 *
 * @author OSCAR
 */
public class Huerta {
    private String id;
    private String idHuerta;
    private String huerta;
    private String productor;
    private String ubicacion;
    private String idAsesoriaTecnica;
    private String fechaAsesoriaTecnica;
    private String modificado;
    private String fechaModificacion;
    private String tipoRegistro;
   
    
    public Huerta(){
    
    }
    public Huerta(String id, String idHuerta,String huerta, String productor,String ubicacion,String idAsesoriaTecnica,String fechaAsesoriaTecnica,String modificado, String fechaModificacion,
                    String tipoRegistro) {
        this.id = id;
        this.idHuerta = idHuerta;
        this.huerta = huerta;
        this.productor = productor;
        this.ubicacion = ubicacion;
        this.idAsesoriaTecnica = idAsesoriaTecnica;
        this.fechaAsesoriaTecnica = fechaAsesoriaTecnica;
        this.modificado = modificado;
        this.fechaModificacion = fechaModificacion;
        this.tipoRegistro = tipoRegistro;
    }

    public String getIdHuerta() {
        return idHuerta;
    }

    public void setIdHuerta(String idHuerta) {
        this.idHuerta = idHuerta;
    }
    
    
    public String getFechaAsesoriaTecnica() {
        return fechaAsesoriaTecnica;
    }

    public void setFechaAsesoriaTecnica(String FechaAsesoriaTecnica) {
        this.fechaAsesoriaTecnica = FechaAsesoriaTecnica;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHuerta() {
        return huerta;
    }

    public void setHuerta(String huerta) {
        this.huerta = huerta;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getIdAsesoriaTecnica() {
        return idAsesoriaTecnica;
    }

    public void setIdAsesoriaTecnica(String idAsesoriaTecnica) {
        this.idAsesoriaTecnica = idAsesoriaTecnica;
    }

    public String getModificado() {
        return modificado;
    }

    public void setModificado(String modificado) {
        this.modificado = modificado;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.test;

import com.sforce.soap.enterprise.sobject.Huerta__c;
import mx.com.ctc.aztec.control.ConexionSF;
import mx.com.ctc.aztec.control.HuertaAcciones;
import mx.com.ctc.aztec.dao.Conexion;


/**
 *
 * @author OSCAR
 */
public class Test {
    public static void main(String[] args) {
        Conexion coneccion = new Conexion();
        coneccion.crearTablas();
        
        ConexionSF.user = "admin@mundi.com.mx.sandbox";
        ConexionSF.pwd = "12345aztecpride";
        
        HuertaAcciones ha = new HuertaAcciones();
    
       
        //ha.insertHuertaAPP(ha.getHuertaSF("a0KM0000004QaE0MAK"));
        //HuertaDAO hdao =  new HuertaDAO();
       // Huerta h = new Huerta("a0KM0000004QeBeT","TEST ZONAS","Petronilo Villalva","1/10/2013","");
        //Huerta h2 = new Huerta("a0KM0000004QeBe","TEST ZONAS","Petronilo Villalva","1/10/2013","");
        //hdao.delete(h2);
       // for(Huerta huerta:hdao.selectAll()){
        //    System.out.println("Hurta: " + huerta.getId());
        //}
        //hdao.insert(h2);
       // h.setProductor("Otro");
        //hdao.update(h);
    }
    
}

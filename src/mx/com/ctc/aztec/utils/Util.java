/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class Util {
    
    static Logger logger =  LoggerFactory.getLogger(Util.class);
    
    public static String monthToNumber(String mes){
        String numero = "";
        switch(mes){
            case "Enero":
                numero = "01";   
                break;
            case "Febrero":
                numero = "02";   
                break;
            case "Marzo":
                numero = "03";   
                break;
            case "Abril":
                numero = "04";   
                break;
            case "Mayo":
                numero = "05";   
                break;
            case "Junio":
                numero = "06";   
                break;
            case "Julio":
                numero = "07";   
                break;
            case "Agosto":
                numero = "08";   
                break;
            case "Septiembre":
                numero = "09";   
                break;
            case "Octubre":
                numero = "10";   
                break;
            case "Noviembre":
                numero = "11";   
                break;
            case "Diciembre":
                numero = "12";   
                break;
        }
        return numero;
    }
    
    public static String numberToMonth(Integer mes){
        String numero = "";
        switch(mes){
            case 1:
                numero = "Enero";   
                break;
            case 2:
                numero = "Febrero";   
                break;
            case 3:
                numero = "Marzo";   
                break;
            case 4:
                numero = "Abril";   
                break;
            case 5:
                numero = "Mayo";   
                break;
            case 6:
                numero = "Junio";   
                break;
            case 7:
                numero = "Julio";   
                break;
            case 8:
                numero = "Agosto";   
                break;
            case 9:
                numero = "Septiembre";   
                break;
            case 10:
                numero = "Octubre";   
                break;
            case 11:
                numero = "Noviembre";   
                break;
            case 12:
                numero = "Diciembre";   
                break;
        }
        return numero;
    }
    
    public static Date strToDate(String sFecha,String formato){
        Date fecha = null;
        SimpleDateFormat formatoTexto = new SimpleDateFormat(formato);
        try{
            if(!sFecha.equals("")){
                fecha = formatoTexto.parse(sFecha);
            }
        }catch(ParseException ex){
            logger.error(ex.getLocalizedMessage());
        }
        return fecha;
    }
    
    public static String toDay(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String fecha = dateFormat.format(cal.getTime());
        //System.out.println(fecha);
        return fecha;
    }
    
     public static String toDaySimple(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String fecha = dateFormat.format(cal.getTime());
        //System.out.println(fecha);
        return fecha;
    }
    
    public static Calendar stringToCalendar(String fecha){
        Calendar calendario = null;
        if(!isNull(fecha)){
            calendario = Calendar.getInstance();
            if(fecha.contains("/")){
                Integer year = Integer.valueOf(fecha.split("/")[2]);
                Integer month = Integer.valueOf(fecha.split("/")[1]);
                Integer date = Integer.valueOf(fecha.split("/")[0]);
                calendario.set(year, month-1, date);
            }
        }
        return calendario;
    }
    
    public static String calendarToString(Calendar fecha){
        String sf = "";
        if(!isNull(fecha)){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sf = sdf.format(fecha.getTime());
        }
        return sf; 
    }
    
    public static boolean isDate(String sFecha,String formato){
        SimpleDateFormat formatoTexto = new SimpleDateFormat(formato);
        try{
            if(!sFecha.equals("")){
               Date fecha = formatoTexto.parse(sFecha);
            }
        }catch(ParseException ex){
            return false;
        }
        return true;
    }
    
    public static boolean isNumber(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        int i = 0;
        if (string.charAt(0) == '-') {
            if (string.length() > 1) {
                i++;
            } else {
                return false;
            }
        }
        for (; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isDecimal(String string){
        try{
            Double d = Double.parseDouble(string);
        }catch(Exception e){
            System.out.println(e.getLocalizedMessage() + "," +e.getMessage());
            return false;
        }
               
        return true;
    }
    
    public static boolean isNull(Object object){
        if(object == null){
            return true;
        }else if(object.equals("")){
            return true;
        }
        return false;
    }
    
    public static String nullToStr(Object object){
        if(object == null){
            return "";
        }else{
            return object.toString();
        }
    }
    
    public  static class HuertaCombo{
        private String id;
        private String Name;
        
        @Override
        public String toString(){
            return  Name;
        }
        public HuertaCombo(String id, String Name) {
            this.id = id;
            this.Name = Name;
        }
        
        
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
        
    }
}

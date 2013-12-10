/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ctc.aztec.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author OSCAR
 */
public class Conexion {
    public Connection conexion;
    public Statement statement;
    
    public String ruta;
    
    static Logger logger =  LoggerFactory.getLogger(Conexion.class);
    
    public Conexion(String ruta){
        this.ruta = ruta;
    }
    
    public Conexion(){
        File logDir = new File("db");
        if(!logDir.exists()) {
           logger.info("Creando el directorio de la BD");
           logDir.mkdir();
        }
        this.ruta = "db/aztec.db";
    }
    public void conectar(){
        
            try{
                 Class.forName("org.sqlite.JDBC");
            }catch(ClassNotFoundException e){
                logger.error(e.getMessage());
            }

            try{
                conexion = DriverManager.getConnection("jdbc:sqlite:" + ruta);  
                statement = conexion.createStatement();

            }catch(Exception e){
                logger.error(e.getMessage() + " Ruta: " +  ruta);
            }
        }
    
    public void crearTablas(){
        conectar();
        Boolean existe = false;
        String qry = "";
        try {
            ResultSet rs =  statement.executeQuery("SELECT name as Tabla FROM sqlite_master WHERE type='table' AND name='Huerta'");
            while (rs.next()) {
                existe = true;
            }
            
            if(!existe){
              qry = "CREATE TABLE AgenteCausalCatalogo (Id INTEGER PRIMARY KEY, Diagnostico INTEGER, Agente TEXT); " +
                    "CREATE TABLE Arbol (ModificadoProduccion TEXT, Modificado TEXT, IdTemporal INTEGER PRIMARY KEY, Mensaje TEXT, Placa TEXT, IdUltimaRevision TEXT, Estatus TEXT, Replantado NUMERIC, Vivero TEXT, ProduccionEstimada NUMERIC, Notas TEXT, InjertadoFuerte TEXT, NumeroEstacion TEXT, FechaPlantacion TEXT, Variedad TEXT, Tipo TEXT, Elevacion TEXT, Longitud TEXT, Latitud TEXT, QR TEXT, " +
                                 "ClaveUnica TEXT, UbicacionColumna TEXT, UbicacionFila TEXT, IdHuerta TEXT, Arbol TEXT, Id TEXT,AnioProduccion TEXT,Insertado TEXT);" +
                    "CREATE TABLE ArbolDiagnostico (Fecha DATE, Id INTEGER PRIMARY KEY, idArbol TEXT, idDiagnostico TEXT); "+
                    "CREATE TABLE AsesoriaTecnica (ObservacionesAsesor TEXT, ComentariosLimpieza TEXT, Basura TEXT, Maleza TEXT, Instalaciones TEXT, InsectosBeneficos TEXT, Folio TEXT, IdTemporal INTEGER PRIMARY KEY, Huerta TEXT, Asesoria TEXT, Id TEXT, IdHuerta TEXT, FechaAsesoria DATE);" +
                    "CREATE TABLE Bitacora (IdArbol TEXT, Id INTEGER PRIMARY KEY, Fecha DATE, Descripcion TEXT); "+
                    "CREATE TABLE Diagnostico (IdTemporal INTEGER PRIMARY KEY, Id TEXT, Subtipo TEXT, Tipo TEXT, TipoInsercion TEXT, IdHuerta TEXT, fechaModificacion DATETIME, IdArbol , AgenteCausal TEXT, GradoAfeccion TEXT, SitioAfeccion TEXT, Diagnostico TEXT, IdRevision TEXT);" +
                    "CREATE TABLE DiagnosticoCatalogo (Subtipo TEXT, Id INTEGER PRIMARY KEY, Diagnostico TEXT); " +
                    "CREATE TABLE Huerta (Id TEXT, FechaModificado DATE, Modificado TEXT, IdHuerta TEXT, Ubicacion TEXT, FechaAsesoriaTecnica DATE, IdAsesoriaTecnica TEXT, Productor TEXT, Huerta TEXT,TipoRegistro TEXT); " +
                    "CREATE TABLE Revision (IdTemporal INTEGER PRIMARY KEY, IdHuerta TEXT, Tumores TEXT, Seco TEXT, PudricionRadicular TEXT, PudricionFruto TEXT, PocoAmarre TEXT, PocaHoja , Pigmeo TEXT, OxidacionHojas TEXT, Otro TEXT, Necrosis TEXT, MarcasHojasFrutosTallos TEXT, ManchasCorchosas TEXT, LesionesSol TEXT, HuesoBarrenado TEXT, Hueco TEXT, HojasVerdeOscuro TEXT, HojasVerdeAmarillento TEXT, "+
                                            " HojasSecas TEXT, HojasManchadas TEXT, HojasCloroticas TEXT, HojasChinas TEXT, HojasAmarillas TEXT, HojaChica TEXT, Helada TEXT, Granizada TEXT, FrutoMalformacion TEXT, FloracionRetrasada TEXT, Defoliacion TEXT, CortezaDanada TEXT, CaidaRamas TEXT, CaidaHojas TEXT, CaidaFrutos TEXT, BrotesDanados TEXT, IdAsesoriaTecnica TEXT, IdArbol TEXT, Amarillo TEXT, " +
                                            " AgallasHojas TEXT, Id TEXT,CenizaVolcanica TEXT,FaltaAgua TEXT, Incendio TEXT, Plaga TEXT);" +
                    "CREATE TABLE Usuario (GuardarSF TEXT, Token TEXT, PasswordSF TEXT, UserSF TEXT, Id INTEGER PRIMARY KEY, User TEXT, Password TEXT);" +
                    "INSERT INTO Usuario VALUES('false',NULL,NULL,NULL,1,'Admin','Admin');" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(1,'Tristeza del aguacatero','Phytophthora cinnamomi');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(2,'Marchitamiento','Verticillium albo-atrum');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(3,'Pudrición de raíz','Fusarium episphaeria');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(4,'Pudricióon texana','Phymatotrichum omnivorum');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(5,'Pudrición blanca','Rosselinia necatrix');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(6,'Pudricióon de raíz y tronco','Armillaria mellea');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(7,'Marhitez','Rizoctonia solani');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(8,'Marchitez de puntas','Glomerella cingulata');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(9,'Cáncer de tronco y ramas','Nectria galligena/Fusarium episphaeria');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(10,'Mancha de sol o Sun blotch','Viroide');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(11,'Enchinamiento del follaje','Xillella fastidiosa');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(12,'Agalla del cuello o corona','Agrobacterium tumefasciens');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(13,'Anillamiento del pedúnculo','Bacterias');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(14,'Anillamiento del pedúnculo','Consorcio de hongos y bacterias');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(15,'Anillamiento del pedúnculo','Hongos');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(16,'Antracnosis','Colletotrichum gloeosporioides');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(17,'Antracnosis','Colletotrichum gloeosporioides/acutatum');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(18,'Pudrición basal','Phytophthora heveae');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(19,'Mancha púurpura','Cercospora purpurea');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(20,'Fumagina','Capnodium sp');\n" +
                    "INSERT INTO AgenteCausalCatalogo VALUES(21,'Mancha de chapopote','Phylachora gratissima');" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',1,'Tristeza del aguacatero');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',2,'Marchitamiento');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',3,'Pudrición de raíz');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',4,'Pudrición texana');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',5,'Pudrición blanca');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',6,'Pudrición de raíz y tronco');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',7,'Marchitez');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',8,'Marchitez de puntas');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',9,'Cáancer de tronco y ramas');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',10,'Mancha de sol o Sun blotch');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',11,'Enchinamiento del follaje');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',12,'Agalla del cuello o corona');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',13,'Anillamiento del pendúnculo');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',14,'Antracnosis');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',15,'Roña');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',16,'Pudrición basal');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',17,'Mancha púurpura');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',18,'Fumagina');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',19,'Mancha de chapopote');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Enfermedad',20,'Fitoplasma');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',21,'Nitrógeno');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',22,'Fósforo');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',23,'Potasio');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',24,'Calcio');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',25,'Magnesio');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',26,'Sodio');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',27,'Manganeso');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',28,'Hierro');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',29,'Zinc');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',30,'Cobre');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',31,'Boro');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',32,'Azufre');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',33,'Molibdeno');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',34,'Cloro');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',35,'Silicio');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',36,'Niquel');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',37,'Cobalto');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',38,'Cromo');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',39,'Yodo');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',40,'Selenio');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Deficiencia',41,'Flúor');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Daño',42,'Helada');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Daño',43,'Granizada');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Daño',44,'Inundación');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Daño',45,'Erupción volcánica');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Daño',46,'Incendio');" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',47,'Araña roja');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',48,'Araña cristalina');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',49,'Trips');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',50,'Minador de la hoja');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',51,'Mosquita blanca');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',52,'Barrenador de ramas');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',53,'Barrenador de hueso');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',54,'Chicharrita');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',55,'Escamas');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',56,'Periquito del aguacatero');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',57,'Plagas cuarentenarias');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',58,'Agalla de la hoja');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',59,'Trioza');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',60,'Mosca polvorienta');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',61,'Gusano descarnador');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',62,'Gusano trozador');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',63,'Gusano verde o quemador');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',64,'Gusano perro');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',65,'Falso medidor');\n" +
                    "INSERT INTO DiagnosticoCatalogo VALUES('Plaga',66,'Gusano confeti');";

            }
            statement.executeUpdate(qry);
        } catch (Exception e) {
           logger.error(e.getLocalizedMessage());
        }finally{
             try{    
                 statement.close();  
                 conexion.close();
             }catch (Exception e){                 
                logger.error(e.getLocalizedMessage());
             }  
         }
    }
    
    
}

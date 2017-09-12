package BaseDeDatos;

import AnalizadorXML.parserXML;
import USQL.Nodo;
import XML.RecorridoXML;
import fisql.Errores;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author aylin
 */
public class BaseDeDatos {

    public static String DBActual = "";
    public static ArrayList listaImprimir = new ArrayList();
    //public String[][][] fecha = new String[1][1][1];

    public void limpiar() {
        RegistroMaestro.listaDB.clear();
        RegistroDB.listaTabla.clear();
    }

    public static void cargar(String ruta) throws IOException {
        if ("".equals(BaseDeDatos.DBActual)) {
            Errores.agregarErrorSQL("bd", "Error Semantico", "No se ha indicado ninguna bd", 0, 0);
            return;
        }
        File fichero = new File(ruta);
        if (fichero.exists()) {
            String cadena = "";
            try {
                FileReader fr = new FileReader(fichero);
                try (BufferedReader b = new BufferedReader(fr)) {
                    String linea;
                    while ((linea = b.readLine()) != null) {
                        cadena += linea + "\n";
                        //System.out.println(cadena);
                    }
                    if (!"".equals(cadena)) {
                        Nodo nodo = parserXML.compilar(cadena);
                        RecorridoXML r = new RecorridoXML();
                        r.Recorrido(nodo);
                    }
                }

            } catch (FileNotFoundException ex) {
                System.out.println("ERROR al cargar : " + ex);
            } catch (IOException | AnalizadorXML.ParseException ex) {
                System.out.println("ERROR al cargar: " + ex);
            }

        } else {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.close();
        }
    }

    public static void cargarBD() throws IOException {
        String ruta = "/home/aylin/NetBeansProjects/FISQL/BD/";
        cargar(ruta + BaseDeDatos.DBActual + "/DB.xml");
      //  cargar(ruta + BaseDeDatos.DBActual + "/OBJ.xml");
        //cargar(ruta + BaseDeDatos.DBActual + "/PROC.xml");
     

    }

    public static void crearArchivos() {
        RegistroMaestro rm = new RegistroMaestro();
        rm.generarArchivo();
        RegistroProcedure rp = new RegistroProcedure();
        rp.generarArchivo();
        RegistroObjeto ro = new RegistroObjeto();
        ro.generarArchivo();
        RegistroDB rd = new RegistroDB();
        rd.generarArchivo(DBActual);
        RegistroTabla rt = new RegistroTabla();
        rt.generarArchivo();
        RegistroUsuario ru = new RegistroUsuario();
        ru.generarArchivo();
    }

    public static ArrayList voltear(ArrayList lista) {
        ArrayList nueva = new ArrayList();
        for (int i = lista.size() - 1; i >= 0; i--) {
            nueva.add(lista.get(i));
        }
        return nueva;
    }

    public static ArrayList voltearAgregar(Object obj, ArrayList lista) {
        ArrayList nueva = new ArrayList();
        nueva.add(obj);
        for (int i = lista.size() - 1; i >= 0; i--) {
            nueva.add(lista.get(i));
        }
        return nueva;
    }

    public static void limpiarListas() {
        RegistroDB.listaTabla.clear();
        RegistroObjeto.listaObject.clear();
        RegistroProcedure.listaProcedure.clear();
        RegistroTabla.listaRegistro.clear();
    }

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }

    //Metodo usado para obtener la hora actual del sistema
    //@return Retorna un <b>STRING</b> con la hora actual formato "hh:mm:ss"
    public static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        return formateador.format(ahora);
    }

    //Sumarle dias a una fecha determinada
    //@param fch La fecha para sumarle los dias
    //@param dias Numero de dias a agregar
    //@return La fecha agregando los dias
    public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    //Restarle dias a una fecha determinada
    //@param fch La fecha
    //@param dias Dias a restar
    //@return La fecha restando los dias
    public static synchronized java.sql.Date restarFechasDias(java.sql.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, -dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    //Diferencias entre dos fechas
    //@param fechaInicial La fecha de inicio
    //@param fechaFinal  La fecha de fin
    //@return Retorna el numero de dias entre dos fechas
    public static synchronized int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) throws ParseException {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        fechaInicial = df.parse(fechaInicioString);

        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }

    //Devuele un java.util.Date desde un String en formato dd-MM-yyyy
    //@param La fecha a convertir a formato date
    //@return Retorna la fecha en formato Date
    public static synchronized java.util.Date deStringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaEnviar = null;
        try {
            fechaEnviar = formatoDelTexto.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            return null;
        }
    }
}

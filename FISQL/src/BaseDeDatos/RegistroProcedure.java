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
import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class RegistroProcedure {

    public static ArrayList<proc> listaProcedure = new ArrayList();
    String ruta = "/home/aylin/NetBeansProjects/FISQL/BD/";
    String cadena = "";

    public void cargarProcedure() throws IOException {
        if ("".equals(BaseDeDatos.DBActual)) {
            Errores.agregarErrorSQL("bd", "Error Semantico", "No se ha indicado ninguna bd", 0, 0);
            return;
        }
        File fichero = new File(ruta + BaseDeDatos.DBActual + "/proc.xml");
        if (fichero.exists()) {
            String cad = "";
            try {
                FileReader fr = new FileReader(fichero);
                try (BufferedReader b = new BufferedReader(fr)) {
                    String linea;
                    while ((linea = b.readLine()) != null) {
                        cad += linea + "\n";
                        //System.out.println(cadena);
                    }
                    if (!"".equals(cad)) {
                        Nodo nodo = parserXML.compilar(cad);
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

    public static void agregarProcedure(String nombre, Nodo instruccion, ArrayList parametros, String tipo) {
        if("".equals(BaseDeDatos.DBActual)){
            Errores.agregarErrorSQL("BD", "Error Semantico", "No se ha indicado una base de datos", 0, 0);
            return;
        }
        for (int i = 0; i < listaProcedure.size(); i++) {
            proc p = listaProcedure.get(i);
            if (nombre.equals(p.nombre)) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "Ya existe el nombre " + nombre, i, i);
                return;
            }
        }
        RegistroProcedure p = new RegistroProcedure();
        String instruc = p.generarInstruccion(instruccion);
       // System.out.println(instruc);

        listaProcedure.add(new proc(nombre, tipo, parametros, instruc));
    }

    public void generarArchivo() {
        File fichero = new File(ruta + BaseDeDatos.DBActual + "/proc.xml");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

            for (int i = 0; i < listaProcedure.size(); i++) {
                proc p = listaProcedure.get(i);

                bw.write("<PROC>\n");
                bw.write("  <nombre>" + p.nombre + "</nombre>\n");
                if (!p.listaParametros.isEmpty()) {
                    bw.write("  <params>\n");
                    for (int j = 0; j < p.listaParametros.size(); j++) {
                        Parametro pm = p.listaParametros.get(j);
                        bw.write("      <" + pm.tipo + ">" + pm.nombre + "</" + pm.tipo + ">\n");
                    }
                    bw.write("  </params>\n");
                }
                bw.write("  <src>\"" + p.intrucciones + "\"</src>\n");
                bw.write("</PROC>\n");

            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("ERROR al generar el archivo maestro");
        }

    }

    public static void imprimir() {
        System.out.println("\n* * * * * * * * * * PROCEDURE * * * * * * * * * *");
        for (int i = 0; i < RegistroProcedure.listaProcedure.size(); i++) {
            proc db = RegistroProcedure.listaProcedure.get(i);
            System.out.println("> " + db.nombre + " - " + db.tipo + " - " + db.intrucciones + " - " + db.listaParametros.size());
        }
    }

    public String generarInstruccion(Nodo nodo) {

        if (nodo.cantidadHijos > 0) {
            for (int i = 0; i < nodo.cantidadHijos; i++) {
                generarInstruccion(nodo.hijos[i]);
            }

        } else {
            cadena += nodo.texto + " ";
        }
        return cadena;
    }

}

class proc {

    String nombre;
    String tipo;
    ArrayList<Parametro> listaParametros;
    String intrucciones;

    proc(String n, String t, ArrayList p, String i) {
        this.nombre = n;
        this.listaParametros = p;
        this.intrucciones = i;
        this.tipo = t;
    }
}

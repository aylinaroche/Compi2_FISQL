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
public class RegistroMaestro {

    public static ArrayList<DB> listaDB = new ArrayList();
    File fichero = new File("/home/aylin/NetBeansProjects/FISQL/BD/MAESTRO.xml");

    public void cargarBD() throws IOException {
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
                System.out.println("ERROR al obtener : " + ex);
            } catch (IOException | AnalizadorXML.ParseException ex) {
                System.out.println("ERROR al obtener: " + ex);
            }

        } else {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.close();
            System.out.println("No existe");
        }
    }

    public static void agregarBD(String nombre, String path) {

        for (int i = 0; i < listaDB.size(); i++) {
            DB db = listaDB.get(i);
            if (nombre.equals(db.nombre)) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "Ya existe el nombre " + nombre, i, i);
                return;
            }
        }
        listaDB.add(new DB(path, nombre));
        File folder = new File(path);
        folder.mkdir();
        RegistroDB r = new RegistroDB();
       // r.generarArchivo(nombre);
    }

    public static void usarBD(String nombre) {
        BaseDeDatos.crearArchivos();
        BaseDeDatos.limpiarListas();
        for (int i = 0; i < RegistroMaestro.listaDB.size(); i++) {
            DB db = RegistroMaestro.listaDB.get(i);
            if (nombre.equals(db.nombre)) {
                BaseDeDatos.DBActual = nombre;
                try {
                    BaseDeDatos.cargarBD();
                } catch (IOException ex) {
                    System.out.println("Error al cargar TODOS los datos: " + ex);
                }
                return;
            }
        }
        Errores.agregarErrorSQL(nombre, "Error semantico", "No existe la base de datos indicada", 0, 0);
    }

    public static void eliminarBD(String nombre) {
        for (int i = 0; i < listaDB.size(); i++) {
            DB db = listaDB.get(i);
            if (nombre.equals(db.nombre)) {
                listaDB.remove(i);
                File f = new File("/home/aylin/NetBeansProjects/FISQL/BD/" + nombre);
                if (f.delete()) {
                    System.out.println("Se ha borrado correctamente.");
                }
            }
        }
        Errores.agregarErrorSQL(nombre, "Error semantico", "No existe la base de datos indicada", 0, 0);
    }

    public void generarArchivo() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

            for (int i = 0; i < listaDB.size(); i++) {
                DB db = listaDB.get(i);

                bw.write("<BD>\n");
                bw.write("  <nombre>" + db.nombre + "</nombre>\n");
                bw.write("  <path>\"" + db.path + "\"</path>\n");
                bw.write("</BD>\n");

            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("ERROR al generar el archivo maestro");
        }

    }

    public static void imprimir() {
        System.out.println("\n* * * * * * * * * * DB * * * * * * * * * *");
        for (int i = 0; i < RegistroMaestro.listaDB.size(); i++) {
            DB db = RegistroMaestro.listaDB.get(i);
            System.out.println("> " + db.nombre + " - " + db.path);
        }
    }
}

class DB {

    public String path;
    public String nombre;

    public DB(String p, String n) {
        this.path = p;
        this.nombre = n;
    }
}

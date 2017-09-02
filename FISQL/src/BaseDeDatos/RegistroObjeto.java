/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class RegistroObjeto {

    public static ArrayList<obj> listaObject = new ArrayList();
    String ruta = "/home/aylin/NetBeansProjects/FISQL/BD/";
    String cadena = "";

    public void cargarObject() throws IOException {
        if ("".equals(BaseDeDatos.DBActual)) {
            Errores.agregarErrorSQL("bd", "Error Semantico", "No se ha indicado ninguna bd", 0, 0);
            return;
        }
        File fichero = new File(ruta + BaseDeDatos.DBActual + "/obj.xml");
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

    public static void agregarObject(String nombre, ArrayList atributos) {
        if("".equals(BaseDeDatos.DBActual)){
            Errores.agregarErrorSQL("BD", "Error Semantico", "No se ha indicado una base de datos", 0, 0);
            return;
        }
        for (int i = 0; i < listaObject.size(); i++) {
            obj p = listaObject.get(i);
            if (nombre.equals(p.nombre)) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "Ya existe el nombre " + nombre, i, i);
                return;
            }
        }
        listaObject.add(new obj(nombre, atributos));
    }

    public void generarArchivo() {
        File fichero = new File(ruta + BaseDeDatos.DBActual + "/proc.xml");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

            for (int i = 0; i < listaObject.size(); i++) {
                obj p = listaObject.get(i);

                bw.write("<OBJ>\n");
                bw.write("  <nombre>" + p.nombre + "</nombre>\n");
                if (!p.listaAtributos.isEmpty()) {
                    bw.write("  <atr>\n");
                    for (int j = 0; j < p.listaAtributos.size(); j++) {
                        Parametro pm = p.listaAtributos.get(j);
                        bw.write("      <" + pm.tipo + ">" + pm.nombre + "</" + pm.tipo + ">\n");
                    }
                    bw.write("  </atr>\n");
                }
                bw.write("</OBJ>\n");

            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("ERROR al generar el archivo maestro");
        }

    }

    public static void imprimir() {
        System.out.println("\n* * * * * * * * * * OBJECT * * * * * * * * * *");
        for (int i = 0; i < RegistroObjeto.listaObject.size(); i++) {
            obj o = RegistroObjeto.listaObject.get(i);
            System.out.println("> " + o.nombre + " - " + o.listaAtributos.size());
        }
    }
}

class obj {

    String nombre;
    ArrayList<Parametro> listaAtributos;

    obj(String n, ArrayList a) {
        this.nombre = n;
        this.listaAtributos = a;
    }
}

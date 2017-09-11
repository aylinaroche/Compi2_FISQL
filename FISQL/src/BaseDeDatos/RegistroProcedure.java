package BaseDeDatos;

import USQL.Objetos.Parametro;
import AnalizadorSQL.ParseException;
import AnalizadorSQL.parserSQL;
import USQL.Nodo;
import USQL.Objetos.Proced;
import USQL.RecorridoSQL;
import USQL.Variables;
import fisql.Errores;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class RegistroProcedure {

    public static ArrayList<Proced> listaProcedure = new ArrayList();
    String ruta = "/home/aylin/NetBeansProjects/FISQL/BD/";
    String cadena = "";

    public static void agregarProcedure(String nombre, Object instruccion, ArrayList parametros, String tipo) {
        if ("".equals(BaseDeDatos.DBActual)) {
            Errores.agregarErrorSQL("BD", "Error Semantico", "No se ha indicado una base de datos", 0, 0);
            return;
        }
        for (int i = 0; i < listaProcedure.size(); i++) {
            Proced p = listaProcedure.get(i);
            if (nombre.toLowerCase().equals(p.nombre)) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "Ya existe el nombre " + nombre, i, i);
                return;
            }
        }
        String instruc ="";
        if (instruccion instanceof Nodo) {
            RegistroProcedure p = new RegistroProcedure();
            instruc = p.generarInstruccion((Nodo) instruccion);
            // System.out.println(instruc);
        }else{
            instruc = instruccion.toString();
        }
        listaProcedure.add(new Proced(nombre.toLowerCase(), tipo, parametros, instruc));
    }

    public void generarArchivo() {
        File fichero = new File(ruta + BaseDeDatos.DBActual + "/PROC.xml");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

            for (int i = 0; i < listaProcedure.size(); i++) {
                Proced p = listaProcedure.get(i);

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
            System.out.println("ERROR al generar el archivo proc");
        }

    }

    public static void imprimir() {
        System.out.println("\n* * * * * * * * * * PROCEDURE * * * * * * * * * *");
        for (int i = 0; i < RegistroProcedure.listaProcedure.size(); i++) {
            Proced db = RegistroProcedure.listaProcedure.get(i);
            System.out.println("> " + db.nombre + " - " + db.tipo + " - " + db.intrucciones + " - " + db.listaParametros.size());
        }
    }

    public String generarInstruccion(Nodo nodo) {
        String nombre = nodo.texto;
        //  System.out.println(nombre);
        if (nodo.cantidadHijos > 0) {
            for (int i = 0; i < nodo.cantidadHijos; i++) {
                generarInstruccion(nodo.hijos[i]);
            }

        } else {
            cadena += nodo.texto + " ";
        }
        return cadena;
    }

    public Object ejecutarProcedure(String nombre, ArrayList params) throws CloneNotSupportedException {
        for (int i = 0; i < listaProcedure.size(); i++) {
            Proced p = listaProcedure.get(i);
            if (p.nombre.equals(nombre.toLowerCase()) && params.size() == p.listaParametros.size()) {
                Variables.pilaAmbito.push(nombre);
                for (int j = 0; j < params.size(); j++) {
                    Variables.crearVariable(p.listaParametros.get(j).tipo, p.listaParametros.get(j).nombre, params.get(j));
                }
                try {
                    Nodo nodo = parserSQL.compilar(p.intrucciones);
                    RecorridoSQL r = new RecorridoSQL();
                    if (nodo != null) {
                        Object result = r.Recorrido(nodo);
                        Variables.pilaAmbito.pop();
                        RecorridoSQL.retornar = false;
                        return result;
                    }

                } catch (ParseException ex) {
                    System.out.println("ERROR al ejecutar instruccion: " + ex);
                }
                Variables.pilaAmbito.pop();
                return null;
            }
        }
        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe el metodo indicado", 0, 0);
        return null;
    }

}

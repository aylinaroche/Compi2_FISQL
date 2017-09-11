package BaseDeDatos;

import USQL.Objetos.Parametro;
import fisql.Errores;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import USQL.Objetos.Objeto;

/**
 *
 * @author aylin
 */
public class RegistroObjeto {

    public static ArrayList<Objeto> listaObject = new ArrayList();
    String ruta = "/home/aylin/NetBeansProjects/FISQL/BD/";
    String cadena = "";

    public static void agregarObjeto(String nombre, ArrayList atributos) {
        if ("".equals(BaseDeDatos.DBActual)) {
            Errores.agregarErrorSQL("BD", "Error Semantico", "No se ha indicado una base de datos", 0, 0);
            return;
        }
        for (int i = 0; i < listaObject.size(); i++) {
            Objeto p = listaObject.get(i);
            if (nombre.equalsIgnoreCase(p.nombre)) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "Ya existe el nombre " + nombre, i, i);
                return;
            }
        }
        listaObject.add(new Objeto(nombre.toLowerCase(), atributos));
    }

    public static void alterarObjeto(String nombre, String tipo, ArrayList campos) {
        for (int i = 0; i < listaObject.size(); i++) {
            Objeto o = listaObject.get(i);
            if (nombre.toLowerCase().equals(o.nombre)) {
                if (tipo.toLowerCase().equals("agregar")) {
                    for (int j = 0; j < campos.size(); j++) {
                        Parametro p = (Parametro) campos.get(j);
                        o.listaAtributos.add(p);
                    }
                } else {
                    for (int j = 0; j < campos.size(); j++) {
                        String p = (String) campos.get(j);
                        for (int k = 0; k < o.listaAtributos.size(); k++) {
                            if (o.listaAtributos.get(k).nombre.equals(p)) {
                                o.listaAtributos.remove(k);
                            }
                        }
                    }
                }
            }
            return;
        }
        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe el Objeto indicado", 0, 0);
    }

    public static void eliminarObjeto(String nombre) {
        for (int i = 0; i < listaObject.size(); i++) {
            Objeto o = listaObject.get(i);
            if (nombre.toLowerCase().equals(o.nombre)) {
                listaObject.remove(i);
            }
            return;
        }
        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe el Objeto indicado", 0, 0);
    }

    public void generarArchivo() {
        File fichero = new File(ruta + BaseDeDatos.DBActual + "/OBJ.xml");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));

            for (int i = 0; i < listaObject.size(); i++) {
                Objeto p = listaObject.get(i);

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
            System.out.println("ERROR al generar el archivo Obj");
        }

    }

    public static void imprimir() {
        System.out.println("\n* * * * * * * * * * OBJECT * * * * * * * * * *");
        for (int i = 0; i < RegistroObjeto.listaObject.size(); i++) {
            Objeto o = RegistroObjeto.listaObject.get(i);
            System.out.println("> " + o.nombre + " - " + o.listaAtributos.size());
        }
    }
}

package BaseDeDatos;

import USQL.Objetos.Encabezado;
import USQL.Objetos.Parametro;
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
public class RegistroDB {

    public static ArrayList<Encabezado> listaTabla = new ArrayList();
    public static String ruta = "/home/aylin/NetBeansProjects/FISQL/BD/";

    public static void agregarTabla(String nombre, ArrayList atributos) {

        if ("".equals(BaseDeDatos.DBActual)) {
            Errores.agregarErrorSQL("BD", "Error Semantico", "No se ha indicado una base de datos", 0, 0);
            return;
        }
        for (int i = 0; i < listaTabla.size(); i++) {
            Encabezado p = listaTabla.get(i);
            if (nombre.equals(p.nombre)) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "Ya existe el nombre " + nombre, i, i);
                return;
            }
        }
        boolean existe = false;
        for (int j = 0; j < atributos.size(); j++) {

            Parametro p = (Parametro) atributos.get(j);
            for (int i = 0; i < p.complemento.size(); i++) {
                Object obj = p.complemento.get(i);

                if (obj instanceof Parametro) { //Si es foranea
                    Parametro param = (Parametro) obj;

                    for (int k = 0; k < listaTabla.size(); k++) { //Busco la tabla
                        Encabezado enc = listaTabla.get(k);

                        if (enc.nombre.equalsIgnoreCase(param.tipo)) {
                            for (int l = 0; l < enc.campos.size(); l++) {
                                Parametro pam = enc.campos.get(l);
                                if (pam.nombre.equalsIgnoreCase(param.nombre)) {
                                    existe = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (existe == false) {
                        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe la llave foranea", 0, 0);
                        return;
                    }
                }
            }
        }

        listaTabla.add(new Encabezado(nombre.toLowerCase(), ruta + BaseDeDatos.DBActual + "/" + nombre + ".xml", atributos));

        RegistroTabla.crearArchivo(nombre);
    }

    public static void alterarTabla(String tabla, String tipo, ArrayList campos) {
        for (int i = 0; i < listaTabla.size(); i++) {
            Encabezado e = listaTabla.get(i);
            if (tabla.toLowerCase().equals(e.nombre.toLowerCase())) {
                if (tipo.toLowerCase().equals("agregar")) {
                    for (int j = 0; j < campos.size(); j++) {
                        Parametro p = (Parametro) campos.get(j);
                        e.campos.add(p);
                    }
                } else {
                    for (int j = 0; j < campos.size(); j++) {
                        String p = (String) campos.get(j);
                        for (int k = 0; k < e.campos.size(); k++) {
                            if (e.campos.get(k).nombre.equals(p)) {
                                e.campos.remove(k);
                            }
                        }
                    }
                }
            }
            return;
        }
        Errores.agregarErrorSQL(tabla, "Error Semantico", "No existe la tabla indicada", 0, 0);
        //Falta remover los registros de ese campo
    }

    public static void eliminarTabla(String tabla) {
        for (int i = 0; i < listaTabla.size(); i++) {
            Encabezado e = listaTabla.get(i);
            if (tabla.toLowerCase().equals(e.nombre.toLowerCase())) {
                listaTabla.remove(i);
            }
            return;
        }
        Errores.agregarErrorSQL(tabla, "Error Semantico", "No existe la tabla indicada", 0, 0);
        //Falta remover los registros de ese campo, 
        // verificarforanea
    }

    public void generarArchivo(String nombre) {
        File fichero = new File("/home/aylin/NetBeansProjects/FISQL/BD/" + nombre + "/DB.xml");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write("<procedure>\n");
            bw.write("  \"/home/aylin/NetBeansProjects/FISQL/BD/" + nombre + "/proc.xml\"\n");
            bw.write("</procedure>\n");
            bw.write("<object>\n");
            bw.write("  \"/home/aylin/NetBeansProjects/FISQL/BD/" + nombre + "/obj.xml\"\n");
            bw.write("</object>\n\n");

            for (int i = 0; i < listaTabla.size(); i++) {
                Encabezado tb = listaTabla.get(i);

                bw.write("<tabla>\n");
                bw.write("  <nombre>" + tb.nombre + "</nombre>\n");
                bw.write("  <path>\"" + tb.path + "\"</path>\n");
                for (int j = 0; j < tb.campos.size(); j++) {
                    Parametro tp = tb.campos.get(j);
                    bw.write("  <campo>\n");
                    bw.write("      <" + tp.tipo + ">" + tp.nombre + "</" + tp.tipo + ">\n");
                    for (int k = 0; k < tp.complemento.size(); k++) {
                        Object obj = tp.complemento.get(k);
                        if (obj instanceof Parametro) {
                            Parametro p = (Parametro) obj;
                            bw.write("      <complemento>\n");
                            bw.write("          <foranea>\n");
                            bw.write("              <nombre>" + p.tipo + "</nombre>\n");
                            bw.write("              <atributo>" + p.nombre + "</atributo>\n");
                            bw.write("          </foranea>\n");
                            bw.write("      </complemento>\n");
                        } else {
                            bw.write("      <complemento>" + tp.complemento.get(k) + "</complemento>\n");
                        }
                    }

                    bw.write("  </campo>\n");
                }

                bw.write("</tabla>\n\n");

            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("ERROR al generar el archivo db: " + ex);
        }

    }

    public static void imprimir() {
        System.out.println("\n* * * * * * * * * * TABLA * * * * * * * * * *");
        for (int i = 0; i < RegistroDB.listaTabla.size(); i++) {
            Encabezado o = RegistroDB.listaTabla.get(i);
            System.out.println("> " + o.nombre + " - " + o.path);
            for (int j = 0; j < o.campos.size(); j++) {
                Parametro c = o.campos.get(j);
                System.out.println("    " + c.nombre + " - " + c.tipo);
            }
        }
    }
}

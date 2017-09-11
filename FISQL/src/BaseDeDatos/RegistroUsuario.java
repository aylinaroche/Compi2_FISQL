package BaseDeDatos;

import AnalizadorXML.parserXML;
import USQL.Nodo;
import USQL.Objetos.Encabezado;
import USQL.Objetos.Objeto;
import USQL.Objetos.Proced;
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
public class RegistroUsuario {

    public static ArrayList<User> listaUsuarios = new ArrayList();
    public static String ruta = "/home/aylin/NetBeansProjects/FISQL/BD/USERS.xml";

    public void cargarBD() throws IOException {
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

    public static void agregarUsuario(String nombre, String pass) {
//        if ("".equals(BaseDeDatos.DBActual)) {
//            Errores.agregarErrorSQL("BD", "Error Semantico", "No se ha indicado una base de datos", 0, 0);
//            return;
//        }
        for (int i = 0; i < listaUsuarios.size(); i++) {
            User p = listaUsuarios.get(i);
            if (nombre.equals(p.nombre)) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "Ya existe el nombre " + nombre, i, i);
                return;
            }
        }
        ArrayList vacia = new ArrayList();
        listaUsuarios.add(new User(nombre.toLowerCase(), pass, vacia));
    }

    public static void alterarUsuario(String nombre, String pass) {
        //Verificar que usuario modifica
        for (int i = 0; i < listaUsuarios.size(); i++) {
            User u = listaUsuarios.get(i);
            if (nombre.toLowerCase().equals(u.nombre)) {
                u.pass = pass;
                return;
            }
        }
        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe el usuario indicado", 0, 0);
    }

    public static void eliminarUsuario(String nombre) {
        //Verificar que usuario modifica
        for (int i = 0; i < listaUsuarios.size(); i++) {
            User u = listaUsuarios.get(i);
            if (nombre.toLowerCase().equals(u.nombre)) {
                listaUsuarios.remove(i);
                return;
            }
        }
        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe el usuario indicado", 0, 0);
    }

    public void generarArchivo() {
        File fichero = new File(ruta);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            for (int i = 0; i < listaUsuarios.size(); i++) {
                User u = listaUsuarios.get(i);

                bw.write("<usuario>\n");
                bw.write("  <nombre>" + u.nombre + "</nombre>\n");
                bw.write("  <pass>" + u.pass + "</pass>\n");
                for (int j = 0; j < u.bases.size(); j++) {
                    Permiso p = u.bases.get(j);
                    bw.write("      <BD>\n");
                    bw.write("          <nombre>" + p.nombre + "</nombre>\n");
                    for (int k = 0; k < p.objetos.size(); k++) {
                        String obj = p.objetos.get(k);
                        bw.write("          <permiso>" + obj + "</permiso>\n");
                    }
                    bw.write("      </BD>\n\n");
                }
                bw.write("</usuario>\n");
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("ERROR al generar el archivo db: " + ex);
        }

    }

    public static void imprimir() {
        System.out.println("\n* * * * * * * * * * USUARIOS * * * * * * * * * *");
        for (int i = 0; i < listaUsuarios.size(); i++) {
            User u = listaUsuarios.get(i);
            System.out.println("> " + u.nombre + " - " + u.pass);
            for (int j = 0; j < u.bases.size(); j++) {
                System.out.println("    " + u.bases.get(j));
            }
        }
    }

    public static void otorgarPermiso(String user, String bd, String objeto) {
        ArrayList l = new ArrayList();

        for (int i = 0; i < listaUsuarios.size(); i++) {
            User u = listaUsuarios.get(i);
            if (user.equals(u.nombre)) {
                if (objeto.equals("*")) {
                    for (int j = 0; j < RegistroDB.listaTabla.size(); j++) {
                        Encabezado e = RegistroDB.listaTabla.get(j);
                        l.add(e.nombre);
                    }
                    for (int j = 0; j < RegistroProcedure.listaProcedure.size(); j++) {
                        Proced p = RegistroProcedure.listaProcedure.get(j);
                        l.add(p.nombre);
                    }
                    for (int j = 0; j < RegistroObjeto.listaObject.size(); j++) {
                        Objeto o = RegistroObjeto.listaObject.get(j);
                        l.add(o.nombre);
                    }

                    for (int k = 0; k < u.bases.size(); k++) {
                        Permiso perm = u.bases.get(k);
                        if (perm.nombre.equalsIgnoreCase(bd)) {
                            perm.objetos = l;
                            return;
                        }
                    }
                    u.bases.add(new Permiso(bd, l));
                    return;
                } else {
                    for (int j = 0; j < u.bases.size(); j++) {
                        Permiso p = u.bases.get(j);
                        if (p.nombre.equalsIgnoreCase(bd)) {
                            if (!p.objetos.contains(objeto)) {
                                p.objetos.add(objeto);
                                return;
                            }
                        }
                    }
                    l.add(objeto);
                    u.bases.add(new Permiso(bd, l));
                    return;
                }

            }
        }

    }

    public static void denegarPermiso(String user, String bd, String objeto) {
        ArrayList l = new ArrayList();

        for (int i = 0; i < listaUsuarios.size(); i++) {
            User u = listaUsuarios.get(i);
            if (user.equals(u.nombre)) {
                if (objeto.equals("*")) {
                    for (int k = 0; k < u.bases.size(); k++) {
                        Permiso perm = u.bases.get(k);
                        if (perm.nombre.equalsIgnoreCase(bd)) {
                            perm.objetos = l;
                            return;
                        }
                    }
                    u.bases.add(new Permiso(bd, l));
                } else {
                    for (int j = 0; j < u.bases.size(); j++) {
                        Permiso p = u.bases.get(j);
                        if (p.nombre.equalsIgnoreCase(bd)) {

                            if (p.objetos.contains(objeto)) {
                                p.objetos.remove(objeto);
                                return;
                            }
                        }
                    }
                    Errores.agregarErrorSQL(objeto, "Error Semantico", "No existe el objeto", i, i);
                }

            }
        }

    }
}

class Permiso {

    String nombre;
    ArrayList<String> objetos;

    public Permiso(String n, ArrayList o) {
        this.nombre = n;
        this.objetos = o;
    }
}

class User {

    String nombre;
    String pass;
    ArrayList<Permiso> bases;

    public User(String n, String p, ArrayList b) {
        this.nombre = n;
        this.pass = p;
        this.bases = b;
    }

    public User(String n, String p) {
        this.nombre = n;
        this.pass = p;
    }
}

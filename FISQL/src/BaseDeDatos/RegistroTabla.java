package BaseDeDatos;

import USQL.Objetos.Encabezado;
import USQL.Objetos.Parametro;
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
public class RegistroTabla {

    public static ArrayList<Tabla> listaRegistro = new ArrayList();
    public static String ruta = "/home/aylin/NetBeansProjects/FISQL/BD/";

    public static void crearArchivo(String nombre) {
        File fichero = new File(ruta + BaseDeDatos.DBActual + "/" + nombre + ".xml");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.close();
        } catch (IOException ex) {
            System.out.println("ERROR al generar el archivo tabla " + nombre + " : " + ex);
        }

        listaRegistro.add(new Tabla(nombre, new ArrayList()));
    }

    public void generarArchivo() {

        try {
            for (int i = 0; i < listaRegistro.size(); i++) {
                Tabla r = listaRegistro.get(i);
                File fichero = new File("/home/aylin/NetBeansProjects/FISQL/BD/" + BaseDeDatos.DBActual + "/" + r.nombre + ".xml");

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
                    for (int j = 0; j < r.registros.size(); j++) {
                        bw.write("<registro>\n");
                        Registro t = r.registros.get(j);
                        for (int l = 0; l < t.valores.size(); l++) {
                            Parametro p = t.valores.get(l);
                            bw.write("  <" + p.nombre + ">" + p.valor + "</" + p.nombre + ">\n");
                        }
                        bw.write("</registro>\n\n");
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("ERROR al generar el archivo db: " + ex);
        }

    }

    public static void imprimir() {
        System.out.println("\n* * * * * * * * * * REGISTROS * * * * * * * * * *");
        for (int i = 0; i < RegistroTabla.listaRegistro.size(); i++) {
            Tabla o = RegistroTabla.listaRegistro.get(i);
            System.out.println("> " + o.nombre + "\n");

            for (int j = 0; j < o.registros.size(); j++) {
                Registro p = o.registros.get(j);
                System.out.println("::::::::::::::::::::::::");
                for (int l = 0; l < p.valores.size(); l++) {
                    Parametro m = p.valores.get(l);
                    System.out.println("    " + m.nombre + " - " + m.valor.toString());
                }
            }
        }
    }

    public static void insertarRegistro(String nombre, ArrayList atributos) {
        if ("".equals(BaseDeDatos.DBActual)) {
            Errores.agregarErrorSQL("BD", "Error Semantico", "No se ha indicado una base de datos", 0, 0);
            return;
        }

        //Obtengo encabezado
        Encabezado cabeza = null;
        for (int j = 0; j < RegistroDB.listaTabla.size(); j++) {
            Encabezado cab = RegistroDB.listaTabla.get(j);
            if (cab.nombre.equalsIgnoreCase(nombre)) {
                cabeza = cab;
                break;
            }
        }

        // Obtengo tabla
        Tabla tabla = null;
        for (int i = 0; i < listaRegistro.size(); i++) {
            Tabla t = listaRegistro.get(i);
            if (nombre.equalsIgnoreCase(t.nombre)) {
                tabla = t;
                break;
            }
        }
        if (cabeza == null || tabla == null) {
            Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe la tabla " + nombre, 0, 0);
            return;
        }

        ArrayList<Parametro> valores = new ArrayList();

        // For por cada campo
        int cont = 0;
        for (int i = 0; i < cabeza.campos.size(); i++) {
            Parametro p = cabeza.campos.get(i);
            Object valor = null;
            try {
                valor = atributos.get(i - cont);
            } catch (Exception e) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "Cantidad de atributos incorrecta", 0, 0);
                return;
            }
            if (p.complemento.contains("autoincrementable")) {
                cont++;
                valores.add(new Parametro(p.nombre, cabeza.autoincremental + 1));
                cabeza.autoincremental++;
                continue;
            }
            valores.add(new Parametro(p.nombre, valor));

//Verificar si es nulo
            if (valor.toString().equalsIgnoreCase("nulo")) {
                if (p.complemento.contains("no nulo")) {
                    Errores.agregarErrorSQL(nombre, "Error Semantico", "El valor " + p.nombre + " no puede ser nulo", 0, 0);
                    return;
                } else {
                    continue;
                }
            }

//Verificar tipo            
            if (Variables.verificarTipo(p.tipo, valor) == false) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "El tipo no coincide" + nombre, 0, 0);
                return;
            }

            //Verificar si es único o primaria
            if (p.complemento.contains("unico") || p.complemento.contains("llave_primaria")) {
                for (int j = 0; j < tabla.registros.size(); j++) {
                    Registro r = tabla.registros.get(j);
                    if (r.valores.get(i).valor == (valor)) {
                        Errores.agregarErrorSQL(nombre, "Error Semantico", "Ya existe el valor de = " + r.valores.get(i).nombre, 0, 0);
                        return;
                    }

                }
            }
            //Verificar si es foránea
            boolean foranea = false;
            for (int j = 0; j < p.complemento.size(); j++) {
                Object obj = p.complemento.get(j);
                if (obj instanceof Parametro) { //Si es foranea
                    Parametro pForanea = (Parametro) obj;

                    for (int k = 0; k < listaRegistro.size(); k++) { //Busca la tabla foranea
                        Tabla t = listaRegistro.get(k);
                        if (t.nombre.equalsIgnoreCase(pForanea.tipo)) {

                            for (int l = 0; l < t.registros.size(); l++) { //Busca en los registros
                                Registro r = t.registros.get(l);

                                for (int m = 0; m < r.valores.size(); m++) { //Busca en los valores
                                    Parametro pValor = r.valores.get(m);

                                    if (pValor.nombre.equals(pForanea.nombre)) { //Busca el valor de la foranea
                                        if (valor.toString().equalsIgnoreCase(pValor.valor.toString())) {
                                            foranea = true;
                                            break;
                                        }
                                    }
                                }

                            }
                        }
                    }
                    if (foranea == false) {
                        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe la tabla o atributo usado como foranea", 0, 0);
                        return;
                    }
                }
            }

        }
        tabla.registros.add(new Registro(valores));
    }

    public ArrayList agregarIncrementar(int posicion, ArrayList lista, int num) {
        ArrayList nueva = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            if (i == posicion) {
                nueva.add(num);
            }
            nueva.add(lista.get(i));
        }
        return nueva;
    }

    public static void insertarEspecial(String nombre, ArrayList nombresAtributos, ArrayList valores) {

        ArrayList atributos = new ArrayList();

        for (int j = 0; j < RegistroDB.listaTabla.size(); j++) {
            Encabezado enc = RegistroDB.listaTabla.get(j);
            if (enc.nombre.equalsIgnoreCase(nombre)) {
                int c = 0;
                if (enc.autoincremental > 0) {
                    c = 1;
                }
                for (int i = c; i < enc.campos.size(); i++) {
                    atributos.add("nulo");
                }

                for (int i = 0; i < nombresAtributos.size(); i++) {
                    String nomAtributo = (String) nombresAtributos.get(i);

                    for (int k = 0; k < enc.campos.size(); k++) {
                        Parametro p = enc.campos.get(k);

                        if (p.nombre.equalsIgnoreCase(nomAtributo)) {
                            atributos.set(k - c, valores.get(i));
                        }

                    }

                }

                break;
            }
        }

        insertarRegistro(nombre, atributos);
    }

    public static void actualizarTabla(String nombre, ArrayList nombresAtributos, ArrayList valores) {

        for (int k = 0; k < listaRegistro.size(); k++) { //Busca la tabla foranea
            Tabla t = listaRegistro.get(k);
            
            if (t.nombre.equalsIgnoreCase(nombre)) {

                for (int l = 0; l < t.registros.size(); l++) { //Busca en los registros
                    Registro r = t.registros.get(l);

                    for (int m = 0; m < r.valores.size(); m++) { //Busca en los valores
                        Parametro pValor = r.valores.get(m);

                        
                    }

                }
            }
        }
    }
}

class Registro {

    public ArrayList<Parametro> valores;

    public Registro(ArrayList r) {
        this.valores = r;
    }
}

class Tabla {

    public String nombre;
    public ArrayList<Registro> registros;

    public Tabla(String n, ArrayList r) {
        this.nombre = n;
        this.registros = r;
    }
}

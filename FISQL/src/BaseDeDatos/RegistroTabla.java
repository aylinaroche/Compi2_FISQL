package BaseDeDatos;

import USQL.Nodo;
import USQL.Objetos.Encabezado;
import USQL.Objetos.Parametro;
import USQL.Objetos.Registro;
import USQL.Objetos.Tabla;
import USQL.RecorridoSQL;
import USQL.Variables;
import fisql.Errores;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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

    public static void insertarDesdeXML(String nombre, ArrayList atributos) {
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

        for (int i = 0; i < cabeza.campos.size(); i++) {
            Parametro p = cabeza.campos.get(i);
            Object valor = null;
            valor = atributos.get(i);
            valores.add(new Parametro(p.nombre, valor));

        }
        tabla.registros.add(new Registro(valores));
    }

    public static ArrayList seleccionar(Object tipo, String nombreTabla, Nodo donde, ArrayList ordenar) {
        ArrayList lista = new ArrayList();
        if ("".equals(BaseDeDatos.DBActual)) {
            Errores.agregarErrorSQL("BD", "Error Semantico", "No se ha indicado una base de datos", 0, 0);
            return lista;
        }
        //Obtengo encabezado
        Encabezado cabeza = null;
        for (int j = 0; j < RegistroDB.listaTabla.size(); j++) {
            Encabezado cab = RegistroDB.listaTabla.get(j);
            if (cab.nombre.equalsIgnoreCase(nombreTabla)) {
                cabeza = cab;
                break;
            }
        }
        // Obtengo tabla
        Tabla tabla = null;
        for (int i = 0; i < listaRegistro.size(); i++) {
            Tabla t = listaRegistro.get(i);
            if (nombreTabla.equalsIgnoreCase(t.nombre)) {
                tabla = t;
                break;
            }
        }
        if (cabeza == null || tabla == null) {
            Errores.agregarErrorSQL(nombreTabla, "Error Semantico", "No existe la tabla " + nombreTabla, 0, 0);
            return lista;
        }
        if (tipo instanceof ArrayList) {
            ArrayList nombreAtr = (ArrayList) tipo;
            ArrayList nombres = new ArrayList();
            //lista.add(nombreAtr);
            // Posiciones de las cabezas
            ArrayList<Integer> posicion = new ArrayList();
            for (int i = 0; i < nombreAtr.size(); i++) {
                String nombre = (String) nombreAtr.get(i);
                Parametro m = new Parametro(nombre, nombre);
                for (int j = 0; j < cabeza.campos.size(); j++) {
                    Parametro p = cabeza.campos.get(j);
                    if (p.nombre.equalsIgnoreCase(nombre)) {
                        posicion.add(j);
                    }
                }
                nombres.add(m);
            }
            lista.add(nombres);

            for (int i = 0; i < tabla.registros.size(); i++) {
                Registro registro = tabla.registros.get(i);
                if (donde != null) {
                    Variables.pilaAmbito.push("donde");
                    Variables.nivelAmbito++;

                    for (int j = 0; j < registro.valores.size(); j++) {
                        Parametro p = registro.valores.get(j);
                        Parametro c = cabeza.campos.get(j);
                        Variables.crearVariable(c.tipo, p.nombre, p.valor);
                    }
                    try {
                        RecorridoSQL r = new RecorridoSQL();
                        Object boolDonde = r.Recorrido(donde);

                        if (boolDonde.toString().equalsIgnoreCase("true")) {
                            ArrayList val = new ArrayList();
                            for (int k = 0; k < posicion.size(); k++) {
                                Parametro p = registro.valores.get(posicion.get(k));
                                val.add(p);
                            }
                            lista.add(val);
                        }

                    } catch (CloneNotSupportedException ex) {
                        System.out.println("Error en el nodo donde = " + ex);
                    }
                    Variables.eliminarVariables();
                    Variables.pilaAmbito.pop();
                    Variables.nivelAmbito--;
                } else {
                    ArrayList val = new ArrayList();
                    for (int k = 0; k < posicion.size(); k++) {
                        Parametro p = registro.valores.get(posicion.get(k));
                        val.add(p);
                    }
                    lista.add(val);
                }
            }
        } else { // *
            lista.add(cabeza.campos);
            for (int i = 0; i < tabla.registros.size(); i++) {
                Registro registro = tabla.registros.get(i);
                if (donde != null) {
                    Variables.pilaAmbito.push("donde");
                    Variables.nivelAmbito++;

                    for (int j = 0; j < registro.valores.size(); j++) {
                        Parametro p = registro.valores.get(j);
                        Parametro c = cabeza.campos.get(j);
                        Variables.crearVariable(c.tipo, p.nombre, p.valor);
                    }
                    try {
                        RecorridoSQL r = new RecorridoSQL();
                        Object boolDonde = r.Recorrido(donde);

                        if (boolDonde.toString().equalsIgnoreCase("true")) {
                            lista.add(registro.valores);
                        }

                    } catch (CloneNotSupportedException ex) {
                        System.out.println("Error en el nodo donde = " + ex);
                    }
                    Variables.eliminarVariables();
                    Variables.pilaAmbito.pop();
                    Variables.nivelAmbito--;
                } else {
                    lista.add(registro.valores);
                }
            }
        }

        if (!ordenar.isEmpty()) {
            try {
                String campo = (String) ordenar.get(0);
                String tipoOrdenar = (String) ordenar.get(1);

                ArrayList<Parametro> aux = new ArrayList();
                int pos = 0;
                for (int i = 0; i < cabeza.campos.size(); i++) {
                    Parametro p = cabeza.campos.get(i);
                    if (p.nombre.equalsIgnoreCase(campo)) {
                        pos = i;
                        for (int j = 1; j < lista.size(); j++) {
                            ArrayList valores = (ArrayList) lista.get(j);
                            aux.add((Parametro) valores.get(i));
                        }
                        break;
                    }
                }
                if (tipoOrdenar.equalsIgnoreCase("asc")) {
                    Collections.sort(aux);
                } else {
                    Collections.sort(aux, Collections.reverseOrder());
                }
                ArrayList nueva = new ArrayList();
                nueva.add(lista.get(0));

                for (int i = 0; i < aux.size(); i++) {
                    Parametro p = aux.get(i);
                    //System.out.println(p.valor);
                    for (int j = 1; j < lista.size(); j++) {
                        ArrayList valores = (ArrayList) lista.get(j);
                        Parametro v = (Parametro) valores.get(pos);
                        if (v.valor.toString().equalsIgnoreCase(p.valor.toString())) {
                            nueva.add(valores);
                        }
                    }

                }
                //System.out.println("/////////////////////////////////");

                lista.clear();
                lista = nueva;
            } catch (Exception e) {
                System.out.println("Error al ordenar = " + e);
            }
        }
        imprimirSeleccionar(lista);
        return lista;
    }

    public static void imprimirSeleccionar(ArrayList lista) {

        ArrayList cab = (ArrayList) lista.get(0);
        for (int j = 0; j < cab.size(); j++) {
            Parametro p = (Parametro) cab.get(j);
            System.out.print(p.nombre + "           ");
        }
        System.out.println("");
        for (int i = 1; i < lista.size(); i++) {
            ArrayList reg = (ArrayList) lista.get(i);

            for (int j = 0; j < reg.size(); j++) {
                Parametro p = (Parametro) reg.get(j);
                System.out.print(p.valor.toString() + "        ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println(" *************************************** ");

    }

    public static void actualizarTabla(String nombreTabla, ArrayList listaCampos, ArrayList listaValores, Nodo donde) {
        if ("".equals(BaseDeDatos.DBActual)) {
            Errores.agregarErrorSQL("BD", "Error Semantico", "No se ha indicado una base de datos", 0, 0);
            return;
        }
        Encabezado cabeza = obtenerEncabezado(nombreTabla);
        Tabla tabla = obtenerTabla(nombreTabla);
        if (cabeza == null || tabla == null) {
            Errores.agregarErrorSQL(nombreTabla, "Error Semantico", "No existe la tabla " + nombreTabla, 0, 0);
            return;
        }
        // Posiciones de las cabezas
        ArrayList<Integer> posicion = new ArrayList();
        for (int i = 0; i < listaCampos.size(); i++) {
            String nombre = (String) listaCampos.get(i);
            Parametro m = new Parametro(nombre, nombre);
            for (int j = 0; j < cabeza.campos.size(); j++) {
                Parametro p = cabeza.campos.get(j);
                if (p.nombre.equalsIgnoreCase(nombre)) {
                    posicion.add(j);
                }
            }
        }
        for (int i = 0; i < tabla.registros.size(); i++) {
            Registro registro = tabla.registros.get(i);
            if (donde != null) {
                Variables.pilaAmbito.push("donde");
                Variables.nivelAmbito++;

                for (int j = 0; j < registro.valores.size(); j++) {
                    Parametro p = registro.valores.get(j);
                    Parametro c = cabeza.campos.get(j);
                    Variables.crearVariable(c.tipo, p.nombre, p.valor);
                }
                try {
                    RecorridoSQL r = new RecorridoSQL();
                    Object boolDonde = r.Recorrido(donde);

                    if (boolDonde.toString().equalsIgnoreCase("true")) {
                        ArrayList val = new ArrayList();
                        for (int k = 0; k < posicion.size(); k++) {
                            Parametro p = registro.valores.get(posicion.get(k));
                            val.add(p);
                        }
                    }

                } catch (CloneNotSupportedException ex) {
                    System.out.println("Error en el nodo donde = " + ex);
                }
                Variables.eliminarVariables();
                Variables.pilaAmbito.pop();
                Variables.nivelAmbito--;
            } else {
                for (int k = 0; k < posicion.size(); k++) {
                    Parametro p = registro.valores.get(posicion.get(k));
                    p.valor = listaValores.get(k);
                }
            }

        }
    }

    public static Encabezado obtenerEncabezado(String nombreTabla) {
        for (int j = 0; j < RegistroDB.listaTabla.size(); j++) {
            Encabezado cab = RegistroDB.listaTabla.get(j);
            if (cab.nombre.equalsIgnoreCase(nombreTabla)) {
                return cab;
            }
        }
        return null;
    }

    public static Tabla obtenerTabla(String nombreTabla) {
        for (int i = 0; i < listaRegistro.size(); i++) {
            Tabla t = listaRegistro.get(i);
            if (nombreTabla.equalsIgnoreCase(t.nombre)) {
                return t;
            }
        }
        return null;
    }
}

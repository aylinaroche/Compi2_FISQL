package fisql;

import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class Errores {

    public static ArrayList erroresSQL = new ArrayList();
    public static ArrayList erroresXML = new ArrayList();

    public static void agregarErrorSQL(String l, String t, String d, int f, int c) {
        Error e = new Error();
        e.lexema = l;
        e.tipo = t;
        e.descripcion = d;
        e.fila = f;
        e.columna = c;
        erroresSQL.add(e);
        System.out.println("-> " + t + " : " + l + " , " + d + " , " + c);
    }

    public static void imprimirError() {
        System.out.println(" * * * * * * * * * * ERRORES * * * * * * * * * ");
        for (int i = 0; i < erroresSQL.size(); i++) {
            Error e = (Error) erroresSQL.get(i);
            System.out.println("> " + e.descripcion);

        }
    }
}

class Error {

    public String lexema;
    public String descripcion;
    public String tipo;
    public int columna;
    public int fila;

}

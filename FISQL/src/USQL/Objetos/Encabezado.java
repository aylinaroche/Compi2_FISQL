package USQL.Objetos;

import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class Encabezado {

    public String nombre;
    public String path;
    public ArrayList<Parametro> campos;
    public int autoincremental =0;

    public Encabezado(String n, String p, ArrayList l) {
        this.nombre = n;
        this.path = p;
        this.campos = l;
    }
}

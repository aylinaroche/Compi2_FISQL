package USQL.Objetos;

import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class Proced {

    public String nombre;
    public String tipo;
    public ArrayList<Parametro> listaParametros;
    public String intrucciones;

    public Proced(String n, String t, ArrayList p, String i) {
        this.nombre = n;
        this.listaParametros = p;
        this.intrucciones = i;
        this.tipo = t;
    }
}

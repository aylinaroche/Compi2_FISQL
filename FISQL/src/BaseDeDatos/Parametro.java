package BaseDeDatos;

import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class Parametro {

    public String nombre;
    public String tipo;
    public ArrayList complemento;

    public Parametro(String n, String t, ArrayList c) {
        this.nombre = n;
        this.tipo = t;
        this.complemento = c;
    }
}

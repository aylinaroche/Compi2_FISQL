package USQL.Objetos;

import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class Tabla {

    public String nombre;
    public ArrayList<Registro> registros;

    public Tabla(String n, ArrayList r) {
        this.nombre = n;
        this.registros = r;
    }
}

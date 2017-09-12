package USQL.Objetos;

import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class Registro implements Comparable<Registro> {

    public ArrayList<Parametro> valores;

    public Registro(ArrayList r) {
        this.valores = r;
    }

    @Override
    public int compareTo(Registro o) {
        return 0;
        //return valores.compareTo(o.valores);
    }
}
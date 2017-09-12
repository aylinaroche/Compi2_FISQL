package USQL.Objetos;

import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class Parametro implements Comparable<Parametro> {

    public String nombre;
    public String tipo;
    public ArrayList complemento;
    public Object valor;

    public Parametro(String n, String t, ArrayList c) {
        this.nombre = n;
        this.tipo = t;
        this.complemento = c;
    }

    public Parametro(String n, String t, Object v) {
        this.nombre = n;
        this.tipo = t;
        this.valor = v;
    }

    public Parametro(String t, String n) {
        this.nombre = n;
        this.tipo = t;
    }

    public Parametro(String n, Object v) {
        this.nombre = n;
        this.valor = v;
    }

    public Parametro(Object valor) {
        this.valor = valor;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }

    @Override
    public int compareTo(Parametro p) {
        if (p.valor != null && valor != null) {
            String val = valor.toString();
            String pVal = p.valor.toString();
            return val.compareTo(pVal);
        }
        return 0;
    }

}

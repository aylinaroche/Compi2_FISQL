package USQL.Objetos;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

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

        if (valor != null && p.valor != null) {
            String val1 = "";
            String val2 = "";
            if (p.valor instanceof Date && valor instanceof Date) {
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                String fecha1 = df.format((Date) valor);
                String fecha2 = df.format((Date) p.valor);
                try {
                    Date f1 = df.parse(fecha1);
                    Date f2 = df.parse(fecha2);
                    long fechaIni = f1.getTime();
                    long fechaFin = f2.getTime();
                    val1 = String.valueOf(fechaIni);
                    val2 = String.valueOf(fechaFin);
                } catch (ParseException ex) {
                }

            } else {
                val1 = valor.toString();
                val2 = p.valor.toString();
            }
            return val1.compareTo(val2);
        }
        return 0;
    }

}

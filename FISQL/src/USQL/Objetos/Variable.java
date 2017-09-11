package USQL.Objetos;

import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class Variable {

    public String ambito;
    public int nivel;
    public String nombre;
    public Object valor;
    public String tipo;
    public ArrayList<Parametro> atributos;

    public Variable(String t, String n, Object v, String a, int ni, ArrayList atr) {
        this.tipo = t;
        this.nombre = n;
        this.valor = v;
        this.ambito = a;
        this.nivel = ni;
        this.atributos = atr;
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

}

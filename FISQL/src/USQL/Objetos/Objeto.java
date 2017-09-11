package USQL.Objetos;

import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class Objeto implements Cloneable {

    public String nombre;
    public ArrayList<Parametro> listaAtributos;

    public Objeto(String n, ArrayList a) {
        this.nombre = n;
        this.listaAtributos = a;
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

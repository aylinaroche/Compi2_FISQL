package USQL;

import java.util.ArrayList;
import java.util.Stack;
import USQL.Objetos.*;

/**
 *
 * @author aylin
 */
public class Variables {

    public static Stack<String> pilaAmbito = new Stack<>();
    public static int nivelAmbito = 0;
    public static ArrayList listaVariables = new ArrayList();
    //public static ArrayList variableGlobal = new ArrayList();

    public static void crearVariable(String tipo, String nombre, Object valor, String visib) {
//        if (verificarTipo(tipo) == false) {
//            return;
//        }
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equals(nombre) && s.ambito.equals(pilaAmbito.peek()) && s.nivel == nivelAmbito) {
                //paradigmas.ReporteError.agregarErrorGK(nombre, "Error Semantico", "La variable " + nombre + " ya existe", 0, 0);
                return;
            }
        }
//        if (valor instanceof ObjetoALS) {
//            igualarALS(nombre, valor);
//            ObjetoALS obj = (ObjetoALS) valor;
//            obj.nombre = nombre;
//            valor = obj;
//        }
//        
        Variable v = new Variable();
        v.nombre = nombre;
        v.tipo = tipo;
        v.valor = valor;
        v.nivel = nivelAmbito;
        v.ambito = pilaAmbito.peek();
        listaVariables.add(v);

//        paradigmas.Atributos.crearSimboloGraphik(nombre, tipo, "Variable", v.ambito, "0");
    }

}

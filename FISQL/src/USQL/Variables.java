package USQL;

import USQL.Objetos.Parametro;
import BaseDeDatos.RegistroObjeto;
import java.util.ArrayList;
import java.util.Stack;
import USQL.Objetos.*;
import fisql.Errores;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aylin
 */
public class Variables {

    public static Stack<String> pilaAmbito = new Stack<>();
    public static int nivelAmbito = 0;
    public static ArrayList<Variable> listaVariables = new ArrayList();

    public static void crearVariable(String tipo, String nombre, Object valor) {
        if (verificarTipo(tipo,valor) == false) {
            return;
        }
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equals(nombre) && s.ambito.equals(pilaAmbito.peek()) && s.nivel == nivelAmbito) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "La variable " + nombre + " ya existe", 0, 0);
                return;
            }
        }
        if (valor instanceof Objeto) {
            try {
                valor = obtenerObjeto(valor);
            } catch (CloneNotSupportedException ex) {
                System.out.println("Error al asignar objeto  = " + ex);
            }
        }
        Variable v = new Variable(tipo, nombre, valor, pilaAmbito.peek(), nivelAmbito, null);
        listaVariables.add(v);
    }

    public static void crearVariable(String tipo, String nombre) {
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equals(nombre) && s.ambito.equals(pilaAmbito.peek()) && s.nivel == nivelAmbito) {
                Errores.agregarErrorSQL(nombre, "Error Semantico", "La variable " + nombre + " ya existe", 0, 0);
                return;
            }
        }
        ArrayList atr = new ArrayList();
        atr = obtenerAtributos(tipo);
        Variable v = new Variable(tipo, nombre, null, pilaAmbito.peek(), nivelAmbito, atr);
        listaVariables.add(v);
    }

    public static void asignarValor(String nombre, Object valor) {
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equals(nombre) && pilaAmbito.contains(s.ambito)) {
                s.valor = valor;
                return;
            }
        }
        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe la variable " + nombre, 0, 0);
    }

    public static void eliminarVariables() {
        for (int i = listaVariables.size() - 1; i >= 0; i--) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nivel == nivelAmbito) {
                listaVariables.remove(i);
            }
        }
    }

    public static Object ObtenerValor(String nombre) {
        if(nombre.equalsIgnoreCase("nulo")){
            return "nulo";
        }
        Variable s = null;
        for (int i = listaVariables.size() - 1; i >= 0; i--) {
            Variable sim = (Variable) listaVariables.get(i);
            if (sim.nombre.equals(nombre.toLowerCase())) {
                return sim.valor;
            }
        }
        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe la variable", 0, 0);
        return "";
    }

    public static void imprimirVariables() {
        System.out.println("* * * * * * * * * * VARIABLES * * * * * * * * * \n");
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            System.out.println(" -> " + s.nombre + " - " + s.tipo + " - " + s.valor + " - " + s.ambito);
        }
    }

    public static void incrementar(String nombre, int n) {
        for (int i = 0; i < listaVariables.size(); i++) {
            Variable s = (Variable) listaVariables.get(i);
            if (s.nombre.equals(nombre) && pilaAmbito.contains(s.ambito)) {
                try {
                    s.valor = (int) s.valor + n;
                } catch (Exception e) {
                    System.out.println("Error al incrementar = " + e);
                }
                return;
            }
        }
    }

    public static Boolean verificarTipo(String tipo, Object valor) {
        if(valor.toString().equalsIgnoreCase("nulo")){
            return true;
        }
        String t = tipo.toLowerCase();
        if (valor instanceof Integer && t.equalsIgnoreCase("integer")) {
            return true;
        } else if (valor instanceof String && t.equalsIgnoreCase("text")) {
            return true;
        } else if (valor instanceof Double && t.equalsIgnoreCase("double")) {
            return true;
        } else if (valor instanceof Boolean && t.equalsIgnoreCase("bool")) {
            return true;
        } else if (valor instanceof Date && t.equalsIgnoreCase("date")) {
            return true;
        } else if (valor instanceof Date && t.equalsIgnoreCase("datetime")) {
            return true;
        } else if (valor instanceof Integer && t.equalsIgnoreCase("bool")) {
            return true;
        } else{
            for (int i = 0; i < RegistroObjeto.listaObject.size(); i++) {
                Objeto obj = RegistroObjeto.listaObject.get(i);
                if (obj.nombre.equals(tipo) && valor instanceof Objeto) {
                    return true;
                }
            }
        }
        Errores.agregarErrorSQL(tipo, "Error Semantico", "El tipo no existe", 0, 0);
        return false;
    }

    public static ArrayList obtenerAtributos(String nombre) {
        ArrayList l = new ArrayList();
        for (int i = 0; i < RegistroObjeto.listaObject.size(); i++) {
            Objeto obj = RegistroObjeto.listaObject.get(i);

            if (nombre.equalsIgnoreCase(obj.nombre)) {
                for (int j = 0; j < obj.listaAtributos.size(); j++) {
                   Parametro p = (Parametro) obj.listaAtributos.get(j);
                   if(p!=null){
                    l.add(new Parametro(p.nombre,p.tipo,p.complemento.clone()));
                   }
                }
                
                return l;
            }
        }
        return l;
    }

    public static Object obtenerObjeto(Object valor) throws CloneNotSupportedException {

        Objeto objeto = (Objeto) valor;

        for (int i = 0; i < RegistroObjeto.listaObject.size(); i++) {
            Objeto obj = RegistroObjeto.listaObject.get(i);

            if (objeto.nombre.equalsIgnoreCase(obj.nombre)) {
                ArrayList<Parametro> atr = new ArrayList();

                for (int j = 0; j < obj.listaAtributos.size(); j++) {
                    Parametro p = (Parametro) obj.listaAtributos.get(i).clone();
                    Parametro p2 = new Parametro(p.nombre, p.tipo, p.valor);
                    atr.add(p2);
                }
                Objeto nuevo = new Objeto(obj.nombre, (ArrayList) atr.clone());
                return nuevo;
            }
        }
        return null;
    }

    public static Object obtenerValorAtributo(String nombre, String atr) {

        for (int k = 0; k < listaVariables.size(); k++) {
            Variable v = listaVariables.get(k);
            if (v.nombre.equals(nombre)) {
                ArrayList<Parametro> atributos = v.atributos;

                for (int i = 0; i < atributos.size(); i++) {
                    Parametro p = atributos.get(i);
                    if (p.nombre.equals(atr)) {
                        return p.valor;
                    }
                }
            }
        }
        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe la variable", 0, 0);
        return "";
    }

    public static void asignarAtributo(String nombre, String atr, Object valor) {

        for (int k = 0; k < listaVariables.size(); k++) {
            Variable v = listaVariables.get(k);
            if (v.nombre.equals(nombre)) {
                ArrayList<Parametro> atributos = v.atributos;

                for (int i = 0; i < atributos.size(); i++) {
                    Parametro p = atributos.get(i);
                    if (p.nombre.equals(atr)) {
                        p.valor = valor;
                        return;
                    }
                }
            }
        }

        Errores.agregarErrorSQL(nombre, "Error Semantico", "No existe la variable", 0, 0);
        return;
    }

}

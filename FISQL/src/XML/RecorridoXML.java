package XML;

import BaseDeDatos.RegistroMaestro;
import java.util.ArrayList;
import USQL.Nodo;

/**
 *
 * @author aylin
 */
public class RecorridoXML {

    public static ArrayList parametros = new ArrayList();
    public static int tipo = 0;

    public Object Recorrido(Nodo raiz) {
        Object result = null;
      
        if (raiz != null) {
            switch (raiz.texto) {
                case "INICIO":
                    result = Recorrido(raiz.hijos[0]);
                    break;
                case "ETIQUETA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Recorrido(raiz.hijos[0]);
                            Object obj = Recorrido(raiz.hijos[1]);
                            ArrayList lista = (ArrayList) obj;
                            lista.add(result);
                            result = lista;
                            break;
                    }
                    break;
                case "ETIQUETAP":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList lista1 = new ArrayList();
                            lista1.add(Recorrido(raiz.hijos[0]));
                            result = lista1;
                            break;
                        case 2:
                            ArrayList lista = (ArrayList) Recorrido(raiz.hijos[1]);
                            lista.add(Recorrido(raiz.hijos[0]));
                            result = lista;
                            break;
                    }
                    break;
                case "ETQ":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            switch (raiz.hijos[0].texto.toLowerCase()) {
                                case "nombre":
                                case "path":
                                    result = Recorrido(raiz.hijos[1]);
                                    // result = new etq(raiz.hijos[0].texto, result);
                                    break;
                                case "db":
                                    result = Recorrido(raiz.hijos[1]);
                                    ArrayList lista = (ArrayList) result;
                                    RegistroMaestro.agregarBD(lista.get(1).toString(), lista.get(0).toString());
                                    break;
                            }

                            break;
                    }
                    break;
                case "OPCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "CONTENIDO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            if ("id".equals(raiz.hijos[0].tipo)) {
                                result = raiz.hijos[0].texto;
                            } else {
                                String cadena = raiz.hijos[0].texto.replace("\"", "");
                                result = cadena;
                            }
                            break;
                    }
                    break;
            }
        }
        return result;
    }
}

class etq {

    String nombre;
    Object valor;

    public etq(String n, Object v) {
        this.nombre = n;
        this.valor = v;
    }
}

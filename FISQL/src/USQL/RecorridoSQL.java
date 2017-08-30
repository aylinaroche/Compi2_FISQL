package USQL;

import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class RecorridoSQL {

    public static ArrayList parametros = new ArrayList();

    public Object Recorrido(Nodo raiz) {
        Object result = null;
        // Nodo nodo = null;
        if (raiz != null) {
            switch (raiz.texto) {
                case "INICIO":
                    result = Recorrido(raiz.hijos[0]);
                    break;
                case "PAQUETE":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "SENTENCIA":
                    result = Recorrido(raiz.hijos[0]);
                    result = Recorrido(raiz.hijos[1]);
                    break;
                case "SENTENCIAP":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "INSTRUCCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            switch (raiz.hijos[0].texto.toLowerCase()) {
                                case "detener":
                                    break;
                                case "retorno":
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                    break;
                case "TIPO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = raiz.hijos[0].texto.toLowerCase();
                            break;
                    }
                    break;
                case "COMPLEMENTO":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "COMPLEMENTOP":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            //result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "DDL":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "crear":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "usar":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                        case "alterar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "eliminar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CREAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "base_datos":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "tabla":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                        case "objeto":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "procedimiento":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "funcion":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;

                case "PARAMETROS":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "PARAMETROSP":
                    switch (raiz.cantidadHijos) {
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            Recorrido(raiz.hijos[3]);
                            Recorrido(raiz.hijos[4]);
                            break;
                    }
                    break;
                case "PARAMETROSVAR":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "PARAMETROSVARP":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            result = Recorrido(raiz.hijos[3]);
                            break;
                    }
                    break;
                case "ALTERAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "tabla":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                        case "objeto":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "usuario":
                            result = Recorrido(raiz.hijos[1]);
                            break;

                    }
                    break;
                case "TIPOALTERAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "agregar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "quitar":
                            //result = Recorrido(raiz.hijos[2]);
                            break;

                    }
                    break;
                case "ELIMINAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "tabla":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "base_datos":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                        case "objeto":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "usuario":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "DML":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "insertar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "actualizar":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                        case "borrar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "seleccionar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TIPOINSERTAR":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LISTACAMPOS":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                case "LISTACAMPOSP":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LISTAVALORES":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LISTAVALORESP":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "DONDE":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "SELECCIONAR":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "ORDENAR":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TIPOORDENAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "asc":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "desc":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "DCL":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "otorgar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "denegar":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "LISTAVARIABLES":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "DECLARAR":
                    switch (raiz.cantidadHijos) {
                        case 6:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "DECLARARP":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "ASIGNACION":
                    switch (raiz.cantidadHijos) {
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "VAR":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "SI":
                    switch (raiz.cantidadHijos) {
                        case 7:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "SINO":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "SELECCIONA":
                    switch (raiz.cantidadHijos) {
                        case 7:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CASO":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TIPOCASO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            break;
                    }
                    break;
                case "PARA":
                    switch (raiz.cantidadHijos) {
                        case 7:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "PARAP":
                    switch (raiz.hijos[0].texto) {
                        case "declarar":
                            break;
                        case "asignacion":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "OPCIONPARA":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            break;
                    }
                    break;
                case "MIENTRAS":
                    switch (raiz.cantidadHijos) {
                        case 7:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "IMPRIMIR":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CONTAR":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "FUNCION":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "backup":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "restaurar":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "BACKUP":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "usqldump":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "completo":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "RESTAURAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "usqldump":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "completo":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "RETORNAR":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 1:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LLAMADA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "OBJETO":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                    }
                    break;
                case "OPERACION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            break;
                    }
                    break;
            }
        }
        return result;
    }

}

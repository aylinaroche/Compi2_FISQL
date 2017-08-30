package fisql;

import AnalizadorSQL.*;
import USQL.*;

/**
 *
 * @author aylin
 */
public class FISQL {

    public static void main(String[] args) throws ParseException {
//        try {
//            FileInputStream archivo = new FileInputStream("/home/aylin/NetBeansProjects/FISQL/src/AnalizadorSQL/texto.txt");
//
//            parserSQL analizador = new parserSQL(archivo);
//            analizador.INICIO();
//
//            System.out.println("Se ha compilado con exito");
//        } catch (ParseException e) {
//            System.out.println(e.getMessage());
//            System.out.println("Se han encontrado errores");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(FISQL.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            Nodo nodo = parserSQL.compilar(texto1());
            RecorridoSQL r = new RecorridoSQL();
            r.Recorrido(nodo);
            
        } catch (ParseException e) {
            System.out.println("ERROR: " + e);
        }
    }

    public static String texto1() {
        String texto = "crear base_datos Proyecto1db;\n"
                +"crear base_datos db1;\n"
                +"crear base_datos db1;\n"
                + "\n"
                + "crear tabla Estudiante (INTEGER id Llave_Primaria Autoincrementable, TEXT Nombre No Nulo, DATE fh_nac No Nulo, BOOL\n"
                + "trabaja);\n"
                + "\n"
                + "crear objeto Empresa (INTEGER codigo, TEXT nombre, TEXT telefono);\n"
                + "\n"
                + "CREAR PROCEDIMIENTO set_Salario (DOUBLE @salario, DOUBLE\n"
                + "@comision, INTEGER @codigo){\n"
                + "	ACTUALIZAR TABLA trabajador (salario) VALORES (@salario);\n"
                + "}\n"
                + "\n"
                + "CREAR FUNCION suma (INTEGER @var1, INTEGER @var2) INTEGER{\n"
                + "	Set_Salario(8500.00,964.50);\n"
                + "	@var_ejemplo = suma(10,15);\n"
                + "	RETORNO @var1 * @var2;\n"
                + "}\n"
                + "\n"
                + "USAR Proyecto1;\n"
                + "\n"
                + "ALTERAR TABLA Estudiante AGREGAR (INTEGER edad, TEXT direccion);\n"
                + "ALTERAR TABLA Estudiante QUITAR trabaja;\n"
                + "\n"
                + "ALTERAR OBJETO empresa AGREGAR (INTEGER nit);\n"
                + "ALTERAR OBJETO empresa QUITAR direccion, nit;\n"
                + "\n"
                + "ALTERAR USUARIO Admin CAMBIAR password = \"1234\";\n"
                + "\n"
                + "ELIMINAR TABLA estudiante;\n"
                + "ELIMINAR BASE_DATOS Proyecto1;\n"
                + "ELIMINAR OBJETO empresa;\n"
                + "ELIMINAR USER usuario1;\n"
                + "\n"
                + "INSERTAR EN TABLA estudiante (\"Jose Luis Figueroa\", 10-10-1994,1);\n"
                + "\n"
                + "INSERTAR EN TABLA estudiante (Nombre, fh_nac) VALORES (\"Jose Luis\n"
                + "Figueroa\", 10-10-1994);\n"
                + "\n"
                + "ACTUALIZAR TABLA estudiante (nombre, trabaja) VALORES (\"Jose Luis\n"
                + "Martinez Hernandez\",1) DONDE nombre == \"Jose Luis Martinez\" &&\n"
                + "trabaja == 0;\n"
                + "ACTUALIZAR TABLA estudiante (nombre, trabaja) VALORES (\"Carlos\n"
                + "Enriquez\",0);\n"
                + "\n"
                + "BORRAR EN TABLA estudiante DONDE nombre == \"Jose Luis Martinez\"\n"
                + "&& trabaja == 0;\n"
                + "BORRAR EN TABLA estudiante;\n"
                + "\n"
                + "SELECCIONAR nombre, fh_nac DE estudiante DONDE id > 10 ORDENAR POR\n"
                + "fh_nac asc;\n"
                + "SELECCIONAR * DE estudiante DONDE estudiante.id == \n"
                + "asignacion.id_estudiante;\n"
                + "SELECCIONAR nombre, fh_nac DE estudiante DONDE id < 10;\n"
                + "SELECCIONAR * DE estudiante;\n"
                + "\n"
                + "OTORGAR PERMISOS usuario1, Proyecto1.estudiante;\n"
                + "OTORGAR PERMISOS usuario1, Proyecto1.*;\n"
                + "\n"
                + "DENEGAR PERMISOS usuario1, Proyecto1.estudiante;\n"
                + "DENEGAR PERMISOS usuario1, Proyecto1.*;\n"
                + "\n"
                + "CREAR PROCEDIMIENTO set_Salario (DOUBLE @salario, DOUBLE\n"
                + "@comision, INTEGER @codigo){\n"
                + "\n"
                + "	DECLARAR @variable1, @variable2 INTEGER = 23;\n"
                + "	DECLARAR @ejemplo TEXT = \"Hola mundo\";\n"
                + "	DECLARAR @empresa1 emp;\n"
                + "\n"
                + "	@a = 3 * 23;\n"
                + "	@ejemplo = saludo(\"Hola\");\n"
                + "\n"
                + "	@empresa1.nit = 87654321-0;\n"
                + "\n"
                + "	SI( @a < 10 ){\n"
                + "		@b = 23;\n"
                + "	}\n"
                + "\n"
                + "	SI( @a < 10 ){\n"
                + "		@b = 23;\n"
                + "	}SINO{\n"
                + "		@b = 46;\n"
                + "	}\n"
                + "\n"
                + "	SELECCIONA ( @a * 15 ){\n"
                + "		CASO 10 :\n"
                + "			@b = 23;\n"
                + "		CASO 15 :\n"
                + "			@b = 23;\n"
                + "		DEFECTO :\n"
                + "			@b = 23;\n"
                + "	}\n"
                + "\n"
                + "	PARA(DECLARAR @a INTEGER = 10; @a > 0; --){\n"
                + "		@b = 23;\n"
                + "	}\n"
                + "\n"
                + "	MIENTRAS( @a < 10 ){\n"
                + "		@a = @a + 1;\n"
                + "		DETENER;\n"
                + "	}\n"
                + "\n"
                + "	IMPRIMIR(\"Hola mundo\");\n"
                + "	@fecha = FECHA();\n"
                + "	@fecha_h = FECHA_HORA();\n"
                + "\n"
                + "	BACKUP USQLDUMP Proyecto1 Proyecto1_backup;\n"
                + "	BACKUP COMPLETO Proyecto1 Proyecto1_backup2;\n"
                + "\n"
                + "	RESTAURAR USQLDUMP \"ruta_archivo\";\n"
                + "	RESTAURAR COMPLETO \"/home/usuario/backups/backup_prueba.zip\";\n"
                + "	\n"
                + "	CONTAR(<<SELECCIONAR * DE estudiante>>);\n"
                + "}";
        return texto;
    }

}

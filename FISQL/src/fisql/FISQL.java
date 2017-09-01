package fisql;

import AnalizadorSQL.*;
import BaseDeDatos.RegistroMaestro;
import USQL.*;
import java.io.IOException;

/**
 *
 * @author aylin
 */
public class FISQL {

    public static void main(String[] args) throws ParseException, IOException {

        RegistroMaestro rm = new RegistroMaestro();
        rm.cargarBD();

        Nodo nodo = parserSQL.compilar(textoSQL1());
        RecorridoSQL r = new RecorridoSQL();
        r.Recorrido(nodo);

        //Nodo n = parserXML.compilar(textoXML1());
        //RecorridoXML x = new RecorridoXML();
        //x.Recorrido(n);
        imprimirDatos();
        crearArchivos();

    }

    public static String textoSQL1() {

        String texto = "crear base_datos Proyecto1;\n"
                + "crear base_datos db1;\n"
                + "crear base_datos db1;\n"
                + "\n"
                + "crear tabla Estudiante (INTEGER id Llave_Primaria Autoincrementable, TEXT Nombre No Nulo, DATE fh_nac No Nulo, BOOL\n"
                + "trabaja);\n"
                + "\n"
                + "crear objeto Empresa (INTEGER codigo, TEXT nombre, TEXT telefono);\n"
                + "\n"
                + "USAR Proyecto1;\n"
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
                //                + "\n"
                //                + "ALTERAR TABLA Estudiante AGREGAR (INTEGER edad, TEXT direccion);\n"
                //                + "ALTERAR TABLA Estudiante QUITAR trabaja;\n"
                //                + "\n"
                //                + "ALTERAR OBJETO empresa AGREGAR (INTEGER nit);\n"
                //                + "ALTERAR OBJETO empresa QUITAR direccion, nit;\n"
                //                + "\n"
                //                + "ALTERAR USUARIO Admin CAMBIAR password = \"1234\";\n"
                //                + "\n"
                //                + "ELIMINAR TABLA estudiante;\n"
                //                + "ELIMINAR BASE_DATOS Proyecto1;\n"
                //                + "ELIMINAR OBJETO empresa;\n"
                //                + "ELIMINAR USER usuario1;\n"
                //                + "\n"
                //                + "INSERTAR EN TABLA estudiante (\"Jose Luis Figueroa\", 10-10-1994,1);\n"
                //                + "\n"
                //                + "INSERTAR EN TABLA estudiante (Nombre, fh_nac) VALORES (\"Jose Luis\n"
                //                + "Figueroa\", 10-10-1994);\n"
                //                + "\n"
                //                + "ACTUALIZAR TABLA estudiante (nombre, trabaja) VALORES (\"Jose Luis\n"
                //                + "Martinez Hernandez\",1) DONDE nombre == \"Jose Luis Martinez\" &&\n"
                //                + "trabaja == 0;\n"
                //                + "ACTUALIZAR TABLA estudiante (nombre, trabaja) VALORES (\"Carlos\n"
                //                + "Enriquez\",0);\n"
                //                + "\n"
                //                + "BORRAR EN TABLA estudiante DONDE nombre == \"Jose Luis Martinez\"\n"
                //                + "&& trabaja == 0;\n"
                //                + "BORRAR EN TABLA estudiante;\n"
                //                + "\n"
                //                + "SELECCIONAR nombre, fh_nac DE estudiante DONDE id > 10 ORDENAR POR\n"
                //                + "fh_nac asc;\n"
                //                + "SELECCIONAR * DE estudiante DONDE estudiante.id == \n"
                //                + "asignacion.id_estudiante;\n"
                //                + "SELECCIONAR nombre, fh_nac DE estudiante DONDE id < 10;\n"
                //                + "SELECCIONAR * DE estudiante;\n"
                //                + "\n"
                //                + "OTORGAR PERMISOS usuario1, Proyecto1.estudiante;\n"
                //                + "OTORGAR PERMISOS usuario1, Proyecto1.*;\n"
                //                + "\n"
                //                + "DENEGAR PERMISOS usuario1, Proyecto1.estudiante;\n"
                //                + "DENEGAR PERMISOS usuario1, Proyecto1.*;\n"
                //                + "\n"
                //                + "CREAR PROCEDIMIENTO set_Salario (DOUBLE @salario, DOUBLE\n"
                //                + "@comision, INTEGER @codigo){\n"
                //                + "\n"
                //                + "	DECLARAR @variable1, @variable2 INTEGER = 23;\n"
                //                + "	DECLARAR @ejemplo TEXT = \"Hola mundo\";\n"
                //                + "	DECLARAR @empresa1 emp;\n"
                //                + "\n"
                //                + "	@a = 3 * 23;\n"
                //                + "	@ejemplo = saludo(\"Hola\");\n"
                //                + "\n"
                //                + "	@empresa1.nit = 87654321-0;\n"
                //                + "\n"
                //                + "	SI( @a < 10 ){\n"
                //                + "		@b = 23;\n"
                //                + "	}\n"
                //                + "\n"
                //                + "	SI( @a < 10 ){\n"
                //                + "		@b = 23;\n"
                //                + "	}SINO{\n"
                //                + "		@b = 46;\n"
                //                + "	}\n"
                //                + "\n"
                //                + "	SELECCIONA ( @a * 15 ){\n"
                //                + "		CASO 10 :\n"
                //                + "			@b = 23;\n"
                //                + "		CASO 15 :\n"
                //                + "			@b = 23;\n"
                //                + "		DEFECTO :\n"
                //                + "			@b = 23;\n"
                //                + "	}\n"
                //                + "\n"
                //                + "	PARA(DECLARAR @a INTEGER = 10; @a > 0; --){\n"
                //                + "		@b = 23;\n"
                //                + "	}\n"
                //                + "\n"
                //                + "	MIENTRAS( @a < 10 ){\n"
                //                + "		@a = @a + 1;\n"
                //                + "		DETENER;\n"
                //                + "	}\n"
                //                + "\n"
                //                + "	IMPRIMIR(\"Hola mundo\");\n"
                //                + "	@fecha = FECHA();\n"
                //                + "	@fecha_h = FECHA_HORA();\n"
                //                + "\n"
                //                + "	BACKUP USQLDUMP Proyecto1 Proyecto1_backup;\n"
                //                + "	BACKUP COMPLETO Proyecto1 Proyecto1_backup2;\n"
                //                + "\n"
                //                + "	RESTAURAR USQLDUMP \"ruta_archivo\";\n"
                //                + "	RESTAURAR COMPLETO \"/home/usuario/backups/backup_prueba.zip\";\n"
                //                + "	\n"
                //                + "	CONTAR(<<SELECCIONAR * DE estudiante>>);\n"
                //    + "}
                + "";
        return texto;
    }

    public static String textoSQL2() {

        String texto = "CREAR BASE_DATOS Ejemplo1;\n"
                + "USAR Ejemplo1;\n"
                + "\n"
                + "CREAR OBJETO Direccion(INTEGER avenida, INTEGER calle, TEXT nombre, TEXT descripcion);\n"
                + "\n"
                + "CREAR TABLA Estudiante ( INTEGER id Llave_Primaria Autoincrementable, TEXT Nombre No Nulo,\n"
                + "DATE fh_nac No Nulo, BOOL trabaja, Direccion direccion);\n"
                + "\n"
                + "CREAR TABLA Curso( INTEGER id Llave_Primaria Autoincrementable,TEXT Nombre No Nulo,\n"
                + "INTEGER creditos No Nulo);\n"
                + "\n"
                + "CREAR TABLA Asignacion( INTEGER id Llave_Primaria Autoincrementable, DATETIME fh_Asignacion No Nulo,INTEGER id_estudiante Llave_Foranea Estudiante,\n"
                + "INTEGER id_curso Llave_Foranea Curso);\n"
                + "\n"
                + "CREAR PROCEDIMIENTO Asignar (INTEGER @id_e, INTEGER @id_c){\n"
                + "\n"
                + "	DECLARAR @estudiante INTEGER= CONTAR(<<SELECCIONAR * DE estudiante DONDE id == @id_e >>);\n"
                + "\n"
                + "	DECLARAR @curso INTEGER= CONTAR(<<SELECCIONAR * DE curso DONDE id == @id_c >>);\n"
                + "	\n"
                + "	SI (@curso > 0){\n"
                + "		SI(@estudiante > 0){\n"
                + "			INSERTAR EN TABLA asignacion(id_estudiante, id_curso) \n"
                + "			VALORES (@id_e, @id_c);\n"
                + "		}SINO{\n"
                + "			IMPRIMIR(\"El usuario ingresado no existe\");\n"
                + "		}\n"
                + "	}SINO{\n"
                + "			IMPRIMIR(\"El curso ingresado no existe\");\n"
                + "	}\n"
                + "}\n"
                + "\n"
                + "CREAR FUNCION crearDireccion (INTEGER @calle, INTEGER @avenida, TEXT @nombre, TEXT @descripcion) Direccion{\n"
                + "	\n"
                + "	DECLARAR @direc DIRECCION;\n"
                + "	@direc.calle = @calle;\n"
                + "	@direc.avenida = @avenida;\n"
                + "	@direc.nombre = @nombre;\n"
                + "	@direc.descripcion = @descripcion;\n"
                + "	RETORNO @direc;\n"
                + "}";
        return texto;
    }

    public static String textoSQL3() {

        String texto = "CREAR BASE_DATOS Ejemplo2;\n"
                + "\n"
                + "USAR Ejemplo2;\n"
                + "\n"
                + "CREAR TABLA Proveedor(\n"
                + "	INTEGER id Llave_Primaria Autoincrementable,\n"
                + "	TEXT nombre No Nulo,\n"
                + "	TEXT direccion No Nulo\n"
                + ");\n"
                + "CREAR TABLA Producto(\n"
                + "	INTEGER id Llave_Primaria Autoincrementable,\n"
                + "	TEXT nombre No Nulo,\n"
                + "	DOUBLE precio No Nulo,\n"
                + "	INTEGER id_proveedor Llave_Foranea Proveedor\n"
                + ");\n"
                + "CREAR PROCEDIMIENTO llenarTablas(){\n"
                + "	DECLARAR @producto INTEGER = 0;\n"
                + "	#inserta 10 registros en la tabla proveedor\n"
                + "	\n"
                + "	PARA(DECLARAR @i INTEGER = 0; @i < 10; ++){\n"
                + "		INSERTAR EN TABLA Proveedor(id, nombre, direccion)\n"
                + "		VALORES (@i, \"Proveedor\" + @i, \"Direccion\" + @i);\n"
                + "		#inserta 20 productos por proveedor\n"
                + "		\n"
                + "		PARA(DECLARAR @j INTEGER = 0; @j < 20; ++){\n"
                + "			DECLARAR @precio DOUBLE = generarPrecio(@producto);\n"
                + "			INSERTAR EN TABLA Producto\n"
                + "			(@producto, \"Producto\" + @producto, @precio, @i);\n"
                + "			@producto = @producto + 1;\n"
                + "		}\n"
                + "	}\n"
                + "}\n"
                + "\n"
                + "CREAR FUNCION generarPrecio(INTEGER @a) INTEGER{\n"
                + "	DECLARAR @retorno DOUBLE = 1.5;\n"
                + "	RETORNO @retorno * @a;\n"
                + "}\n"
                + "\n"
                + "#llenar las tablas\n"
                + "llenarTablas();\n"
                + "#imprimir cantidad de registros de la tabla Producto\n"
                + "\n"
                + "IMPRIMIR(contar(<<SELECCIONAR * DE Producto>>));\n"
                + "#el resultado de la instrucción debería ser:\n"
                + "#200\n"
                + "#borrar todos los productos del proveedor con id 1\n"
                + "BORRAR EN TABLA Producto DONDE id_proveedor == 1;\n"
                + "#imprimir cantidad de registros de la tabla Producto\n"
                + "IMPRIMIR(contar(<<SELECCIONAR * DE Producto>>));";
        return texto;
    }

    public static String textoXML1() {
        String texto = ""
                + "<DB>\n"
                + "<nombre>DB1</nombre>\n"
                + "<path>\"C:/Documentos/BD/DB1.usac\"</path>\n"
                + "</DB>\n"
                + "<DB>\n"
                + "<nombre>DB2</nombre>\n"
                + "<path>\"C:/Documentos/BD/DB2.usac\"</path>\n"
                + "</DB>"
                + ""
                + "<Procedure>\n"
                + "<path>\"C:/Documentos/BD/proc.usac\"</path>\n"
                + "</Procedure>\n"
                + "<Object>\n"
                + "<path>\"C:/Documentos/BD/obj.usac\"</path>\n"
                + "</ Object >\n"
                + "<Tabla>\n"
                + "<nombre>nombre_tabla1</nombre>\n"
                + "<path>\"C:/Documentos/BD/nombre_tabla1.usac\"</path>\n"
                + "<rows>\n"
                + "<int>Nombre_campo1</int>\n"
                + "<text>Nombre_campo2</text>\n"
                + "<bool>Nombre_campo3</bool>\n"
                + "</rows>\n"
                + "</Tabla>"
                + ""
                //                + ""
                //                + "<Row>\n"
                //                + "<Nombre_campo1> 1 </Nombre_campo1>\n"
                //                + "<Nombre_campo2> “Texto” </Nombre_campo2>\n"
                //                + "<Nombre_campo3> True </Nombre_campo3>\n"
                //                + "</Row>\n"
                //                + "<Row>\n"
                //                + "<Nombre_campo1> 2 </Nombre_campo1>\n"
                //                + "<Nombre_campo2> “Texto” </Nombre_campo2>\n"
                //                + "<Nombre_campo3> False </Nombre_campo3>\n"
                //                + "</Row>"
                //                + ""
                //                + ""
                //                + "<Proc>\n"
                //                + "< nombre >nombre_proc1</ nombre >\n"
                //                + "<params>\n"
                //                + "<int>Nombre_param1</int>\n"
                //                + "<text>Nombre_param2</text>\n"
                //                + "<bool>Nombre_param3</bool>\n"
                //                + "</params>\n"
                //                + "<src> Instrucciones </src>\n"
                //                + "</ Proc >\n"
                //                + "<Proc>\n"
                //                + "<nombre>nombre_proc2</ nombre >\n"
                //                + "<params>\n"
                //                + "<int>Nombre_param1</int>\n"
                //                + "<text>Nombre_param2</text>\n"
                //                + "<bool>Nombre_param3</bool>\n"
                //                + "</params>\n"
                //                + "<src> Instrucciones </src>\n"
                //                + "</ Proc >"
                //                + ""
                //                + ""
                //                + "<Obj>\n"
                //                + "< nombre >nombre_obj1</ nombre >\n"
                //                + "<attr>\n"
                //                + "<int>Nombre_param1</int>\n"
                //                + "<text>Nombre_param2</text>\n"
                //                + "<bool>Nombre_param3</bool>\n"
                //                + "</attr>\n"
                //                + "</ Obj >\n"
                //                + "<Obj >\n"
                //                + "<nombre>nombre_obj2</ nombre >\n"
                //                + "<attr>\n"
                //                + "<int>Nombre_param1</int>\n"
                //                + "<text>Nombre_param2</text>\n"
                //                + "<bool>Nombre_param3</bool>\n"
                //                + "</attr>\n"
                //                + "</ Obj >"
                + ""
                + "";
        return texto;
    }

    public static String textoXML2() {

        String texto = "";
        return texto;
    }

    public static String textoXML3() {

        String texto = "";
        return texto;
    }

    public static void imprimirDatos() {
        RegistroMaestro.imprimir();
    }

    public static void crearArchivos() {
        RegistroMaestro rm = new RegistroMaestro();
        rm.generarArchivo();
    }
}

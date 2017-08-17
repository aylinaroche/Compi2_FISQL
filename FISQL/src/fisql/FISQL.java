package fisql;

import AnalizadorSQL.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aylin
 */
public class FISQL {

    public static void main(String[] args)throws ParseException
	{
		try
		{
                    FileInputStream archivo = new FileInputStream("/home/aylin/NetBeansProjects/FISQL/src/AnalizadorSQL/texto.txt");  
                    
			parserSQL analizador = new parserSQL(archivo);
			analizador.INICIO();
                        
			System.out.println("Se ha compilado con exito");
		}
		catch(ParseException e)
		{
			System.out.println(e.getMessage());
			System.out.println("Se han encontrado errores");
		} catch (FileNotFoundException ex) {
            Logger.getLogger(FISQL.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
    
}

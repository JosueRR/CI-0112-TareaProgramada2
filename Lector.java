
/**
 * Clase Lector
 * 
 * @author Judith Vargas and Josué Retana  
 * @version 1
 */ 
import java.io.*;
public class Lector
{    
    public String[] leer() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String ecuacion = "";
        String[] ecuacionTotal = new String[2];
        try {
            // Abrir el fichero y crear BufferedReader para hacer la lectura            
            archivo = new File ("C:/Users/personal/Desktop/Ecuacion.txt");

            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!= null)
                ecuacion += linea;
            
            //Dividir la ecuacion completa en partes
            ecuacionTotal = ecuacion.split(":");  

        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerrar el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try{                    
                if( null != fr ){   
                    fr.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        return ecuacionTotal;
    }
}


/**
 * Clase Escritor
 * 
 * @author Judith Vargas and Josué Retana  
 * @version 1
 */ 
import java.io.*;

public class Escritor
{
  public void escribir(String ecuacionResultadoString ){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("C:/Users/personal/Desktop/Ecuacion.txt");
            pw = new PrintWriter(fichero);

            pw.println("Ecuacion simplificada: " + ecuacionResultadoString);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
}


/**
 * Clase Escritor
 * 
 * @author Judith Vargas and Josué Retana  
 * @version 1
 */ 
import java.io.*;

public class Escritor
{
    public void escribir(String ecuacionResultadoString, String entradaEcuacionPrimera, 
                        String entradaEcuacionSegunda,String operador, String entrada){
        FileWriter fichero = null;
        PrintWriter pw = null;
        entrada = entrada.replace("Ecuacion.txt", "Resultado.txt");
        try
        {
            fichero = new FileWriter(entrada);
            pw = new PrintWriter(fichero);
            
            pw.println("Ecuación ingresada: " + entradaEcuacionPrimera + operador + entradaEcuacionSegunda);
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

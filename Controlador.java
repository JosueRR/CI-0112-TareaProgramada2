
/**
 * Clase Controlador: contiene el controlador del programa.
 * 
 * @author Judith Vargas and Josué Retana  
 * @version 1
 */
import javax.swing.JOptionPane;
public class Controlador {
    //Variables para realizar llamadas a diferentes clases
    Lista lista = new Lista();
    Vista vista = new Vista();
    Escritor escritor = new Escritor();
    Lector lector = new Lector();

    //Variables donde se almacenan las ecuaciones
    Lista ecuacion1 = new Lista();
    Lista ecuacion2 = new Lista();
    Lista ecuacionGeneral = new Lista();
    Lista ecuacionResultado = new Lista();

    //Varaibles de tipo String e Int   
    boolean primerValor = true;
    String entradaEcuacionPrimera = "";
    String entradaEcuacionSegunda = "";
    String entradaRutaArchivo = "";
    String operador = "";
    String ecuacionResultadoString = "";

    /**
     * Método que tiene el controlador del programa
     */
    public void Controlador() {
        String entrada= ""; 
        while (! "c".equals (entrada)){
            entrada= JOptionPane.showInputDialog ( "Calculadora de ecuaciones\n\n"
                +"Escoja una opcion:\n"               
                + "a. Ingresar ecuacion de manera manual.\n"
                + "b. Ingresar ecuacion por txt.\n"
                + "c. Salir. \n");

            //Dependiendo del caso
            switch (entrada.toLowerCase()){
                case "a":
                entradaEcuacionPrimera = vista.ventanaEscribirEcuacion();
                ecuacion1 = lista.ingresarEcuacion(entradaEcuacionPrimera);
                vista.mostrarIngreso(entradaEcuacionPrimera);

                operador = vista.ventanaEscribirOperador();
                vista.mostrarIngreso(operador);

                entradaEcuacionSegunda = vista.ventanaEscribirEcuacion();
                ecuacion2 = lista.ingresarEcuacion(entradaEcuacionSegunda);
                vista.mostrarIngreso(entradaEcuacionSegunda);

                ecuacionResultado = lista.operacionAritmetica(operador, ecuacion1, ecuacion2);

                ecuacionResultadoString = lista.convertirString(ecuacionResultado,primerValor);
                vista.mostrarResultado(ecuacionResultadoString);
                break;

                case "b":
                entradaRutaArchivo = vista.ventanaRuta();
                String[] ecuacionTotal = lector.leer(entradaRutaArchivo);

                entradaEcuacionPrimera = ecuacionTotal[0];
                ecuacion1 = lista.ingresarEcuacion(entradaEcuacionPrimera);

                operador = ecuacionTotal[1];

                entradaEcuacionSegunda = ecuacionTotal[2];
                ecuacion2 = lista.ingresarEcuacion(entradaEcuacionSegunda);

                ecuacionResultado = lista.operacionAritmetica(operador, ecuacion1, ecuacion2);

                ecuacionResultadoString = lista.convertirString(ecuacionResultado,primerValor);
                
                
                escritor.escribir(ecuacionResultadoString,entradaEcuacionPrimera, entradaEcuacionSegunda, operador,entradaRutaArchivo);
                
                JOptionPane.showMessageDialog(null,"Ecuación leída y simplificada");
                break;

                case "c":
                JOptionPane.showMessageDialog(null,"Muchas gracias por usar el programa");
                break;
            }
        }       
    }
}

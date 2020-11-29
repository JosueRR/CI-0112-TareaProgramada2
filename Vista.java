
/**
 * Clase Vista: contiene la vista del programa.
 * 
 * @author Judith Vargas and Josué Retana  
 * @version 1
 */

import javax.swing.JOptionPane;

public class Vista {
    /**
     * Método que despligua la ventana preguntando por la ecuación al usurio. Retorna un String.
     */
    public String ventanaEscribirEcuacion() {      
        String ecuacion = JOptionPane.showInputDialog("Escriba la ecuación");
        return ecuacion;
    }

    /**
     * Método que despligua la ventana preguntando por la operación al usurio. Retorna un String.
     */
    public String ventanaEscribirOperador() {
        String operador = "";
        while (!"+".equals(operador) && !"-".equals(operador) && !"*".equals(operador) && !"/".equals(operador)) {
            operador = JOptionPane.showInputDialog("Escriba el operador");
        }
        return operador;
    }

    /**
     * Método que despligua la ventana con el resultado
     */
    public void mostrarResultado(String ecuacionResultadoString) {
        JOptionPane.showMessageDialog(null,"Ecuacion simplificada: "+ ecuacionResultadoString);
    }
    
    /**
     * Método que despligua la ventana con el ingreso del usuario
     */
    public void mostrarIngreso(String ingreso) {
        JOptionPane.showMessageDialog(null,"Usted ha ingresado: "+ ingreso);
    }
    
    /**
     * Método que despligua la ventana que pregunta por la dirección de archivos
     */
    public String ventanaRuta() {
        return JOptionPane.showInputDialog("Escriba la dirección en donde se encuentra el archivo (leer formato en manual de usuario)");
    }
}

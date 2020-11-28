
/**
 * Clase Nodo: contiene el objeto tipo Nodo.
 * 
 * @author Judith Vargas and Josué Retana  
 * @version 1
 */

public class Nodo {
    //Variables que conforman el objeto tipo nodo
    private double valor;
    private char incognita;
    private int potencia;
    private Nodo siguiente;

    /**
     * Constructor de la clase nodo, caso sin parámetros.
     */
    public void Nodo() {
        this.valor = 0;
        this.incognita = ' ';
        this.potencia = 0;
        this.siguiente = null;
    }
    
    /**
     * Constructor de la clase nodo, caso con parámetros.
     */
    public Nodo(double valor,char incognita,int potencia) {
        this.valor = valor;
        this.incognita = incognita;   
        this.potencia = potencia;
    }
    
    //Setters y getters de la clase    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public char getIncognita() {
        return incognita;
    }

    public void setIncognita(char incognita) {
        this.incognita = incognita;
    }
    
    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }
    
    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}


/**
 * Clase Lista: contiene la lista enlazada y sus funciones.
 * 
 * @author Judith Vargas and Josué Retana  
 * @version 1
 */ 

public class Lista {

    String operador = "";
    String ecuacionResultadoString = "";

    int potencia1 = 0;
    int potencia2 = 0;
    int potenciaNueva = 0;
    double valor1 = 0;
    double valor2 = 0;
    double valorNuevo = 0;

    private Nodo inicioCola;
    private Nodo finalCola;
    private int tamanio;

    /**
     * Constructor para objectos de la clase Cola.
     * 
     */
    public void Lista()
    {
        inicioCola = null;
        finalCola = null;
        tamanio = 0;
    }

    public boolean esVacia()
    {
        return inicioCola == null;
    }

    public int getTamaño()
    {
        return tamanio;
    }

    /**
     * Método que divide y crea las ecuaciones. Recibe un String con la euacion ingresada por el usuario 
     * y el número de ecuación que corresponde.
     * 
     */
    public Lista ingresarEcuacion(String ecuacion) {
        //Variable que se utiliza para realizar llamadas a otros métodos de la clase
        Lista lista = new Lista();
        //Varaibles que conforman el objeto tipo Nodo
        double valor;
        char incognita;
        int potencia;

        String[] vectorEcuaciones = ecuacion.split("(?=-|\\+)");
        for (int i = 0; i < vectorEcuaciones.length; i++) {
            int poscIndiceX = vectorEcuaciones[i].indexOf("x");
            int poscIndiceGrado = vectorEcuaciones[i].indexOf("^");
            int poscIndiceUltimo = vectorEcuaciones[i].length();
            try {
                valor = Double.parseDouble(vectorEcuaciones[i].substring(0, poscIndiceX));
                incognita = 'x';
                potencia = Integer.parseInt(vectorEcuaciones[i].substring(poscIndiceGrado + 1, poscIndiceUltimo));
            }
            catch (StringIndexOutOfBoundsException e){
                valor = 0;
                incognita = 'x';
                potencia = 0;
            }
            lista.agregarElemento(valor, incognita, potencia);
        }
        return lista;
    }

    /**
     * Método que se encarga de realizar las operaciones aritméticas necesarias.
     * 
     */ 
    public Lista operacionAritmetica(String operador, Lista ecuacion1, Lista ecuacion2) {
        Lista ecuacionGeneral = new Lista();
        Lista ecuacionResultado = new Lista();

        if ("*".equals(operador)) {
            ecuacionGeneral = multiplicacion(ecuacion1, ecuacion2);
            ecuacionResultado = reducir(ecuacionGeneral);
            return ecuacionResultado;
        }

        if ("/".equals(operador)) {
            ecuacionResultado = division(ecuacion1,ecuacion2);
            return ecuacionResultado;
        }

        if("+".equals(operador) || "-".equals(operador)) {
            ecuacionGeneral = unirListas(operador, ecuacion1, ecuacion2);
            ecuacionResultado = reducir(ecuacionGeneral);
            return ecuacionResultado;
        }

        return ecuacionResultado;
    }

    /**
     * Método que se encarga de reducir la ecuación, por medio de sumas y restas.
     * 
     */
    public Lista reducir(Lista ecuacionGeneral) {
        Lista lista = new Lista();
        Nodo auxiliar1 = ecuacionGeneral.inicioCola;
        Nodo auxiliar2 = auxiliar1.getSiguiente();

        boolean reducidoAlMaximo = true;
        boolean coincidencia = false;
        double valor;
        char incognita;
        int potencia;

        //Ciclo que se repite mientras la ecuación ingresada no esté vacía
        while (!ecuacionGeneral.esVacia()) {
            //Ciclo que recorre una posición con respecto a múltiples posiciones 
            for (int indiceExterno = 0; indiceExterno < ecuacionGeneral.getTamaño(); indiceExterno++) {
                coincidencia = false;
                //Ciclo que recorre múltiples posiciones con respecto a   una posición 
                for (int indiceInterno = 0; indiceInterno < ecuacionGeneral.getTamaño() - 1; indiceInterno++) {
                    try {
                        //Condicional si la posición sí coincidió con ninguna posición
                        if (auxiliar1.getPotencia() == auxiliar2.getPotencia()) {
                            coincidencia = true;
                            reducidoAlMaximo = false;

                            valor = (auxiliar1.getValor()) + (auxiliar2.getValor());
                            incognita = auxiliar1.getIncognita();
                            potencia = auxiliar1.getPotencia();

                            lista.agregarElemento(valor, incognita, potencia);

                            eliminarElemento(ecuacionGeneral, auxiliar1);
                            eliminarElemento(ecuacionGeneral, auxiliar2);

                            auxiliar1 = ecuacionGeneral.inicioCola;
                            auxiliar2 = auxiliar1.getSiguiente();
                        }
                    }
                    catch (NullPointerException errorNull) {
                        break;
                    }
                    if (coincidencia == false) {
                        auxiliar2 = auxiliar2.getSiguiente();
                    }
                }
                //Condicional si la posición no coincidió con ninguna posición
                try {
                    if (coincidencia == false) {
                        lista.agregarElemento(auxiliar1.getValor(),auxiliar1.getIncognita(),auxiliar1.getPotencia());
                        eliminarElemento(ecuacionGeneral, auxiliar1);

                        auxiliar1 = ecuacionGeneral.inicioCola;
                        auxiliar2 = auxiliar1.getSiguiente();
                    }
                }
                catch (NullPointerException errorNull) {
                    break;
                }
            }
        }
        //Condicional recursivo que se activa en caso de que la ecuación no esté reducida al máximo
        if (reducidoAlMaximo == false) {
            lista = reducir(lista);
        }
        return lista;
    } 

    /**
     * Método que se encarga de realizar la multiplicacion de las ecuaciones.
     * 
     */
    public Lista multiplicacion(Lista ecuacion1,Lista ecuacion2) {
        Lista lista = new Lista();

        Nodo auxiliar1 = ecuacion1.inicioCola;
        Nodo auxiliar2 = ecuacion2.inicioCola;

        for(int indiceExterno = 0; indiceExterno < ecuacion1.getTamaño(); indiceExterno++) {
            for(int indiceInterno = 0; indiceInterno < ecuacion2.getTamaño(); indiceInterno++) {
                if(indiceInterno == 0) {
                    auxiliar2 = ecuacion2.inicioCola;
                }
                else {
                    auxiliar2 = auxiliar2.getSiguiente(); 
                }
                //Obtiene cada potencia y las suma para conseguir la nueva potencia
                potencia1 = auxiliar1.getPotencia();
                potencia2 = auxiliar2.getPotencia();
                potenciaNueva = potencia1 + potencia2;

                //Obtiene cada valor y las multiplica para conseguir el nuevo valor
                valor1 = auxiliar1.getValor();
                valor2 = auxiliar2.getValor();
                valorNuevo = valor1 * valor2;

                //Agrega cada resultado de multiplicacion a una nueva lista
                lista.agregarElemento(valorNuevo,'x',potenciaNueva);                
            }
            auxiliar1 = auxiliar1.getSiguiente();
        }
        return lista;
    }

    /**
     * Método que se encarga de realizar la division de las ecuaciones.
     * 
     */
    public Lista division(Lista ecuacion1, Lista ecuacion2){
        Lista lista = new Lista();

        potencia1 = ecuacion1.inicioCola.getPotencia();
        potencia2 = ecuacion2.inicioCola.getPotencia();

        double valorUno = ecuacion1.inicioCola.getValor();
        double valorDos = ecuacion2.inicioCola.getValor();
        valorNuevo = valorUno/valorDos;

        if(potencia1 >= potencia2) {
            potenciaNueva = potencia1 - potencia2;
        }
        else {
            potenciaNueva = potencia2 - potencia1;
        }

        lista.agregarElemento(valorNuevo,'x', potenciaNueva);

        return lista;
    }

    /**
     * Metodo para agregar un valor a la cola.
     * 
     */
    public void agregarElemento(double valor,char incognita,int potencia) {
        Nodo nuevoElemento = new Nodo(valor,incognita,potencia);
        nuevoElemento.setValor(valor);
        nuevoElemento.setIncognita(incognita);
        nuevoElemento.setPotencia(potencia);

        if(esVacia()) {
            inicioCola = nuevoElemento;
            finalCola = nuevoElemento;
        }
        else {
            Nodo auxiliar = inicioCola;
            while(auxiliar.getSiguiente() != null) {
                auxiliar = auxiliar.getSiguiente();
            }
            auxiliar.setSiguiente(nuevoElemento);
        }
        tamanio++;    
    }

    /**
     * Método que imprime la cola.
     * 
     */
    public void imprimirLista() {
        if(!esVacia()) {
            Nodo auxiliar = inicioCola;
            int posicion = 0;
            while(auxiliar.getSiguiente() != null) {
                System.out.println("Posición: " + posicion + "\n"
                    + auxiliar.getValor() 
                    + auxiliar.getIncognita() 
                    + "^" + auxiliar.getPotencia());

                auxiliar = auxiliar.getSiguiente();
                posicion++;
            }
            System.out.println("Posición: " + posicion + "\n"
                + auxiliar.getValor() 
                + auxiliar.getIncognita()  
                + "^" + auxiliar.getPotencia());
            System.out.println("\n");
        }
    }

    /**
     * Método que convierte un objeto tipo lista en String (Método toString)
     * 
     */
    public String convertirString(Lista ecuacionResultado, boolean primerValor) {
        //Varaible tipo nodo
        Nodo auxiliar = ecuacionResultado.inicioCola;

        //Caso base para la recursividad
        if (auxiliar == null)  {
            return ecuacionResultadoString;
        }
        else {
            //Condicional que evalúa si el valor es diferente de cero,
            //en caso de serlo lo agrega, y en caso de no serlo se lo salta
            if (auxiliar.getValor() != 0) {
                //Condicional que agrega un más al valor si es positivo
                if (auxiliar.getValor() > 0) {
                    if(primerValor == false){
                        ecuacionResultadoString += " + ";
                    }                
                }
                //Condicional que decide en qué formato se debe agregar el valor (con o sin decimales)
                if (auxiliar.getValor() % 1 == 0) {
                    ecuacionResultadoString +=  " ";
                    ecuacionResultadoString += String.format("%.0f", auxiliar.getValor());                    

                }
                else {
                    ecuacionResultadoString +=  " ";
                    ecuacionResultadoString += String.format("%.1f", auxiliar.getValor());
                }

                ecuacionResultadoString += auxiliar.getIncognita();
                ecuacionResultadoString += "^";
                ecuacionResultadoString += auxiliar.getPotencia();
            }
            primerValor = false;
            eliminarElemento(ecuacionResultado, auxiliar);            
            convertirString(ecuacionResultado,primerValor);
        }
        return ecuacionResultadoString;
    }

    /**
     * Método que une dos objetos de tipo lista, junto con un operador.
     * 
     */
    public Lista unirListas(String operador, Lista ecuacion1, Lista ecuacion2) {
        Lista lista = new Lista();
        Lista ecuacionGeneral = new Lista();
        String ecuacionGeneralString = "";

        //Condicional que modifica las listas por separado según la entrada del usuario (aplica signos)
        if (operador.equals("-")) {
            Nodo auxiliar = ecuacion2.inicioCola;
            int posicion = 0;
            while(auxiliar.getSiguiente() != null) {
                auxiliar.setValor(-auxiliar.getValor());

                auxiliar = auxiliar.getSiguiente();
                posicion++;
            }
            auxiliar.setValor(-auxiliar.getValor());
        }
        if (operador.equals("+")) {
            Nodo auxiliar = ecuacion2.inicioCola;
            auxiliar.setValor(+auxiliar.getValor());
        }

        //Variables necesarias para la creación de la nueva lista
        double valor;
        char incognita;
        int potencia;

        //Ciclo que se encargan de crear la nueva lista con base a la ecuación 1
        Nodo auxiliar = ecuacion1.inicioCola;

        for (int indice = 0; indice < ecuacion1.getTamaño(); indice++) {
            valor = auxiliar.getValor(); 
            incognita = auxiliar.getIncognita(); 
            potencia = auxiliar.getPotencia();
            lista.agregarElemento(valor, incognita, potencia);

            auxiliar = auxiliar.getSiguiente();
        }

        //Ciclo que se encargan de crear la nueva lista con base a la ecuación 2
        auxiliar = ecuacion2.inicioCola;

        for (int indice = 0; indice < ecuacion2.getTamaño(); indice++) {
            valor = auxiliar.getValor(); 
            incognita = auxiliar.getIncognita(); 
            potencia = auxiliar.getPotencia();
            lista.agregarElemento(valor, incognita, potencia);

            auxiliar = auxiliar.getSiguiente();
        }
        return lista;
    }

    /**
     * Método que elimina un nodo de una lista.
     * 
     */
    public void eliminarElemento(Lista ecuacionGeneral, Nodo nodoPorEliminar) {
        Nodo auxiliar = ecuacionGeneral.inicioCola;

        //Condicional que se activa si el nodo es el inicio de la cola 
        if(auxiliar == nodoPorEliminar) {
            ecuacionGeneral.inicioCola = ecuacionGeneral.inicioCola.getSiguiente();
            auxiliar.setSiguiente(null);
        }
        else {
            while(auxiliar.getSiguiente() != nodoPorEliminar) {
                auxiliar = auxiliar.getSiguiente();
            }
            Nodo nodoSiguiente = auxiliar.getSiguiente().getSiguiente();
            auxiliar.getSiguiente().setSiguiente(null);
            auxiliar.setSiguiente(nodoSiguiente);
        }
        tamanio--;
    }
}

import java.util.Scanner;

public class UndirLaFlotaExtra {
    private static ProcessBuilder CLS = new ProcessBuilder("cmd", "/c", "cls"); // Esta sentencia es para crear un proceso de cmd.
    private static Process start;

    private static Scanner sc = new Scanner(System.in); 


    public static final int iMultiplicador = 30;

    public static final int iBarcosNivel4 = 1;
    public static final int iBarcosNivel3 = 2;
    public static final int iBarcosNivel2 = 3;
    public static final int iBarcosNivel1 = 4;

    public static final int iTurnos = 19;
    public static final int iBarcosTocados = 20;

    public static final char cMar = 'm';
    public static final char cBarco = 'B';

    public static void main(String[] args) {
        int iPosicionX = 0, iPosicionY = 0, iTurnosPlayer = 0, iTocadosContador = 0;
        String sCordenadas = "", sMenu = "";

        // Es un Array de dos dimensiones de tipo char en la que emos denominado
        // cTablero, loemos mapedo de manera manual.
        // En el Array emos determidado los caracteres m y B, el caracter m representa
        // el mar y el caracter B representan los barcos.

        /*
        char[][] cTablero = { 
            { 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'B', },
            { 'm', 'B', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'B', },
            { 'm', 'B', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'B', },
            { 'm', 'B', 'm', 'B', 'B', 'B', 'B', 'B', 'm', 'm', },
            { 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', },
            { 'm', 'm', 'm', 'B', 'B', 'm', 'm', 'B', 'm', 'm', },
            { 'm', 'B', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', },
            { 'm', 'm', 'm', 'm', 'm', 'B', 'm', 'm', 'B', 'm', },
            { 'B', 'B', 'm', 'B', 'm', 'm', 'm', 'm', 'B', 'm', },
            { 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', } 
        };
        */

        char[][] cTablero = new char[10][10];
        int iRandom, iRandom2,  iBarcosNivel1Contador = 0, iBarcosNivel2Contador = 0, iBarcosNivel3Contador = 0 ,iBarcosNivel4Contador = 0;
        boolean bTestArriba = false, bTestAbajo = false, bTestIzquierda = false, bTestDerecha = false;




        try { // el try es un metodo para preveer las Excepciones
           start = CLS.inheritIO().start(); // Esta sentencia es para al macenar el proceso con la funcion de
                                             // inicializacion en start que es una variable privada.
            start.waitFor(); // Esta sentencia lo que hace es inicializar el proceso en todas las lineas
                          // necesarias.
        } catch (Exception e) {
        }

        System.out.println("//////////////////////////////");
        System.out.println("//       1. Jugar           //");
        System.out.println("//       2. Salir           //");
        System.out.println("//////////////////////////////");

        do {
            System.out.print("Elige una opcion: ");
            sMenu = sc.nextLine();
            if (sMenu.equals("2")) {
                System.exit(0);
            } else {
                System.out.println("Pon 1 o 2");
            }
        } while (!sMenu.equals("1"));


        for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
            for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                cTablero[iPosicionX][iPosicionY] = cMar;
            }
        }

        // barco de 4
        do {
            for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
                for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                    if (cTablero[iPosicionX][iPosicionY] == cMar) { 
                        try {
                            iRandom = (int) (Math.random()*iMultiplicador)+1;
                            iRandom2 = (int) (Math.random()*2)+1;
                            if (cTablero[iPosicionX][iPosicionY + 3] == cMar & iRandom == iMultiplicador/2 & iRandom2 == 1) {
                                if (iBarcosNivel4Contador != iBarcosNivel4) {
                                    cTablero[iPosicionX][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX][iPosicionY+1] = cBarco ;
                                    cTablero[iPosicionX][iPosicionY+2] = cBarco ;
                                    cTablero[iPosicionX][iPosicionY+3] = cBarco ;
                                    iBarcosNivel4Contador++;
                                    break;
                                }  
                            } 
                            
                            if (cTablero[iPosicionX + 2][iPosicionY] == cMar &  cTablero[iPosicionX - 1][iPosicionY] == cMar & iRandom == iMultiplicador/2 ) {
                                if (iBarcosNivel4Contador != iBarcosNivel4) {
                                    cTablero[iPosicionX + 2][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX + 1][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX  - 1][iPosicionY] = cBarco ;
                                    iBarcosNivel4Contador++;
                                    break;
                                }
                            }
                            
                        }catch (Exception e) {
                        }
                        
                    }
                }
            }
        } while (iBarcosNivel4Contador != iBarcosNivel4);

        // barco de 3
        do {
            for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
                for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                    if (cTablero[iPosicionX][iPosicionY] == cMar) { 
                        try {
                            bTestArriba = false;
                            bTestAbajo = false;
                            bTestIzquierda = false;
                            bTestDerecha = false;
                            iRandom = (int) (Math.random()*iMultiplicador)+1;
                            iRandom2 = (int) (Math.random()*2)+1; 
                           
                            // barco de 3 orizontal
                            if (
                                cTablero[iPosicionX][iPosicionY - 1] == cMar & 
                                cTablero[iPosicionX][iPosicionY + 1] == cMar & 
                                cTablero[iPosicionX][iPosicionY + 2] == cMar & 
                                cTablero[iPosicionX][iPosicionY + 3] == cMar & 
                                iRandom == iMultiplicador/2 & iRandom2 == 1
                            ) {
                                try {
                                    if (cTablero[iPosicionX + 1][iPosicionY - 1] == cMar & 
                                        cTablero[iPosicionX + 1][iPosicionY] == cMar &
                                        cTablero[iPosicionX + 1][iPosicionY + 1] == cMar & 
                                        cTablero[iPosicionX + 1][iPosicionY + 2] == cMar & 
                                        cTablero[iPosicionX + 1][iPosicionY + 3] == cMar
                                    ) {
                                        bTestArriba = true;   
                                    }
                                } catch (Exception e) {
                                    bTestArriba = true;
                                }
                                
                                try {
                                    if (cTablero[iPosicionX - 1][iPosicionY - 1] == cMar & 
                                    cTablero[iPosicionX - 1][iPosicionY] == cMar &
                                    cTablero[iPosicionX - 1][iPosicionY + 1] == cMar & 
                                    cTablero[iPosicionX - 1][iPosicionY + 2] == cMar & 
                                    cTablero[iPosicionX - 1][iPosicionY + 3] == cMar
                                    ) {
                                        bTestAbajo = true;
                                    }
                                    
                                } catch (Exception e) {
                                    bTestAbajo = true;
                                }

                                if (bTestAbajo == true & bTestArriba == true & iBarcosNivel3Contador != iBarcosNivel3) {
                                    cTablero[iPosicionX][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX][iPosicionY+1] = cBarco ;
                                    cTablero[iPosicionX][iPosicionY+2] = cBarco ;
                                    iBarcosNivel3Contador++;
                                    break;
                                }
                            }
                            
                            // barco de 3 vertical
                            if (
                                cTablero[iPosicionX + 2][iPosicionY] == cMar &
                                cTablero[iPosicionX + 1][iPosicionY] == cMar &
                                cTablero[iPosicionX - 1][iPosicionY] == cMar & 
                                cTablero[iPosicionX - 2][iPosicionY] == cMar & 
                                iRandom == iMultiplicador/2 

                            ) {
                                try {
                                    if (
                                        cTablero[iPosicionX - 2][iPosicionY - 1] == cMar & 
                                        cTablero[iPosicionX - 1][iPosicionY - 1] == cMar &
                                        cTablero[iPosicionX][iPosicionY - 1] == cMar &
                                        cTablero[iPosicionX + 1][iPosicionY - 1] == cMar &
                                        cTablero[iPosicionX + 2][iPosicionY - 1] == cMar
                                    ) {
                                        bTestIzquierda = true;
                                    }  
                                } catch (Exception e) {
                                    bTestIzquierda = true;
                                }

                                try {
                                    if (
                                        cTablero[iPosicionX - 2][iPosicionY  + 1] == cMar & 
                                        cTablero[iPosicionX - 1][iPosicionY + 1] == cMar &
                                        cTablero[iPosicionX][iPosicionY + 1] == cMar &
                                        cTablero[iPosicionX + 1][iPosicionY + 1] == cMar &
                                        cTablero[iPosicionX + 2][iPosicionY + 1] == cMar
                                    ) {
                                        bTestDerecha = true;
                                    }    
                                } catch (Exception e) {
                                    bTestDerecha = true;
                                }
                                
                                if (bTestIzquierda == true & bTestDerecha == true & iBarcosNivel3Contador != iBarcosNivel3) {
                                    cTablero[iPosicionX + 1][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX  - 1][iPosicionY] = cBarco ;
                                    iBarcosNivel3Contador++;
                                    break;
                                }
                                
                            }

                        }catch (Exception e) {
                        }
                        
                    }
                }
            }
        } while (iBarcosNivel3Contador != iBarcosNivel3);

        // barco de 2
        do {
            for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
                for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                    if (cTablero[iPosicionX][iPosicionY] == cMar) { 
                        try {
                            bTestArriba = false;
                            bTestAbajo = false;
                            bTestIzquierda = false;
                            bTestDerecha = false;
                            iRandom = (int) (Math.random()*iMultiplicador)+1;
                            iRandom2 = (int) (Math.random()*2)+1; 
                           
                            // barco de 2 orizontal
                            if (
                                cTablero[iPosicionX][iPosicionY - 1] == cMar & 
                                cTablero[iPosicionX][iPosicionY + 1] == cMar & 
                                cTablero[iPosicionX][iPosicionY + 2] == cMar &
                                iRandom == iMultiplicador/2 & iRandom2 == 1
                            ) {
                                try {
                                    if (cTablero[iPosicionX + 1][iPosicionY - 1] == cMar & 
                                        cTablero[iPosicionX + 1][iPosicionY] == cMar &
                                        cTablero[iPosicionX + 1][iPosicionY + 1] == cMar & 
                                        cTablero[iPosicionX + 1][iPosicionY + 2] == cMar
                                    ) {
                                        bTestArriba = true;   
                                    }
                                } catch (Exception e) {
                                    bTestArriba = true;
                                }
                                
                                try {
                                    if (cTablero[iPosicionX - 1][iPosicionY - 1] == cMar & 
                                    cTablero[iPosicionX - 1][iPosicionY] == cMar &
                                    cTablero[iPosicionX - 1][iPosicionY + 1] == cMar & 
                                    cTablero[iPosicionX - 1][iPosicionY + 2] == cMar
                                    ) {
                                        bTestAbajo = true;
                                    }
                                    
                                } catch (Exception e) {
                                    bTestAbajo = true;
                                }

                                if (bTestAbajo == true & bTestArriba == true & iBarcosNivel2Contador != iBarcosNivel2) {
                                    cTablero[iPosicionX][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX][iPosicionY+1] = cBarco ;
                                    iBarcosNivel2Contador++;
                                    break;
                                }
                            }
                            
                            // barco de 2 vertical
                            if (
                                cTablero[iPosicionX + 2][iPosicionY] == cMar &
                                cTablero[iPosicionX + 1][iPosicionY] == cMar &
                                cTablero[iPosicionX - 1][iPosicionY] == cMar &
                                iRandom == iMultiplicador/2 

                            ) {
                                try { // vertical izquierda
                                    if (
                                        cTablero[iPosicionX + 2][iPosicionY - 1] == cMar & 
                                        cTablero[iPosicionX + 1][iPosicionY - 1] == cMar &
                                        cTablero[iPosicionX][iPosicionY - 1] == cMar &
                                        cTablero[iPosicionX - 1][iPosicionY - 1] == cMar
                                    ) {
                                        bTestIzquierda = true;
                                    }  
                                } catch (Exception e) {
                                    bTestIzquierda = true;
                                }

                                try { // vertical derecha
                                    if (
                                        cTablero[iPosicionX + 2][iPosicionY  + 1] == cMar & 
                                        cTablero[iPosicionX + 1][iPosicionY + 1] == cMar &
                                        cTablero[iPosicionX][iPosicionY + 1] == cMar &
                                        cTablero[iPosicionX - 1][iPosicionY + 1] == cMar
                                    ) {
                                        bTestDerecha = true;
                                    }    
                                } catch (Exception e) {
                                    bTestDerecha = true;
                                }
                                
                                if (bTestIzquierda == true & bTestDerecha == true & iBarcosNivel2Contador != iBarcosNivel2) {
                                    cTablero[iPosicionX + 1][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX][iPosicionY] = cBarco ;
                                    iBarcosNivel2Contador++;
                                    break;
                                }
                                
                            }

                        }catch (Exception e) {
                        }
                        
                    }
                }
            }
        } while (iBarcosNivel2Contador != iBarcosNivel2);

        
        // barco de 1
        do {
            for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
                for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                    if (cTablero[iPosicionX][iPosicionY] == cMar) { 
                        try {
                            bTestArriba = false;
                            bTestAbajo = false;
                            iRandom = (int) (Math.random()*iMultiplicador)+1;
                            if (
                                cTablero[iPosicionX][iPosicionY - 1] == cMar & 
                                cTablero[iPosicionX][iPosicionY + 1] == cMar & 
                                iRandom == iMultiplicador/2
                            ) {
                                try {
                                    if (cTablero[iPosicionX + 1][iPosicionY - 1] == cMar & 
                                        cTablero[iPosicionX + 1][iPosicionY] == cMar &
                                        cTablero[iPosicionX + 1][iPosicionY + 1] == cMar
                                    ) {
                                        bTestArriba = true;   
                                    }
                                } catch (Exception e) {
                                    bTestArriba = true;
                                }
                                
                                try {
                                    if (cTablero[iPosicionX - 1][iPosicionY - 1] == cMar & 
                                    cTablero[iPosicionX - 1][iPosicionY] == cMar &
                                    cTablero[iPosicionX - 1][iPosicionY + 1] == cMar
                                    ) {
                                        bTestAbajo = true;
                                    }
                                    
                                } catch (Exception e) {
                                    bTestAbajo = true;
                                }

                                if (bTestAbajo == true & bTestArriba == true & iBarcosNivel1Contador != iBarcosNivel1) {
                                    cTablero[iPosicionX][iPosicionY] = cBarco ;
                                    iBarcosNivel1Contador++;
                                    break;
                                }
                            }

                        }catch (Exception e) {
                        }
                        
                    }
                }
            }
        } while (iBarcosNivel1Contador != iBarcosNivel1);



        // Este do while representa el loop principal del juego, terminara cuando agotes
        // todos los turnos o undas todos los barcos.
        do {
            do {
                try { // el try es un metodo para preveer las Excepciones
                    start = CLS.inheritIO().start(); // Esta sentencia es para al macenar el proceso con la funcion de
                                                     // inicializacion en start que es una variable privada.
                    start.waitFor(); // Esta sentencia lo que hace es inicializar el proceso en todas las lineas
                                     // necesarias.
                } catch (Exception e) {

                }

                // Con print imprimimos en pantallas una secuencia un secuanecia de l un al 10
                // que la usamos como una manera de visualizar las cordenadas.
                // Despues del prin se ejecuta un for que lo que hace es recorrer las filas del
                // "Array char" las emos representado como iPosicionX,
                // las colupnas las emos representado como iPosicionY.

                System.out.println("    1  2  3  4  5  6  7  8  9  10");
                for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) { // Al comenzar el for lo que hace es
                                                                                   // poner iPosicionX en 0 para que
                                                                                   // en pieze desde la primera fila
                                                                                   // asta que iPosicionX sea menor a la
                                                                                   // longitud del cTablero.

                    // Este prin imprime en pantalla los caracteres de la A a la J.
                    // El Character.forDigit() es un metodo que convierte cualquier entero de base
                    // 10 a la base que le indiques, como el tabledo contiene 10 filas lo que emos
                    // echo es pner el numero de filas mas 10 que es iPosicionX + 10.
                    System.out.print(" " + Character.forDigit(iPosicionX + 10, 20) + " ");

                    // Dentro del for se ejecuta otro for que recorre las colupnas del Array.
                    // con este for se imprime enpantalla las colupmas y comprueba si tiene que
                    // sustituir el caracter B pro el m para que no se vea en el tablero.
                    for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {

                        if (cTablero[iPosicionX][iPosicionY] == 'T') {
                            System.out.print(" " + cTablero[iPosicionX][iPosicionY] + " ");
                        } else if (cTablero[iPosicionX][iPosicionY] == cBarco ) {
                            System.out.print("[" + "m" + "]");
                        } else {
                            System.out.print("[" + cTablero[iPosicionX][iPosicionY] + "]");
                        }
                    } // El for terminara cuandao haya recorido toda la fila

                    System.out.println(""); // con este prin separo las colupnas para que salga el tablero de forma
                                            // uniforme.
                } // El for terminara cuandao haya recorido toda las colupnas

                System.out.println("----------------------------------");
                System.out.println("Turnos: " + (iTurnosPlayer + 1) + " de " + (iTurnos + 1) + " : tocados: " + iTocadosContador + " de " + iBarcosTocados);
                // Este do while sirve para cuando el usuario tenga que poner las cordenads se
                // repita en caso de que sean erroneas.
                do {

                    System.out.print("Pon las cordenadas: ");
                    sCordenadas = sc.nextLine(); // en iCordenadas se almacenadan las cordenadas de la siguiente manera:
                                                 // A1, G5, etc...

                    // En iPosicionX lo que hacemos es coger el primer caracter del String con el
                    // metodo charAt() luego lo convertimos a entero con la classe
                    // Character usando el metodo getNumericValue() luego le restamos 10 ya que al
                    // pasar por ejemplo el carachter A se convierte en 10 y al restarle 10 pasa a
                    // 0. otros ejemplos seria B = 1, C = 3, etc...

                    try { // con el try catch estamos comprobando si puede dar una excepcion en caso de
                          // que los valores no sean correctosm, si aparece una excepcion ejecutara el
                          // catch en vez del try.
                        iPosicionX = Character.getNumericValue(sCordenadas.charAt(0)) - 10;
                    } catch (Exception e) {
                        System.out.println("!!! Cordenadas incorrectas: Valor erroneo !!!");
                    }

                    // En iPosicionY lo que hacemos es retidar el primer caracter del String con el
                    // metodo substring() luego lo convertimos a entero con la classe
                    // Integer usando el metodo parseInt().
                    try {
                        iPosicionY = Integer.parseInt(sCordenadas.substring(1)) - 1;
                    } catch (Exception e) {
                        System.out.println("!!! Cordenadas incorrectas: Conversion erronea !!!");

                    }

                    // Con este if se comprueba que la posicon X y Y no sea mayor 10 ni inferior a 0.
                    // Si se cumple saldra del do while.
                    if (iPosicionX >= 0 & iPosicionX < cTablero.length & iPosicionY >= 0 & iPosicionY < cTablero.length) {
                        break;
                    } else {
                        System.out.println("!!! Cordenadas incorrectas: Fuera de rango !!!");
                    }
                } while (true);

                // Con este if comprobamos si se atocado el barco es mar si detecta que la
                // posicon del array es B pondra una T de rocado y si detecta que es m pondra
                // una V de vacios.
                if (cTablero[iPosicionX][iPosicionY] == cBarco ) {
                    cTablero[iPosicionX][iPosicionY] = 'T';
                    iTocadosContador++;

                } else if (cTablero[iPosicionX][iPosicionY] == cMar) {
                    cTablero[iPosicionX][iPosicionY] = 'V';
                    break;

                }

            } while (iTocadosContador != iBarcosTocados);

            // con este if conprobamos que los turnos del jugador sean iguales alos turnos
            // del juego si se cumple la condicion imprime ne pantalla el tabledo mostrando
            // la uvicacion de los barcos y mandando te un mensaje de que as perdido y
            // finaliza el juego.
            if (iTurnosPlayer == iTurnos) {
                try { // el try es un metodo para preveer las Excepciones
                    start = CLS.inheritIO().start(); // Esta sentencia es para al macenar el proceso con la funcion de
                                                     // inicializacion en start que es una variable privada.
                    start.waitFor(); // Esta sentencia lo que hace es inicializar el proceso en todas las lineas
                                     // necesarias.
                } catch (Exception e) {
                }
                System.out.println("    1  2  3  4  5  6  7  8  9  10");

                for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
                    System.out.print(" " + Character.forDigit(iPosicionX + 10, 20) + " ");
                    for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                        System.out.print("[" + cTablero[iPosicionX][iPosicionY] + "]");

                    }
                    System.out.println("");
                }

                System.out.println("----------------------------------");
                System.out.println("!!! Game Over !!!");
                System.out.println("!!! Perdiste boludo !!!");
                System.exit(0);

                // con el if else se comprueba si los barcos tocados es igual al numero de
                // tocados del juego se muester en pantalla els tablero y te salte un mensaje de
                // que as ganado y finaliza el juego.
            } else if (iTocadosContador == iBarcosTocados) {
                try { // el try es un metodo para preveer las Excepciones
                    start = CLS.inheritIO().start(); // Esta sentencia es para al macenar el proceso con la funcion de
                                                     // inicializacion en start que es una variable privada.
                    start.waitFor(); // Esta sentencia lo que hace es inicializar el proceso en todas las lineas
                                     // necesarias.
                } catch (Exception e) {
                }
                System.out.println("    1  2  3  4  5  6  7  8  9  10");

                for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
                    System.out.print(" " + Character.forDigit(iPosicionX + 10, 20) + " ");

                    for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                        System.out.print("[" + cTablero[iPosicionX][iPosicionY] + "]");

                    }
                    System.out.println("");
                }

                System.out.println("----------------------------------");
                System.out.println("!!! Ganaste !!!");
                System.out.println("!!! boludo !!!");
                System.exit(0);
            }
            iTurnosPlayer++; // con esta linea sumas los turnos del jugador cadevez que llehas al final del
                             // bucle.
        } while (true);
    }
}

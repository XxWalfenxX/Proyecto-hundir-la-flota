import java.security.Principal;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class UndirLaFlotaExtra2_6 {
    // variables no tocar
    // --------------------------------------------------------------------------------------------------
    private static ProcessBuilder CLS = new ProcessBuilder("cmd", "/c", "cls"); // Esta variable es para crear un proceso de cmd.
    private static Process start;

    private static Scanner sc = new Scanner(System.in); 

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String RESET = "\u001B[0m";

    public static final int iBarcosNivel4 = 1;
    public static final int iBarcosNivel3 = 2;
    public static final int iBarcosNivel2 = 3;
    public static final int iBarcosNivel1 = 4;

    public static final int iBarcosNivel4longitud = 4;
    public static final int iBarcosNivel3longitud = 3;
    public static final int iBarcosNivel2longitud = 2;
    public static final int iBarcosNivel1longitud = 1;
    // --------------------------------------------------------------------------------------------------

    // variavles que se pueden tocar y creemos que funcionara
    // --------------------------------------------------------------------------------------
    public static final int iTurnos = 19; //Siempre sera uno menor al numero de turnos desearo
    public static final int iBarcosTocados = 20;

    public static final int iTableroTamano = 10;
    public static final int iMultiplicador = 30;
    
    // Elementos del tablero
    public static final char cMar = '*';
    public static final char cBarco = 'B';
    public static final char cTocados = 'T';
    public static final char cVacio = 'A';
    public static final char cHundido = 'H';
    // --------------------------------------------------------------------------------------

    // Semaforos
    public static boolean bSemaforo1 = false;

    public static void main(String[] args) {
        
        // variables no tocar
        // -------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

        // variables no tocar
        // -------------------------------------------------------------------------------------------------------------------------------------------------------------------
        char[][] cTablero = new char[iTableroTamano][iTableroTamano];
        int iRandom, iRandom2,  iBarcosNivel1Contador = 0, iBarcosNivel2Contador = 0, iBarcosNivel3Contador = 0 ,iBarcosNivel4Contador = 0;
        boolean bTestArriba = false, bTestAbajo = false, bTestIzquierda = false, bTestDerecha = false;

        String[] aBarcosNivel4Posiciones = new String[iBarcosNivel4longitud*iBarcosNivel4];
        String[] aBarcosNivel3Posiciones = new String[iBarcosNivel3longitud*iBarcosNivel3];
        // El 2 representa el tama??o del varco 
        String[] aBarcosNivel2Posiciones = new String[iBarcosNivel2longitud*iBarcosNivel2];
        String[] aBarcosNivel1Posiciones = new String[iBarcosNivel1longitud*iBarcosNivel1];
        int iBarcosTocadosNivel4 = 0, iBarcosTocadosNivel3 = 0,iBarcosTocados2Nivel3 = 0, iBarcosTocadosNivel2 = 0, iBarcosTocadosNivel1 = 0;
        boolean iBarcosNivel4Undido = false , iBarcosNivel3Undido = false, iBarcosNivel3Undido2 = false, iBarcosNivel2Undido = false, iBarcosNivel1Undido = false;
        // -------------------------------------------------------------------------------------------------------------------------------------------------------------------



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


                                    //Almacenar Posicion del barco de nivel 4 vertical
                                    //------------------------------------------------------------------------------------------
                                    aBarcosNivel4Posiciones[0] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                    aBarcosNivel4Posiciones[1] = Integer.toString(iPosicionY + 1) + Integer.toString(iPosicionX);
                                    aBarcosNivel4Posiciones[2] = Integer.toString(iPosicionY + 2) + Integer.toString(iPosicionX);
                                    aBarcosNivel4Posiciones[3] = Integer.toString(iPosicionY + 3) + Integer.toString(iPosicionX);
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

                                    //Almacenar Posicion del barco de nivel 4 orinzontal
                                    //-------------------------------------------------------------------------------------------
                                    aBarcosNivel4Posiciones[3] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX + 2);
                                    aBarcosNivel4Posiciones[2] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX + 1);
                                    aBarcosNivel4Posiciones[1] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                    aBarcosNivel4Posiciones[0] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX - 1);
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
        //------------------------------------------------------------------------------------------------------------------------
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
                            //-------------------------------------------------------------
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
                                    

                                    ///Almacenar Posicion del barco de nivel 3 orizontal
                                    //------------------------------------------------------------------------------------------
                                    
                                    if (iBarcosNivel3Contador == 0) {
                                        System.out.println("");
                                        aBarcosNivel3Posiciones[0] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                        aBarcosNivel3Posiciones[1] = Integer.toString(iPosicionY + 1) + Integer.toString(iPosicionX);
                                        aBarcosNivel3Posiciones[2] = Integer.toString(iPosicionY + 2) + Integer.toString(iPosicionX);
                                    } else {
                                        
                                        try {
                                            aBarcosNivel3Posiciones[0+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                            aBarcosNivel3Posiciones[1+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY + 1) + Integer.toString(iPosicionX);
                                            aBarcosNivel3Posiciones[2+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY + 2) + Integer.toString(iPosicionX);  
                                        } catch (Exception e) {
                                            System.out.println("Error de posiciones");
                                        }
                                        
                                        
                                    }

                                    iBarcosNivel3Contador++;
                                    break;
                                }
                            }
                            
                            // barco de 3 vertical
                            //----------------------------------------------------------------
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
                                
                                // creacion de barco
                                if (bTestIzquierda == true & bTestDerecha == true & iBarcosNivel3Contador != iBarcosNivel3) {
                                    cTablero[iPosicionX + 1][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX][iPosicionY] = cBarco ;
                                    cTablero[iPosicionX  - 1][iPosicionY] = cBarco ;
                                    

                                    //Almacenar Posicion del barco de nivel 3 vertical
                                    //------------------------------------------------------------------------------------------
                                    
                                    if (iBarcosNivel3Contador == 0) {
                                        aBarcosNivel3Posiciones[2] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX + 1);
                                        aBarcosNivel3Posiciones[1] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                        aBarcosNivel3Posiciones[0] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX - 1);
                                    } else {
                                        try {
                                            aBarcosNivel3Posiciones[0+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX + 1);
                                            aBarcosNivel3Posiciones[1+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                            aBarcosNivel3Posiciones[2+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX - 1);  
                                        } catch (Exception e) {
                                            //TODO: handle exception
                                            System.out.println("Error de posiciones2");
                                        }
                                        
                                    }

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

                                    //Almacenar Posicion del barco de nivel 2 orizontal
                                    //------------------------------------------------------------------------------------------
                                    
                                    if (iBarcosNivel2Contador == 0) {
                                        aBarcosNivel2Posiciones[0] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                        aBarcosNivel2Posiciones[1] = Integer.toString(iPosicionY + 1) + Integer.toString(iPosicionX);
                                    } else {
                                        try {
                                            aBarcosNivel2Posiciones[0+(iBarcosNivel2Contador)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                            aBarcosNivel2Posiciones[1+(iBarcosNivel2Contador)] = Integer.toString(iPosicionY + 1) + Integer.toString(iPosicionX); 
                                        } catch (Exception e) {
                                            //TODO: handle exception
                                            System.out.println("Error de posiciones");
                                        }
                                        
                                        
                                    }

                                    iBarcosNivel2Contador+=iBarcosNivel2longitud;
                                    break;
                                }
                            }
                            //System.out.println(cTablero);
                            // barco de 2 vertical
                            //-----------------------------------------------------------------------------------------------------------------------
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
                                    
                                    //Almacenar Posicion del barco de nivel 2 vertical
                                    //------------------------------------------------------------------------------------------
                                    
                                    if (iBarcosNivel2Contador == 0) {
                                        aBarcosNivel2Posiciones[1] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX + 1);
                                        aBarcosNivel2Posiciones[0] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                    } else {
                                        try {
                                            System.out.println("vertical3");
                                            aBarcosNivel2Posiciones[1+(iBarcosNivel2Contador)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX + 1);
                                            aBarcosNivel2Posiciones[0+(iBarcosNivel2Contador)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);  
                                        } catch (Exception e) {
                                            //TODO: handle exception
                                        }
                                        
                                    }
                                    
                                    iBarcosNivel2Contador +=iBarcosNivel2longitud;
                                    break;
                                }
                                
                            }

                        }catch (Exception e) {
                        }
                        
                    }
                }
            }
        } while (iBarcosNivel2Contador < iBarcosNivel2*iBarcosNivel2longitud);
        //-----------------------------------------------------------------------------------------------------------------------
        
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


        // variables de cronometro y inicio de cronometro
        int iS = 0, iM = 0, iH = 0;
        long inicio = System.currentTimeMillis();

        // Este do while representa el loop principal del juego, terminara cuando agotes
        // todos los turnos o undas todos los barcos.
        do {
            do {
                // Comprobacion de Tocado y Hundido de BarcoNivel4
                //----------------------------------------------------------------------------------------
                if (iBarcosNivel4Undido != true) {
                    for (int i = 0; i < aBarcosNivel4Posiciones.length; i++) {
                        iPosicionY = Character.getNumericValue(aBarcosNivel4Posiciones[i].charAt(0));
                        iPosicionX = Character.getNumericValue(aBarcosNivel4Posiciones[i].charAt(1));
                        if (cTablero[iPosicionX][iPosicionY] == cTocados) {
                            iBarcosTocadosNivel4++;
                        }
                    }
                    if (iBarcosTocadosNivel4 == 4) {
                        for (int i = 0; i < aBarcosNivel4Posiciones.length; i++) {
                            iPosicionY = Character.getNumericValue(aBarcosNivel4Posiciones[i].charAt(0));
                            iPosicionX = Character.getNumericValue(aBarcosNivel4Posiciones[i].charAt(1));
                           
                            cTablero[iPosicionX][iPosicionY] = cHundido;
                            iBarcosNivel4Undido = true;
                        }
                    } else {
                        iBarcosTocadosNivel4 = 0;
                    }
                }
                
                // Comprobacion de Tocado y Hundido de BarcoNivel3
                //---------------------------------------------------------------------------------------------------------
                if (iBarcosNivel3Undido != true) {
                    for (int i = 0; i < aBarcosNivel3Posiciones.length-3; i++) {
                        iPosicionY = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(0));
                        iPosicionX = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(1));
                        if (cTablero[iPosicionX][iPosicionY] == cTocados) {
                            iBarcosTocadosNivel3++;
                        } else {
                            iBarcosTocadosNivel3 = 0;
                        }
                    }
                    if (iBarcosTocadosNivel3 == aBarcosNivel3Posiciones.length-3) {
                        for (int i = 0; i < aBarcosNivel3Posiciones.length-3; i++) {
                            iPosicionY = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(0));
                            iPosicionX = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(1));
                           
                            cTablero[iPosicionX][iPosicionY] = cHundido;
                            iBarcosNivel3Undido = true;
                        }
                    }
                }
    
                if (iBarcosNivel3Undido2 != true) {
                    for (int i = aBarcosNivel3Posiciones.length-3; i < aBarcosNivel3Posiciones.length; i++) {
                        iPosicionY = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(0));
                        iPosicionX = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(1));
                        if (cTablero[iPosicionX][iPosicionY] == cTocados) {
                            iBarcosTocados2Nivel3++;
                        } else {
                            iBarcosTocados2Nivel3 = 0;
                        }
                    }
                    if (iBarcosTocados2Nivel3 == aBarcosNivel3Posiciones.length-3) {
                        for (int i = aBarcosNivel3Posiciones.length-3; i < aBarcosNivel3Posiciones.length; i++) {
                            iPosicionY = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(0));
                            iPosicionX = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(1));
                           
                            cTablero[iPosicionX][iPosicionY] = cHundido;
                            iBarcosNivel3Undido2 = true;
                        }
                    }
                }
                //---------------------------------------------------------------------------------------------------------


                // Comprobacion de Tocado y Hundido de BarcoNivel2
                //---------------------------------------------------------------------------------------------------------
                iBarcosNivel2Contador = 0;
                int conatador = 0;

                int iSumatorioFor = 0;

                for (int iContador = 0; iContador < iBarcosNivel2; iContador++) {
                    for (int i = conatador; i < iBarcosNivel2longitud; i++) {
                        
                        iPosicionY = Character.getNumericValue(aBarcosNivel2Posiciones[i].charAt(0));
                        iPosicionX = Character.getNumericValue(aBarcosNivel2Posiciones[i].charAt(1));
                        if (cTablero[iPosicionX][iPosicionY] == cTocados) {
                            iBarcosTocadosNivel2++;
                        }

                        //conatador+=2;

                        //bSemaforo1 = true;
                    }
                     
                    if (iBarcosTocadosNivel2 == iBarcosNivel2longitud) {
                        for (int i = conatador; i < iBarcosNivel2longitud; i++) {
                            iPosicionY = Character.getNumericValue(aBarcosNivel2Posiciones[i].charAt(0));
                            iPosicionX = Character.getNumericValue(aBarcosNivel2Posiciones[i].charAt(1));
                            if (cTablero[iPosicionX][iPosicionY] == cTocados) {
                                cTablero[iPosicionX][iPosicionY] = cHundido;
                            }  
                        }       
                    }
                    //conatador += iBarcosNivel2longitud;
                    conatador+=2;
                    iBarcosTocadosNivel2 = 0;

                    //System.out.println("Iter: " + iContador + " Valor contador " + conatador);

                }
            
                //---------------------------------------------------------------------------------------------------------
                
                
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

                        if (cTablero[iPosicionX][iPosicionY] == cHundido) {
                            System.out.print( GREEN+ " " + cTablero[iPosicionX][iPosicionY] + " " + RESET);
                        } else if (cTablero[iPosicionX][iPosicionY] == cTocados) {
                            System.out.print( RED + " " + cTablero[iPosicionX][iPosicionY] + " " + RESET);
                        } else if (cTablero[iPosicionX][iPosicionY] == cBarco ) {
                            // System.out.print( BLUE + " " + cMar + " " + RESET);
                            System.out.print("[" + cMar + "]" + RESET);
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

                    System.out.print("Pon las cordenadas o Escribe Salir para cerrar:");
                    sCordenadas = sc.nextLine(); // en iCordenadas se almacenadan las cordenadas de la siguiente manera:
                                                 // A1, G5, etc...
                    
                    // Comprobacion para dcerrrar el juego en midad del proceso
                    if (sCordenadas.equalsIgnoreCase("salir")) {
                        System.exit(0);
                    }

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
                    cTablero[iPosicionX][iPosicionY] = cTocados;
                    iTocadosContador++;

                } else if (cTablero[iPosicionX][iPosicionY] == cMar) {
                    cTablero[iPosicionX][iPosicionY] = cVacio ;
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
                        if (cTablero[iPosicionX][iPosicionY] == cHundido) {
                            System.out.print( GREEN+ " " + cTablero[iPosicionX][iPosicionY] + " " + RESET);
                        } else if (cTablero[iPosicionX][iPosicionY] == cTocados) {
                            System.out.print( RED + " " + cTablero[iPosicionX][iPosicionY] + " " + RESET);
                        } else if (cTablero[iPosicionX][iPosicionY] == cBarco ) {
                            System.out.print( BLUE + " " + cMar + " " + RESET);
                            // System.out.print("[" + cMar + "]" + RESET);
                        } else {
                            System.out.print("[" + cTablero[iPosicionX][iPosicionY] + "]");
                        }
                        

                    }
                    System.out.println("");
                }

                //final del cronometro
                long fin = System.currentTimeMillis();
                long tiempo = ((fin - inicio)/1000);

                // calcular timpo transcurido
                for (int iTiempo = 0; iTiempo < tiempo; iTiempo++) {
                    iS++;
                    if (iM == 60) {
                        iH++;
                        iM = 0;
                    }
                    if (iS == 60) {
                        iM++;
                        iS = 0;
                    }
                }

                System.out.println("----------------------------------");
                System.out.println(" Tiempo transcurrido : " + iH + ":" + iM + ":" + iS);
                System.out.println("");
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
                        if (cTablero[iPosicionX][iPosicionY] == cHundido) {
                            System.out.print( GREEN+ " " + cTablero[iPosicionX][iPosicionY] + " " + RESET);
                        } else if (cTablero[iPosicionX][iPosicionY] == cTocados) {
                            System.out.print( RED + " " + cTablero[iPosicionX][iPosicionY] + " " + RESET);
                        } else if (cTablero[iPosicionX][iPosicionY] == cBarco ) {
                            // System.out.print( BLUE + " " + cMar + " " + RESET);
                            System.out.print("[" + cMar + "]" + RESET);
                        } else {
                            System.out.print("[" + cTablero[iPosicionX][iPosicionY] + "]");
                        }

                    }
                    System.out.println("");
                }

                //final del cronometro
                long fin = System.currentTimeMillis();
                long tiempo = ((fin - inicio)/1000);

                // calcular timpo transcurido
                for (int iTiempo = 0; iTiempo < tiempo; iTiempo++) {
                    iS++;
                    if (iM == 60) {
                        iH++;
                        iM = 0;
                    }
                    if (iS == 60) {
                        iM++;
                        iS = 0;
                    }
                }

                System.out.println("----------------------------------");
                System.out.println(" Tiempo transcurrido : " + iH + ":" + iM + ":" + iS);
                System.out.println("");
                System.out.println("!!! Ganaste !!!");
                System.out.println("!!! boludo !!!");
                System.exit(0);
            }
            iTurnosPlayer++; // con esta linea sumas los turnos del jugador cadevez que llehas al final del
                             // bucle.
        } while (true);
    }
}



import java.util.Scanner;

/**
 *
 * @author MarcLecha
 */
public class cosas {

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
    public static final char cMar = '*';
    public static final char cBarco = 'B';
    public static final char cTocados = 'T';
    public static final char cVacio = 'A';

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int iPosicionX = 0, iPosicionY = 0, iTurnosPlayer = 0, iTocadosContador = 0;
        String sCordenadas = "", sMenu = "";

        char[][] cTablero = new char[10][10];
        int iRandom, iRandom2, iBarcosNivel1Contador = 0, iBarcosNivel2Contador = 0, iBarcosNivel3Contador = 0, iBarcosNivel4Contador = 0;
        boolean bTestArriba = false, bTestAbajo = false, bTestIzquierda = false, bTestDerecha = false;

        String[] aBarcosNivel3Posiciones = new String[3];
        int iBarcosTocadosNivel3 = 0;
        boolean iBarcosNivel3Undido = false;

        for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
            for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                cTablero[iPosicionX][iPosicionY] = cMar;
            }
        }

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
                                    iBarcosNivel3Contador++;

                                    //Almacenar Posicion del barco de nivel 3 orizontal
                                    //------------------------------------------------------------------------------------------
                                    if (iBarcosNivel3Contador == 0) {
                                        aBarcosNivel3Posiciones[0] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                        aBarcosNivel3Posiciones[1] = Integer.toString(iPosicionY + 1) + Integer.toString(iPosicionX);
                                        aBarcosNivel3Posiciones[2] = Integer.toString(iPosicionY + 2) + Integer.toString(iPosicionX);
                                    } else {
                                        aBarcosNivel3Posiciones[0+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                        aBarcosNivel3Posiciones[1+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY + 1) + Integer.toString(iPosicionX);
                                        aBarcosNivel3Posiciones[2+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY + 2) + Integer.toString(iPosicionX);
                                    }
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
                                    iBarcosNivel3Contador++;

                                    //Almacenar Posicion del barco de nivel 3 vertical
                                    //------------------------------------------------------------------------------------------
                                    if (iBarcosNivel3Contador == 0) {
                                        aBarcosNivel3Posiciones[2] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX + 1);
                                        aBarcosNivel3Posiciones[1] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                        aBarcosNivel3Posiciones[0] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX - 1);
                                    } else {
                                        aBarcosNivel3Posiciones[0+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX + 1);
                                        aBarcosNivel3Posiciones[1+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX);
                                        aBarcosNivel3Posiciones[2+(iBarcosNivel3Contador + 2)] = Integer.toString(iPosicionY) + Integer.toString(iPosicionX - 1);
                                    }

                                    break;
                                }
                                
                            }

                        }catch (Exception e) {
                        }
                        
                    }
                }
            }
        } while (iBarcosNivel3Contador != iBarcosNivel3);

        for (int i = 0; i < aBarcosNivel3Posiciones.length; i++) {
            System.out.println(aBarcosNivel3Posiciones[i]);
        }

        for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
            for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                if (cTablero[iPosicionX][iPosicionY] == cBarco) {
                    System.out.print(" " + cTablero[iPosicionX][iPosicionY] + " ");
                } else {
                    System.out.print(" " + "*" + " ");
                }

            }
            System.out.println("");
        }

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        do {

            if (iBarcosNivel3Undido != true) {
                for (int i = 0; i < aBarcosNivel3Posiciones.length; i++) {
                    iPosicionY = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(0));
                    iPosicionX = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(1));
                    if (cTablero[iPosicionX][iPosicionY] == 'T') {
                        iBarcosTocadosNivel3++;
                    }
                }
                if (iBarcosTocadosNivel3 == 4) {
                    for (int i = 0; i < aBarcosNivel3Posiciones.length; i++) {
                        iPosicionY = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(0));
                        iPosicionX = Character.getNumericValue(aBarcosNivel3Posiciones[i].charAt(1));
                       
                        cTablero[iPosicionX][iPosicionY] = 'H';
                        iBarcosNivel3Undido = true;
                    }
                }
            }

            for (iPosicionX = 0; iPosicionX < cTablero.length; iPosicionX++) {
                for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                    if (cTablero[iPosicionX][iPosicionY] == cBarco) {
                        System.out.print(" " + "*" + " ");
                    } else {
                        System.out.print(" " + cTablero[iPosicionX][iPosicionY] + " ");
                    }

                }
                System.out.println("");
            }

            do {

                System.out.print("Pon las cordenadas: ");
                sCordenadas = sc.nextLine();

                try {
                    iPosicionX = Character.getNumericValue(sCordenadas.charAt(0)) - 10;
                } catch (Exception e) {
                    System.out.println("!!! Cordenadas incorrectas: Valor erroneo !!!");
                }

                try {
                    iPosicionY = Integer.parseInt(sCordenadas.substring(1)) - 1;
                } catch (Exception e) {
                    System.out.println("!!! Cordenadas incorrectas: Conversion erronea !!!");

                }

                if (iPosicionX >= 0 & iPosicionX < cTablero.length & iPosicionY >= 0 & iPosicionY < cTablero.length) {
                    break;
                } else {
                    System.out.println("!!! Cordenadas incorrectas: Fuera de rango !!!");
                }
            } while (true);

            if (cTablero[iPosicionX][iPosicionY] == cBarco) {
                cTablero[iPosicionX][iPosicionY] = cTocados;
                iTocadosContador++;

            } else if (cTablero[iPosicionX][iPosicionY] == cMar) {
                cTablero[iPosicionX][iPosicionY] = cVacio;
            }


        } while (true);

    }
}



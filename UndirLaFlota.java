import java.util.Scanner;

public class UndirLaFlota {
    // final int iTurnos = 2;

    public static final int iTurnos = 2;
    private static Process start;

    public static void main(String[] args) {
        ProcessBuilder CLS = new ProcessBuilder("cmd", "/c", "cls"); // Esta sentencia es para crear un proceso de cmd.
        Scanner sc = new Scanner(System.in); 

        int iPosicionX, iPosicionY, iTurnosPlayer;
        String iCordenadas = "";

        // Es un Array de dos dimensiones de tipo char en la que emos denominado
        // cTablero, loemos mapedo de manera manual.
        // En el Array emos determidado los caracteres m y B, el caracter m representa
        // el mar y el caracter B representan los barcos.
        char[][] cTablero = { { 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'B', 'm', },
                { 'm', 'B', 'm', 'm', 'm', 'm', 'm', 'm', 'B', 'm', },
                { 'm', 'B', 'm', 'm', 'm', 'm', 'm', 'm', 'B', 'm', },
                { 'm', 'B', 'm', 'B', 'B', 'B', 'B', 'B', 'm', 'm', },
                { 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', },
                { 'm', 'm', 'm', 'B', 'B', 'm', 'm', 'm', 'm', 'm', },
                { 'm', 'B', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', },
                { 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'B', 'm', },
                { 'B', 'B', 'm', 'B', 'm', 'm', 'm', 'm', 'B', 'm', },
                { 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', 'm', } };

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
                                                                                // poner iPosicionX en 0 para que en
                                                                                // pieze desde la primera fila asta que
                                                                                // iPosicionX sea menor a la longitud
                                                                                // del cTablero.

                    // Este prin imprime en pantalla los caracteres de la A a la J.
                    // El Character.forDigit() es un metodo que convierte cualquier entero de base
                    // 10 a la base que le indiques, como el tabledo contiene 10 filas lo que emos
                    // es echo es pner el numero de filas mas 10 que es iPosicionX + 10.
                    System.out.print(" " + Character.forDigit(iPosicionX + 10, 20) + " ");

                    // Dentro del for se ejecuta otro for que recorre las colupnas del Array.
                    // con este for se imprime enpantalla las colupmas y comprueba si tiene que
                    // sustituir el caracter B pro el m para que no se vea en el tablero.
                    for (iPosicionY = 0; iPosicionY < cTablero.length; iPosicionY++) {
                        if (cTablero[iPosicionX][iPosicionY] == 'B') {
                            System.out.print("[" + "m" + "]");
                        } else {
                            System.out.print("[" + cTablero[iPosicionX][iPosicionY] + "]");
                        }
                    } // El for terminara cuandao haya recorido toda la fila

                    System.out.println(""); // con este prin separo las colupnas para que salga el tablero de forma
                                            // uniforme.
                } // El for terminara cuandao haya recorido toda las colupnas

                System.out.println("----------------------------------");

                // Este do while sirve para cuando el usuario tenga que poner las cordenads se
                // repita en caso de que sean erroneas.
                do {

                    // este do while comprueba que no hayan menos de 2 caracteres en la longitud de
                    // cordenadas en caso de lo contradio se repite.
                    do {
                        System.out.print("Pon las cordenadas: ");
                        iCordenadas = sc.nextLine(); // en iCordenadas se almacenadan las cordenadas de la siguiente manera:
                                                    // A1, G5, etc...
                    } while (iCordenadas.length() < 2);

                    // En iPosicionX lo que hacemos es coger el primer caracter del String con el
                    // metodo charAt() luego lo convertimos a entero con la classe
                    // Character usando el metodo getNumericValue() luego le restamos 10 ya que al
                    // pasar por ejemplo el carachter A se convierte en 10 y al restarle 10 pasa a
                    // 0. otros ejemplos seria B = 1, C = 3, etc...
                    iPosicionX = Character.getNumericValue(iCordenadas.charAt(0)) - 10;

                    // En iPosicionY lo que hacemos es retidar el primer caracter del String con el
                    // metodo substring() luego lo convertimos a entero con la classe
                    // Integer usando el metodo parseInt().
                    iPosicionY = Integer.parseInt(iCordenadas.substring(1)) - 1;

                    // Con este if se comprueba que la posicon X y Y no sea mayo 10 ni inferior a 0.
                    // Si se cumple saldra del do while.
                    if (iPosicionX >= 0 & iPosicionX < 10 & iPosicionY >= 0 & iPosicionY < 10) {
                        break;
                    } else {
                        System.out.println("!!! Cordenadas incorrectas !!!");
                    }
                } while (true);

                // Con este if comprobamos si se atocado el barco es mar si detecta que la
                // posicon del array es B pondra una T de rocado y si detecta que es m pondra
                // una V de vacios.
                if (cTablero[iPosicionX][iPosicionY] == 'B') {
                    cTablero[iPosicionX][iPosicionY] = 'T';
                } else if (cTablero[iPosicionX][iPosicionY] == 'm') {
                    cTablero[iPosicionX][iPosicionY] = 'V';
                    break;
                }

                // iTurnosPlayer++;
            } while (true);
            System.out.println("cosas");
        } while (true);
    }
}

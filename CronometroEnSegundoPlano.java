import java.util.Scanner;

public class CronometroEnSegundoPlano {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int iS = 0, iM = 0, iH = 0;

        long inicio = System.currentTimeMillis();
         
        sc.nextLine();
         
        long fin = System.currentTimeMillis();
         
        long tiempo = ((fin - inicio)/1000);

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

        System.out.println(iH + "h : " + iM + "m : " + iS + "s");
        
    }
}
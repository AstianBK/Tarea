package tp_Integrador.util;

import java.util.Scanner;

public class EscrituraDeDatos {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Ingresar id del jugador:");
        int idJugador=scanner.nextInt();
        System.out.println("Ingresar id del partido:");
        int idPartido= scanner.nextInt();
        System.out.println("Ingresar pronostico:");
        String pronostico= scanner.next();
        SQLUtil.addPronostico(idJugador,idPartido,pronostico);
    }
}

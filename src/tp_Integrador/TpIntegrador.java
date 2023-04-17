package tp_Integrador;

import tp_Integrador.clases.Jugador;
import tp_Integrador.clases.Partido;
import tp_Integrador.clases.Pronostico;
import tp_Integrador.exception.ExceptionPuntosNegativos;
import tp_Integrador.util.SQLUtil;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class TpIntegrador {
    public static int aumentoDePuntos=1;
    public static void main(String[] arg) {
        String[] codigos=new String[]{"S","N"};
        ArrayList<Pronostico> pronosticos= SQLUtil.getPronosticos();
        String cc;
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("Desea modificar los puntos S para si o N para dejarlos en predeterminado:");
            cc=scanner.next();
            if(Objects.equals(cc, "S")){
                SQLUtil.resetPuntosATodosLosJugadores();
                System.out.println("Ingresar nuevo valor:");
                aumentoDePuntos=scanner.nextInt();
            }
        }while (!verificarExistencia(cc,codigos));
        mostrarPronostico(pronosticos);
        ArrayList<Jugador> jugadores=SQLUtil.getJugadores();
        mostrarJugadores(jugadores);
    }

    private static void mostrarPronostico(ArrayList<Pronostico> pronosticos) {
        int i=0;
        while (i<pronosticos.size()){
            Partido partidos=pronosticos.get(i).getPartido();
            Jugador jugador=pronosticos.get(i).getJugador();
            boolean flag1=partidos.getEstadoDelPartido().equals(pronosticos.get(i).getResultado());
            System.out.println( partidos.getEquipo1().getNombre() + "\t" + partidos.getGolesEquipo1() + " vs " + partidos.getEquipo2().getNombre() + "\t" + partidos.getGolesEquipo2() + "\t Ronda " + partidos.getRonda() );
            System.out.println(flag1 ? "Correcto +" + aumentoDePuntos + " punto para el jugador " + jugador.getNombre() + "\n" : "Incorrecto\n");
            if(flag1){
                try {
                    updatePuntos(jugador.getIdJugador());
                } catch (ExceptionPuntosNegativos e) {
                    throw new RuntimeException(e);
                }
            }
            i++;
        }

    }

    public static void mostrarJugadores(ArrayList<Jugador> jugadores){
        int i=0;
        System.out.println("Nombre del jugador \t-\tPuntos");
        while (i<jugadores.size()){
            Jugador jugador=jugadores.get(i);
            System.out.println(jugador.getNombre()+"\t\t\t\t-\t"+jugador.getPuntos());
            i++;
        }
    }

    public static boolean verificarExistencia(String s1,String[] codigos){
        boolean flag=false;
        int i=0;
        while (i<codigos.length && !flag){
            if(s1.equals(codigos[i])){
                flag=true;
            }
            i++;
        }
        return flag;
    }

    public static void updatePuntos(int idjugador)throws ExceptionPuntosNegativos {
        Jugador jugador=SQLUtil.getJugadorPorId(idjugador);
        if(jugador.puntos<0){
            throw new ExceptionPuntosNegativos(jugador);
        }else {
            SQLUtil.setPuntos(jugador.getIdJugador(),jugador.getPuntos()+aumentoDePuntos);
        }

    }
}

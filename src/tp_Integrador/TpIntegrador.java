package tp_Integrador;

import tp_Integrador.clases.Jugador;
import tp_Integrador.clases.Partido;
import tp_Integrador.clases.Pronostico;
import tp_Integrador.exception.ExceptionPuntosNegativos;
import tp_Integrador.util.SQLUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class TpIntegrador {
    public static int aumentoDePuntos=1;

    public static void main(String[] arg) {
        String[] codigos=new String[]{"S","N","R"};
        String cc;
        Scanner scanner=new Scanner(System.in);
        System.out.println(SQLUtil.getRondaMax());
        do {
            System.out.println("Desea modificar los puntos S para si o N para dejarlos en predeterminado:");
            cc=scanner.next();
            boolean flag=false;
            switch (cc){
                case "S":
                    do {
                        Scanner scanner1=new Scanner(System.in);
                        System.out.println("Ingresar nuevo valor:");
                        if(scanner1.hasNextInt()){
                            flag=true;
                            aumentoDePuntos=scanner1.nextInt();
                        }
                    }while (!flag);

                    break;
                case "R":
                    SQLUtil.resetPuntosATodosLosJugadores();
                    break;
            }
        }while (!verificarExistencia(cc,codigos));
        ArrayList<Jugador> jugadores=SQLUtil.getJugadores();
        int i=0;
        while (i<jugadores.size()){
            Jugador jugador=jugadores.get(i);
            System.out.println("------------------------------------------------Jugador " + jugador.getNombre() + "------------------------------------------------\n");
            calcularPuntaje(jugador);
            i++;
        }

        mostrarJugadores(jugadores);
    }

    private static void calcularPuntaje(Jugador pJugador) {
        int i;
        int j = 0;
        boolean aciertoPerfectoDeRondas;
        while (j<SQLUtil.getRondaMax()){
            i=0;
            ArrayList<Pronostico> pronosticos1=SQLUtil.getPronosticosPorRonda(j+1,pJugador.getIdJugador());
            aciertoPerfectoDeRondas=pronosticos1.size()==SQLUtil.getCantDePartidosPorRonda(j+1);

            System.out.println("------------------------------------------------Ronda " + (j+1) + "------------------------------------------------\n");

            while (i<pronosticos1.size()){
                Partido partidos=pronosticos1.get(i).getPartido();
                boolean flag1=partidos.getEstadoDelPartido().equals(pronosticos1.get(i).getResultado());

                System.out.println( partidos.getEquipo1().getNombre() + "\t" + partidos.getGolesEquipo1() + " vs " + partidos.getEquipo2().getNombre() + "\t" + partidos.getGolesEquipo2() + "\t Ronda :" + partidos.getRonda() );
                System.out.println( flag1 ? "Correcto +" + aumentoDePuntos + " punto para el jugador " + pJugador.getNombre() + "\n" : "Incorrecto\n");

                if(flag1){
                    pJugador.setPuntos(pJugador.getPuntos()+aumentoDePuntos);
                }else if(aciertoPerfectoDeRondas){
                    aciertoPerfectoDeRondas=false;
                }
                i++;
            }
            if(aciertoPerfectoDeRondas){
                System.out.println("A acertado todos los pronosticos sobre la ronda "+(j+1)+"\n");

                pJugador.setPuntos( pJugador.getPuntos()+5);
            }
            j++;
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
}

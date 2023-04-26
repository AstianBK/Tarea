package tp_Integrador;

import tp_Integrador.clases.Jugador;
import tp_Integrador.clases.Partido;
import tp_Integrador.clases.Pronostico;
import tp_Integrador.util.SQLUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class TpIntegrador {
    /**
     * Variables modificables para los distintos puntajes:<br>
     * aumentoDePunto -> Cambia la cantidad de puntos que se obtiene por pronostico acertado y
     * se puede modificar en codigo y antes de mostrar los puntos.<br>
     * puntosExtraRonda -> Cambia la cantidad de puntos que se obtiene por acertar todos los pronosticos
     * en una misma ronda, Este solo se puede modificar en codigo.<br>
     * puntosExtraFase -> Cambia la cantidad de puntos que se obtiene por acertar todos los pronosticos
     * en una misma fase, Este solo se puede modificar en codigo.<br>
     * */
    public static int aumentoDePuntos=1;
    public static int puntoExtraRonda=5;
    public static int puntoExtraFase=5;

    public static void main(String[] arg) {
        String[] codigos=new String[]{"S","N","R"};
        String cc;
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("Desea modificar los puntos S para si o N para dejarlos en predeterminado:");
            cc=scanner.next();
            boolean flag=false;
            /*Se decide el puntaje por acierto de pronosticos:
            * "S"-> para cambiar el puntaje estandar.
            * "N"-> El programa corre con el puntaje estandar
            * "R"-> Resetea los punto de los jugadores a 0*/
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
        if(!jugadores.isEmpty()){
            for(Jugador jugador:jugadores){
                System.out.println("//------------------------------------------------Jugador " + jugador.getNombre() + "------------------------------------------------//\n");
                calcularPuntaje(jugador);
            }

            mostrarJugadores(jugadores);
        }else {
            System.out.println("No existe jugadores.");
        }
    }
    /**
     * Este método se encarga de calcular el puntaje final para cada jugador,
     * Siendo pJugador el jugador en cuestión.
     * */

    private static void calcularPuntaje(Jugador pJugador) {
        int j;
        int k = 1;
        boolean aciertoPerfectoDeRondas;
        boolean aciertoPerfectoDeFase;
        boolean participoEnTodasLasRondas;
        ArrayList<Pronostico> pronosticos1=SQLUtil.getPronosticosPorJugador(pJugador.getIdJugador());
        if(!pronosticos1.isEmpty()){
            while(k<=SQLUtil.getFaseMax()){
                j=1;
                System.out.println("\t/------------------------------------------------Fase " + (k) + "------------------------------------------------/\n");
                participoEnTodasLasRondas=true;
                aciertoPerfectoDeFase=true;
                while (j<=SQLUtil.getRondaPorFaseMax(k)){
                    if(SQLUtil.existePronosticoParaLaRonda(j,k,pJugador.getIdJugador())){
                        aciertoPerfectoDeRondas=true;
                        ArrayList<Pronostico> listAux=new ArrayList<>();
                        System.out.println("\t\t\t-----------------------------------------Ronda " + (j) + "------------------------------------------\n");

                        for (Pronostico pronostico1 : pronosticos1){
                            Partido partido =pronostico1.getPartido();
                            boolean flag1= partido.getEstadoDelPartido().equals(pronostico1.getResultado());
                            boolean flag2= partido.getFase()==k && partido.getRonda()==j;
                            if(flag2){
                                System.out.println( partido.getEquipo1().getNombre() + "\t" + partido.getGolesEquipo1() + " vs " + partido.getEquipo2().getNombre() + "\t" + partido.getGolesEquipo2() + "\t Ronda :" + partido.getRonda() + "\t Fase :"+ partido.getFase());
                                System.out.println( flag1 ? "Correcto +" + aumentoDePuntos + " punto para el jugador " + pJugador.getNombre() + "\n" : "Incorrecto.\n");

                                if(flag1){
                                    pJugador.setPuntos(pJugador.getPuntos()+aumentoDePuntos);
                                }else if(aciertoPerfectoDeRondas){
                                    aciertoPerfectoDeRondas=false;
                                    aciertoPerfectoDeFase=false;
                                }
                            }else {
                                listAux.add(pronostico1);
                            }
                        }
                        pronosticos1=listAux;
                        if(aciertoPerfectoDeRondas){
                            System.out.println("A acertado todos los pronosticos sobre la ronda "+ (j) +", De la fase "+ (k) +".\n Obtuvo "+ puntoExtraRonda +" puntos extra.\n");
                            pJugador.setPuntos( pJugador.getPuntos()+puntoExtraRonda);
                        }
                    }else {
                        participoEnTodasLasRondas=false;
                    }
                    j++;
                }
                if (aciertoPerfectoDeFase && participoEnTodasLasRondas){

                    System.out.println("A acertado todos los pronosticos sobre la fase "+ (k+1) +".\n Obtuvo "+ puntoExtraFase +" puntos extra.\n");
                    pJugador.setPuntos( pJugador.getPuntos()+puntoExtraFase);
                }
                k++;
            }
        }else {
            System.out.println("No Hay pronosticos para el jugador "+pJugador.getNombre()+".");
        }
    }

    /**
     * Este método se encarga de Mostrar el puntaje final de todos los jugadores,
     * Siendo jugadores un ArrayList con todos lo jugadores existente en la base de datos.
     * */

    public static void mostrarJugadores(ArrayList<Jugador> jugadores){
        int i=0;
        System.out.println("Nombre del jugador \t-\tPuntos");

        while (i<jugadores.size()){
            Jugador jugador=jugadores.get(i);
            System.out.println(jugador.getNombre()+"\t\t\t\t-\t"+jugador.getPuntos());
            i++;
        }
    }
    /**
     * Esta funcion devuelve TRUE si s1 existe en el vector codigos,
     * En el caso contrario devuelve FALSE.
     * */

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

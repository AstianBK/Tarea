package tp_Integrador;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tarea {
    public static void main(String[] arg){
        int puntosUser;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Ingrese nombre de usuario:");
        String nameUser=scanner.next();
        ArrayList<Pronostico> pronosticos=LecturaDeDatos.leerArchivo(Paths.get("\\Users\\agust\\OneDrive\\Escritorio\\Proyecto\\Illager\\Tarea\\pronostico.txt"));
        Ronda ronda=new Ronda("Fase de Grupos",LecturaDeDatos.leerArchivo(Paths.get("\\Users\\agust\\OneDrive\\Escritorio\\Proyecto\\Illager\\Tarea\\partidos.txt"),6));
        System.out.println("\t\t  FASE "+ronda.getNombre());
        System.out.println("\tLos Resultados de los partidos son:\n");
        puntosUser=mostrarPronostico(pronosticos.toArray(Pronostico[]::new));
        System.out.println("\nPuntos final de " +nameUser+" es: "+puntosUser);
    }

    private static Pronostico[] cargarVector(int max,Ronda ronda){
        int i=0;
        String c;
        String[] codigos={"A","B","C"};
        Scanner scanner=new Scanner(System.in);
        Pronostico[] pronosticos=new Pronostico[max];
        while (i<ronda.partidos.length){
            Partido partido=ronda.partidos[i];
            System.out.println("\tElegir Resultado del partido "+partido.getEquipo1().getNombre()+"\t"+partido.getEquipo2().getNombre());
            do{
                System.out.println("Escribir A si gana "+partido.getEquipo1().getNombre()+",B si gana "+partido.getEquipo2().getNombre()+" y C si es EMPATE");
                c=scanner.next();
            }while (!verificarExistencia(c,codigos));
            switch (c) {
                case "A" -> pronosticos[i] = new Pronostico(partido, partido.getEquipo1().getNombre());
                case "B" -> pronosticos[i] = new Pronostico(partido, partido.getEquipo2().getNombre());
                case "C" -> pronosticos[i] = new Pronostico(partido, "EMPATE");
            }
            i++;
        }
        return pronosticos;
    }
    private static int mostrarPronostico(Pronostico[] pronosticos) {
        int i=0;
        int j=0;
        while (i<pronosticos.length){
            Partido partidos=pronosticos[i].getPartido();
            boolean flag1=partidos.getEstadoDelPartido().equals(pronosticos[i].getResultado());
            System.out.println(partidos.getEquipo1().getNombre()+"\t"+partidos.getGolesEquipo1()+" vs "+partidos.getEquipo2().getNombre()+"\t"+partidos.getGolesEquipo2());
            System.out.println(flag1 ? "Correcto +1 punto\n" : "Incorrecto\n");
            if(flag1){
                j++;
            }
            i++;
        }
        return j;
    }

    private static Ronda crearRonda() {
        Partido[] partidos = new Partido[8];
        String[] nombreEquipos={"Argentina","ArabiaSaudita","Mexico","Polonia"};
        String[] nombreDescripcion={"Campeon Mundial","arabia","descripcionMexico","Polonia"};
        Random random=new Random();
        int i=0;
        while (i<partidos.length){
            int j=random.nextInt(0,3);
            int jj=random.nextInt(0,3);
            if(j==jj){
                if(j<3){
                    j++;
                }else {
                    j--;
                }

            }
            Equipo equipo1=new Equipo(nombreEquipos[j],nombreDescripcion[j]);
            Equipo equipo2=new Equipo(nombreEquipos[jj],nombreDescripcion[jj]);
            partidos[i]=new Partido(equipo1,equipo2, random.nextInt(0,3), random.nextInt(0,3) );
            i++;
        }
        return new Ronda("Grupos",partidos);
    }

    private static void ingresarPartidos(Scanner scanner){
        Partido[] partidos=new Partido[8];
        int i=0,cantGolesEq1,cantGolesEq2;
        while(i< partidos.length){
            System.out.println("Ingrese nombre de equipo 1");
            String s1=scanner.next();
            System.out.println("Ingrese Descripcion de equipo 1");
            String s2=scanner.next();
            System.out.println("Ingrese Goles de equipo 1");
            cantGolesEq1=scanner.nextInt();
            Equipo equipo1=new Equipo(s1,s2);
            System.out.println("Ingrese nombre de equipo 2");
            s1=scanner.next();
            System.out.println("Ingrese Descripcion de equipo 2");
            s2=scanner.next();
            System.out.println("Ingrese Goles de equipo 2");
            cantGolesEq2=scanner.nextInt();
            Equipo equipo2=new Equipo(s1,s2);
            partidos[i]=new Partido(equipo1,equipo2,cantGolesEq1,cantGolesEq2);
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

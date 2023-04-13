package tp_Integrador;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EscrituraDeDatos {
    public static void main(String[] args) {
        String s1 = "Tarea\\partidos.csv";
        Scanner scanner=new Scanner(System.in);
        Path url=Paths.get(s1);
        String[] s3=new String[6];
        String[] s2=new String[4];
        s2[0]="FIN";
        int i=0;
        while (!s2[0].equals("FIN")){
            System.out.println("Ingresar nombre de equipo 1:");
            s2[0]=scanner.next();
            if(!s2[0].equals("FIN")){
                System.out.println("Ingresar goles de equipo 1:");
                s2[1]=scanner.next();
                System.out.println("Ingresar nombre de equipo 2:");
                s2[2]=scanner.next();
                System.out.println("Ingresar goles de equipo 2:");
                s2[3]=scanner.next();
                s3[i]=s2[0]+";"+s2[1]+";"+s2[2]+";"+s2[3];
                i++;
            }
            if (s2[0].equals("FIN")){
                try {
                    Files.createFile(url);
                    Files.write(url, ("Argentina;1;ArabiaSaudita;2;Argentina\nArgentina;2;Mexico;0;Argentina\nArgentina;2;Polonia;0;Argentina\nPolonia;0;Mexico;0;Polonia\nPolonia;0;ArabiaSaudita;0;EMPATE\nMexico;1;ArabiaSaudita;0;EMPATE\n").getBytes());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        escrituraDeArchivos();
        System.out.println("datos guardados.");
    }
    public static void escrituraDeArchivos(){
        String s1 = "\\Users\\agust\\OneDrive\\Escritorio\\Proyecto\\Illager\\Tarea\\pronostico.csv";
        Scanner scanner=new Scanner(System.in);
        Path url=Paths.get(s1);
        try {
            Files.createFile(url);
            Files.write(url, ("Argentina;1;ArabiaSaudita;2;Argentina\nArgentina;2;Mexico;0;Argentina\nArgentina;2;Polonia;0;Argentina\nPolonia;0;Mexico;0;Polonia\nPolonia;0;ArabiaSaudita;0;EMPATE\nMexico;1;ArabiaSaudita;0;EMPATE\n").getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

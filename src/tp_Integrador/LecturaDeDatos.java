package tp_Integrador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LecturaDeDatos {
    public static Partido[] leerArchivo(Path path,int cant_partidos){
        List<String> s1;
        try {
            s1 = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Partido[] partidos=new Partido[cant_partidos];
        String[] s=new String[cant_partidos];
        String[] s3;
        int i=0;
        for(String s2:s1){
            s[i]=s2;
            i++;
        }
        i=0;
        for (String value : s) {
            s3 = value.split(";");
            Equipo equipo1 = new Equipo(s3[0], " ");
            Equipo equipo2 = new Equipo(s3[2], " ");
            int goles_equipo1 = Integer.parseInt(s3[1]);
            int goles_equipo2 = Integer.parseInt(s3[3]);
            partidos[i]=new Partido(equipo1, equipo2, goles_equipo1, goles_equipo2);
            i++;
        }
        return partidos;
    }
    public static ArrayList<Pronostico> leerArchivo(Path path){
        List<String> s1;
        try {
            s1 = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Pronostico> pronosticos=new ArrayList<>();
        String[] s3;

        for (String value : s1) {
            s3 = value.split(";");
            Equipo equipo1 = new Equipo(s3[0], " ");
            Equipo equipo2 = new Equipo(s3[2], " ");
            int goles_equipo1 = Integer.parseInt(s3[1]);
            int goles_equipo2 = Integer.parseInt(s3[3]);
            pronosticos.add(new Pronostico(new Partido(equipo1, equipo2, goles_equipo1, goles_equipo2),s3[4]));
        }
        return pronosticos;
    }
}

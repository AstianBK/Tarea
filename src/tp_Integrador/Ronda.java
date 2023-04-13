package tp_Integrador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Ronda {
    private String nombre;
    public Partido[] partidos;

    public Ronda(String nombre,Partido[] partidos){
        this.nombre=nombre;
        this.partidos=partidos;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Partido[] getPartidos() {
        return partidos;
    }
}

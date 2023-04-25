package tp_Integrador.clases;

import tp_Integrador.exception.ExceptionPuntosNegativos;
import tp_Integrador.util.SQLUtil;

public class Jugador {
    private final String nombre;
    private final int idJugador;
    public int puntos;
    public Jugador(int id, String nombre){
        this.nombre=nombre;
        this.idJugador=id;
        this.puntos=0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getIdJugador() {
        return this.idJugador;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
        try {
            updatePuntos(this,puntos);
        } catch (ExceptionPuntosNegativos e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePuntos(Jugador jugador,int puntos)throws ExceptionPuntosNegativos {
        if(puntos<0){
            throw new ExceptionPuntosNegativos(jugador);
        }else {
            SQLUtil.setPuntos(jugador.getIdJugador(),puntos);
        }
    }
}

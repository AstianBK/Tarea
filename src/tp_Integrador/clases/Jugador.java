package tp_Integrador.clases;

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
    }
}

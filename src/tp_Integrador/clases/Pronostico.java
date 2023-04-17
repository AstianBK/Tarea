package tp_Integrador.clases;

public class Pronostico {
    private final Partido partido;
    private final String resultado;
    private final int idPronostico;
    private final Jugador jugador;
    public Pronostico(int id,Partido partido,String resultado,Jugador jugador){
        this.idPronostico=id;
        this.partido=partido;
        this.resultado=resultado;
        this.jugador=jugador;
    }

    public Partido getPartido() {
        return partido;
    }

    public String getResultado() {
        return resultado;
    }

    public int getIdPronostico() {
        return this.idPronostico;
    }

    public Jugador getJugador() {
        return jugador;
    }

}

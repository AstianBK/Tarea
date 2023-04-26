package tp_Integrador.clases;

public class Partido {
    private final int golesEquipo1;
    private final int golesEquipo2;
    private final int id;
    private final Equipo equipo1;
    private final Equipo equipo2;
    private final String EstadoDelPartido;
    private final int ronda;
    private final int fase;

    public Partido(int id,Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2,String resultado,int rondaNum,int fase){
        this.id=id;
        this.equipo1=equipo1;
        this.equipo2=equipo2;
        this.golesEquipo1=golesEquipo1;
        this.golesEquipo2=golesEquipo2;
        this.EstadoDelPartido=resultado;
        this.ronda=rondaNum;
        this.fase=fase;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public String getEstadoDelPartido() {
        return EstadoDelPartido;
    }

    public int getIdPartido() {
        return id;
    }

    public int getRonda() {
        return ronda;
    }

    public int getFase() {
        return fase;
    }
}

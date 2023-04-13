package tp_Integrador;

public class Partido {
    private int golesEquipo1;
    private int golesEquipo2;
    private Equipo equipo1;
    private Equipo equipo2;
    private String EstadoDelPartido;
    public Partido(Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2){
        this.equipo1=equipo1;
        this.equipo2=equipo2;
        this.golesEquipo1=golesEquipo1;
        this.golesEquipo2=golesEquipo2;
        this.EstadoDelPartido=decidirEstado(golesEquipo1,golesEquipo2,equipo1.getNombre(),equipo2.getNombre());
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
    public String decidirEstado(int golesEquipo1,int golesEquipo2,String equipo1,String equipo2){
        if(golesEquipo1==golesEquipo2){
            return "EMPATE";
        }else if(golesEquipo1<golesEquipo2){
            return equipo2;
        }else
            return equipo1;
    }

}

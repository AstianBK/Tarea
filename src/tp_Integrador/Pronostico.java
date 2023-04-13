package tp_Integrador;

public class Pronostico {
    private Partido partido;
    private String resultado;
    public Pronostico(Partido partido,String resultado){
        this.partido=partido;
        this.resultado=resultado;
    }

    public Partido getPartido() {
        return partido;
    }

    public String getResultado() {
        return resultado;
    }

}

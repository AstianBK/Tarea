package tp_Integrador.clases;

public class Equipo {
    private String nombre;
    private String descrip;
    private int total_goles=0;
    public Equipo(String nombre,String descrip){
        this.nombre=nombre;
        this.descrip=descrip;
    }
    public String getNombre() {
        return nombre;
    }

    public String getDescrip() {
        return this.nombre+" "+this.descrip;
    }

    public void setTotal_goles(int goles){
        this.total_goles+=goles;
    }

    public int getTotal_goles() {
        return this.total_goles;
    }
}

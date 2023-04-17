package tp_Integrador.util;

import tp_Integrador.clases.Equipo;
import tp_Integrador.clases.Jugador;
import tp_Integrador.clases.Partido;
import tp_Integrador.clases.Pronostico;

import java.sql.*;
import java.util.ArrayList;

public class SQLUtil {
    public static Connection getConnection(){
        try{
            try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Error al registrar el driver de MySQL: " + ex);
                }
                Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql12","root","astian13");
                return con;
            } catch(Exception e){ System.out.println(e);
        }
        return null;
    }
    public static ArrayList<Jugador> getJugadores(){
        Connection con=getConnection();
        ArrayList<Jugador> jugadores=new ArrayList<>();
        try {
            if(con!=null){
                Statement statement=con.createStatement();
                ResultSet rs=statement.executeQuery("select * from jugadores");
                while (rs.next()){
                    Jugador jugador=new Jugador(rs.getInt(1),rs.getString(2));
                    jugador.setPuntos(rs.getInt(3));
                    jugadores.add(jugador);
                }
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jugadores;
    }
    public static ArrayList<Pronostico> getPronosticos(){
        Connection con=getConnection();
        ArrayList<Pronostico> pronosticos=new ArrayList<>();
        try {
            if(con!=null){
                Statement statement=con.createStatement();

                ResultSet rs=statement.executeQuery("select * from pronosticos");
                while (rs.next()){
                    Pronostico pronostico=new Pronostico(rs.getInt(1),getPartidoPorId(rs.getInt(3)),
                            rs.getString(4),getJugadorPorId(rs.getInt(2)));
                    pronosticos.add(pronostico);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pronosticos;
    }
    public static Jugador getJugadorPorId(int id){
        Connection con=getConnection();
        Jugador jugador=null;
        try {
            if(con!=null){
                Statement statement=con.createStatement();
                ResultSet rs=statement.executeQuery("select * from jugadores where idJugador=" + id );
                while (rs.next()){
                    jugador=new Jugador(rs.getInt(1),rs.getString(2));
                    jugador.setPuntos(rs.getInt(3));
                }
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return jugador;
    }
    public static Partido getPartidoPorId(int id){
        Connection con=getConnection();
        Partido partido=null;
        try {
            if(con!=null){
                Statement statement=con.createStatement();
                ResultSet rs=statement.executeQuery("select * from partidos where idPartido="  + id );
                while (rs.next()){
                    partido=new Partido(rs.getInt(1),getEquipoPorNombre(rs.getString(2))
                            ,getEquipoPorNombre(rs.getString(3)),rs.getInt(4),rs.getInt(5)
                            ,rs.getString(6),rs.getInt(7));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return partido;
    }
    public static Equipo getEquipoPorNombre(String nombre){
        Connection con=getConnection();
        Equipo equipo=null;
        try {
            if(con!=null){
                Statement statement=con.createStatement();
                ResultSet rs=statement.executeQuery("select * from equipos where nombre=" + "'" + nombre + "'" );
                while (rs.next()){
                    equipo=new Equipo(rs.getString(1),rs.getString(2));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return equipo;
    }

    public static void setPuntos(int idJugador,int puntos){
        Connection con=getConnection();
        try {
            if(con!=null){
                Statement statement=con.createStatement();
                statement.executeUpdate("update jugadores set puntos=" + puntos + " where idJugador=" + idJugador);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void resetPuntosATodosLosJugadores(){
        Connection con=getConnection();
        try {
            if(con!=null){
                Statement statement=con.createStatement();
                statement.executeUpdate("update jugadores set puntos=0");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addPronostico(int idJugador,int idPartido,String pronostico){
        Connection con=getConnection();
        try {
            if(con!=null){
                ArrayList<Pronostico> pronosticos=getPronosticos();
                Statement statement=con.createStatement();
                statement.executeUpdate("insert into pronosticos values (" + (pronosticos.size()+1) + "," + idJugador + "," + idPartido + "," + "'" + pronostico+ "'" + ")" );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

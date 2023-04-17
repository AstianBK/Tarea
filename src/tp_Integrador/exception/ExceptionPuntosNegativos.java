package tp_Integrador.exception;

import tp_Integrador.clases.Jugador;

public class ExceptionPuntosNegativos extends Exception{
    private final Jugador jugador;
        public ExceptionPuntosNegativos(Jugador jugador){
        this.jugador=jugador;
    }
}

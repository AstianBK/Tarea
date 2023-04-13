package actividad_clase6;

import java.util.Scanner;

public class tarea_clase6 {
    public static void main(String[] args) {
        double num1,num2;
        Scanner scanner=new Scanner(System.in);
        System.out.println("ingresar 2 numeros:");
        num1=scanner.nextDouble();
        num2=scanner.nextDouble();
        System.out.println("ingresar A para sumar, B para restar, C para multiplicar y D para dividir");
        String c=scanner.next();

        switch (c){
            case "A"->System.out.println(Calculadora.suma(num1,num2));
            case "B"-> System.out.println(Calculadora.resta(num1,num2));
            case "C"-> System.out.println(Calculadora.multiplicacion(num1,num2));
            case "D"-> System.out.println(Calculadora.division(num1,num2));
        }
    }
}

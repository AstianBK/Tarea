import java.util.Scanner;

public class Tarea_dia1 {
    public static void main(String[] args) {
        int selecionar_actividad;
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("Ingrese 1 para ver actividad 1 o 2 para ver la actividad 2");
            selecionar_actividad=scanner.nextInt();
        }while (selecionar_actividad!=1 && selecionar_actividad!=2);

        switch (selecionar_actividad){
            case 1:{
                int a=5;
                int b=14;
                while(a<=b){
                    System.out.println(a);
                    a++;
                }
                System.out.println("////////////////////////////////////////////////////////////////////////");
                System.out.println("Actividad b");
                actividad_B();
                System.out.println("////////////////////////////////////////////////////////////////////////");
                System.out.println("Actividad c");
                actividad_C();
                System.out.println("////////////////////////////////////////////////////////////////////////");
                System.out.println("Actividad d");
                actividad_D();
            }
            case 2:{
                System.out.println("////////////////////////////////////////////////////////////////////////");
                System.out.println("Actividad 2");
                actividad_2();
            }
        }
    }

    public static void actividad_B(){
        int a=5;
        int b=14;
        while (a<=b){
            if(a%2==0){
                System.out.println(a);
            }
            a++;
        }
    }
    public static void actividad_C(){
        int a=5;
        int b=14;
        Scanner scanner=new Scanner(System.in);
        int c;
        do{
            System.out.println("ingresar 0 para par y 1 para impar");
            c=scanner.nextInt();
        }while (c!=0 && c!=1);

        while (a<=b){
            if(c==1 && a%2==0){
                System.out.println(a);
            } else if (c==0 && a%2!=0) {
                System.out.println("///////////"+a);
            }
            a++;
        }
    }
    public static void actividad_D(){
        int a=5;
        for(int i=14;i>a;i-=2){
            System.out.println(i);
        }
    }
    public static void actividad_2() {
        int ingreso;
        int autos;
        int inmuebles;
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println("ingresar ingreso:");
            ingreso = scanner.nextInt();
        }while (!esMayorA0(ingreso));

        do{
            System.out.println("ingresar Cuantos Autos poseen con menos de 5 años de antiguedad:");
            autos=scanner.nextInt();
        }while (!esMayorA0(autos));

        do{
            System.out.println("ingresar Cuantos inmuebles poseen:");
            inmuebles=scanner.nextInt();
        }while (!esMayorA0(inmuebles));

        if(ingreso>=489083 || autos>=3 || inmuebles>=3){
            System.out.println("////////////////////////////////////////////////////////////////////////");
            System.out.println("Eres parte de la poblacion de ingresos altos");
        }else{
            System.out.println("////////////////////////////////////////////////////////////////////////");
            System.out.println("No Eres partes de la poblacion de ingresos altos");
        }
    }

    public static boolean esMayorA0(int a){
        return a>0;
    }
}
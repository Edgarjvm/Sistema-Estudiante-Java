package SistemaEstudiante;

import java.util.Scanner;

public class RegCarrera {
    static Scanner esc = new Scanner(System.in);

    public Carrera registrar(){
        Carrera carrera = new Carrera();
        System.out.print("Nombre: ");
        carrera.setNombre(esc.nextLine());
        System.out.print("Clave: ");
        carrera.setClave(esc.nextLine()); 
        System.out.println();
        return carrera;
    }

    public Carrera registrar(String nombre, String clave){
        Carrera carrera = new Carrera();
        carrera.setNombre(nombre);
        carrera.setClave(clave);
        return carrera;
    }

    public void imprimir(Carrera carrera){
        System.out.println("----- Carrera -----");
        System.out.println("Nombre: " +carrera.getNombre());
        System.out.println("Clave: " +carrera.getClave());
    }

    public void imprimir(Carrera carrera, int indice){
        System.out.println("----- Carrera "+indice+ " -----");
        System.out.println("Nombre: " +carrera.getNombre());
        System.out.println("Clave: " +carrera.getClave());

    }
    
}

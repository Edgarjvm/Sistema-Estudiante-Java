package SistemaEstudiante;

import java.util.Scanner;

public class RegEstuadiante {

    static Scanner esc = new Scanner(System.in);

    public Estudiante registrar(){
        Estudiante estudiante = new Estudiante();
        System.out.print("Nombre: ");
        estudiante.setNombre(esc.nextLine());
        System.out.print("Edad: ");
        estudiante.setEdad(esc.nextInt()); esc.nextLine();
        System.out.print("Sexo (H/M): ");
        estudiante.setSexo(esc.next().charAt(0)); esc.nextLine();
        System.out.print("Matricula: ");
        estudiante.setMatricula(esc.nextLine());
        System.out.print("Carrera: ");
        estudiante.setCarrera(esc.nextLine());
        System.out.print("Correo: ");
        estudiante.setCorreo(esc.nextLine());
        return estudiante;
    }

    public void imprimir(Estudiante estudiante, int nme){
        System.out.println("----- Estudiante " +nme+ " -----");
        System.out.println("Nombre: " +estudiante.getNombre());
        System.out.println("Edad: " +estudiante.getEdad());
        System.out.println("Sexo: " +estudiante.getSexo());
        System.out.println("Matricula: " +estudiante.getMatricula());
        System.out.println("Carrera: " +estudiante.getCarrera());
        System.out.println("Correo: " +estudiante.getCorreo());
        System.out.println();
    }
    public void imprimir(Estudiante estudiante){
        System.out.println("----- Estudiante -----");
        System.out.println("Nombre: " +estudiante.getNombre());
        System.out.println("Edad: " +estudiante.getEdad());
        System.out.println("Sexo: " +estudiante.getSexo());
        System.out.println("Matricula: " +estudiante.getMatricula());
        System.out.println("Carrera: " +estudiante.getCarrera());
        System.out.println("Correo: " +estudiante.getCorreo());
        System.out.println();
    }

    
}

package SistemaEstudiante;

import java.util.List;
import java.util.Scanner;

public class RegEstuadiante {

    static Scanner esc = new Scanner(System.in);
    static RegCarrera regC = new RegCarrera();

    public Estudiante registrar(List<Carrera> carreras){
        Estudiante estudiante = new Estudiante();
        System.out.print("Nombre: ");
        estudiante.setNombre(esc.nextLine());
        System.out.print("Edad: ");
        estudiante.setEdad(esc.nextInt()); esc.nextLine();
        System.out.print("Sexo (H/M): ");
        estudiante.setSexo(esc.next().charAt(0)); esc.nextLine();
        System.out.print("Matricula: ");
        estudiante.setMatricula(esc.nextLine());
        System.out.println("Selecciona una carrera");
        for(int i = 0; i < carreras.size(); i++)
            regC.imprimir(carreras.get(i), i+1);
        System.out.print("Ingresa clave : ");
        estudiante.setCarrera(busCarrera(esc.nextLine(), carreras));
        System.out.print("Correo: ");
        estudiante.setCorreo(esc.nextLine());
        return estudiante;
    }

    public static Carrera busCarrera(String clave, List<Carrera> carreras){
        for(int i = 0; i < carreras.size(); i++){
            if (carreras.get(i).getClave().equals(clave)) {
                return carreras.get(i);
            }
        }
        return null;
    }

    public void imprimir(Estudiante estudiante, int nme){
        System.out.println("----- Estudiante " +nme+ " -----");
        System.out.println("Nombre: " +estudiante.getNombre());
        System.out.println("Edad: " +estudiante.getEdad());
        System.out.println("Sexo: " +estudiante.getSexo());
        System.out.println("Matricula: " +estudiante.getMatricula());
        System.out.println("Carrera: " +estudiante.getCarrera().getNombre());
        System.out.println("Correo: " +estudiante.getCorreo());
    }
    public void imprimir(Estudiante estudiante){
        System.out.println("----- Estudiante -----");
        System.out.println("Nombre: " +estudiante.getNombre());
        System.out.println("Edad: " +estudiante.getEdad());
        System.out.println("Sexo: " +estudiante.getSexo());
        System.out.println("Matricula: " +estudiante.getMatricula());
        System.out.println("Carrera: " +estudiante.getCarrera().getNombre());
        System.out.println("Correo: " +estudiante.getCorreo());
    }

    
}

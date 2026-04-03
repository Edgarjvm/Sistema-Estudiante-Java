package SistemaEstudiante;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaEstu {
    static List<Estudiante> estudiantes = new ArrayList<>();
    static RegEstuadiante regE = new RegEstuadiante();
    static Scanner esc = new Scanner(System.in);
    static int opc = 0, indice = -1;
    static String archivo = "C:\\Users\\varae\\OneDrive\\Desktop\\Programacion\\EJVM\\SistemaEstudiante\\estudiantes.txt";
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(
                new FileReader(archivo)
            );String linea;
            while ((linea = br.readLine())!=null) {
                String leido[] = linea.split(", ");
                String nombre = leido[0];
                int edad = Integer.parseInt(leido[1]);
                char sexo = leido[2].charAt(0);
                String matruicula = leido[3];
                String carrera = leido[4];
                String correro = leido[5];
                Estudiante estudiante = new Estudiante(nombre, edad, sexo, matruicula, carrera, correro);
                estudiantes.add(estudiante);
            }
            System.out.println("ESTUDIANTES CARGADOS: " +estudiantes.size());
            System.out.println();
            br.close();
            
            do {
                
                System.out.println("1)Registrar estudiante   2)Mostrar estudiantes");
                System.out.println("3)Buscar Estudiante      4)Modificar estudiante");
                System.out.println("5)Eliminar estudiante    6)Salir");
                opc = esc.nextInt();
                System.out.println();

                if (opc == 1) {
                    System.out.println("Ingresa datos:");
                    Estudiante estudiante = regE.registrar();
                    boolean repetido = false;
                    for(int i = 0; i< estudiantes.size(); i++){
                        if (estudiantes.get(i).getMatricula().equals(estudiante.getMatricula())) {
                            System.out.println();
                            System.out.println("MATRICULA REPETIDA");
                            System.out.println();
                            repetido = true;
                            break;
                        }
                    }
                    if (!repetido) {
                        estudiantes.add(estudiante);
                        guardar();
                    }
                    
                }
                if (opc == 2) {
                    if (estudiantes.isEmpty()) {
                        System.out.println("NO HAY ESTUDIANTES REGISTRADOS");
                    }else
                        for(int i = 0; i< estudiantes.size(); i++)
                            regE.imprimir(estudiantes.get(i), i+1);
                }  
                if (opc == 3) {
                    if (estudiantes.isEmpty())
                        System.out.println("NO HAY ESTUDIANTES REGISTRADOS");
                    else{
                        System.out.println("Ingresa matricula de estudiante a buscar: ");
                        String matricula = esc.next();
                        System.out.println();
                        Estudiante estudiante = buscar(matricula);
                        if (estudiante == null) {
                            System.out.println("ESTUDIANTE NO ENCONTRADO");
                        }
                        else{
                            regE.imprimir(estudiante);
                        }
                    }
                }
                if (opc ==4) {
                    Estudiante estudiante = new Estudiante();
                    System.out.println("Ingresa la matricula del estudiante a modificar: ");
                    String matricula = esc.next();
                    System.out.println();
                    estudiante = buscar(matricula);
                    if (estudiante == null) {
                        System.out.println("ESTUDIANTE NO ENCONTRADO");
                        continue;
                    }
                    regE.imprimir(estudiante);
                    System.out.println("¿Deseas modificar este estudiante?");
                    System.out.println("1)SI   2)NO");
                    int opcm = esc.nextInt();
                    if (opcm == 1) {
                        modificarEst(matricula, indice(matricula) );
                        System.out.println();
                        System.out.println("ESTUDIANTE MODIFICADO");
                        System.out.println();
                    }   
                }
                if (opc == 5) {
                    System.out.println("Ingresa matricula del estudiante a eliminar");
                    String matricula = esc.next();
                    eliminarEst(matricula);
                }
            } while (opc != 6);
            
        } catch (IOException e) {
            e.fillInStackTrace();
        }

        
    }

    public static Estudiante buscar(String matricula){
        for(int i = 0; i< estudiantes.size(); i++){
            if (estudiantes.get(i).getMatricula().equals(matricula)) {
                System.out.println("ESTUDIANTE ENCONTRADO");
                return estudiantes.get(i);
                
            }
        }
        return null;
        
    }

    public static void guardar(){
        try {
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo)
            );
            for(int i = 0; i<estudiantes.size(); i++){
                Estudiante estudianteG = estudiantes.get(i);
                bw.write(estudianteG.getNombre()+", "+estudianteG.getEdad()+", "+
                        estudianteG.getSexo()+", "+estudianteG.getMatricula()+", "+
                        estudianteG.getCarrera()+", "+estudianteG.getCorreo());
                bw.newLine();
            }
            bw.close();

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public static int indice(String matricula){
        for(int i = 0; i<estudiantes.size(); i++){
            if (estudiantes.get(i).getMatricula().equals(matricula)) {
                return indice = i;
                
            }
        }    
        return indice;
    }

    public static void modificarEst(String matricula, int indice){
        System.out.println("Ingresa los nuevos datos:");
        Estudiante estudiante = regE.registrar();
        System.out.println();
        boolean repetido = false;
        for(int i = 0; i< estudiantes.size(); i++){
            if (i != indice && estudiantes.get(i).getMatricula().equals(estudiante.getMatricula())) {
                System.out.println();
                System.out.println("MATRICULA REPETIDA");
                System.out.println();
                repetido = true;
                break;
            }
        }
        if (!repetido) {
            
            estudiantes.set(indice, estudiante);

            System.out.println("Datos Actualizados");
            System.out.println();
            guardar();
                
        }
    }

    public static void eliminarEst(String matricula){
        int opc = 0;
        for(int i = 0; i<estudiantes.size(); i++){
            if (estudiantes.get(i).getMatricula().equals(matricula)) {
                regE.imprimir(estudiantes.get(i));
                System.out.println();
                System.out.println("Deseas eliminar este estudiante");
                System.out.println("1)SI     2)NO");
                opc = esc.nextInt();
                if (opc == 1) {
                    estudiantes.remove(i);
                    guardar();
                    System.out.println("");
                    break;
                }
                else
                    System.out.println("No eliminado");
            }
        }
        
    }
}

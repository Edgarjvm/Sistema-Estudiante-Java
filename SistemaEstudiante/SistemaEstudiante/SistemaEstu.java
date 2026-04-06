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
    static List<Carrera> carreras = new ArrayList<>();
    static RegEstuadiante regE = new RegEstuadiante();
    static RegCarrera regC = new RegCarrera();
    static Scanner esc = new Scanner(System.in);
    static int  indice = -1, opc = 0;
    static String archivo = "estudiantes.txt";
    static String archivoCarreras = "carreras.txt";
    public static void main(String[] args) {
        try {
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo, true)
            );
            BufferedReader br = new BufferedReader(
                new FileReader(archivo)
            );String linea;
            BufferedWriter bwC = new BufferedWriter(
                new FileWriter(archivoCarreras, true)
            );
            BufferedReader brC = new BufferedReader(
                new FileReader(archivoCarreras)
            );
            while ((linea = br.readLine())!=null) {
                String[] leido = linea.split(", ");
                String nombre = leido[0];
                int edad = Integer.parseInt(leido[1]);
                char sexo = leido[2].charAt(0);
                String matricula = leido[3];
                Carrera carrera = regC.registrar(leido[4], leido[5]);
                String correo = leido[6];
                Estudiante estudiante = new Estudiante(nombre, edad, sexo, matricula, carrera, correo);
                estudiantes.add(estudiante);
            }
            while ((linea = brC.readLine())!=null) {
                String[] leido = linea.split(", ");
                String nombre = leido[0];
                String clave = leido[1];
                Carrera carrera = new Carrera(nombre, clave);
                carreras.add(carrera);
            }
            System.out.println("ESTUDIANTES CARGADOS: " +estudiantes.size());
            System.out.println("CARRERAS CARGADAS: " +carreras.size());
            br.close();
            bw.close();
            bwC.close();;
            brC.close();  
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        do{
            System.out.println();
            System.out.println("--------------------MENU--------------------");
            System.out.println("1)Registrar estudiante   2)Registrar carrera");
            System.out.println("3)Modificar estudiante   4)Modificar carrera");
            System.out.println("5)Mostrar estudiantes    6)Buscar Estudiante");
            System.out.println("7)Eliminar estudiante    8)Salir");
            System.out.println("--------------------------------------------");
            opc = esc.nextInt();
            System.out.println();
            if (opc == 1) {
                System.out.println("Ingresa datos:");
                Estudiante estudiante = regE.registrar(carreras);
                matriculaRep(estudiante);
            }
            if (opc == 2){
                Carrera carrera = regC.registrar();
                carreras.add(carrera);
                guardarCarrera();
            }
            if (opc == 3) {
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
            if (opc == 4){
                System.out.print("Clave de carrera a modificar: ");
                String clave = esc.next();
                System.out.println();
                Carrera carrera = busCarrera(clave);
                if (carrera == null) {
                    System.out.println("CARRERA NO ENCONTRADA");
                    continue;
                }
                regC.imprimir(carrera);
                int indiceC = indiceC(clave);
                System.out.println("¿Desea modificar esta carrera?");
                System.out.println("1)SI    2)NO");
                int mc = esc.nextInt();
                if (mc == 1) {
                    modificarCar(clave, indiceC);
                }

            }
            if (opc == 5) {
                if (estudiantes.isEmpty()) {
                    System.out.println("NO HAY ESTUDIANTES REGISTRADOS");
                }else
                    for(int i = 0; i< estudiantes.size(); i++)
                        regE.imprimir(estudiantes.get(i), i+1);
                
            }
            if (opc == 6) {
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
            if (opc == 7) {
                System.out.println("Ingresa matricula del estudiante a eliminar");
                String matricula = esc.next();
                eliminarEst(matricula);
            }
            
            
        } while (opc!=8);
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

    public static Carrera busCarrera(String clave){
        for(int i = 0; i < carreras.size(); i++){
            if (carreras.get(i).getClave().equals(clave)) {
                System.out.println("CARRERA ENCONTRADA");
                return carreras.get(i);
            }
        }
        return null;
    }

    public static void guardarEs(){
        try {
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo)
            );
            for(int i = 0; i<estudiantes.size(); i++){
                Estudiante estudianteG = estudiantes.get(i);
                bw.write(estudianteG.getNombre()+", "+estudianteG.getEdad()+", "+
                        estudianteG.getSexo()+", "+estudianteG.getMatricula()+", "+
                        estudianteG.getCarrera().getNombre()+", "+estudianteG.getCarrera().getClave()+", "+
                        estudianteG.getCorreo());
                bw.newLine();
            }
            bw.close();

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public static void guardarCarrera(){
        try {
            BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivoCarreras)
            );
            for(int i = 0; i < carreras.size(); i++){
                Carrera carrera = carreras.get(i);
                bw.write(carrera.getNombre()+", "+carrera.getClave());
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

    public static int indiceC(String clave){
        for(int i = 0; i < carreras.size(); i++){
            if(carreras.get(i).getClave().equals(clave))
                return i;
        }
        return 0;
    }

    public static void modificarEst(String matricula, int indice){
        System.out.println("Ingresa los nuevos datos:");
        Estudiante estudiante = regE.registrar(carreras);
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
            guardarEs();
                
        }
    }

    public static void modificarCar(String clave, int indice){
        System.out.println("Ingresa datos nuevos");
        Carrera carrera = regC.registrar();
        for(int i = 0; i < estudiantes.size(); i++){
            if (estudiantes.get(i).getCarrera().getClave().equals(clave)) {
                estudiantes.get(i).setCarrera(carrera);
            }
        }
        carreras.set(indice, carrera);
        guardarCarrera();
        guardarEs();
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
                    guardarEs();
                    System.out.println("");
                    break;
                }
                else
                    System.out.println("No eliminado");
            }
        }
        
    }

    public static void matriculaRep(Estudiante estudiante){
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
            guardarEs();
        }

    }

}

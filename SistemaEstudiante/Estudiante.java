package SistemaEstudiante;

public class Estudiante {
    private String nombre;
    private int edad;
    private char sexo;
    private String matricula;
    private String carrera;
    private String correo;

    public Estudiante(){
    }
    public Estudiante(String nombre, int edad, char sexo, String matricula, String carrera, String correo){
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.matricula = matricula;
        this.carrera = carrera;
        this.correo = correo;
    }
    public char getSexo(){
        return sexo;
    }
    public void setSexo(char sexo){
        this.sexo = sexo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    

    
}

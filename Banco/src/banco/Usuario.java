package banco;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Usuario {
    Scanner sc = new Scanner(System.in);
    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    int edad, estrato;
    String nombre, sexo, cel, nCedula;
    
    public Usuario(){
        this.edad=0;
        this.estrato=0;
        this.nombre=null;
        this.sexo=null;
        this.cel=null;
        this.nCedula=null;
    }
    public String nombre(){
        return nombre;
    }        
    public String sexo(){
        return sexo;
    }
    public void setData() throws IOException{
        System.out.print("Nombre completo: ");
        nombre= br.readLine();
        System.out.print("Sexo: ");
        sexo= br.readLine();
        System.out.print("Edad: ");
        edad= sc.nextInt();
        System.out.print("No. Cedula: ");
        nCedula= br.readLine();
        System.out.print("Estrato: ");
        estrato= sc.nextInt();
        System.out.print("No. Telefono movil: ");
        cel= br.readLine();               
    }
    
    public int edad(){                
        return edad;
    }
    
    public void getData(){
        System.out.println("Nombre: "+nombre);
        System.out.println("Sexo: "+ sexo);
        System.out.println("No. Cedula: "+nCedula);
        System.out.println("Edad: "+edad);
        System.out.println("Estrato: "+estrato);
        System.out.println("No. Telefono movil: "+cel);
    }                        
}

package banco;
import java.io.IOException;
import java.util.Scanner;
public class Banco {
    public static void main(String[] args) throws IOException{       
        Prestamo pr = new Prestamo();       
        Usuario us= new Usuario();
        Scanner sc = new Scanner(System.in);        
        pr.menu();
        System.out.println("Programa realizado por Josue Daniel Rodriguez, Cod: 20191020109");
        
        
    }
    
}

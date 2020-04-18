package banco;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Prestamo {
    Scanner sc = new Scanner(System.in);
    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    Usuario us = new Usuario();
    int nPrestamo, vPrestamo, aux, recursos, nC, dia, mes, year, size, presMas, presFem;          
    String fA, fC, datacredito, nombres[]= new String [100000];

    public Prestamo(){
        this.nPrestamo=0;
        this.vPrestamo=0;
        this.aux=0;       
        this.recursos=100000000;
        this.nC=0;
        this.dia=0;
        this.mes=0;
        this.year=0;
        this.size=0;
        this.presMas=0;
        this.presFem=0;
        this.fA=null;
        this.fC=null;
        this.datacredito=null;
    }
        
    public void menu()throws IOException{                      
        int op=0;
        int i=1;
        System.out.println("PROGRAMA HECHO POR JOSUE RODRIGUEZ, COD:20191020109");
        do{
            System.out.println("    PROGRAMA PRESTAMOS BANCARIOS");
            System.out.println(" 1. Para pedir prestamo");
            System.out.println(" 2. Personas que han realizado prestamos");
            System.out.println(" 3. Monto total clasificado por sexo");        
            System.out.println(" 4. Para salir \n");              
            op= sc.nextInt();
            switch (op){
                case 1: 
                    aux=0;
                    System.out.println("Prestamo No. "+i);
                    System.out.println("El banco cuenta con recursos totales de $"+recursos);            
                    System.out.println("Fecha de autorizacion:(dd/mm/aaaa)");
                    fA= br.readLine();  
                    verificarFecha();                       
                    if(aux!=-1){
                        System.out.println("Esta reportado en Datacretdito? ");
                        datacredito= br.readLine();  
                        verificarDatacredito();
                        if(aux!=-1){
                            us.setData();
                            verificarMayoriaEdad();
                            if(aux!=-1){
                                System.out.print("Cantidad de dinero a pedir prestado \nsi es mayor a $2.000.000 se retendra el  5%): $");
                                vPrestamo= sc.nextInt(); 
                                verificarPrestamo();                                
                                if(aux!=-1){
                                    System.out.print("Cuotas a pagar: ");
                                    nC= sc.nextInt(); 
                                    verificarCuota();
                                    System.out.println("\n\n Prestamo No."+i);
                                    if(aux!=-1)
                                        size++;
                                        setListaNombres(size,us.nombre());
                                        setPrestamoSexos(us.sexo());
                                        getDatos();
                                }                                
                            }                            
                        }                        
                    }
                    i+=aux;
                    if(recursos<=vPrestamo)
                        recursos=0;
                    else
                        recursos-=vPrestamo;                    
                    i++;
                    break;
                case 2:
                    getListaNombres(size);
                    break;
                case 3:
                    getPrestamoSexos();
                    break;
            }
            System.out.println("\n\n\n\n");
        }    
        while(op!=4);            
    }
    
    public void setPrestamoSexos(String _sexo){
        if(_sexo.equals("masculino")){
            presMas+=vPrestamo;
        }
        else if(_sexo.equals("femenino")){
            presFem+=vPrestamo;
        }
    }
    
    public void getPrestamoSexos() throws IOException{
        String enter;
        System.out.println("Cantidad prestada a hombres: $"+presMas);
        System.out.println("Cantidad prestada a mujeres: $"+presFem);
        System.out.println("\n Enter pasa continuar...");
        enter= br.readLine();        
    }
    
    public void setListaNombres(int _size, String _nombre){
        int i=_size-1;      
        nombres[i]=_nombre;   
    }
    
    public void getListaNombres(int _size) throws IOException{
        int i=0;
        String enter;
        System.out.println("\nPersonas que han realizado prestamos: ");
        for(i=0;i<_size;i++){        
            System.out.println("- "+nombres[i]);            
        }
        System.out.println("\n Enter para continuar...");
        enter=br.readLine();
    }
    
    public double retencion(int n){
        if(vPrestamo>2000000){
            return vPrestamo-(vPrestamo*0.05);
        }
        else
            return vPrestamo;
    }
    public void getDatos() throws IOException{        
        String enter;
        System.out.println("-------------------------------------");
        System.out.println("fecha de autorizacion: "+fA);  
        us.getData();
        System.out.println("Prestamo neto: $"+retencion(vPrestamo));        
        System.out.println("Fecha de entrega del prestamo: "+fechaEntrega(dia));
        fechasCuotas(nC);  
        System.out.println("Enter para continuar...");
        enter=br.readLine();
    }
    public boolean verificarBisiesto(int _year){        
        if(_year%100==0){
           if(_year%400==0){
               return true;
           }
       }
        if(_year%4==0 && _year%100!=0){
            return true;
       }       
       else
           return false;               
    }
    
    public String getFechaTradicional(String _fecha){                              
        String  fc="", ff;
        int _dia=0, _mes=0, _year=0, cmp=0;
        ff=_fecha;
        if(ff.length()==9){
            if(ff.substring(1,2).equals("/")&&ff.substring(4,5).equals("/"))  
                cmp=1;
            if(ff.substring(2,3).equals("/")&&ff.substring(4,5).equals("/"))
                cmp=1;
            if(cmp!=1){    
                if((ff.substring(1,2).equals("/"))==false||(ff.substring(4,5).equals("/"))==false){                    
                    ff=ff.replace(ff.substring(1,2),"/");
                    ff=ff.replace(ff.substring(4,5),"/");                                        
                }
                else if((ff.substring(2,3).equals("/"))==false||(ff.substring(4,5).equals("/"))==false){                    
                    ff=ff.replace(ff.substring(2,3),"/");
                    ff=ff.replace(ff.substring(4,5),"/");                                        
                }
            } 
            ///////// 
            if(ff.substring(2,3).equals("/")){               
                ff=ff.replaceFirst("/","/0");                  
            }
            else if(ff.substring(1,2).equals("/")){    
                _dia=Integer.parseInt(ff.substring(0,1));
                ff=ff.replaceFirst(ff.substring(0,1),"0"+_dia);                 
            }
        }        
        else if(ff.length()==8){
            if((ff.substring(1,2).equals("/"))==false||(ff.substring(3,4).equals("/"))==false){
                ff=ff.replace(ff.substring(1,2),"/");                
            }
            _dia=Integer.parseInt(ff.substring(0,1));            
            _mes=Integer.parseInt(ff.substring(2,3));
            _year=Integer.parseInt(ff.substring(4,8));
            ff=fc.concat("0"+String.valueOf(_dia)+"/"+"0"+String.valueOf(_mes)+"/"+String.valueOf(_year));                        
        }
        else{
            if((ff.substring(2,3).equals("/"))==false||(ff.substring(5,6).equals("/"))==false){
                ff=ff.replace(ff.substring(2,3),"/");                
                ff=ff.replace(ff.substring(5,6),"/");                
            }            
        }
        return ff;
    }     
    public void fechasCuotas(int nCuotas){
        int i=0, _dia=0, op=0; 
        String fa="";
        fC=fechaEntrega(dia);                   
        _dia=Integer.parseInt(fC.substring(0,2));
        mes=Integer.parseInt(fC.substring(3,5));
        year=Integer.parseInt(fC.substring(6,10));        
        verificarBisiesto(year);                
        System.out.println("Las fechas de pago son: ");
        do{
            op=0;
            fC=null;
            if(mes>=12)
                mes-=12;
            
            if(mes==1||mes==3||mes==5||mes==7||mes==8||mes==10||mes==0){
                op=1;                
            }                                
            else if(mes==2){
                op=2;                
            }                
            else if(mes==4||mes==6||mes==9||mes==11){
                op=3;                
            }                
            switch (op){
                case 1:
                    if(_dia==1){
                        _dia=31;  
                        mes+=1;               
                        fC=fa.concat(String.valueOf(_dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(year));
                        System.out.println(getFechaTradicional(fC));                    
                    }    
                    else {
                        _dia-=1;
                        mes+=1;//3+1=4                        
                        fC=fa.concat(String.valueOf(_dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(year));
                        System.out.println(getFechaTradicional(fC));     
                    }     
                    break;
                case 2:
                    if(mes==2){ 
                        if(verificarBisiesto(year)==true){
                            _dia+=1;
                            mes+=1;                            
                            fC=fa.concat(String.valueOf(_dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(year));
                            System.out.println(getFechaTradicional(fC));     
                        }
                        else{
                            _dia+=2;
                            mes+=1;                            
                            fC=fa.concat(String.valueOf(_dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(year));
                            System.out.println(getFechaTradicional(fC));     
                        }
                    }
                    break;
                case 3:
                    if(mes==4||mes==6||mes==9||mes==11){ 
                        mes+=1;                        
                        fC=fa.concat(String.valueOf(_dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(year));
                        System.out.println(getFechaTradicional(fC));     
                    }  
                break;                               
            }                                      
            i++;            
        }
        while(i<nCuotas);
    }
    
    public String fechaEntrega(int _dia){        
        int _mes=0, _year=0;
        String _fA=null;
        _dia=dia+5;               
        _fA=fA.replaceFirst(fA.substring(0,2),String.valueOf(_dia));        
        _year=year;
        _mes=mes;
        return getFechaTradicional(_fA);        
    }               
    
    public void verificarFecha() throws IOException{        
        String des; 
        int cmp=0;
        fA=getFechaTradicional(fA);
        dia=Integer.parseInt(fA.substring(0,2)); 
        year=Integer.parseInt(fA.substring(6,10));
        mes=Integer.parseInt(fA.substring(3,5));                      
        do{    
            if(dia>20||dia<=0){
                do{
                    System.out.println("Solo se autorizan prestamos los primeros 20 dias del mes.");
                    System.out.println("Desea cambiar la fecha?");
                    des=br.readLine();
                    if(des.equals("si")){
                        System.out.println("Digite la nueva fecha");
                        fA=null;
                        fA= br.readLine();                    
                        dia=0;                        
                        fA=getFechaTradicional(fA);
                        dia=Integer.parseInt(fA.substring(0,2));
                    }
                    else if (des.equals("no"))
                        aux=-1;
                }
                while(dia>20&&aux!=-1);                        
            }
            if(mes>12||mes<=0){
                System.out.println("Fecha invalida.");
                System.out.println("Desea cambiar la fecha?");
                    des=br.readLine();
                    if(des.equals("si")){
                        System.out.println("Digite la nueva fecha");
                        fA=null;
                        fA= br.readLine();                    
                        mes=0;
                        fA=getFechaTradicional(fA);
                        mes=Integer.parseInt(fA.substring(3,5));
                    }
                    else if (des.equals("no"))
                        aux=-1;
            }
            if(dia>20||dia<=0||mes>12||mes<=0) //FALTA AGREAR UNA IGUALDAD MAS
                cmp=1;
            else
                cmp=0;
        }
        while(cmp==1&&aux!=-1);
    }
    public void verificarDatacredito(){              
        if(datacredito.equals("si")){
            System.out.println("No se puede autorizar el prestamo debido a que se encuentra reportado en datacredito");
            aux=-1;
        }
    }
    public void verificarMayoriaEdad(){
        if(us.edad()<18&&us.edad()>0){
            System.out.println("Solo se puede autorizar el prestamo a mayores de edad");
            aux=-1;
        }   
    }
    
    public void verificarPrestamo() throws IOException{          
        String des;
        int cmp=0;
        do{
            if(vPrestamo<=0){
                do{ 
                    System.out.println("No se puede autorizar el prestamo debido a que \nla cantidad de dinero a pedir es negativa\n");
                    System.out.println("Desea modificar la cantidad del prestamo?");
                    des= br.readLine();
                    if(des.equals("si")){
                        System.out.print("Digite la nueva cantidad: $");
                        vPrestamo= sc.nextInt();
                    }
                    else
                        aux=-1;
                }
                while(vPrestamo<0&&aux!=-1);
            }                 
            if(vPrestamo>recursos){
                do {
                    System.out.println("No se puede autorizar el prestamo debido a que \nla cantidad de dinero a pedir supera los recursos del banco\n");
                    System.out.println("Los recursos disponibles son de $"+recursos);
                    System.out.println("Desea modificar su prestamo?");
                    des= br.readLine();
                    if(des.equals("si")){                                        
                        System.out.println("Ingrese la nueva suma de dinero");
                        vPrestamo= sc.nextInt();                                        
                    }
                    else {
                        aux=-1;                    
                    }
                } 
                while (vPrestamo>recursos&&aux!=-1);            
            }
            if(vPrestamo<=0||vPrestamo>recursos)
                cmp=1;
            else
                cmp=0;
        }
        while(cmp==1&&aux!=-1);   
                                                                                   
    }
    
    public void verificarCuota() throws IOException{
        String des;
        int _nC=nC, cmp=0;       
        do{            
            if(_nC<=0){                
                do{                    
                    System.out.println("No se puede autorizar el prestamo puesto que el minimo de cuotas es de 1\n");
                    System.out.println("Desea modificar el No. de cuotas?");
                    des= br.readLine();
                    if(des.equals("si")){
                        System.out.println("Digite el nuevo No. de cuotas");
                        _nC=0;
                        _nC= sc.nextInt();                        
                    }
                    else 
                        aux=-1;
                }
                while(_nC<=0&&aux!=-1);
                nC=_nC;       
            }

            if(_nC>6){                 
                while(_nC>6&&aux!=-1){                    
                    System.out.println("No se puede autorizar el prestamo puesto que el maximo de cuotas es de 6\n");
                    System.out.println("Desea modificar el No. de cuotas?");
                    des= br.readLine();
                    if(des.equals("si")){
                        System.out.println("Ingrese el nuevo No. de cuotas");
                        _nC=0;
                        _nC= sc.nextInt();                        
                    }
                    else 
                        aux=-1;
                }               
                nC=_nC;
            }                       
            if(nC>6||nC<=0)
                cmp=1;
            else
                cmp=0;
        }
        while(cmp==1&&aux!=-1);               
    }        
}

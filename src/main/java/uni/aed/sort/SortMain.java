package uni.aed.sort;

import java.util.Random;
import java.util.Scanner;

public class SortMain {
    private static final Scanner scr=new Scanner(System.in).useDelimiter("\n");     
    private static Sort sort=new Sort();
    private Integer dataOrigen[]=null;
    
    public static void main(String[] args){
        SortMain sortMain=new SortMain();
        sortMain.menu();        
    }
    private void menu(){
        int opcion=1;        
        String SEPARADOR="\n";
        try{
            do{
                System.out.print("Algoritmos Ordenamiento"+SEPARADOR+
                        "1.- Carga de Datos en Array "+SEPARADOR+
                        "2.- Carga de Datos Duplicados en Array "+SEPARADOR+
                        "3.- Carga Aleatoria en Array "+SEPARADOR+
                        "4.- Visualizar Array "+SEPARADOR+
                        "5.- Ordenar - metodo de Seleccion "+SEPARADOR+
                        "6.- Salir "+SEPARADOR+
                        "Elija una opcion:");
                opcion=scr.nextInt();
                switch(opcion)
                {
                    case 1->{carga();visualizar();}
                    case 2->{cargaDuplicados();visualizar();}
                    case 3->{cargaAleatoria();visualizar();}
                    case 4->{visualizar();}
                    case 5->{testSelectionSort();}
                    default-> {break;}                
               }
            }while(opcion!=6);
            scr.close();
        }catch(Exception e){
            System.out.println("Error presentado "+ e.getMessage());
        }
    }
    private void carga(){
        Integer X[]={5,14,24,39,43,65,84,45};        
        dataOrigen=X.clone();
        sort.setX(X);
    }
    private void cargaDuplicados(){
        Integer X[]={5,14,24,14,43,5,84,45};        
        dataOrigen=X.clone();
        sort.setX(X);
    }
    private void cargaAleatoria(){       
        System.out.println("Carga Aleatoria");
        int N=5000;
        int min=0;
        int max=20000;
        Integer[] X=new Integer[N];
        Random random=new Random();
        for(int i=0;i<X.length;i++)
            X[i]=random.nextInt(max);
        dataOrigen=X.clone();
        sort.setX(X);
    }
    private void visualizar(){
        System.out.println("El Array contiene los siguientes elementos");
        System.out.println(sort.toString());
    }
    private void visualizarDataOrigen(){
        sort.setX(dataOrigen.clone());
        System.out.println("El Array contiene los siguientes elementos");
        System.out.println(sort.toString());
    }
    private void testSelectionSort(){
        visualizarDataOrigen();//setear la data origen y visualizarla
        sort.selectionSort();//invocamos al algoritmo de ordenamiento
        System.out.println(sort.toString());//visualizamos el resultado
    }
}

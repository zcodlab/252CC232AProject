package uni.aed.sort;

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
        char Rpta='S';
        String SEPARADOR="\n";
        try{
            do{
                System.out.print("Algoritmos Ordenamiento"+SEPARADOR+
                        "1.- Carga de Datos en Array "+SEPARADOR+
                        "2.- Visualizar Array "+SEPARADOR+
                        "3.- Ordenar - metodo de Seleccion "+SEPARADOR+
                        "4.- Salir "+SEPARADOR+
                        "Elija una opcion:");
                opcion=scr.nextInt();
                switch(opcion)
                {
                    case 1->{carga();visualizar();}
                    case 2->{visualizar();}
                    case 3->{testSelectionSort();}
                    default-> {break;}                
                }
                System.out.print("Para continuar con la operaciones pulse S, para finalizar pulse N: ");
                Rpta=scr.next().toUpperCase().charAt(0);            
            }while(Rpta=='S');
            scr.close();
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    private void carga(){
        Integer X[]={5,14,24,39,43,65,84,45};
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

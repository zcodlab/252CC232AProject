package uni.aed.tda.arraylistTDA;

import uni.aed.tda.listTDA.ListTDA;

public class ArrayListTDAMain {
    public static void main(String[] args){
        ArrayListTDAMain listTDAMain=new ArrayListTDAMain();
        listTDAMain.testArrayListTDAString();
        
    }
    private void testArrayListTDAString(){
        ListTDA<String> lista=new ArrayListTDA<>();
        System.out.println("Antes de añadir elementos");
        System.out.println("La lista contiene "+lista.size()+" elementos");
        lista.add("Jose");
        lista.add("Beatriz");
        lista.add("Sandro");
        lista.add("Franklin");
        System.out.println("Despues de añadir elementos");
        System.out.println("La lista contiene "+lista.size()+" elementos");
        
        System.out.println("Visualizando la lista");
        System.out.println(lista.toString());
        
        lista.add(2,"Adelaida");        
        
        System.out.println("Visualizando la lista");
        System.out.println(lista.toString());
        
        String data="Adelaida";
        if(lista.contain(data)){
            System.out.println("El elemento buscado "+ data+ 
            " se encuentra en la posicion "+ lista.indexOf(data)+
                    ", se procedera a su eliminacion");
            lista.delete(lista.indexOf(data));
        }else
            System.out.println("El elemento buscado "+ data+ 
            " NO se encuentra en la lista ");
        
        System.out.println("Visualizando la lista");
        System.out.println(lista.toString());
    }
}

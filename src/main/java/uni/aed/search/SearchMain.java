package uni.aed.search;

public class SearchMain {    
    public static void main(String[] args){
        SearchMain searchMain=new SearchMain();
        //searchMain.testSearchLineal();
        searchMain.testSearchBinaria();
    }
    private void testSearchLineal(){
        Search s=new Search();
        Integer X[]={15,1,25,60,69,86,3,78,2,10};
        s.setX(X);
        //visualizando X
        System.out.println("El contenido del arreglo es:");
        for(Integer x:s.getX())
            System.out.print(x+",");
        System.out.println("");
        //Busqueda Satisfactoria
        int valorB=69;
        int result=s.searchLineal(valorB);
        if(result==-1)
            System.out.println("El valor buscado "+valorB+" no existe");
        else
            System.out.println("El valor buscado "+valorB+" se encontro en la posicion "+ result);        
        //Busqueda Insatisfactoria
        valorB=87;
        result=s.searchLineal(valorB);
        if(result==-1)
            System.out.println("El valor buscado "+valorB+" no existe");
        else
            System.out.println("El valor buscado "+valorB+" se encontro en la posicion "+ result);        
    }
    private void testSearchBinaria(){
        Search s=new Search();
        //Arreglo ordenado
        //Integer X[]={1,2,3,10,15,25,60,69,78,86};
        Integer X[]={5,12,17,23,38,44,77,84,90};
        s.setX(X);
        //Busqueda Satisfactoria
        int valorB=44;
        int result=s.searchBinaria(valorB);
        if(result==-1)
            System.out.println("El valor buscado "+valorB+" no existe");
        else
            System.out.println("El valor buscado "+valorB+" se encontro en la posicion "+ result);        
        //Busqueda Insatisfactoria
        valorB=45;
        result=s.searchBinaria(valorB);
        if(result==-1)
            System.out.println("El valor buscado "+valorB+" no existe");
        else
            System.out.println("El valor buscado "+valorB+" se encontro en la posicion "+ result);        
        
    }
}

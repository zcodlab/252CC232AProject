package uni.aed.search;

public class SearchMain {    
    public static void main(String[] args){
        SearchMain searchMain=new SearchMain();
        searchMain.testSearchLineal();
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
}

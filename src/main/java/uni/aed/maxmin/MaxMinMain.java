package uni.aed.maxmin;

public class MaxMinMain {
    public static void main(String[] args){
        MaxMinMain maxMinMain=new MaxMinMain();
        maxMinMain.testMaxMin();
    }
    
    private void testMaxMin(){
        //Se instancia la clase en la que estan definidos los algortimos Maximo y Minimo
        MaxMin mm=new MaxMin();
        //Declarar e inicializar y arreglo
        Integer X[]={15,1,25,60,69,86,3,78,2,10};
        //Visualizar el contenido del Array X
        for(int i=0;i<X.length;i++)
            System.out.print(X[i]+",");
        System.out.println("");
        //Determinando el maximo y minimo del Array
        System.out.println("El maximo valor es:"+ mm.getMaxValor(X));
        System.out.println("El minimo valor es:"+ mm.getMinValor(X));
    }
    
    
}

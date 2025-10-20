package uni.aed.tda.stackTDA;

public class StackTDAMain {
    public static void main(String[] args){
        StackTDAMain stackTDAMain=new StackTDAMain();        
        stackTDAMain.testArrayStackTDA();
        
    }
    private void testArrayStackTDA(){
        StackTDA<String> pila=new ArrayStackTDA<>();
        System.out.println("Antes de añadir elementos");
        System.out.println("La Pila contiene "+pila.size()+" elementos");
        pila.push("Jose");
        pila.push("Beatriz");
        pila.push("Sandro");
        pila.push("Franklin");   
        System.out.println("Despues de añadir elementos");
        System.out.println("La Pila contiene "+pila.size()+" elementos");
        
        System.out.println("Visualizando la Pila");        
        System.out.println(pila.toString());
        System.out.println("Eliminando Elementos");
        System.out.println("Elemento Eliminado: "+pila.pop().toString());
        System.out.println("Visualizando la Pila");        
        System.out.println(pila.toString());
        
        System.out.println("Recuperando Elementos");
        System.out.println("Elemento Recuperado: "+pila.peek().toString());
        System.out.println("Visualizando la Pila");        
        System.out.println(pila.toString());
        
        
    }
}

package uni.aed.sort;
import java.util.concurrent.TimeUnit;

public class Sort {
    private Integer[] X;
    private int nComp=0;    //# Comparaciones
    private int nInt=0;     //# Intercambios
    private long tEjec=0;   //tiempo de ejecucion

    public void setX(Integer[] X) {
        this.X = X;
        clear();        
    }

    public Integer[] getX() {
        return X;
    }

    public void settEjec(long tEjec) {
        this.tEjec = tEjec;
    }

    public long gettEjec() {
        return tEjec;
    }

    public int getnComp() {
        return nComp;
    }

    public int getnInt() {
        return nInt;
    }
    
    
    private void clear(){
        this.nComp=0;    
        this.nInt=0;     
        this.tEjec=0;   
    }
    
    public void selectionSort(){
        int startIndex,minIndex,lenght,temp;
        lenght=X.length;
        clear();
        long tIni=System.nanoTime();
        for(startIndex=0;startIndex<=lenght-2;startIndex++){
            minIndex=startIndex;
            //buscamos el valor mas pequeño de la coleccion
            for(int i=startIndex+1;i<=lenght-1;i++){
                //realizamos la comparacion
                nComp++;
                if(X[i]<X[minIndex]) minIndex=i;
            }
            //intercambio
            temp=X[startIndex];
            X[startIndex]=X[minIndex];
            X[minIndex]=temp;
            nInt++;
        }
        long tFin=System.nanoTime();
        this.settEjec(tFin-tIni);        
    }
    public void heapSort(){
        heapSortConstruct();//fase1: construccion
        heapSortExtract();//fase2: extraccion        
    }
    private void heapSortConstruct(){
        int current=0, maxChildIndex;
        boolean hecho;
        for(int i=(X.length-2)/2; i>=0;i--){
            current=i;
            hecho=false;
            while(!hecho){//2*i+1,2*i+2
                if(2*current + 1>X.length-1)
                    //nodo actual no tiene hijo
                    hecho=true;
                else{
                    maxChildIndex=heapSortMaxChild(current,X.length-1);
                    if(X[current]<X[maxChildIndex]){
                        intercambio(current,maxChildIndex);
                        current=maxChildIndex;
                    }else
                        hecho=true;
                }
            }
        }
    }
    private int heapSortMaxChild(int loc,int end){
        int result, izq,der;
        izq=2*loc+1;//indice impar
        der=2*loc+2;//indice par        
        
        return 0;//incompleto
    }
    
    private void intercambio(int p,int q){
        int temp=X[p];
        X[p]=X[q];
        X[q]=temp;
    }
            
    private void heapSortExtract(){
        
    }

    @Override
    public String toString() {
        if(X==null ||X.length==0) return "";
        StringBuilder str=new StringBuilder();
        for(Integer x:X){
            if(str.isEmpty())
                str.append(x);
            else
                str.append(","+x);
        }
        return str.toString()+"\n nComp= "+nComp+" nInt= "+ 
                nInt +" tEjec(ns)= "+tEjec 
                +" tEjec(ms)= "+ TimeUnit.NANOSECONDS.toMillis(tEjec);
    }
    
}

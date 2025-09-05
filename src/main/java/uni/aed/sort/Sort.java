package uni.aed.sort;

public class Sort {
    private Integer[] X;

    public void setX(Integer[] X) {
        this.X = X;
    }

    public Integer[] getX() {
        return X;
    }
    
    public void selectionSort(){
        int startIndex,minIndex,lenght,temp;
        lenght=X.length;
        for(startIndex=0;startIndex<=lenght-2;startIndex++){
            minIndex=startIndex;
            //buscamos el valor mas pequeño de la coleccion
            for(int i=startIndex+1;i<=lenght-1;i++)
                //realizamos la comparacion
                if(X[i]<X[minIndex]) minIndex=i;
            //intercambio
            temp=X[startIndex];
            X[startIndex]=X[minIndex];
            X[minIndex]=temp;
        }        
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
        return str.toString();
    }
    
}

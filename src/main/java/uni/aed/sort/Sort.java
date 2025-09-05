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

package uni.aed.search;

public class Search {
    private final int NO_ENCONTRADO=-1;
    private Integer[] X;

    public void setX(Integer[] X) {
        this.X = X;
    }

    public Integer[] getX() {
        return X;
    }
    
    public int searchLineal(int valor){
        int loc=0;
        while(loc<X.length && X[loc]!=valor){
            loc++;
        }
        if(loc==X.length)
            return NO_ENCONTRADO;
        else
            return loc;
    }
    
    public int searchBinaria(int valor){
        int bajo=0,alto=X.length-1,medio=(bajo+alto)/2;
        while(bajo<=alto && X[medio]!=valor){
            if(X[medio]<valor)
                bajo=medio+1;
            else
                alto=medio-1;
            medio=(bajo+alto)/2;
        }
        if(bajo>alto)
            medio=NO_ENCONTRADO;        
        return medio;
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

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
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.tda.treeTDA.experimentalBST;

import uni.aed.tda.treeTDA.BstNodeTDA;

/**
 *
 * Clase con la estructura para registrar resultados
 */
public class Resultado {    
    private BstNodeTDA<Integer> raiz;
    private long hRequerida;
    private long initialHeight;
    private long initialIPL;
    private long initialNodeCount;
    private boolean initialBalance;
    private long finalHeight;
    private long finalIPL;
    private long finalNodeCount;
    private boolean finalBalance;

    public Resultado() {
    }
    
    public Resultado(BstNodeTDA<Integer> raiz, long hRequerida,long initialHeight, long initialIPL, long initialNodeCount, boolean initialBalance ) {
        this.raiz = raiz;    
        this.hRequerida=hRequerida;
        this.initialHeight = initialHeight;
        this.initialIPL = initialIPL;
        this.initialNodeCount = initialNodeCount;
        this.initialBalance = initialBalance;        
    }  

    public Resultado(BstNodeTDA<Integer> raiz, long hRequerida, long initialHeight, long initialIPL, long initialNodeCount, boolean initialBalance, long finalHeight, long finalIPL, long finalNodeCount, boolean finalBalance) {
        this.raiz = raiz;
        this.hRequerida = hRequerida;
        this.initialHeight = initialHeight;
        this.initialIPL = initialIPL;
        this.initialNodeCount = initialNodeCount;
        this.initialBalance = initialBalance;
        this.finalHeight = finalHeight;
        this.finalIPL = finalIPL;
        this.finalNodeCount = finalNodeCount;
        this.finalBalance = finalBalance;
    }    

    public BstNodeTDA<Integer> getRaiz() {
        return raiz;
    }   

    public void setRaiz(BstNodeTDA<Integer> raiz) {
        this.raiz = raiz;
    }

    public void sethRequerida(long hRequerida) {
        this.hRequerida = hRequerida;
    }

    public void setInitialHeight(long initialHeight) {
        this.initialHeight = initialHeight;
    }

    public void setInitialIPL(long initialIPL) {
        this.initialIPL = initialIPL;
    }

    public void setInitialNodeCount(long initialNodeCount) {
        this.initialNodeCount = initialNodeCount;
    }

    public void setInitialBalance(boolean initialBalance) {
        this.initialBalance = initialBalance;
    }

    public void setFinalHeight(long finalHeight) {
        this.finalHeight = finalHeight;
    }

    public void setFinalIPL(long finalIPL) {
        this.finalIPL = finalIPL;
    }

    public void setFinalNodeCount(long finalNodeCount) {
        this.finalNodeCount = finalNodeCount;
    }

    public void setFinalBalance(boolean finalBalance) {
        this.finalBalance = finalBalance;
    }
    
    

    public long gethRequerida() {
        return hRequerida;
    }

    public long getInitialHeight() {
        return initialHeight;
    }

    public long getInitialIPL() {
        return initialIPL;
    }

    public long getInitialNodeCount() {
        return initialNodeCount;
    }

    public boolean isInitialBalance() {
        return initialBalance;
    }

    public long getFinalHeight() {
        return finalHeight;
    }

    public long getFinalIPL() {
        return finalIPL;
    }

    public long getFinalNodeCount() {
        return finalNodeCount;
    }

    public boolean isFinalBalance() {
        return finalBalance;
    }
    
    

    @Override
    public String toString() {
        return raiz.toString();
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.tda.treeTDA.AVL.CrossReference;
import uni.aed.tda.linkedlistTDA.LinkedListTDA;
import uni.aed.tda.listTDA.ListTDA;
/**
 *
 * @author
 */
public class Palabra implements Comparable<Palabra>{
    private String palabra;
    private ListTDA<Integer> listaLineas = new LinkedListTDA<>();
    public Palabra(String palabra){
       this.palabra = palabra;
    }
    public void agregarNumLinea(int numLinea){
       this.listaLineas.add(numLinea);
    }
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public ListTDA<Integer> getListaLineas() {
        return listaLineas;
    }

    public void setListaLineas(LinkedListTDA<Integer> listaLineas) {
        this.listaLineas = listaLineas;
    }

    @Override
    public int compareTo(Palabra p) {
       return this.palabra.compareTo(p.getPalabra());
    }

    @Override
    public String toString() {
        //return " [palabra : " + palabra + " || Lineas de ocurrencia : (" + listaLineas.toString() + ")]";
        return palabra + "[" + listaLineas.toString() + "]";
    }
    
}

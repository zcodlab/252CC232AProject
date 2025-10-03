package uni.aed.tda.linkedlistTDA;

import uni.aed.tda.listTDA.IteratorTDA;
import uni.aed.tda.listTDA.ListTDA;

public class LinkedListTDA<E> implements ListTDA<E>{
    public static final int NOT_FOUND=-1;
    private Nodo<E> head;
    private Nodo<E> cola;
    private int count;

    public LinkedListTDA() {
        clear();
    }

    @Override
    public void add(E data) {
        Nodo<E> newNodo=new Nodo<>(data);
        if(count==0)
            head=cola=newNodo;
        else{//agregamos el elemento en la parte final de la lista enlazada
            cola.setNext(newNodo);
            cola=newNodo;
        }
        count++;            
    }

    @Override
    public void add(int index, E data) throws IndexOutOfBoundsException {
       Nodo<E> apt=head;
       Nodo<E> newNodo=new Nodo<>(data);
       if(index==0){//añadiendo el nuevo nodo en la parte frontal de la lista
           newNodo.setNext(head);
           head=newNodo;
       }else{
           for(int i=1;i<index;i++)
               apt=apt.getNext();
           newNodo.setNext(apt.getNext());
           apt.setNext(newNodo);
       }
       if(index==count)
           cola=newNodo;//apuntador cola apunta al nuevo nodo
       count++;       
    }

    @Override
    public void clear() {
        head=cola=null;        
        count=0;
    }

    @Override
    public boolean contain(E data) {
        boolean resultado=true;
        int index=indexOf(data);
        if(index==NOT_FOUND)
            resultado=false;
        return resultado;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        revisaPosAcceso(index);        
        Nodo<E> apt=head;
        for(int i=0;i<index;i++)
            apt=apt.getNext();
        return apt.getData();
    }

    private void revisaPosInsercion(int index){
        if(index<0)
            throw new IndexOutOfBoundsException("Indice negativo es invalido");
        else if(index>size())
            throw new IndexOutOfBoundsException(index+" es mayor al permitido "+ size());
    }
     private void revisaPosAcceso(int index){
        if(size()==0)
            throw new IndexOutOfBoundsException("Indice "+ index+" es ivalido. Lista vacia");
        else if(index<0)
            throw new IndexOutOfBoundsException("Indice negativo "+ index+" es ivalido");
        else if(index > size()-1)
            throw new IndexOutOfBoundsException("Indice "+ index+" es mayor al limite superior valido "+ (size()-1));
    }
     
    @Override
    public int indexOf(E data) {
        int index=0;
        Nodo<E> apt=head;
        while(index<count && !apt.getData().equals(data)){
            index++;
            apt=apt.getNext();
        }
        if(index==count)
            index=NOT_FOUND;
        return index;            
    }

    @Override
    public boolean isEmpty() {
        return count==0;
    }

    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        revisaPosAcceso(index);
        Nodo<E> nodoDelete;
        Nodo<E> apt=head;
        if(index==0){//si el nodo a eliminar se encuentra en la parte frontal de la lista
            nodoDelete=apt;
            head=head.getNext();
            if(head==null)
                cola=null;
        }else{
            for(int i=1;i<index;i++)
                apt=apt.getNext();
            nodoDelete=apt.getNext();
            apt.setNext(nodoDelete.getNext());
            if(apt.getNext()==null)
                cola=apt;
        }
        count--;
        return nodoDelete.getData();        
    }

    @Override
    public boolean delete(E data) {
        boolean resultado=false;
        Nodo<E> apt=head;
        Nodo<E> nodoDelete=null;
        while(apt!=null && !apt.getData().equals(data)){
            nodoDelete=apt;
            apt=apt.getNext();
        }
        if(apt!=null){
            if(nodoDelete==null){
                head=head.getNext();
                if(head==null)
                    cola=null;
            }else{
                nodoDelete.setNext(apt.getNext());
                if(nodoDelete.getNext()==null)
                    cola=nodoDelete;
            }
            count--;
            resultado=true;
        }
        return resultado;
    }

    @Override
    public E modify(int index, E data) throws IndexOutOfBoundsException {
        revisaPosAcceso(index);
        Nodo<E> apt=head;
        for(int i=0; i<index;i++)
            apt=apt.getNext();
        E anterior=apt.getData();
        apt.setData(data);
        return anterior;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public IteratorTDA<E> iterador() {
        return new Iterador(head);
    }
    
    @Override
    public String toString() {
        StringBuilder str=new StringBuilder();
        IteratorTDA<E> it=iterador();
        while(it.hasNext()){
            if(!str.isEmpty())
                str.append("->");
            str.append(it.next().toString());
        }
        return str.toString();
    }
    
}

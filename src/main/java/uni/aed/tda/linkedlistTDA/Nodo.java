package uni.aed.tda.linkedlistTDA;

public class Nodo<E> {
    private E data;
    private Nodo next;

    public Nodo(E data) {
        this.data = data;
        this.next=null;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(Nodo<E> next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public Nodo getNext() {
        return next;
    }
    
    
}


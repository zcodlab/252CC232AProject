package uni.aed.tda.listTDA;

public interface ListTDA<E> {
    public void add(E data);//añadir 1 elemento a la lista
    public void add(int index, E data) throws IndexOutOfBoundsException;
    public void clear();//limpiar la lista
    public boolean contain(E data);//verificar si la lista contiene la data
    public E get(int index) throws IndexOutOfBoundsException;//recuperar la data segun posicion en la lista
    public int indexOf(E data);//retorna la posicion de la data
    public boolean isEmpty();//determinar si la lista esta vacia
    public E delete(int index)  throws IndexOutOfBoundsException;
    public boolean delete(E data);//eliminar en la lista, dada la data
    public E modify(int index, E data) throws IndexOutOfBoundsException;
    public int size();//retorna el # de elementos de la lista
}

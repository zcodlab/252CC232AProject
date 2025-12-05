package uni.aed.tda.treeTDA;

import uni.aed.tda.linkedlistTDA.LinkedListTDA;
import uni.aed.tda.listTDA.IteratorTDA;
import uni.aed.tda.listTDA.ListTDA;
import uni.aed.tda.queueTDA.LinkedQueueTDA;
import uni.aed.tda.queueTDA.QueueTDA;

public class BstTDA<E extends Comparable<E>> {    
    public static final int NOT_FOUND=-1;
    public static final int IS_EMPTY=0;
    public static final int FOUND=1;    
    protected BstNodeTDA<E> root;
    
    public BstTDA() {
        this.root = null;
    }      

    public BstNodeTDA<E> getRoot() {
        return root;
    }
    
    public void add(E e){
        BstNodeTDA<E> p = root;
        BstNodeTDA<E> prev = null;
        while(p!=null){
            prev=p;
            if(p.getKey().compareTo(e)<0)
                p=p.getRight();
            else    
                p=p.getLeft();
        }
        if(root==null)
            root=new BstNodeTDA(e);
        else if(prev.getKey().compareTo(e) < 0)
            prev.setRight(new BstNodeTDA(e));
        else
            prev.setLeft(new BstNodeTDA(e));
        
        updateHeightsAndFB(root);
    }    
    
    //Eliminacion por fusion(simetrico)
    public int deleteByMerging(E e ){
        BstNodeTDA<E> tmp;        
        BstNodeTDA<E> node,p=root,prev=null;        
        while(p!=null && p.getKey().compareTo(e)!=0){
            prev=p;
            if(p.getKey().compareTo(e)<0)
                p=p.getRight();
            else
                p=p.getLeft();
        }
        node=p;
        if(p!=null && p.getKey().compareTo(e)==0){
            if(node.getRight()==null)
                node=node.getLeft();
            else if (node.getLeft()==null)
                node=node.getRight();
            else{
                tmp=node.getLeft();
                while(tmp.getRight()!=null){
                    tmp=tmp.getRight();
                }
                tmp.setRight(node.getRight());
                node=node.getLeft();
            }
            if(p==root)
                root=node;
            else if(prev.getLeft()==p)
                prev.setLeft(node);
            else
                prev.setRight(node);
        }
        else if(root!=null)
            return NOT_FOUND;
            //System.out.println("El valor no se encuentra en el arbol");
        else
            return IS_EMPTY;
            //System.out.println("El arbol esta vacio");
        updateHeightsAndFB(root);
        return FOUND;
    }
    
    //Eliminacion por copiado(simetrico)
    public int deleteByCopying(E e){
        BstNodeTDA<E> tmp;
        BstNodeTDA<E> node,p=root,prev=null, previous;
        //buscamos el nodo a eliminar
        while(p!=null && p.getKey().compareTo(e)!=0){
            prev=p;
            if(p.getKey().compareTo(e)<0)
                p=p.getRight();
            else
                p=p.getLeft();
        }
        node=p;
        if(p!=null && p.getKey()==e){//encontro el elemento a eliminar
            if(node.getRight()==null)//no tiene hijo derecho
                node=node.getLeft();
            else if (node.getLeft()==null)//no tiene hijo izq
                node=node.getRight();
            else{//el nodo tiene dos hijos
                tmp=node.getLeft();
                previous=node;
                while(tmp.getRight()!=null){//rama derecha de la rama izq del nodo a eliminar
                    previous=tmp;
                    tmp=tmp.getRight();
                }
                node.setKey(tmp.getKey());//copiamos el nodo derecho mas extremo de la rama izq del nodo con dos hijos a eliminar
                if(previous==node)
                    previous.setLeft(tmp.getLeft());
                else
                    previous.setRight(tmp.getLeft());
            }
            if(p==root)
                root=node;
            else if(prev.getLeft()==p)
                prev.setLeft(node);
            else
                prev.setRight(node);
        }
        else if(root!=null)//no encontro el elemento a elimnar en el arbol
            return NOT_FOUND;            
        else
            return IS_EMPTY;
            
        updateHeightsAndFB(root);
        return FOUND;
    }
    
    // Eliminacion por fusion (asimetricamente: usa el sucesor)
    public int deleteByMergingAsymmetric(E e) {
        BstNodeTDA<E> node = root, prev = null;
        // Buscar el nodo a eliminar
        while (node != null && node.getKey().compareTo(e) != 0) {
            prev = node;
            if (e.compareTo(node.getKey()) < 0)
                node = node.getLeft();
            else
                node = node.getRight();
        }

        if (node == null) {
            if (root == null) return IS_EMPTY;
            return NOT_FOUND;
        }

        BstNodeTDA<E> tmp;
        // Caso 1: sin hijo derecho
        if (node.getRight() == null)
            node = node.getLeft();        
        // Caso 2: sin hijo izquierdo
        else if (node.getLeft() == null)
            node = node.getRight();        
        // Caso 3: dos hijos -> fusion asimetrica
        else {
            tmp = node.getRight(); // vamos al subarbol derecho
            // buscar el sucesor: el mas pequeno -> ir a la izquierda
            while (tmp.getLeft() != null)
                tmp = tmp.getLeft();
            // conectar el subarbol izquierdo en el punto encontrado
            tmp.setLeft(node.getLeft());
            // el nuevo subarbol fusionado sera el derecho
            node = node.getRight();
        }
        // Reemplazar puntero del padre
        if (node == root)
            root = node;        
        else if (prev.getLeft() != null && ((Comparable)(prev.getLeft().getKey())).compareTo(e) == 0)
            prev.setLeft(node);        
        else
            prev.setRight(node);
        
        updateHeightsAndFB(root);
        return FOUND;
    }
    
    // Eliminacion por copiado (asimetricamente)
    public int deleteByCopyingAsymmetric(E e){
        BstNodeTDA<E> tmp;
        BstNodeTDA<E> node, p = root, prev = null, previous;

        // Buscamos el nodo a eliminar
        while (p != null && p.getKey().compareTo(e) != 0) {
            prev = p;
            if (p.getKey().compareTo(e) < 0)
                p = p.getRight();
            else
                p = p.getLeft();
        }

        node = p;
        // Si encontramos el elemento
        if (p != null && p.getKey().compareTo(e) == 0) {
            // Caso 1: sin hijo derecho
            if (node.getRight() == null)
                node = node.getLeft();
            // Caso 2: sin hijo izquierdo
            else if (node.getLeft() == null)
                node = node.getRight();
            // Caso 3: tiene dos hijos — eliminacion por copiado asimetrica
            else {
                tmp = node.getRight();    // vamos al subarbol derecho
                previous = node;
                // buscamos el minimo del subarbol derecho (sucesor)
                while (tmp.getLeft() != null) {
                    previous = tmp;
                    tmp = tmp.getLeft();
                }
                // copiamos la clave del sucesor al nodo a eliminar
                node.setKey(tmp.getKey());
                // reenganchamos el subarbol del sucesor
                if (previous == node)
                    previous.setRight(tmp.getRight());
                else
                    previous.setLeft(tmp.getRight());
            }

            // Ajustamos el padre
            if (p == root)
                root = node;
            else if (prev.getLeft() == p)
                prev.setLeft(node);
            else
                prev.setRight(node);
        }
        else if (root != null)
            return NOT_FOUND;
        else
            return IS_EMPTY;
        
        updateHeightsAndFB(root);
        return FOUND;
    }
    
    public BstNodeTDA<E> search(E e){
        return search(root,e);
    }
    public BstNodeTDA<E> search(BstNodeTDA<E> p, E e){
        while(p!=null){            
            if(e.compareTo(p.getKey())==0)
                return p;
            else if(e.compareTo(p.getKey())<0)
                p=p.getLeft();
            else
                p=p.getRight();
        }
        return null;
    }
    
    public void visit(BstNodeTDA<E> p,StringBuilder str){
        if(p==null){
            str.append(NOT_FOUND);
            return;
        }
        if (!str.isEmpty()) 
            str.append(",");  
        str.append(p.getKey());        
    }
    
    public void visit(BstNodeTDA<E> p,StringBuilder str, String patron){
        if(p==null){
            str.append(NOT_FOUND);
            return;
        }
        if (!str.isEmpty()) 
            str.append(patron);  
        str.append(p.getKey());        
    }
    //LVR
    public void inorder(StringBuilder str, String patron){
        inorder(root,str,patron);
    }
    private void inorder(BstNodeTDA<E> p,StringBuilder str, String patron){
        if(p!=null){
            inorder(p.getLeft(),str,patron);
            visit(p,str,patron);
            inorder(p.getRight(),str,patron);
        }        
    }
    
     //Resuelve 4PC:VisualizarArbol Final: LVR inorder
    public BstTDA<E> inorder(){
        BstTDA<E> bst=new BstTDA<>();
        ListTDA<E> lista=new LinkedListTDA<>();
        inorder(root,lista);
        IteratorTDA<E> it=lista.iterador();        
        while(it.hasNext()){            
            E e=it.next();    
            bst.add(e);
        }
        return bst;
    }
    private void inorder(BstNodeTDA<E> p,ListTDA<E> l){
        if(p!=null){
            inorder(p.getLeft(),l);
            l.add(p.getKey());            
            inorder(p.getRight(),l);}
    }
    //LVR inorder
    public void inorder(StringBuilder str){
        preorder(root,str);
    }
    public void inorder(BstNodeTDA<E> p,StringBuilder str){//VLR
        if(p!=null){            
            preorder(p.getLeft(),str);
            visit(p,str);
            preorder(p.getRight(),str);}
    }
    
    //VLR preorder
    public void preorder(StringBuilder str){
        preorder(root,str);
    }
    public void preorder(BstNodeTDA<E> p,StringBuilder str){//VLR
        if(p!=null){
            visit(p,str);
            preorder(p.getLeft(),str);
            preorder(p.getRight(),str);}
    }
    
    //LRV postorder
    public void postorder(StringBuilder str){//LRV
        postorder(root,str);
    }
    public void postorder(BstNodeTDA<E> p,StringBuilder str){//LRV
        if(p!=null){            
            postorder(p.getLeft(),str);
            postorder(p.getRight(),str);
            visit(p,str);   
        }         
    }
     //recorrido primero en amplitud
    public void breadthFirst(StringBuilder str){
        BstNodeTDA<E> p=root;
        QueueTDA<BstNodeTDA> queue= new LinkedQueueTDA();
        if(p!=null){
            queue.enqueue(p);
            while(!queue.isEmpty()){
                p=queue.dequeue();
                visit(p,str);
                if(p.getLeft()!=null)
                    queue.enqueue(p.getLeft());
                if(p.getRight()!=null)
                    queue.enqueue(p.getRight());                    
            }
        }
    }    
    
    private int updateHeightsAndFB(BstNodeTDA<E> node) {
        if (node == null) return 0;

        int leftH = updateHeightsAndFB(node.getLeft());
        int rightH = updateHeightsAndFB(node.getRight());

        node.setFb(rightH-leftH);

        return Math.max(leftH, rightH) + 1;
    }

    
    // Calcular la Longitud de Ruta Interna (IPL)
    public long calcularIPL() {
        return calcularIPLRecursivo(root, 0);
    }

    private long calcularIPLRecursivo(BstNodeTDA<E> nodo, long nivel) {
        if (nodo == null) return 0;
        boolean esHoja = (nodo.getLeft() == null && nodo.getRight() == null);
        long suma = 0;
        // Solo sumar si NO es hoja
        if (!esHoja)
            suma += nivel;        

        return suma
             + calcularIPLRecursivo(nodo.getLeft(), nivel + 1)
             + calcularIPLRecursivo(nodo.getRight(), nivel + 1);
    }

    public long calculateHeight() {
        return calculateHeightRecursivo(root);
    }
    // Metodo para calcular la altura del arbol de manera recursiva
    private long calculateHeightRecursivo(BstNodeTDA<E> node) {
        if (node == null) return 0; // Si el nodo es nulo, su altura es 0
        // Retorna la altura maxima entre el subarbol izquierdo y derecho
        return 1 + Math.max(calculateHeightRecursivo(node.getLeft()), calculateHeightRecursivo(node.getRight()));
    }
    
    public long countNodes() {
        return countNodesRecursivo(root);
    }
    // Metodo para contar el numero total de nodos en el arbol
    private long countNodesRecursivo(BstNodeTDA<E> node) {
        if (node == null) return 0; // Si el nodo es nulo, no hay nodos
        // Retorna 1 (el nodo actual) mas el conteo de los nodos en los subarboles izquierdo y derecho
        return 1 + countNodesRecursivo(node.getLeft()) + countNodesRecursivo(node.getRight());
    }
    
    public boolean isBalanced() {
        return isBalancedRecursivo(root);
    }
    // Metodo para verificar si el arbol esta balanceado
    private boolean isBalancedRecursivo(BstNodeTDA<E>  node) {
        if (node == null) return true; // Si el nodo es nulo, el arbol es balanceado
        // Compara las alturas de los subarboles izquierdo y derecho
        long leftHeight = calculateHeightRecursivo(node.getLeft());
        long rightHeight = calculateHeightRecursivo(node.getRight());
        // El arbol esta balanceado si la diferencia de alturas no es mayor a 1 y ambos subarboles son balanceados
        return Math.abs(rightHeight-leftHeight) <= 1 && isBalancedRecursivo(node.getLeft()) && isBalancedRecursivo(node.getRight());
    }
    @Override
    public String toString() {        
        return root.toString();        
    }   
}

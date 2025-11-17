package uni.aed.tda.treeTDA;

import uni.aed.tda.queueTDA.LinkedQueueTDA;
import uni.aed.tda.queueTDA.QueueTDA;

public class BstTDA<E extends Comparable<E>> {
    private static final int NOT_FOUND=-1;
    private static final int FOUND=1;
    private static final int IS_EMPTY=0;
    private BstNodeTDA<E> root;

    public BstTDA() {
        this.root=null;
    }
    
    public void add(E e){
        BstNodeTDA<E> p =root;
        BstNodeTDA<E> prev =null;
        while(p!=null){
            prev=p;
            if(p.getKey().compareTo(e)<0)
                p=p.getRight();
            else
                p=p.getLeft();
        }
        if(root==null)
            root=new BstNodeTDA<>(e);
        else if(prev.getKey().compareTo(e)<0)
            prev.setRight(new BstNodeTDA<>(e));
        else
            prev.setLeft(new BstNodeTDA<>(e));            
    }
    
    public BstNodeTDA<E> search(E e){
        return search(root,e);
    }
    private BstNodeTDA<E> search(BstNodeTDA<E> p, E e){
        while(p!=null){
             if(p.getKey().compareTo(e)==0)//son iguales
                 return p;
             else if(p.getKey().compareTo(e)<0)
                    p=p.getRight();
             else
                    p=p.getLeft();
        }
        return null;
    }
    public void visit(BstNodeTDA<E> p, StringBuilder str){
        if(p==null){
            str.append(NOT_FOUND);
            return;
        }
        if(!str.isEmpty())
            str.append("\n");
        str.append(p.getKey());
    }
    //recorrido del arbol - primero en profundidad
    //inorder(LVR)
    public void inorder(StringBuilder str){
        inorder(root,str);
    }
    private void inorder(BstNodeTDA<E> p, StringBuilder str){
        if(p!=null){
            inorder(p.getLeft(),str);//(L)
            visit(p,str);           //(V)
            inorder(p.getRight(),str);//(R)            
        }
    }
    
    //preorder(VLR)
    public void preorder(StringBuilder str){
        preorder(root,str);
    }
    private void preorder(BstNodeTDA<E> p, StringBuilder str){
        if(p!=null){
            visit(p,str);           //(V)
            preorder(p.getLeft(),str);//(L)            
            preorder(p.getRight(),str);//(R)            
        }
    }
    
    //postorder(LRV)
    public void postorder(StringBuilder str){
        postorder(root,str);
    }
    private void postorder(BstNodeTDA<E> p, StringBuilder str){
        if(p!=null){            
            postorder(p.getLeft(),str);//(L)            
            postorder(p.getRight(),str);//(R)            
            visit(p,str);           //(V)
        }
    }
    //recorrido primero en amplitud
    public void breadthFirst(StringBuilder str){
        BstNodeTDA<E> p=root;
        QueueTDA<BstNodeTDA<E>> q= new LinkedQueueTDA<>();
        if(p!=null){
            q.enqueue(p);
            while(!q.isEmpty()){
                p=q.dequeue();
                visit(p,str);
                if(p.getLeft()!=null)
                    q.enqueue(p.getLeft());
                if(p.getRight()!=null)
                    q.enqueue(p.getRight());
            }
        }
    }
    
    //Eliminacion por copiado
    public int deleteByCopying(E e){
        BstNodeTDA<E> tmp;
        BstNodeTDA<E> node,p=root,prev=null,previous;
        //buscamos el nodo a eliminar
        while(p!=null && p.getKey().compareTo(e)!=0){
            prev=p; //reservamos p
            if(p.getKey().compareTo(e)<0)
                p=p.getRight();
            else
                p=p.getLeft();            
        }
        node=p;
        if(p!=null && p.getKey()==e){//encontro el elemento a eliminar
            if(node.getRight()==null)//verificamos si no tiene hijo derecho
                node=node.getLeft();
            else if(node.getLeft()==null)//verificamos si no tiene hijo izquierdo
                node=node.getRight();
            else{//el nodo tiene dos hijos
                tmp=node.getLeft(); //reservamos el nodo izq(uno de los hijos)
                previous=node;
                //ubicamos la rama derecha de la rama izq del nodo a eliminar
                while(tmp.getRight()!=null){
                    previous=tmp;
                    tmp=tmp.getRight();
                }
                //copiamos el nodo derecho mas extremo de la rama izq del nodo a eliminar
                node.setKey(tmp.getKey());
                if(previous==node)
                    previous.setLeft(tmp.getLeft());
                else
                    previous.setRight(tmp.getLeft());
            }
            if(p==root)
                root=node;
            else if(p==prev.getLeft())
                prev.setLeft(node);
            else
                prev.setRight(node);
        }else if(root!=null)//si no encontro el elemento a eliminar en el arbol
            return NOT_FOUND;
        else
            return IS_EMPTY;        
        return FOUND;        
    }
    
    //Eliminacion por copiado
    public int deleteByMerging(E e){
        BstNodeTDA<E> tmp;
        BstNodeTDA<E> node,p=root,prev=null;
        //buscamos el nodo a eliminar
        while(p!=null && p.getKey().compareTo(e)!=0){
            prev=p; //reservamos p
            if(p.getKey().compareTo(e)<0)
                p=p.getRight();
            else
                p=p.getLeft();            
        }        
        node=p;
        if(p!=null && p.getKey().compareTo(e)==0){//encontro el elemento a eliminar
            if(node.getRight()==null)
                node=node.getLeft();
            else if (node.getLeft()==null)
                node=node.getRight();
            else{//tiene los dos hijos
                tmp=node.getLeft();
                while(tmp.getRight()!=null)
                    tmp=tmp.getRight();                
                //se ubico el nodo derecho mas extremo de la rama izq 
                //y se le establece como node derecho el nodo derecho 
                //del nodo a eliminar(sobreposicion)
                tmp.setRight(node.getRight());
                node=node.getLeft();
            }
            if (p==root)
                root=node;
            else if (prev.getLeft()==p)
                prev.setLeft(node);
            else
                prev.setRight(node);
        }else if (root!=null)
            return NOT_FOUND;
        else
            return IS_EMPTY;
        return FOUND;        
    }

    @Override
    public String toString() {
        return root.toString();
    }  
}

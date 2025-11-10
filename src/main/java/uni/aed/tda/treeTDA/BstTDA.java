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
            str.append(",");
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

    @Override
    public String toString() {
        return root.toString();
    }  
}

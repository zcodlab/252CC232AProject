package uni.aed.tda.treeTDA.AVL;

public class AvlTree<E extends Comparable<E>> {
    private static final int NOT_FOUND=-1;    
    AvlTreeNode<E> root;    
    
    public void add(E key){
        if(findAvlTreeNode(root,key)==null){
            root=BSTInsert(root,key);                        
        }
    }
    private AvlTreeNode<E> BSTInsert(AvlTreeNode<E> node, E key){
        if(node==null)
            return new AvlTreeNode(key);
        else if(key.compareTo(node.getKey()) < 0)
            node.setLeft(BSTInsert(node.getLeft(),key));
        else
            node.setRight(BSTInsert(node.getRight(),key));
        //proceso para validar si el arbol esta balanceado
        return balanceTree(node);
    }
    
    private AvlTreeNode<E> findAvlTreeNode(AvlTreeNode<E> node,E key){
        if(node==null || key.compareTo(node.getKey())==0)
            return node;
        if(key.compareTo(node.getKey()) < 0)
            return findAvlTreeNode(node.getLeft(),key);
        else
            return findAvlTreeNode(node.getRight(),key);
    }
    
    private AvlTreeNode<E> balanceTree(AvlTreeNode<E> node){
        if(node == null) return null;

        updateHeight(node);
        updateFB(node);

        int fb = node.getFb();
        
        if(fb > 1){
            updateHeight(node.getRight());
            updateFB(node.getRight());

            if(node.getRight() != null && node.getRight().getFb() < 0){
                node.setRight(rotateRight(node.getRight())); // RL
            }
            return rotateLeft(node); // RR
        }
        
        if(fb < -1){
            updateHeight(node.getLeft());
            updateFB(node.getLeft());

            if(node.getLeft() != null && node.getLeft().getFb() > 0){
                node.setLeft(rotateLeft(node.getLeft())); // LR
            }
            return rotateRight(node); // LL
        }

        return node;
    }
    
    private int Height(AvlTreeNode<E> node){
        return (node == null) ? -1 : node.getHeight();
    }


    private void updateHeight(AvlTreeNode<E> node){
        if(node == null) return;
        int h = 1 + Math.max(Height(node.getLeft()), Height(node.getRight()));
        node.setHeight(h);
    }
    
    private void updateFB(AvlTreeNode<E> node){
        if(node == null) return;
        int fb = Height(node.getRight()) - Height(node.getLeft());
        node.setFb(fb);
    }
    
    private AvlTreeNode<E> rotateLeft(AvlTreeNode<E> x){
        AvlTreeNode<E> y = x.getRight();
        AvlTreeNode<E> T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        updateHeight(x);
        updateFB(x);

        updateHeight(y);
        updateFB(y);

        return y;
    }

    private AvlTreeNode<E> rotateRight(AvlTreeNode<E> y){
        AvlTreeNode<E> x = y.getLeft();
        AvlTreeNode<E> T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        updateHeight(y);
        updateFB(y);

        updateHeight(x);
        updateFB(x);

        return x;
    }

    
    public String PreOrder(){
        return PreOrder(root);
    }
    
    private String PreOrder(AvlTreeNode<E> node){//VLR
        StringBuilder tree = new StringBuilder();
        if(node!=null){
            tree.append(node.getKey());
            tree.append(",");
            tree.append(PreOrder(node.getLeft()));
            tree.append(PreOrder(node.getRight()));            
        }
        return tree.toString();
            
    }
    
    public String InOrder(){
        return InOrder(root);
    }
    
    private String InOrder(AvlTreeNode<E> node){//VLR
        StringBuilder tree = new StringBuilder();
        if(node!=null){            
            tree.append(InOrder(node.getLeft()));
            tree.append(node.getKey()).append(",");            
            tree.append(InOrder(node.getRight()));            
        }
        return tree.toString();
            
    }
    
    public int search(E key){
        if(findAvlTreeNode(root, key)==null)
            return 0;
        else
            return 1;
    }
    
    public AvlTreeNode<E> searchNode(E key){
        AvlTreeNode<E> node = findAvlTreeNode(root, key);
        if(node==null)
            return null;
        else
            return node;
    }
    
    public String delete(E key){
        if(findAvlTreeNode(root, key)!=null){
            root=Remove(root, key);          
            return root.toString();            
        }else
            return String.valueOf(NOT_FOUND);
    }
    private AvlTreeNode<E> Remove(AvlTreeNode<E> node, E key){
        if(node==null)
            return node;
        else if(key.compareTo(node.getKey()) < 0)
            node.setLeft(Remove(node.getLeft(),key));
        else if(key.compareTo(node.getKey()) > 0)
            node.setRight(Remove(node.getRight(),key));
        else{
            if(node.getRight()==null)
                node=node.getLeft();
            else if(node.getLeft()==null)
                node=node.getRight();
            else{
                AvlTreeNode<E> temp=Succesor(node.getRight());
                node.setKey(temp.getKey());
                node.setRight(Remove(node.getRight(),node.getKey()));
            }                           
        }
        if(node==null)
            return node;
        else
            return balanceTree(node); 
    }
    private AvlTreeNode<E> Succesor(AvlTreeNode<E> node){
        if(node.getLeft()!=null)
            return Succesor(node.getLeft());
        else
            return node;
    }

    @Override
    public String toString() {
        return root.toString();    
    }
}   


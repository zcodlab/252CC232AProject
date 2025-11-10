package uni.aed.tda.treeTDA;

public class BstTDAMain {
    StringBuilder str=new StringBuilder();
    public static void main(String[] args){
        BstTDAMain bstTDAMain=new BstTDAMain();       
        bstTDAMain.testBstTDAInteger();
    }
    private void testBstTDAInteger(){
        BstTDA<Integer> tree=new BstTDA<>();
        tree.add(15);
        tree.add(4);
        tree.add(20);
        tree.add(17);
        tree.add(19);
        System.out.println("Visualizando el arbol");
        System.out.println(tree.toString());
        
        System.out.println("Realizando una llamda inorder(LVR)");
        tree.inorder(str);
        System.out.println(str.toString());
    }
}

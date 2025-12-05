package uni.aed.tda.treeTDA;

public class BstNodeTDA<E> {
    private E key;
    private BstNodeTDA<E> left, right;
    private int fb; //FACTOR DE BALANCE
    private static final String SEPARADOR = "\n";

    public BstNodeTDA() {
        left = right = null;
        fb = 0;
    }

    public BstNodeTDA(E key) {
        this.key = key;
        this.left = this.right = null;
        this.fb = 0;
    }

    public void setKey(E key) { this.key = key; }
    public void setLeft(BstNodeTDA left) { this.left = left; }
    public void setRight(BstNodeTDA right) { this.right = right; }
    public void setFb(int fb) { this.fb = fb; }

    public E getKey() { return key; }
    public BstNodeTDA getLeft() { return left; }
    public BstNodeTDA getRight() { return right; }
    public int getFb() { return fb; }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(key).append(" (fb=").append(fb).append(")");
        buffer.append(SEPARADOR);

        if (left != null && right != null) {
            left.print(buffer, childrenPrefix + "---", childrenPrefix + "   ");
            right.print(buffer, childrenPrefix + "+++", childrenPrefix + "|   ");
        } else if (left != null)
            left.print(buffer, childrenPrefix + "---", childrenPrefix + "   ");
        else if (right != null)
            right.print(buffer, childrenPrefix + "+++", childrenPrefix + "   ");
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        print(buffer, "", "");
        return buffer.toString();
    }
}

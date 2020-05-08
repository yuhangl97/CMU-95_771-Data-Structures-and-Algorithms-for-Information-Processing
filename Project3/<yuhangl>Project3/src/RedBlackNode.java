import java.math.BigInteger;

public class RedBlackNode {
    public static final int BLACK = 0;
    public static final int RED = 1;
    private int color;
    private String data;
    private BigInteger value;
    private RedBlackNode p;
    private RedBlackNode lc;
    private RedBlackNode rc;


    /**
     * Construct a RedBlackNode with data, color, parent pointer, left child pointer and right child pointer.
     *
     * runtime: Θ(1)
     **/
    public RedBlackNode(String data, BigInteger value, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
        this.data = data;
        this.color = (color == BLACK)? BLACK:RED;
        this.p = p;
        this.lc = lc;
        this.rc = rc;
        this.value = value;
    }


    /**
     * Return the color value (RED or BLACK)
     *
     * runtime: Θ(1)
     **/
    public int getColor() {
        return (color == BLACK)? BLACK:RED;
    }


    /**
     * The getData() method returns the data in the node.
     *
     * runtime: Θ(1)
     **/
    public String getData() {
        return data;
    }


    /**
     * The getLc() method returns the left child of the RedBlackNode.
     *
     * runtime: Θ(1)
     **/
    public RedBlackNode getLc() {
        return lc;
    }


    /**
     * The getP() method returns the parent of the RedBlackNode.
     *
     * runtime: Θ(1)
     **/
    public RedBlackNode getP() {
        return p;
    }


    public BigInteger getValue() {
        return value;
    }


    /**
     * The getRc() method returns the right child of the RedBlackNode.
     *
     * runtime: Θ(1)
     **/
    public RedBlackNode getRc() {
        return rc;
    }


    /**
     * The setColor() method sets the color of the RedBlackNode.
     * @param color
     *   is either RED or BLACK
     * @postcondition
     *   The color of the node is updated.
     *
     * runtime: Θ(1)
     **/
    public void setColor(int color) {
        this.color = (color == BLACK)? BLACK:RED;
    }


    /**
     * The setData() method sets the data or key of the RedBlackNode.
     * @param data
     *   is an int holding a node's data value
     * @postcondition
     *   The data of the node is updated.
     *
     * runtime: Θ(1)
     **/
    public void setData(String data) {
        this.data = data;
    }


    /**
     * The setLc() method sets the left child of the RedBlackNode.
     * @param lc
     *   establishes a left child for this node
     * @precondition
     *   lc is RedBlackNode
     * @postcondition
     *   The left child of this node is updated
     *
     * runtime: Θ(1)
     **/
    public void setLc(RedBlackNode lc) {
        this.lc = lc;
    }


    public void setValue(BigInteger value) {
        this.value = value;
    }


    /**
     * The setP() method sets the parent of the RedBlackNode.
     * @param p
     *   establishes a parent pointer for this node
     * @precondition
     *   p is RedBlackNode
     * @postcondition
     *   The parent of this node is updated
     *
     * runtime: Θ(1)
     **/
    public void setP(RedBlackNode p) {
        this.p = p;
    }


    /**
     * The setLc() method sets the right child of the RedBlackNode.
     * @param rc
     *   establishes a right child for this node
     * @precondition
     *   rc is RedBlackNode
     * @postcondition
     *   The right child of this node is updated
     *
     * runtime: Θ(1)
     **/
    public void setRc(RedBlackNode rc) {
        this.rc = rc;
    }
}

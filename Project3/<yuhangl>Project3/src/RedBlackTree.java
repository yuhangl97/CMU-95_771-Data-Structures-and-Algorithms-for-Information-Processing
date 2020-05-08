import java.math.BigInteger;

public class RedBlackTree {
    public static final int BLACK = 0;
    public static final int RED = 1;
    private RedBlackNode NIL;
    private RedBlackNode head;
    private int size;
    private int recentCompares = 0;


    /**
     * @postcondition
     *    This constructor creates an empty RedBlackTree.
     *    It creates a RedBlackNode that represents null.
     *    It sets the internal variable tree to point to this
     *    node. This node that an empty tree points to will be
     *    used as a sentinel node. That is, all pointers in the
     *    tree that would normally contain the value null, will instead
     *    point to this sentinel node.
     *
     * runtime: Θ(1)
     **/
    public RedBlackTree() {
        this.NIL = new RedBlackNode(null, null, BLACK, null, null, null);
        this.head = new RedBlackNode(null, null, BLACK, NIL, NIL, NIL);
        this.size = 0;
    }


    /**
     * The method return the number of values inserted into the tree.
     * @precondition
     *   lc is RedBlackNode
     * @postcondition
     *   Got the number of values inserted into the tree.
     *
     * runtime: Θ(1)
     **/
    public int getSize() {
        return size;
    }


    /**
     * It does not make sense to talk about cases
     * @precondition
     *   t is RedBlackNode that is in the Red Black Tree
     * @postcondition
     *   Perform an inorder traversal of the tree.
     **/
    public void inOrderTraversal(RedBlackNode t) {
        if (t.getLc().getData() != null)
            inOrderTraversal(t.getLc());
        printHelper(t);
        if (t.getRc().getData() != null)
            inOrderTraversal(t.getRc());
    }


    /**
     * print a node in the right format
     *
     * runtime: Θ(1)
     **/
    private void printHelper(RedBlackNode t) {
        String color = t.getColor() == 0? "BLACK":"RED";
        String data = t.getData() == null? "-1":t.getData();
        String pData = t.getP().getData() == null? "-1":t.getP().getData();
        String lData = t.getLc().getData() == null? "-1":t.getLc().getData();
        String rData = t.getRc().getData() == null? "-1":t.getRc().getData();
        System.out.println("[data = " + data + ":Color = " + color +
                ":Parent = " + pData + ": LC = " + lData +
                ": RC = " + rData + "]");
    }

    /**
     * @postcondition
     *   Perform an inorder traversal of the tree.
     *
     * runtime: Θ(N)
     **/
    public void inOrderTraversal() {
        inOrderTraversal(head);
    }


    /**
     * It does not make sense to talk about cases
     * @precondition
     *   t is RedBlackNode that is in the Red Black Tree
     * @postcondition
     *   Perform an reverse order traversal of the tree.
     **/
    public void reverseOrderTraversal(RedBlackNode t) {
        if (t.getRc().getData() != null)
            reverseOrderTraversal(t.getRc());
        printHelper(t);
        if (t.getLc().getData() != null)
            reverseOrderTraversal(t.getLc());
    }


    /**
     * @postcondition
     *   Perform an reverse order traversal of the tree.
     *
     * runtime: Θ(N)
     **/
    public void reverseOrderTraversal() {
        reverseOrderTraversal(head);
    }


    /**
     * The insert() method places a data item into the tree.
     * @param key
     *   is an integer to be inserted
     * @precondition
     *   memory is available for insertion
     * @postcondition
     *   the new node is inserted to the red black tree
     *
     * runtime: Θ(1) in the best case;
     * runtime: Θ(log(N)) in the worst case;
     **/
    public void insert(String key, BigInteger value) {
        size++;
        if (head.getData() == null) {
            head.setData(key);
            head.setValue(value);
            return;
        }
        RedBlackNode current = NIL;
        RedBlackNode next = head;
        RedBlackNode valueNode = new RedBlackNode(key, value, RED, current, NIL, NIL);
        while (next != NIL) {
            current = next;
            if (key.compareTo(current.getData()) < 0)
                next = next.getLc();
            else if (key.compareTo(current.getData()) == 0) {
                current.setValue(value);
                return;
            }
            else
                next = next.getRc();
        }
        if (current == NIL)
            head = valueNode;
        else {
            if (valueNode.getData().compareTo(current.getData()) < 0) {
                current.setLc(valueNode);
                RBInsertFixup(current.getLc());
            }
            else {
                current.setRc(valueNode);
                RBInsertFixup(current.getRc());
            }
        }
    }


    /**
     * leftRotate() performs a single left rotation.
     * @param x
     *   the center of the left notation
     * @precondition
     *   the right child of x is not NIL
     *   the parent of the root is NIL
     * @postcondition
     *   x node is already left rotated
     *
     * runtime: Θ(1)
     **/
    public void leftRotate(RedBlackNode x) {
        if (x.getRc() == NIL) return;
        RedBlackNode temp = x.getRc();
        x.setRc(temp.getLc());
        temp.getLc().setP(x);
        temp.setP(x.getP());

        if (x.getP() == NIL)
            head = temp;
        else {
            if (x == x.getP().getLc())
                x.getP().setLc(temp);
            else
                x.getP().setRc(temp);
        }
        temp.setLc(x);
        x.setP(temp);
    }


    /**
     * rightRotate() performs a single right rotation.
     * @param x
     *   the center of the right notation
     * @precondition
     *   the left child of x is not NIL
     *   the parent of the root is NIL
     * @postcondition
     *   x node is already right rotated
     *
     * runtime: Θ(1)
     **/
    public void rightRotate(RedBlackNode x) {
        if (x.getLc() == NIL) return;
        RedBlackNode y = x.getLc();
        x.setLc(y.getRc());
        y.getRc().setP(x);
        y.setP(x.getP());

        if (x.getP() == NIL)
            head = y;
        else {
            if (x == x.getP().getLc())
                x.getP().setLc(y);
            else
                x.getP().setRc(y);
        }
        y.setRc(x);
        x.setP(y);
    }


    /**
     * Fixing up the tree so that Red Black Properties are preserved.
     * @param z
     *   is the new node
     * @precondition
     *   z is a node in the Red Black Tree
     * @postcondition
     *   tree is already fixed up
     *
     * It does not make sense to talk about cases
     **/
    public void RBInsertFixup(RedBlackNode z) {
        while (z.getP().getColor() == RED) {
            if (z.getP() == z.getP().getP().getLc()) {
                RedBlackNode y = z.getP().getP().getRc();
                if (y.getColor() == RED) {
                    z.getP().setColor(BLACK);
                    y.setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    z = z.getP().getP();
                } else {
                    if (z == z.getP().getRc()) {
                        z = z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    rightRotate(z.getP().getP());
                }
            } else {
                RedBlackNode y = z.getP().getP().getLc();
                if (y.getColor() == RED) {
                    z.getP().setColor(BLACK);
                    y.setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    z = z.getP().getP();
                } else {
                    if (z == z.getP().getLc()) {
                        z = z.getP();
                        rightRotate(z);
                    }
                    z.getP().setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    leftRotate(z.getP().getP());
                }
            }
        }
        head.setColor(BLACK);
    }


    /**
     * The boolean contains() returns true if the String v is in the RedBlackTree and false otherwise.
     * It counts each comparison it makes in the variable recentCompares.
     * @param v
     *   the value to search for
     * @precondition
     *   v is a string
     * @postcondition
     *   return true if the tree contains v otherwise false
     *
     * runtime: Θ(1) in the best case;
     * runtime: Θ(log(N)) in the worst case;
     **/
    public boolean contains(String v) {
        recentCompares = 0;
        RedBlackNode cur = head;
        while (cur != NIL) {
            recentCompares++;
            int compare = cur.getData().compareTo(v);
            if (compare < 0)
                cur = cur.getRc();
            else if (compare > 0)
                cur = cur.getLc();
            else
                return true;
        }
        return false;
    }


    /**
     * The method returns the number of comparisons made in last call on the contains method.
     * @precondition
     *   None
     * @postcondition
     *   Returns the most recent compare times
     *
     * runtime: Θ(1)
     **/
    public int getRecentCompares() {
        return recentCompares;
    }


    /**
     * The method closeBy(v) returns a value close to v in the tree. If v is found in the tree it returns v.
     * @param v
     *   the value to search close by for.
     * @precondition
     *   v is a string
     * @postcondition
     *   returns a value close to v in the tree. If v is found in the tree it returns v.
     *
     * runtime: Θ(1) in the best case;
     * runtime: Θ(log(N)) in the worst case;
     **/
    public String closeBy(String v) {
        int smallNum = -1;
        String smallString = null;
        RedBlackNode cur = head;
        while (cur.getData() != null) {
            int compare = cur.getData().compareTo(v);
            if ((Math.abs(compare) < smallNum)||(smallNum == -1)) {
                smallNum = Math.abs(compare);
                smallString = cur.getData();
            }
            if (compare > 0) {
                cur = cur.getLc();
            }
            else if (compare < 0)
                cur = cur.getRc();
            else
                return v;
        }
        return smallString;
    }


    /**
     * This a recursive routine that is used to compute the height of the red black tree.
     * @param t
     *   a pointer to a node in the tree.
     * @precondition
     *   None
     * @postcondition
     *   return the height of the red black tree since a specific node.
     *
     * It does not make sense to talk about cases
     **/
    public int height(RedBlackNode t) {
        if (t.getData() == null) return -1;
        int result = Math.max(height(t.getLc()), height(t.getRc())) + 1;
        return result;
    }


    /**
     * This a recursive routine that is used to compute the height of the red black tree.
     * @precondition
     *   lc is RedBlackNode
     * @postcondition
     *   The left child of this node is updated
     *
     * runtime: Θ(log(N))
     **/
    public int height() {
        return height(head);
    }


    /**
     * Find the biggest one among three numbers
     * @param a,b,c
     *   a, b and c are all int
     * @precondition
     *   a, b and c are all int
     * @postcondition
     *   return the biggest among a, b and c
     *
     * runtime: Θ(1)
     **/
    private int myMax(int a, int b, int c) {
        int result = Math.max(a, b);
        result = Math.max(result, c);
        return result;
    }


    /**
     * The method returns the diameter of the tree
     * @param t
     *   t is the head usually
     * @precondition
     *   t is the node in the red black tree
     * @postcondition
     *   returns the diameter of the tree
     *
     * It dose not make sense to talk about cases
     **/
    public int getDiameter(RedBlackNode t) {
        if (t.getData() == null) return 0;
        int maxDepth = depth(t.getLc()) + depth(t.getRc());
        return myMax(maxDepth, getDiameter(t.getLc()), getDiameter(t.getRc()));
    }


    /**
     * The method returns the depth of a node
     * @param t
     *   t is a node
     * @precondition
     *   t is the node in the red black tree
     * @postcondition
     *   returns the depth of a node in the tree
     *
     * It dose not make sense to talk about cases
     **/
    private int depth(RedBlackNode t) {
        if (t.getData() == null) return 0;
        return 1 + Math.max(depth(t.getLc()), depth(t.getRc()));
    }


    /**
     * The method returns the diameter of the tree
     * @precondition
     *   None
     * @postcondition
     *   returns the diameter of the tree
     *
     * runtime: Θ(log(N))
     **/
    public int getDiameter() {
        return getDiameter(head);
    }


    public RedBlackNode retrieve(String v) {
        RedBlackNode cur = head;
        while (cur != NIL) {
            int compare = cur.getData().compareTo(v);
            if (compare < 0)
                cur = cur.getRc();
            else if (compare > 0)
                cur = cur.getLc();
            else
                return cur;
        }
        return null;
    }


    public BigInteger getValue(String v) {
        return retrieve(v).getValue();
    }
}

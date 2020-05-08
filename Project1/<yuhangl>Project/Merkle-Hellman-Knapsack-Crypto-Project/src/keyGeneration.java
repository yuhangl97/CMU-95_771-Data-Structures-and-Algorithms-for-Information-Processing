import java.math.BigInteger;


public class keyGeneration {
    // Invariant of the keyGeneration class:
    //   1. The w is a superincreasing sequence and the private key.
    //   2. The beta is the public key.
    private SinglyLinkedList w;
    private SinglyLinkedList beta;


    /**
     * Initialize a superincreasing sequence as the private key.
     * @postcondition
     *   This singly linked list contains the specified data and link to the next node.
     *   The data is a big integer.
     **/
    public keyGeneration() {
        w = new SinglyLinkedList();
        BigInteger cur = BigInteger.ONE;
        int length = 640;
        for (int i = 0; i < length; i++) {
            w.addAtEndNode(cur);
            cur = cur.multiply(BigInteger.TWO).add(BigInteger.ONE);
        }
    }


    /**
     * Return the private key of Merkle–Hellman knapsack cryptosystem.
     *
     * runtime: Θ(1)
     **/
    public SinglyLinkedList getW() {
        return w;
    }


    /**
     * Calculate the sum of the private key.
     **/
    public BigInteger getSumW() {
        BigInteger sum = new BigInteger("0");
        ObjectNode cur = w.getObjectAt(0);
        for (int i = 0; i < w.countNodes(); i++) {
            sum = sum.add((BigInteger) cur.getData());
            cur = cur.getLink();
        }
        return sum;
    }


    /**
     * Choose a number q that is greater than the sum and return it.
     **/
    public BigInteger getQ() {
        return getSumW().add(new BigInteger("13"));
    }


    /**
     * Choose a number r that is in the range [1, q) and is coprime to q.
     * Then return it.
     **/
    public BigInteger getR() {
        return new BigInteger("9991111111");
    }


    /**
     * To calculate a public key, generate the sequence β by multiplying
     * each element in w by r mod q.
     **/
    public SinglyLinkedList getBeta() {
        beta = new SinglyLinkedList();
        for (int i = 0; i < w.countNodes(); i++) {
            BigInteger item = (BigInteger) w.getObjectAt(i).getData();
            beta.addAtEndNode((item.multiply(getR())).mod(getQ()));
        }
        return beta;
    }
}
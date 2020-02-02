import java.math.BigInteger;

public class Decryption {
    // Invariant of the Decryption class:
    //   1. The number r that is in the range [1, q) and is coprime to q.
    //   2. The modular inverse of the r.
    //   3. q is a number that is greater than the sum of the private key.
    //   4. The w is a superincreasing sequence and the private key.
    BigInteger r;
    BigInteger reverseR;
    BigInteger q;
    SinglyLinkedList w;


    /**
     * Get the corresponding r, q, w from the keyGeneration class.
     * Calculate the modular inverse of the r.
     **/
    public Decryption()
    {
        keyGeneration key = new keyGeneration();
        r = key.getR();
        q = key.getQ();
        w = key.getW();
        reverseR = r.modInverse(q);
    }


    /**
     * Decode the encrypted string in a singly linked list and return it.
     * @param code
     *   the singly linked list that contains the encrypted string
     * @postcondition
     *   This string contains the data from original client.
     **/
    public String decodeAll(SinglyLinkedList code)
    {
        if (code == null) return null;
        StringBuffer result = new StringBuffer();
        int numOfGroup = 8;
        for (int i = 0; i < code.countNodes(); i++)
        {
            ObjectNode cur = code.getObjectAt(i);
            BigInteger convertBefore = (BigInteger) cur.getData();
            BigInteger convertAfter = convertBefore.multiply(reverseR).mod(q);
            int[] record = {0, 0, 0, 0, 0, 0, 0, 0, };

            for (int j = 7; j != 0; j--)
            {
                ObjectNode test = w.getObjectAt(i*numOfGroup);
                int k = 0;
                while (k < j && test.getLink() != null)
                {
                    k++;
                    test = test.getLink();
                }
                BigInteger num = (BigInteger) test.getData();
                int compare = convertAfter.compareTo(num);
                if (compare == 1 || compare == 0)
                {
                    convertAfter = convertAfter.subtract(num);
                    record[j] = 1;
                }
            }
            int total = 0;
            for (int j = 0; j < 7; j++)
            {
                if (record[7-j] > 0)
                    total += (int) Math.pow(2,j);
            }
            result.append((char) total);
        }

        return result.toString();
    }
}

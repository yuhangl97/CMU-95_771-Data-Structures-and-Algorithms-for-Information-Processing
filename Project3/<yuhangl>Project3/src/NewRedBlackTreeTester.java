import java.math.BigInteger;

public class NewRedBlackTreeTester {
    /**
     * Test function
     **/
    public static void main(String[] args) {
        RedBlackTree testTree = new RedBlackTree();
        BigInteger value = BigInteger.ONE;
        for (int i = 1; i <= 50; i++) {
            String key = "var" + i;
            testTree.insert(key, value);
            value = value.add(BigInteger.ONE);
        }
        String testKey = "var25";
        System.out.println(testKey + ": " + testTree.getValue(testKey));
    }
}

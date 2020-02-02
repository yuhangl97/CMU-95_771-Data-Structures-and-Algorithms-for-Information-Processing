import java.math.BigInteger;


public class Encryption {
    /**
     * Convert the specific character in the corresponding string to a
     * big integer.
     * @param element
     *   the specific character that needs to be converted
     * @param index
     *   the index of the character in the original string
     * @precondition
     *   The element is not empty, otherwise the method returns 0
     * @postcondition
     *   This big integer contains the information of the original character
     **/
    public BigInteger encodeSingle(Character element, int index) {
        BigInteger sum = BigInteger.ZERO;
        if (element == null) return sum;
        int result = element;
        int factor = 128;
        keyGeneration key = new keyGeneration();
        for (int i = 0; i < 8; i++) {
            if (result >= factor) {
                result -= factor;
                SinglyLinkedList beta = key.getBeta();
                BigInteger addition = (BigInteger) key.getBeta().getObjectAt(index * 8 + i).getData();
                sum = sum.add(addition);
            }
            factor /= 2;
        }
        return sum;
    }


    /**
     * Convert the input string to a singly linked list
     * @param input
     *   the string that needs to be converted
     * @precondition
     *   The string is not empty, otherwise the method returns null
     * @postcondition
     *   This singly linked list contains the information of the original string
     **/
    public SinglyLinkedList encodeAll(String input) {
        if (input == null) return null;
        SinglyLinkedList result = new SinglyLinkedList();
        for (int i = 0; i < input.length(); i++)
            result.addAtEndNode(encodeSingle(input.charAt(i), i));
        return result;
    }
}
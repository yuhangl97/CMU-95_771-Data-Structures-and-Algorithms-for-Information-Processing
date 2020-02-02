import java.util.Scanner;

public class MerkleHellmanKnapsackCrypto {
    public static void main(String[] args)
    {
        int length = 81;
        Scanner in = new Scanner(System.in);
        String input = null;
        System.out.println("Enter a string and I will encrypt it as single large integer.");

        // Collect a string whose length is not greater than 80 from the client.
        while (length > 80)
        {
            input = in.nextLine();
            System.out.println("Clear text: ");
            System.out.println(input);
            System.out.println("Number of clear text bytes = " + input.length());

            if (input.length() > 80)
            {
                length = input.length();
                System.out.println("Please enter a string of up to 80 characters.");
                System.out.println("Enter a string and I will encrypt it as single large integer.");
            } else {
                break;
            }
        }

        // Encode the input string.
        Encryption code = new Encryption();
        SinglyLinkedList convertCode = code.encodeAll(input);
        System.out.println(input + " is encrypted as " + convertCode);

        // Decode the converted string.
        Decryption result = new Decryption();
        String convertResult = result.decodeAll(convertCode);
        System.out.println("Result of decryption: " + convertResult);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class MerkleTree {
    /**
     * Citation: This method name h is copied from
     * https://www.andrew.cmu.edu/user/mm6/95-771/Homeworks/homework1_S20/homework1.pdf
     **/
    public static String h(String text) throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= 31; i++) {
            byte b = hash[i];
            sb.append(String.format("%02X", b)); }
        return sb.toString();
    }


    /**
     * Read the file from a specific website line by line and
     * return the data as a singly linked list
     * @param url
     *   the url of a specific file
     * @precondition
     *   The url is available
     * @postcondition
     *   A singly linked list with the data in the website.
     *   Each node contains a string line.
     **/
    public SinglyLinkedList readTxt(String url) throws IOException, NoSuchAlgorithmException {
        String line;
        SinglyLinkedList store = new SinglyLinkedList();
        // store the data in the file as an object BufferedReader named "read"
        BufferedReader read = new BufferedReader(new InputStreamReader(
                new URL(url).openConnection().getInputStream(), StandardCharsets.UTF_8));
        // store strings line by line in a singly linked list named "store"
        while ((line = read.readLine()) != null) {
            store.addAtEndNode(line);
        }
        return store;
    }


    /**
     * Prompt the user for a file name, read the file name, read the file,
     * build the Merkle tree as a list of lists and display the Merkle root.
     * @param tree
     *   a singly linked list with strings that is needed to be hashed
     * @precondition
     *   The singly linked list is not null
     * @postcondition
     *   The Merkle root of the file
     **/
    public String getRoot(SinglyLinkedList tree) throws NoSuchAlgorithmException {
        // initialize some objects used below
        String root = null;
        int sign = 0;           // marks whether the program got Merkle root
        int finishString = 0;

        // constantly hashes leaves until the program gets the Merkle root
        while (true)
        {
            if (sign == 1) break;
            if (tree.countNodes() % 2 == 1)
            {
                Object addOne = tree.getLast();
                tree.addAtEndNode(addOne);
            }
            SinglyLinkedList newTree = new SinglyLinkedList();

            // hashed a specific level's singly linked list
            for (int i = 0; i < tree.countNodes()/2; i++)
            {
                String first = (String) tree.getObjectAt(i*2).getData();
                String second = (String) tree.getObjectAt(i*2+1).getData();
                String combine = null;
                if (finishString == 0)
                    combine = h(first) + h(second);
                else
                    combine = first + second;
                root = h(combine);
                newTree.addAtEndNode(root);
                if (tree.countNodes() == 2)
                    sign = 1;
            }
            tree = newTree;
            finishString = 1;
        }
        return root;
    }

    /**
     * The file that we seek is: CrimeLatLonXY.csv
     **/
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        MerkleTree tree = new MerkleTree();
        SinglyLinkedList test = new SinglyLinkedList();
        Scanner in = new Scanner(System.in);
        String input;

        System.out.println("Enter the name of your test file and I will return its Merkle root,");
        System.out.println("for example: smallFile.txt");
        System.out.println("File name: ");
        input = in.nextLine();
        System.out.println("Just wait for a second......");
        test = tree.readTxt("http://www.andrew.cmu.edu/user/mm6/95-771/Homeworks/homework1_S20/" + input);
        String result = tree.getRoot(test);
        System.out.println("The Merkle root of this file: ");
        System.out.println(result);
    }
}

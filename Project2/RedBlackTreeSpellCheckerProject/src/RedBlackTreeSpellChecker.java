import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class RedBlackTreeSpellChecker {
    /**
     * Read the file from a specific website line by line and
     * return the data as a Red Black Tree
     * @param url
     *   the url of a specific file
     * @precondition
     *   The url is available
     * @postcondition
     *   A Red Black Tree with the data in the website.
     *   Each node contains a string line.
     **/
    private RedBlackTree readTxt(String url) throws IOException {
        String line;
        RedBlackTree store = new RedBlackTree();
        // store the data in the file as an object BufferedReader named "read"
        BufferedReader read = new BufferedReader(new InputStreamReader(
                new URL(url).openConnection().getInputStream(), StandardCharsets.UTF_8));
        // store strings line by line in a RedBlackTree named "store"
        while ((line = read.readLine()) != null) {
            store.insert(line);
        }
        return store;
    }


    /**
     * Print the menu in the terminal
     **/
    private void printMenu() {
        System.out.println("\nLegal commands are: \n" +
                "d   display the entire word tree with a level order traversal.\n" +
                "s    print the words of the tree in sorted order (using an inorder traversal).\n" +
                "r    print the words of the tree in reverse sorted order (reverse inorder traversal).\n" +
                "c   <word> to spell check this word.\n" +
                "a   <word> add this word to tree.\n" +
                "f   <fileName> to check this text file for spelling errors.\n" +
                "i   display the diameter of the tree.\n" +
                "m  view this menu.\n" +
                "!   to quit.\n");
    }


    /**
     * Test function
     **/
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String input;
        String url;
        String order;
        String element;
        RedBlackTree tree;
        RedBlackTreeSpellChecker tester = new RedBlackTreeSpellChecker();
        System.out.println("Enter the name of your test file and I will run the corresponding spell checker,");
        System.out.println("for example: shortwords.txt");
        input = in.nextLine();
        url = "https://www.andrew.cmu.edu/user/mm6/95-771/words/" + input;
        tree = tester.readTxt(url);
        System.out.println("\njava RedBlackTreeSpellChecker " + input);
        System.out.println("Loading a tree of English words from " + input + ".");
        System.out.println("Red Black Tree is loaded with " + tree.getSize() + " words.");
        System.out.println("Initial tree height is " + tree.height() + ".");
        System.out.println("Never worse than 2 * Lg(n+1) = " + 2*(Math.log(tree.getSize()+1))/Math.log(2) + ".");
        tester.printMenu();

        label:
        while (true) {
            input = in.nextLine();
            String[] whole = input.split(" ");
            order = whole[0];
            element = "";
            if (!(order.equals("d")||order.equals("s")||order.equals("r")||
                    order.equals("i")||order.equals("m")||order.equals("!")))
                element = whole[1];

            switch (order) {
                case "d":
                    tree.levelOrderTraversal();
                    break;
                case "s":
                    tree.inOrderTraversal();
                    break;
                case "r":
                    tree.reverseOrderTraversal();
                    break;
                case "c":
                    if (tree.contains(element))
                        System.out.println("Found " + element + " after " + tree.getRecentCompares() +
                                " comparisons");
                    else
                        System.out.println(element + " Not in dictionary. Perhaps you mean \n" + tree.closeBy(element));
                    break;
                case "a":
                    if (tree.contains(element))
                        System.out.println("The word '" + element + "' is already in the dictionary.");
                    else {
                        tree.insert(element);
                        System.out.println(element + " was added to dictionary.");
                    }
                    break;
                case "f":
                    url = "https://www.andrew.cmu.edu/user/mm6/95-771/words/" + element;
                    RedBlackTree beTested = tester.readTxt(url);
                    int sign = 0;

                    String line;
                    // store the data in the file as an object BufferedReader named "read"
                    BufferedReader read = new BufferedReader(new InputStreamReader(
                            new URL(url).openConnection().getInputStream(), StandardCharsets.UTF_8));
                    // store strings line by line in a string array named "store"
                    while ((line = read.readLine()) != null) {
                        String[] sentence = line.split(" ");
                        // determine whether there is a notation in the last of sentence
                        for (int i = 0; i < sentence.length; i++) {
                            if (sentence[i].length() == 0) continue;
                            char last = sentence[i].charAt(sentence[i].length() - 1);
                            if (!(Character.isLetter(last)))
                                sentence[i] = sentence[i].substring(0, sentence[i].length() - 1);
                            if (!(tree.contains(sentence[i]))) {
                                sign = 1;
                                System.out.println("'" + sentence[i] + "' was not found in dictionary.");
                            }
                        }
                    }
                    if (sign == 0)
                        System.out.println("No spelling errors found.");
                    break;
                case "!":
                    System.out.println("Bye.");
                    break label;
                case "m":
                    tester.printMenu();
                    break;
                case "i":
                    System.out.println("The current diameter of the tree is " + tree.getDiameter() + ".");
                    break;
            }
        }
    }
}

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

//public class ReversePolishNotation {
//    private static BigInteger getRealValue(Object element, RedBlackTree tree) {
//        if (Character.isLetter((element.toString()).charAt(0))) {
//            if (tree.contains((String) element))
//                element = tree.getValue((String) element);
//            else
//                throw new IllegalStateException("no variable " + element);
//        }
//        return (BigInteger) element;
//    }


//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String input;
//        String variable;
//        RedBlackTree tree = new RedBlackTree();
//        BigInteger element0;
//        BigInteger element1;
//        BigInteger element2;
//        BigInteger result = null;
//        DynamicStack pool = new DynamicStack();
//        while (true) {
//            input = in.nextLine();
//            String[] whole = input.split(" ");
//            if (whole.length == 1) {
//                if (whole[0].isEmpty()) break;
//                System.out.println(tree.getValue(whole[0]));
//                continue;
//            }
//            for (String s : whole) {
//                if (Character.isDigit(s.charAt(0))) {
//                    BigInteger element = new BigInteger(s);
//                    pool.push(element);
//                    continue;
//                }
//                if (s.equals("+")) {
//                    element0 = getRealValue(pool.pop(), tree);
//                    element1 = getRealValue(pool.pop(), tree);
//                    result = element0.add(element1);
//                    pool.push(result);
//                } else if (s.equals("-")) {
//                    element0 = getRealValue(pool.pop(), tree);
//                    element1 = getRealValue(pool.pop(), tree);
//                    result = element1.subtract(element0);
//                    pool.push(result);
//                } else if (s.equals("*")) {
//                    element0 = getRealValue(pool.pop(), tree);
//                    element1 = getRealValue(pool.pop(), tree);
//                    result = element0.multiply(element1);
//                    pool.push(result);
//                } else if (s.equals("/")) {
//                    element0 = getRealValue(pool.pop(), tree);
//                    element1 = getRealValue(pool.pop(), tree);
//                    result = element1.divide(element0);
//                    pool.push(result);
//                } else if (s.equals("%")) {
//                    element0 = getRealValue(pool.pop(), tree);
//                    element1 = getRealValue(pool.pop(), tree);
//                    result = element1.mod(element0);
//                    pool.push(result);
//                } else if (s.equals("~")) {
//                    element0 = getRealValue(pool.pop(), tree);
//                    result = element0.negate();
//                    pool.push(result);
//                } else if (s.equals("^")) {
//                    element0 = getRealValue(pool.pop(), tree);
//                    element1 = getRealValue(pool.pop(), tree);
//                    element2 = getRealValue(pool.pop(), tree);
//                    result = element2.modPow(element1, element0);
//                    pool.push(result);
//                } else if (s.equals("=")) {
//                    element0 = (BigInteger) pool.pop();
//                    Object element = pool.pop();
//                    try {
//                        variable = (String) element;
//                        if (Character.isLetter(variable.charAt(0))) {
//                            tree.insert(variable, element0);
//                            result = element0;
//                            pool.push(variable);
//                        }
//                    } catch (Exception e) {
//                        throw new IllegalStateException(element + " not an l value");
//                    }
//                } else if (Character.isLetter(s.charAt(0))) {
//                    pool.push(s);
//                }
//            }
//            System.out.println(result);
//        }
//        System.out.println("terminating");
//    }
//}

public class ReversePolishNotation {
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int start, int end) {
        if (start >= end) return;

        var boundary = partition(array, start, end);

        sort(array, start, boundary - 1);
        sort(array, boundary + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        var povit = array[end];
        var boundary = start - 1;

        for (int i = start; i <= end; i++) {
            if (array[i] <= povit)
                swap(array, i, ++boundary);
        }

        return boundary;
    }

    private static void swap(int[] array, int index0, int index1) {
        int temp = array[index0];
        array[index0] = array[index1];
        array[index1] = temp;
    }

    public static void main(String[] args) {
        int[] test = {23,4,2,5,1,324,12};
        sort(test);
        System.out.println(Arrays.toString(test));
    }
}
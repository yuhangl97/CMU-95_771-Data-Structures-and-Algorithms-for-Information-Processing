import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;


public class WorldSeriesOdds {
    Object[][] memo = new Object[51][51];


    /**
     * Computing P by using recursion
     * @precondition
     *   i and j are both int
     * @postcondition
     *   returns the result of Floyd-Warshall problem
     *
     * runtime: Θ(N)
     **/
    public double originalSolver(int i, int j) {
        if (i == 0 && j > 0) return 1;
        if (i > 0 && j == 0) return 0;
        return (originalSolver(i-1, j) + originalSolver(i, j-1))/2;
    }


    /**
     * Computing P by dynamic programming
     * @precondition
     *   i and j are both int
     * @postcondition
     *   returns the result of Floyd-Warshall problem
     *
     * runtime: Θ(N)
     **/
    public double memoizedSolution(int i, int j) {
        if ((i > 0) && (j > 0) && (memo[i][j] != null))
            return (double)memo[i][j];
        double result;
        if (i == 0 && j > 0)
            result = 1;
        else if (i > 0 && j == 0)
            result = 0;
        else
            result = (memoizedSolution(i-1, j) + memoizedSolution(i, j-1))/2;
        memo[i][j] = result;
        return result;
    }


    /**
     * Test function
     **/
    public static void main(String[] args) {
        WorldSeriesOdds solver = new WorldSeriesOdds();
        Scanner input = new Scanner(System.in);
        System.out.println(Arrays.toString(solver.memo));
        System.out.println("Enter the values of your test and I will run Floyd-Warshall program.");
        System.out.println("Solution 0: recursion.");
        System.out.println("Solution 1: memoized solution.");
        System.out.println("Enter solution number: ");
        int solution = input.nextInt();
        System.out.println("Enter i: ");
        int i = input.nextInt();
        System.out.println("Enter j: ");
        int j = input.nextInt();
        double probability;

        long startTime = System.nanoTime();
        if (solution == 0)
            probability = solver.originalSolver(i, j);
        else
            probability = solver.memoizedSolution(i, j);
        long endTime = System.nanoTime();
        long costTime = endTime - startTime;

        System.out.println("Value: " + probability);
        System.out.println("Cost time: " + costTime + " ms");
    }
}

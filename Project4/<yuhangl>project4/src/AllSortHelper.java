import java.util.ArrayList;
import java.util.Arrays;

public class AllSortHelper {
    private int[] array;
    private ArrayList<int[]> allSortStore;

    public AllSortHelper(int[] array) {
        this.array = array;
        this.allSortStore = new ArrayList<int[]>();
    }

    public void allSort(int begin, int end) {
        if (begin == end) {
            allSortStore.add(array.clone());
        }
        for (int i = begin; i <= end; i++) {
            swap(array, begin, i);
            allSort(begin+1,end);
            swap(array, begin, i);
        }
    }

    public void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public ArrayList<int[]> getAllSortStore() {
        return allSortStore;
    }

    public static void main (String[] args) {
        int[] array = {1,2,3};
        AllSortHelper helper = new AllSortHelper(array);
        helper.allSort(0, array.length - 1);
        for (var i : helper.getAllSortStore())
            System.out.println(Arrays.toString(i));
    }
}

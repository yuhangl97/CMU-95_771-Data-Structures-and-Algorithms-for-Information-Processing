import java.util.Arrays;


public class DynamicStack {
    private int top = -1;
    private int size = 6;
    private Object[] items = new Object[size];


    /**
     * Best case: runtime: Θ(1)
     * Worst case: runtime: Θ(N)
     **/
    public void push(Object item) {
        if ((top+1) == size) expandStack();
        items[++top] = item;
    }


    public Object peek() {
        return items[top];
    }


    public Object pop() {
        if (isEmpty())
            throw new IllegalStateException("stack underflow exception");
        return items[top--];
    }


    /**
     * Double the size of stack if the original stack was full
     * @postcondition
     *   Get a new array, containing all old elements, whose capacity is
     *   double the original size
     *
     * runtime: Θ(N)
     **/
    private void expandStack() {
        size = size * 2;
        items = copyStack(size);
    }


    /**
     * Copy the stack to a new array with larger capacity
     * @param size
     *   the size of new stack
     * @postcondition
     *   Get a new array, containing all old elements, whose capacity is
     *   double the original size
     *
     * runtime: Θ(N)
     **/
    private Object[] copyStack(int size) {
        Object[] newItems = new Object[size];
        for(int i = 0; i <= top; i++) {
            newItems[i] = items[i];
        }
        return newItems;
    }


    public boolean isEmpty() {
        return top == -1;
    }


    @Override
    public String toString() {
        return Arrays.toString(copyStack(size));
    }


    /**
     * Test function
     **/
    public static void main(String[] args) {
        DynamicStack test = new DynamicStack();
        for(int i = 0; i < 1000; i++)
            test.push(i);
        System.out.println("Stack before pop(): ");
        System.out.println(test);
        for (int i = 0; i < 1000; i++)
            System.out.println(test.pop());
        System.out.println("Stack after pop(): ");
        System.out.println(test);
    }
}

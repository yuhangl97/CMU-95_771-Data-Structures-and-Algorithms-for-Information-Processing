import java.util.Arrays;


public class Queue {
    private int size = 5;
    private Object[] items;
    private int front = 0;
    private int rear = 0;
    private int count = 0;


    public Queue() {
        this.items = new Object[size];
    }


    /**
     * Boolean method returns true on empty queue, false otherwise.
     * @precondition
     *   None
     *
     * runtime: Θ(1)
     **/
    public boolean isEmpty() {
        return count == 0;
    }


    /**
     * Boolean method returns true if queue is currently at capacity, false otherwise.
     * @precondition
     *   None
     *
     * runtime: Θ(1)
     **/
    public boolean isFull() {
        return count >= size;
    }


    /**
     * Method removes and returns reference in front of queue.
     * @precondition
     *   queue not empty
     * @postcondition
     *   Remove object in front of queue and return it
     *
     * runtime: Θ(1)
     **/
    public Object deQueue() {
        if (isEmpty())
            throw new IllegalStateException("Cannot dequeue an empty queue");
        Object deleteItem = items[front];
        items[front] = null;
        front = (front+1) % items.length;
        count--;
        return deleteItem;
    }


    /**
     * Add an object reference to the rear of the queue.
     * @param x
     *   the element to insert into the queue
     * @postcondition
     *   Get a new array, containing all old elements, whose capacity is
     *   double the original size
     *
     * runtime: Θ(N) when the queue is full
     * runtime: Θ(1) in other cases
     **/
    public void enQueue(Object x) {
        if (isFull())
            enlargeQueue();
        items[rear] = x;
        rear = (rear+1) % items.length;
        count++;
    }


    /**
     * Double the size of queue, and reset front and rear index
     * @postcondition
     *   Get a new array, containing all old elements, whose capacity is
     *   double the original size; adjust front and rear index
     *
     * runtime: Θ(N)
     **/
    private void enlargeQueue() {
        size *= 2;
        items = copyQueue(size);
        front = 0;
        rear = count;
    }


    /**
     * Copy the queue to a new array with larger capacity
     * @param size
     *   the size of new array
     * @precondition
     *   The url is available
     * @postcondition
     *   Get a new array, containing all old elements, whose capacity is
     *   double the original size
     *
     * runtime: Θ(N)
     **/
    private Object[] copyQueue(int size) {
        Object[] newItems = new Object[size];
        for(int i = 0; i < count; i++) {
            newItems[i] = items[front];
            front = (front+1) % items.length;
        }
        return newItems;
    }


    /**
     * Method getFront returns the front of the queue without removing it.
     * @precondition
     *   queue not empty
     * @postcondition
     *   get the queue front without removal
     *
     * runtime: Θ(1)
     **/
    public Object getFront() {
        return items[front];
    }


    /**
     * Returns a String representation of the current queue contents in order
     * It shows the front of the queue first. It then shows the second and
     * third and so on.
     *
     * runtime: Θ(N)
     **/
    @Override
    public String toString() {
        return Arrays.toString(copyQueue(size));
    }


    /**
     * Test function
     **/
    public static void main(String[] args) {
        Queue testQueue = new Queue();
        String newItem = "abc";
        System.out.println("Add: " + newItem);
        testQueue.enQueue(newItem);
        newItem = "qwe";
        System.out.println("Add: " + newItem);
        testQueue.enQueue(newItem);
        newItem = "asd";
        System.out.println("Add: " + newItem);
        testQueue.enQueue(newItem);
        newItem = "fda";
        System.out.println("Add: " + newItem);
        testQueue.enQueue(newItem);
        System.out.println("Delete: " + testQueue.deQueue());
        newItem = "asdf";
        System.out.println("Add: " + newItem);
        testQueue.enQueue(newItem);
        newItem = "fsda";
        System.out.println("Add: " + newItem);
        testQueue.enQueue(newItem);
        System.out.println("Delete: " + testQueue.deQueue());
        newItem = "afd";
        System.out.println("Add: " + newItem);
        testQueue.enQueue(newItem);
        newItem = "fsa";
        System.out.println("Add: " + newItem);
        testQueue.enQueue(newItem);
        System.out.println("Delete: " + testQueue.deQueue());
        System.out.println("Delete: " + testQueue.deQueue());
        System.out.println(testQueue);
    }
}

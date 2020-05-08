import java.util.NoSuchElementException;

public class SinglyLinkedList
{
    // Invariant of the SinglyLinkedList class:
    //   1. The head pointer is null or points to the first
    //      element on the list.
    //   2. The tail pointer is null or points to the last
    //      node on the list.
    //   3. An integer countNodes is maintained to keep
    //      count of the number of nodes added to the list.
    //      next node of the list.
    //      This provided an O(1) count to the caller.
    private ObjectNode head;
    private ObjectNode tail;
    private int countNodes;

    private int cursor = 1;
    private int lastRet = -1;


    /**
     * Initialize a SinglyLinkedList object without elements
     *   runtime: Θ(1)
     **/
    public SinglyLinkedList()
    {
        countNodes = 0;
    }


    /**
     * Reset the iterator to the beginning of the list. That is,
     * set a reference to the head of the list.
     *
     *   runtime: Θ(1)
     **/
    public void reset()
    {
        cursor = 1;
        lastRet = -1;
    }


    /**
     * @postcondition
     * Return the Object pointed to by the iterator and increment
     * the iterator to the next node in the list. This reference
     * becomes null if the object returned is the last node on the list.
     *
     *   runtime: Θ(n)
     **/
    public Object next()
    {
        ObjectNode cur = head;
        if (cursor > countNodes)
            throw new NoSuchElementException();
        if (cursor == countNodes)
        {
            lastRet = cursor++;
            return null;
        }
        for (int i = 0; i < cursor; i++)
        {
            cur = cur.getLink();
        }
        lastRet = cursor++;
        return cur.getData();
    }


    /**
     * @postcondition
     * true if the iterator is not null
     *
     *   runtime: Θ(1)
     **/
    public boolean hasNext()
    {
        return cursor < countNodes;
    }


    /**
     * Add a node containing the Object item to the head of the linked list.
     * @param item
     *   the initial data of this new node
     * @postcondition
     *   This method adds a node containing the Object item to the head of
     *   the linked list and increments a count of the number of nodes on the list.
     *   The count is returned by countNodes.
     *
     *   runtime: Θ(1)
     **/
    public void addAtFrontNode(Object item)
    {
        ObjectNode newHead = new ObjectNode(item, null);
        if (head == null && tail == null)
        {
            head = newHead;
            tail = newHead;
        } else {
            newHead.setLink(head);
            head = newHead;
        }
        countNodes++;
    }


    /**
     * Add a node containing the Object item to the end of the linked list.
     * @param item
     *   the initial data of this new node
     * @postcondition
     *   This method adds a node containing the Object item to the end of
     *   the linked list and increments a count of the number of nodes on the list.
     *   The count is returned by countNodes.
     *
     *   runtime: Θ(1)
     **/
    public void addAtEndNode(Object item)
    {
        ObjectNode newEnd = new ObjectNode(item, null);
        if (head == null && tail == null)
        {
            head = newEnd;
        } else {
            tail.setLink(newEnd);
        }
        tail = newEnd;
        countNodes++;
    }


    /**
     * Counts the number of nodes in the list
     * @postcondition
     *   This method the number of nodes in the list between head and tail inclusive.
     *   No list traversal is performed. Simply return the value of countNodes.
     *
     *   runtime: Θ(1)
     **/
    public int countNodes()
    {
        return countNodes;
    }


    /**
     * @param item
     *   target index
     * @postcondition
     * Returns a reference (0 based) to the object with list index i.
     *
     *   runtime: Θ(n)
     **/
    public ObjectNode getObjectAt(int i)
    {
        int count = 0;
        ObjectNode cur = head;

        while(count < i)
        {
            cur = cur.getLink();
            count++;
        }

        return cur;
    }


    /**
     * @postcondition
     *   Returns the data in the tail of the list
     *
     *   runtime: Θ(1)
     **/
    public Object getLast()
    {
        return tail.getData();
    }


    @Override
    public String toString() {
        StringBuilder stringCollector = new StringBuilder();
        ObjectNode element = head;

        while (element != null)
        {
            stringCollector.append(element.getData());
            element = element.getLink();
        }

        return stringCollector.toString();
    }

    public static void main(String[] args)
    {
        System.out.println("------------------------------------------");
        System.out.println("Testing of instance methods: ");
        System.out.println("------------------------------------------");
        SinglyLinkedList test = new SinglyLinkedList();
        System.out.println("Number of nodes in test: " + test.countNodes() + ".");
        test.addAtFrontNode('B');
        System.out.println("The current elements in test are " + test.toString() + ".");
        test.addAtFrontNode('A');
        System.out.println("The current elements in test are " + test.toString() + ".");
        test.addAtEndNode('C');
        System.out.println("The current elements in test are " + test.toString() + ".");
        System.out.println("Number of nodes in test: " + test.countNodes() + ".");
        int index = 2;
        System.out.println("The element of index " + index + " in test is " + test.getObjectAt(index) + ".");
        System.out.println("The last element in test is " + test.getLast() + ".");

        System.out.println("------------------------------------------");
        System.out.println("Testing of iterator methods: ");
        System.out.println("------------------------------------------");
        System.out.println("The current elements in test are " + test.toString() + ".");
        test.reset();
        while (test.hasNext()){
            System.out.println("Has next element? " + test.hasNext());
            System.out.println("Next element: " + test.next());
        }
        System.out.println("Before reset method: ");
        System.out.println("Has next element? " + test.hasNext());
        System.out.println("Next element: " + test.next());
        test.reset();
        System.out.println("After reset method: ");
        System.out.println("Has next element? " + test.hasNext());
        System.out.println("Next element: " + test.next());
    }
}

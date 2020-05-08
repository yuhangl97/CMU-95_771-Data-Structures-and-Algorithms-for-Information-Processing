public class MyLinkedList {
    public class Node {
        private HashMap.Entry value;
        private Node next;

        public Node(HashMap.Entry value) {
            this.value = value;
        }

        public Node getNext(Node current) {
            return current.next;
        }

        public HashMap.Entry getValue(){
            return value;
        }
    }

    private Node first;
    private Node last;
    private int size = 0;

    public Node getFirst() {
        return first;
    }

    /**
     * Add element at the end of the linked list
     *
     * @param  item the added element
     */
    public void addLast(HashMap.Entry item) {
        var node = new Node(item);

        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }

        size++;
    }

    /**
     * Add element at the start of the linked list
     *
     * @param  item the added element
     */
    public void addFirst(HashMap.Entry item) {
        var node = new Node(item);

        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first = node;
        }

        size++;
    }

    private boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of elements in the list
     *
     * @return the size of linked list
     */
    public int getSize() {
        return size;
    }
}

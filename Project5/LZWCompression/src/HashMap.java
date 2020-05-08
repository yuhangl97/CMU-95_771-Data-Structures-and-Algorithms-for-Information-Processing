public class HashMap {
    // table entry: designed for the increasing ASCII table in the project
    public class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private int maxSize = 127;
    private MyLinkedList[] entries = new MyLinkedList[maxSize];
    private int size = 0;
    private int maxEle = 4095;

    public HashMap() {
        // initialize HashMap with 128 linked lists
        for (int i = 0; i < maxSize; i++) {
            entries[i] = new MyLinkedList();
        }
    }

    /**
     * Hash the input string item as the index
     *
     * @param  item String added into HashMap
     * @return hashcode of input
     */
    private int hash(String item) {
        return Math.abs(item.hashCode()) % maxSize;
    }

    /**
     * Add new key-value
     *
     * @param  key the key of key-value
     * @param  key the value of key-value
     */
    public void put(int key, String value) {
        if (size >= maxEle) return;
        var entry = new Entry(key, value);
        var index = hash(value);
        MyLinkedList target = entries[index];
        target.addLast(entry);
        size++;
    }

    /**
     * Search key-value by value
     *
     * @param  item the value of the target key-value
     * @return return -1 if not found; otherwise, return the key of the target key-value
     */
    public int get(String item) {
        var index = hash(item);
        var targetList = entries[index];
        for (MyLinkedList.Node entry = targetList.getFirst();
             entry != null; entry = entry.getNext(entry)) {
            if (entry.getValue().value.equals(item)) {
                return entry.getValue().key;
            }
        }
        return -1;
    }

    /**
     * Search key-value by key
     *
     * @param  key the key of the target key-value
     * @return return -1 if not found; otherwise, return the value of the target key-value
     */
    public String get(int key) {
        for (MyLinkedList list : entries) {
            for (MyLinkedList.Node entry = list.getFirst();
                 entry != null; entry = entry.getNext(entry)) {
                if (key == entry.getValue().key) {
                    return entry.getValue().value;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (MyLinkedList ele : entries) {
            result.append("NO." + count++ + " ").append("[ ");
            for (MyLinkedList.Node entry = ele.getFirst();
                 entry != null; entry = entry.getNext(entry)) {
                result.append(entry.getValue().key + ":" + entry.getValue().value + ", ");
            }
            result.append(" ]\n");
        }
        return result.toString();
    }

    // Test the function of HashMap
    public static void main(String[] args) {
        var dic = new HashMap();
        dic.put(1, "a");
        dic.put(2, "b");
        dic.put(3, "c");
        dic.put(4, "d");
        dic.put(5, "ab");
        dic.put(6, "abc");
        dic.put(7, "abcd");
    }
}

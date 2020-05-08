import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WeightedGraph {
    public ArrayList<Node> preList = new ArrayList<>();
    private class HeapSort {
        private ArrayList<Edge> store = new ArrayList<Edge>();


        private void fixUp(int n){
            Edge current = store.get(n);
            int father = (n - 1) / 2;
            while (store.get(father).weight > current.weight && n != 0) {
                store.set(n, store.get(father));
                n = father;
                father = (n - 1) / 2;
            }
            store.set(n, current);
        }


        private void fixDown(int i, int n) {
            Edge current = store.get(i);
            int son = i * 2 + 1;
            while (son <= n) {
                if (son + 1 <= n && store.get(son+1).weight < store.get(son).weight)
                    son++;
                if (current.weight < store.get(son).weight)
                    break;
                store.set(i, store.get(son));
                i = son;
                son = i * 2 + 1;
            }
            store.set(i, current);
        }


        public void add(Edge newElement) {
            store.add(newElement);
            fixUp(store.size() - 1);
        }

        public Edge pop() {
            Edge result = store.remove(0);
            Edge last = store.remove(store.size() - 1);
            store.add(0, last);
            fixDown(0, store.size() - 1);
            return result;
        }
    }

    public class Edge {
        public Node start;
        public Node end;
        public double weight;

        public Edge(Node start, Node end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public class Node {
        private int label;
        private String data;

        public Node(int label, String data) {
            this.label = label;
            this.data = data;
        }

        public int getLabel() {
            return label;
        }
        public String getData() {
            return data;
        }

        public String getLocation() {
            String[] ss = getData().split(",");
            return ss[8] + "," + ss[7];
        }
    }

    private List<Node> nodes;
    private double[][] edges;

    public void graphInit(List<String> data) {
        nodesInit(data);
        edgesInit();
    }

    public void nodesInit(List<String> data) {
        nodes = new ArrayList<Node>();
        int count = 0;
        for (String s : data) {
            nodes.add(new Node(count++, s));
        }
    }

    public double[][] getEdges() {
        return edges;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void edgesInit() {
        int size = nodes.size();
        edges = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                double weight = getDistance(nodes.get(i).getData(), nodes.get(j).getData());
                edges[i][j] = weight;
                edges[j][i] = weight;
            }
        }
    }

    private double getDistance(String s1, String s2) {
        double[] l1 = getLocation(s1);
        double[] l2 = getLocation(s2);
        return Math.sqrt((l1[0] - l2[0])*(l1[0] - l2[0]) + (l1[1] - l2[1])*(l1[1] - l2[1])) * 0.00018939;
    }

    private double[] getLocation(String s) {
        double[] location = new double[2];
        String[] ss = s.split(",");
        location[0] = Double.parseDouble(ss[0]);
        location[1] = Double.parseDouble(ss[1]);
        return location;
    }


    public ArrayList<Node> getMST() {
        var result = new ArrayList<Node>();

        ArrayList<LinkedList<Node>> tree = new ArrayList<>();
        for (Node value : nodes) {
            LinkedList<Node> root = new LinkedList<>();
            root.add(value);
            tree.add(root);
        }

        var startNode = nodes.get(0);
        HeapSort store = new HeapSort();
        addEdgeToHeap(startNode, store);
        result.add(startNode);

        while (result.size() < nodes.size()) {
            var minEdge = store.pop();
            var nextNode = minEdge.end;

            if (result.contains(nextNode))
                continue;
            result.add(nextNode);
            for (LinkedList<Node> e : tree) {
                if (e.getFirst().getLabel() == minEdge.start.getLabel()) {
                    e.add(minEdge.end);
                }
            }
            addEdgeToHeap(nextNode, store);
        }
        preList.add(tree.get(0).getFirst());
        preOrder(tree, tree.get(0));

        return preList;
    }



    private void preOrder(ArrayList<LinkedList<Node>> tree, LinkedList<Node> mid) {
        for (int i = 1; i < mid.size(); i++) {
            Node next = mid.get(i);
            preList.add(next);
            preOrder(tree, tree.get(next.getLabel()));
        }
    }

    private void addEdgeToHeap(Node start, HeapSort store) {
        int endIndex = 0;
        for (double weight : edges[start.getLabel()]) {
            if (weight != 0) {
                store.add(new Edge(start, nodes.get(endIndex), weight));
            }
            endIndex++;
        }
    }

    public double getLength(ArrayList<Node> nodes) {
        double length = 0;
        for (int i = 0; i < nodes.size() - 1; i++) {
            length += getDistance(nodes.get(i).getData(), nodes.get(i+1).getData());
        }
        length += getDistance(nodes.get(nodes.size()-1).getData(), nodes.get(0).getData());
        return length;
    }

    public double getPathDistance(int[] path) {
        double length = 0;
        for (int i = 0; i < path.length-1; i++) {
            length += edges[path[i]][path[i+1]];
        }
        return length;
    }
}

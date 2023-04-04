package ngordnet.hyponyms;

import java.util.*;

public class DirectedGraph {

    private TreeMap<Integer, Node> lst;
    private TreeMap<Integer, ArrayList> pcIDs;
    private Set<Integer> ids;
    private TreeMap<Integer, Node> unmarkedLst;
    private HashSet<Node> marked;
    private class Node {

        private String id;
        private ArrayList<Node> children = new ArrayList<>();

        private Node(String i) {
            id = i;
        }

        private void addChild(Node n) {
            children.add(n);
        }

    }

    // turns the map between Integer and String into a map between Integer and node with id String and empty children
    // takes the set of Integers
    public DirectedGraph(TreeMap<Integer, String> i, TreeMap<Integer, ArrayList> j) {

        lst = new TreeMap<>();
        unmarkedLst = new TreeMap<>();
        marked = new HashSet<>();
        for (int id : i.keySet()) {
            lst.put(id, new Node(i.get(id)));
            unmarkedLst.put(id, new Node(i.get(id)));
        }
        ids = i.keySet();

        pcIDs = j;

    }

    // adds an edge from a pointing at b
    public void addEdge(int a, int b) {

        if (!ids.contains(a) || !ids.contains(b)) {
            return;
        }
        lst.get(a).addChild(lst.get(b));
        unmarkedLst.get(a).addChild(unmarkedLst.get(b));
    }

    // get self and all children of id
    public TreeSet<String> getChildren(int id, boolean childList) {


        if (!ids.contains(id)) {
            return new TreeSet<>();
        }

        TreeSet<String> tr;
        if (childList) { // use lst
            tr = getAllChildren(lst.get(id));
        } else {
            tr = getAllChildren(unmarkedLst.get(id));
        }
        marked = new HashSet<>();
        return tr;
    }

    // recursive method, prevents naked recursion. Performs a DFS and adds all children to the list.
    private TreeSet<String> getAllChildren(Node n) {

        TreeSet<String> values = new TreeSet<>();
        if (marked.contains(n)) {
            return values;
        }

        String[] arr = n.id.split(",");
        for (String i : arr) {
            if (!(i.equals(""))) {
                values.add(i);
            }
        }
        if (n.children.isEmpty()) {
            return values;
        } else {
            for (Node c : n.children) {
                marked.add(n);
                values.addAll(getAllChildren(c));
            }
            return values;
        }

    }

}

package ngordnet.hyponyms;

//import org.checkerframework.checker.units.qual.A;
//import org.reflections.vfs.Vfs;

import java.util.*;
//import java.util.HashSet;


public class DirectedGraph {

    private TreeMap<Integer, Node> lst;
    private TreeMap<Integer, ArrayList> pcIDs;
    private Set<Integer> ids;
    private class Node {

        private String id;
        private ArrayList<Node> children = new ArrayList<>();
        private boolean visited = false;

        private Node(String i) {
            id = i;
        }

        private void addChild(Node n) {
            children.add(n);
        }

        private boolean marked() {
            return visited;
        }

        private void visit() {
            visited = true;
        }

        private void revVisit() {
            visited = false;
        }

    }

    // turns the map between Integer and String into a map between Integer and node with id String and empty children
    // takes the set of Integers
    public DirectedGraph(TreeMap<Integer, String> i, TreeMap<Integer, ArrayList> j) {

        lst = new TreeMap<>();
        for (int id : i.keySet()) {
            lst.put(id, new Node(i.get(id)));
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
    }

    // get self and all children of id
    public TreeSet<String> getChildren(int id) {

        TreeMap<Integer, Node> unmarkedLst = lst;
        if (!ids.contains(id)) {
            return new TreeSet<>();
        }
        TreeSet<String> tr = getAllChildren(lst.get(id));
        lst = unmarkedLst;
        return tr;

    }

    // recursive method, prevents naked recursion. Performs a DFS and adds all children to the list.
    private TreeSet<String> getAllChildren(Node n) {

        TreeSet<String> values = new TreeSet<>();
        if (n.marked()) {
            return values;
        } else {
            n.visit();
        }

        String[] arr = n.id.split(",");
        for (String i : arr) {
            if (!(i == "")) {
                values.add(i);
            }
        }
        if (n.children.isEmpty()) {
            return values;
        } else {
            for (Node c : n.children) {
                values.addAll(getAllChildren(c));
            }
            return values;
        }

    }
}

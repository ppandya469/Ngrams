package ngordnet.hyponyms;

//import org.checkerframework.checker.units.qual.A;
//import org.reflections.vfs.Vfs;

import java.util.ArrayList;
//import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class DirectedGraph {

    private TreeMap<Integer, Node> lst;
    private Set<Integer> ids;
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
    public DirectedGraph(TreeMap<Integer, String> i) {

        lst = new TreeMap<>();
        for (int id : i.keySet()) {
            lst.put(id, new Node(i.get(id)));
        }
        ids = i.keySet();

    }

    // adds an edge from a pointing at b
    public void addEdge(int a, int b) {

        if (!ids.contains(a) || !ids.contains(b)) {
            return;
        }
        lst.get(a).addChild(lst.get(b));

    }

    // get self and all children of id
    public ArrayList<String> getChildren(int id) {

        if (!ids.contains(id)) {
            return new ArrayList<>();
        }
        ArrayList<String> tr = getAllChildren(lst.get(id));
        return tr;

    }

    // recursive method, prevents naked recursion. Performs a DFS and adds all children to the list.
    private ArrayList<String> getAllChildren(Node n) {

        ArrayList<String> vals = new ArrayList<>();
        vals.add(n.id);
        if (n.children.isEmpty()) {
            return vals;
        } else {
            for (Node c : n.children) {
                vals.addAll(getAllChildren(c));
            }
            return vals;
        }

    }

}

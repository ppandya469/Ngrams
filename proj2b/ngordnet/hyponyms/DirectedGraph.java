package ngordnet.hyponyms;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

public class DirectedGraph {

    private TreeMap<Integer, Node> lst;
    private Set<Integer> ids;

    private class Node {

        private int id;
        private ArrayList<Node> children;

        private Node(int i) {
            id = i;
        }

        private void addChild(Node n) {
            children.add(n);
        }

    }

    public DirectedGraph(Set<Integer> i) {

        lst = new TreeMap<>();
        for (int id : i) {
            lst.put(id, new Node(id));
        }
        ids = i;

    }

    public void addEdge(int a, int b) {

        if (!ids.contains(a) || !ids.contains(b)) {
            return;
        }
        lst.get(a).addChild(lst.get(b));

    }

    public ArrayList<Integer> getChildren(int id) {

        if (!ids.contains(id)) {
            return new ArrayList<>();
        }
        ArrayList<Integer> tr = getAllChildren(lst.get(id));
        return tr;

    }

    private ArrayList<Integer> getAllChildren(Node n) {

        ArrayList<Integer> vals = new ArrayList<>();
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

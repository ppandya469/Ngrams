package ngordnet.hyponyms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class DirectedGraph {

    private LinkedList<Integer>[] adjacencyList;

    public DirectedGraph(Set<Integer> ids) {
        adjacencyList = new LinkedList[ids.size()];
    }

    public int[] getAllChildren() {
        return new int[0];
    }

    public int[] getAllSiblings() {
        return new int[0];
    }

}

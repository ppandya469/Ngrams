package ngordnet.hyponyms;

import edu.princeton.cs.algs4.In;

import java.util.TreeMap;

public class HyponymMap {

    private DirectedGraph synsets;
    private TreeMap<Integer, String> wordIDs;

    public HyponymMap(String synFile, String hypFile) {
        In syns = new In(synFile);
        In hyps = new In(hypFile);
        while (hyps.hasNextLine()) {
            if (hyps.isEmpty()) {
                break;
            }
            String[] arr = hyps.readString().split(",");
            int id = Integer.valueOf(arr[0]);
            String words = arr[1];
            wordIDs.put(id, words);
        }
        synsets = new DirectedGraph(wordIDs.keySet());
        while (syns.hasNextLine()) {
            if (syns.isEmpty()) {
                break;
            }
            String[] arr = syns.readString().split(",");
            for (int i = 1; i < arr.length; i++) {
                synsets.addEdge(Integer.valueOf(arr[0]), Integer.valueOf(arr[i]));
            }
        }
    }

}

package ngordnet.hyponyms;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import ngordnet.ngrams.*;

public class WordNet {
    private DirectedGraph synsets;
    private TreeMap<Integer, String> wordIDs;
    private TreeMap<String, Integer> revIDs;

    // reads data into two maps (each the reverse of each other) and a directed graph.
    public WordNet(String synFile, String hypFile) {
        In hyps = new In(synFile);
        In syns = new In(hypFile);
        wordIDs = new TreeMap<>();
        revIDs = new TreeMap<>();

        while (hyps.hasNextLine()) {
            if (hyps.isEmpty()) {
                break;
            }
            String[] arr = hyps.readLine().split(",");
            int id = Integer.valueOf(arr[0]);
            String words = arr[1] + ",";
            if (words.contains(" ")) {
                String[] stArr = words.split(" ");
                words = "";
                for (int i = 0; i < stArr.length - 1; i++) {
                    words += (stArr[i] + ",");
                }
                words += (stArr[stArr.length - 1]);
            }
            wordIDs.put(id, words);
            revIDs.put(words, id);
        }
        synsets = new DirectedGraph(wordIDs);
        while (syns.hasNextLine()) {
            if (syns.isEmpty()) {
                break;
            }
            String[] arr = syns.readLine().split(",");
            for (int i = 1; i < arr.length; i++) {
                // if (wordIDs.get(arr[i]) != null) {
                    synsets.addEdge(Integer.valueOf(arr[0]), Integer.valueOf(arr[i]));
                //}
            }
        }
    }

    // gets hyponyms of words
    public String hyponyms(List<String> words, int k, NGramMap n) {

        int id = 0;

        // gets hyponyms of first word
        String firstWord = words.get(words.size()-1) + ",";
        for (String i : revIDs.keySet()) {
            if (i.contains(firstWord)) {
                id = revIDs.get(i);
            }
        }
        ArrayList<String> h = synsets.getChildren(id);

        // k != 0 case
        /*if (k != 0) {

        }*/

        // converts list of hyponyms to string
        String tr = "[";
        for (int i = 0; i < h.size() - 1; i++) {
            tr += (h.get(i) + ", ");
        }
        tr += (h.get(h.size() - 1));
        return tr + "]";
    }
}

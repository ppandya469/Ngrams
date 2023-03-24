package ngordnet.hyponyms;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.TreeMap;

public class WordNet {
    private DirectedGraph synsets;
    private TreeMap<Integer, String> wordIDs;
    private TreeMap<String, Integer> revIDs;
    private In syns;
    private In hyps;

    public WordNet(String synFile, String hypFile) {
        hyps = new In(synFile);
        syns = new In(hypFile);
        wordIDs = new TreeMap<>();
        revIDs = new TreeMap<>();

        while (hyps.hasNextLine()) {
            if (hyps.isEmpty()) {
                break;
            }
            String[] arr = hyps.readLine().split(",");
            int id = Integer.valueOf(arr[0]);
            String words = arr[1];
            if (words.contains(" ")) {
                String[] stArr = words.split(" ");
                words = "";
                for (int i = 0; i < stArr.length - 1; i++) {
                    words += (stArr[i] + ", ");
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
                if (revIDs.get(arr[i]) != null) {
                    synsets.addEdge(Integer.valueOf(arr[0]), revIDs.get(arr[i]));
                }
            }
        }
    }

    public TreeMap<Integer, String> hyponymDataReaderWord() {
        return wordIDs;
    }

    public TreeMap<String, Integer> hyponymDataReaderRevs() {
        return revIDs;
    }

    public DirectedGraph sys() {
        return synsets;
    }

    public String hyponyms(String word) {

        int id = revIDs.get(word);
        ArrayList<String> tr = synsets.getChildren(id);
        return tr;
        ArrayList<String> h = synsets.getChildren(id);
        String tr = "[";
        for (int i = 0; i < h.size() - 1; i++) {
            tr += (h.get(i) + ", ");
        }
        tr += (h.get(h.size() - 1));
        return tr + "]";
    }
}
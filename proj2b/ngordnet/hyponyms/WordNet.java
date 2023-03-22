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
        syns = new In(synFile);
        hyps = new In(hypFile);
        wordIDs = new TreeMap<>();
        revIDs = new TreeMap<>();
    }

    public TreeMap<Integer, String> hyponymDataReaderWord() {
        while (hyps.hasNextLine()) {
            if (hyps.isEmpty()) {
                break;
            }
            String[] arr = hyps.readString().split(",");
            int id = Integer.valueOf(arr[0]);
            String words = arr[1];
            if (words.contains(" ")) {
                String[] stArr = words.split(" ");
                words = "";
                for (int i = 0; i < stArr.length - 1; i++) {
                    words.concat(stArr[i] + ", ");
                }
                words.concat(stArr[stArr.length - 1]);
            }
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
        return wordIDs;
    }

    public TreeMap<String, Integer> hyponymDataReaderRevs() {
        while (hyps.hasNextLine()) {
            if (hyps.isEmpty()) {
                break;
            }
            String[] arr = hyps.readString().split(",");
            int id = Integer.valueOf(arr[0]);
            String words = arr[1];
            if (words.contains(" ")) {
                String[] stArr = words.split(" ");
                words = "";
                for (int i = 0; i < stArr.length - 1; i++) {
                    words.concat(stArr[i] + ", ");
                }
                words.concat(stArr[stArr.length - 1]);
            }
            revIDs.put(words, id);
        }
        synsets = new DirectedGraph(wordIDs.keySet());
        while (syns.hasNextLine()) {
            if (syns.isEmpty()) {
                break;
            }
            String[] arr = syns.readString().split(",");
            for (int i = 1; i < arr.length; i++) {
                synsets.addEdge(Integer.valueOf(arr[0]), revIDs.get(arr[i]));
            }
        }
        return revIDs;
    }
}
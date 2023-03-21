package ngordnet.hyponyms;

import edu.princeton.cs.algs4.In;
import org.checkerframework.checker.units.qual.A;

import java.util.TreeMap;
import java.util.ArrayList;

public class HyponymMap {

    private DirectedGraph synsets;
    private TreeMap<Integer, String> wordIDs;
    private TreeMap<String, Integer> revIDs;

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
            if (words.contains(" ")) {
                 String[] stArr = words.split(" ");
                 words = "";
                for (int i = 0; i < stArr.length - 1; i++) {
                    words.concat(stArr[i] + ", ");
                }
                words.concat(stArr[stArr.length - 1]);
            }
            wordIDs.put(id, words);
            revIDs.put(words, id);
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

    public ArrayList<String> hyponyms(String word) {

        int id = revIDs.get(word);
        ArrayList<Integer> hs = synsets.getChildren(id);
        ArrayList<String> tr = new ArrayList<>();
        for (int i : hs) {
            tr.add(wordIDs.get(i));
        }
        return tr;

    }

}

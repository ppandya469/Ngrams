package ngordnet.hyponyms;

import edu.princeton.cs.algs4.In;
import org.checkerframework.checker.units.qual.A;
import java.util.TreeMap;
import java.util.ArrayList;
import ngordnet.hyponyms.WordNet;

public class HyponymMap {

    private DirectedGraph synsets;
    private TreeMap<Integer, String> wordIDs;
    private TreeMap<String, Integer> revIDs;

    public HyponymMap(String synFile, String hypFile) {
        revIDs = new WordNet(synFile, hypFile).hyponymDataReaderRevs();
        wordIDs = new WordNet(synFile, hypFile).hyponymDataReaderWord();
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

    /*public String lookUp(String word) {
        int id = revIDs.get(word);
        ArrayList<Integer> children = synsets.getChildren(id);


        return;
    }*/
}

package ngordnet.hyponyms;

import edu.princeton.cs.algs4.In;

import java.util.*;

import ngordnet.ngrams.*;

public class WordNet {
    private DirectedGraph synsets;
    private TreeMap<Integer, String> wordIDs;
    private TreeMap<Integer, ArrayList> pcIDS;

    // reads data into two maps (each the reverse of each other) and a directed graph.
    public WordNet(String synFile, String hypFile) {
        In hyps = new In(synFile);
        In syns = new In(hypFile);
        wordIDs = new TreeMap<>();
        pcIDS = new TreeMap<>();

        while (hyps.hasNextLine()) {
            if (hyps.isEmpty()) {
                break;
            }
            String[] arr = hyps.readLine().split(",");
            int id = Integer.valueOf(arr[0]);
            String words = "," + arr[1] + ",";
            if (words.contains(" ")) {
                String[] stArr = words.split(" ");
                words = "";
                for (int i = 0; i < stArr.length - 1; i++) {
                    words += (stArr[i] + ",");
                }
                words += (stArr[stArr.length - 1]);
            }
            wordIDs.put(id, words);
        }
        synsets = new DirectedGraph(wordIDs, pcIDS);
        while (syns.hasNextLine()) {
            if (syns.isEmpty()) {
                break;
            }
            String[] arr = syns.readLine().split(",");
            for (int i = 1; i < arr.length; i++) {
                synsets.addEdge(Integer.valueOf(arr[0]), Integer.valueOf(arr[i]));
            }
        }
    }

    // gets hyponyms of words
    public String hyponyms(List<String> words, int k, int startYear, int endYear, NGramMap n) {

        // gets last word in words adds all ids to wordsIDHolder
        TreeSet<Integer> wordsIDHolder = new TreeSet<>();
        String lastWord = "," + words.get(words.size() - 1) + ",";

        for (int j : wordIDs.keySet()) {
            if (wordIDs.get(j).contains(lastWord)) {
                wordsIDHolder.add(j);
            }
        }

        // gets children of all ids in wordsIDHolder
        TreeSet<String> temp = new TreeSet<>();
        for (int j : wordsIDHolder) {
            temp.addAll(synsets.getChildren(j));
        }
        //


        // removes hyponyms that are not also hyponyms of all other words
        TreeSet<Integer> parentIDHolder = new TreeSet<>();
        if (words.size() > 1) {
            for (int p : wordIDs.keySet()) {
                if (wordIDs.get(p).contains("," + words.get(0) + ",")) {
                    parentIDHolder.add(p);
                }
            }

            ArrayList<String> temp1 = new ArrayList<>();
            for (int w : parentIDHolder) {
                temp1.addAll(synsets.getChildren(w));
            }
            ArrayList<String> holderCopy = new ArrayList<>();
            for (String q : temp) {
                holderCopy.add(q);
            }
            for (String word : holderCopy) {
                if (!temp1.contains(word)) {
                    temp.remove(word);
                }
            }
        }

        // k != 0 case
        ArrayList<String> newHolder = new ArrayList<>();
        if (k > 0) {
            for (int i = 0; i < k; i++) {
                double max = 0.0;
                String maxWord = "";
                for (String o : temp) {
                    double t = 0.0;
                    List<Double> asdf = n.countHistory(o).data();
                    for (Double d : asdf) {
                        t += d;
                    }
                    if (t >= max) {
                        max = t;
                        maxWord = o;
                    }
                }
                if (!maxWord.equals("")) {
                    newHolder.add(maxWord);
                }
                temp.remove(maxWord);
            }
        } else { //if k == 0
            for (String o : temp) {
                if (!(o == "")) {
                    newHolder.add(o);
                }
            }
        }
        Collections.sort(newHolder);
        if (newHolder.isEmpty()) {
            return "[]";
        } else {
            String tr = "[";
            for (int i = 0; i < newHolder.size() - 1; i++) {
                tr += (newHolder.get(i) + ", ");
            }
            tr += (newHolder.get(newHolder.size() - 1));
            return tr + "]";
        }
    }
}
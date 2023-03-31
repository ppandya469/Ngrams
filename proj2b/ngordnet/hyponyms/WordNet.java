package ngordnet.hyponyms;

import edu.princeton.cs.algs4.In;

import java.util.*;

import ngordnet.ngrams.*;

public class WordNet {
    private DirectedGraph synsets;
    private TreeMap<Integer, String> wordIDs;
    private TreeMap<String, Integer> revIDs;
    private ArrayList<Integer> childrenIDs;
    private TreeMap<Integer, ArrayList> pcIDS;

    // reads data into two maps (each the reverse of each other) and a directed graph.
    public WordNet(String synFile, String hypFile) {
        In hyps = new In(synFile);
        In syns = new In(hypFile);
        wordIDs = new TreeMap<>();
        revIDs = new TreeMap<>();
        childrenIDs = new ArrayList<>();
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
            revIDs.put(words, id);
        }
        synsets = new DirectedGraph(wordIDs, pcIDS);
        while (syns.hasNextLine()) {
            if (syns.isEmpty()) {
                break;
            }
            String[] arr = syns.readLine().split(",");
            for (int i = 1; i < arr.length; i++) {
                synsets.addEdge(Integer.valueOf(arr[0]), Integer.valueOf(arr[i]));

                int parentID = Integer.valueOf(arr[0]);
                for (int i2 = 1; i2 < arr.length; i2++) {
                    childrenIDs.add(Integer.valueOf(arr[i2]));
                }
            }
        }
    }

    // gets hyponyms of words
    public String hyponyms(List<String> words, int k, NGramMap n) {

        // gets last word in words adds all ids to wordsIDHolder
        TreeSet<Integer> wordsIDHolder = new TreeSet<>();
        String lastWord = "," + words.get(words.size() - 1) + ",";
        for (String i : revIDs.keySet()) {
            if (i.contains(lastWord)) {
                wordsIDHolder.add(revIDs.get(i));
            }
        }

        // traverses the tree and removes not related ids


        // gets children of all ids in wordsIDHolder
        ArrayList<String> holder = new ArrayList<>();
        for (int j : wordsIDHolder) {
            holder.addAll(synsets.getChildren(j));
        }


        // removes hyponyms that are not also hyponyms of all other words
                for (int i = 1; i < words.size(); i++) {
                    int d = revIDs.get(words.get(i));
                    ArrayList<String> temp = synsets.getChildren(d);
                    for (String word : holder) {
                        if (!temp.contains(word)) {
                            holder.remove(word);
                        }
                    }
                }


        // k != 0 case
        /*
        if (k != 0) {
        }
        */

        // converts list of hyponyms to string
        String tr = "[";
        for (int i = 0; i < holder.size() - 1; i++) {
            tr += (holder.get(i) + ", ");
        }
        tr += (holder.get(holder.size() - 1));
        return tr + "]";
    }
}



    /*public String hyponyms(List<String> words, int k, NGramMap n) {
        TreeSet<Integer> childIDHolder = new TreeSet<>();
        TreeSet<Integer> parentIDHolder = new TreeSet<>();
        TreeSet<Integer> wordsIDHolder = new TreeSet<>();
        boolean matches = false;

        for (int j = 0; j < words.size() - 1; j++) {
            String firstWord = "," + words.get(j) + ",";
            int id = 0;
            for (String i : revIDs.keySet()) {
                if (i.contains(firstWord)) {
                    id = revIDs.get(i);
                }
            }
            ArrayList<String> returnList = synsets.getChildren(id);
            matches = returnList.contains(words.get(j+1));
        }


        // gets hyponyms of first word
        if (words.size() == 1) {
            String firstWord = "," + words.get(words.size() - 1) + ",";
            for (String i : revIDs.keySet()) {
                if (i.contains(firstWord)) {
                    wordsIDHolder.add(revIDs.get(i));
                }
            }
        }
        // traverse the tree to see if there is a match
        // find id of current word
        //find id of next word
        //if current word is related to next word

        // if match is found then look at the last word


        for (int j = words.size() - 1; j == 1; j--) {
            String currWord = words.get(j) + ",";
            String prevWord = words.get(j - 1) + ",";
            for (String i : revIDs.keySet()) { //compares keys in revIDs with the last word
                if (i.contains(currWord)) {
                    childIDHolder.add(revIDs.get(i));
                }
            }
            for (String i : revIDs.keySet()) { //compares keys in revIDs with the last word
                if (i.contains(prevWord)) {
                    parentIDHolder.add(revIDs.get(i));
                }
            }
        }*/
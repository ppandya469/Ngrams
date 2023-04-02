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
    private TreeMap<Integer, Double> frequencyMap;

    // reads data into two maps (each the reverse of each other) and a directed graph.
    public WordNet(String synFile, String hypFile) {
        In hyps = new In(synFile);
        In syns = new In(hypFile);
        wordIDs = new TreeMap<>();
        revIDs = new TreeMap<>();
        childrenIDs = new ArrayList<>();
        pcIDS = new TreeMap<>();
        frequencyMap = new TreeMap<>();

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
    public String hyponyms(List<String> words, int k, int startYear, int endYear, NGramMap n) {

        // gets last word in words adds all ids to wordsIDHolder
        TreeSet<Integer> wordsIDHolder = new TreeSet<>();
        String lastWord = "," + words.get(words.size() - 1) + ",";

        /*
        for (String i : revIDs.keySet()) {
            if (i.contains(lastWord)) {
                wordsIDHolder.add(revIDs.get(i));
            }
        }
         */


        for (int j : wordIDs.keySet()) {
            if (wordIDs.get(j).contains(lastWord)) {
                wordsIDHolder.add(j);
            }
        }


        // gets children of all ids in wordsIDHolder
        ArrayList<String> holder = new ArrayList<>();
        for (int j : wordsIDHolder) {
            holder.addAll(synsets.getChildren(j));
        }


        // removes hyponyms that are not also hyponyms of all other words
        if (words.size() > 1) {
            int d = 0;
            for (String p : revIDs.keySet()) {
                if (p.contains("," + words.get(0) + ",")) {
                    d = revIDs.get(p);
                }
            }

            ArrayList<String> temp1 = synsets.getChildren(d);
            ArrayList<String> holderCopy = new ArrayList<>();
            for (String q : holder) {
                holderCopy.add(q);
            }
            for (String word : holderCopy) {
                if (!temp1.contains(word)) {
                    holder.remove(word);
                }
            }
        }


        // converts list of hyponyms to string
        Collections.sort(holder);
        TreeSet<String> temp = new TreeSet<>();
        for (String m : holder) {
            temp.add(m);
        }

        // k != 0 case
        TreeMap<String, Double> summedOccurrencePerWord = new TreeMap<>();
        ArrayList<String> newHolder = new ArrayList<>();
        double t = 0.0;
        int counter = 0;
        double maxFreq = 0.0;
        double currentFreq;
        String currentWord = "";
        int kValue = k;
        if (k > 0) {
            for (String r : temp) {
                frequencyMap = n.weightHistory(r, startYear, endYear);
                // add values of frequencyMap
                for (int s : frequencyMap.keySet()) {
                    t = t + frequencyMap.get(s);
                }
                // add word and summed value to new TreeMap
                summedOccurrencePerWord.put(r, t);
                t = 0.0;
            }
            // find k number of max value words in new TreeMap
            for (int v = 0; v < kValue; v++) {
                if (!summedOccurrencePerWord.isEmpty()) {
                    for (String u : summedOccurrencePerWord.keySet()) {
                        currentFreq = summedOccurrencePerWord.get(u);
                        if (currentFreq >= maxFreq && currentFreq > 0) {
                            maxFreq = currentFreq;
                            currentWord = u;
                        }
                    }
                    if (!(currentWord == "")) {
                        newHolder.add(currentWord); // store in newHolder arraylist
                    }
                    summedOccurrencePerWord.remove(currentWord, maxFreq);
                    maxFreq = 0.0;
                    currentFreq = 0.0;
                    currentWord = "";
                } else {
                    break;
                }
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
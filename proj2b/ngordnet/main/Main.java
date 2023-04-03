package ngordnet.main;

import ngordnet.browser.NgordnetServer;
import ngordnet.hyponyms.WordNet;
import ngordnet.ngrams.NGramMap;

//this is the updated code 8:00pm 04/02/2023

public class Main {
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();
        String wordFile = "./data/ngrams/top_14377_words.csv";
        String countFile = "./data/ngrams/total_counts.csv";

        String synsetFile = "./data/wordnet/synsets.txt";
        String hyponymFile = "./data/wordnet/hyponyms.txt";

        NGramMap ngm = new NGramMap(wordFile, countFile);

        hns.startUp();
        hns.register("history", new HistoryHandler(ngm));
        hns.register("historytext", new HistoryTextHandler(ngm));
        hns.register("hyponyms", new HyponymsHandler(synsetFile, hyponymFile, ngm));
    }
}

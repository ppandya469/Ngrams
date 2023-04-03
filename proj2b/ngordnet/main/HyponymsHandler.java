package ngordnet.main;

import ngordnet.hyponyms.*;
import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
//import spark.QueryParamsMap;
//import spark.Request;
//import spark.Response;

//import java.util.ArrayList;
//import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {

    private NGramMap ng;
    private String sFile;
    private String hFile;

    public HyponymsHandler(String s, String h, NGramMap n) {
        ng = n;
        sFile = s;
        hFile = h;
    }

    @Override
    public String handle(NgordnetQuery q) {
        WordNet wN = new WordNet(sFile, hFile);
        return wN.hyponyms(q.words(), q.k(), q.startYear(), q.endYear(), ng);
        // what I had before was: there was a wordnet as an instance variable
        // and it called hyponyms on that every time
    }
}

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
    private WordNet wn;

    public HyponymsHandler(String s, String h, NGramMap n) {
        ng = n;
        wn = new WordNet(s, h);
    }

    @Override
    public String handle(NgordnetQuery q) {
        return wn.hyponyms(q.words(), q.k(), q.startYear(), q.endYear(), ng);
    }
}

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
    private WordNet wN;
    private NGramMap ng;

    public HyponymsHandler(WordNet h, NGramMap n) {
        wN = h;
        ng = n;
    }

    @Override
    public String handle(NgordnetQuery q) {
        return wN.hyponyms(q.words(), q.k(), q.startYear(), q.endYear(), ng);
    }
}

package ngordnet.main;

import ngordnet.hyponyms.*;
import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {

        private WordNet wN;

        public HyponymsHandler(WordNet h) {
            wN = h;
        }

        @Override
        public String handle(NgordnetQuery q) {
            List<String> words = q.words();
            String w = words.get(0);
            return wN.hyponyms(w);
        }

}
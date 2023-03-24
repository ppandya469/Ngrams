package ngordnet.main;

import ngordnet.hyponyms.*;
import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {

        private HyponymMap hm;

        public HyponymsHandler(HyponymMap h) {
            hm = h;
        }

        @Override
        public String handle(NgordnetQuery q) {
            List<String> words = q.words();
            String word = words.get(0);
            return hm.hyponyms();
        }
}
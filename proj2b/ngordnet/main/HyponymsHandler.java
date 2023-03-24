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

        private HyponymMap hm;

        public HyponymsHandler(HyponymMap h) {
            hm = h;
        }

        @Override
        public String handle(NgordnetQuery q) {
            List<String> words = q.words();
            String w = words.get(0);
            ArrayList<String> h = hm.hyponyms(w);
            String tr = "";
            for (int i = 0; i < h.size() - 1; i++) {
                tr += (h.get(i) + ", ");
            }
            tr += (h.get(h.size() - 1));
            return tr;
        }
}
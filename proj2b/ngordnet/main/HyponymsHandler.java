package ngordnet.main;

import ngordnet.hyponyms.*;
import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

public class HyponymsHandler extends NgordnetQueryHandler {

        private HyponymMap hm;

        public HyponymsHandler(HyponymMap h) {
            hm = h;
        }

        @Override
        public String handle(NgordnetQuery q) {
            return "Hello!";
        }

}
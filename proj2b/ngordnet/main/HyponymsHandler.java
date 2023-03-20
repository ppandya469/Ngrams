package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;

public class HyponymsHandler extends NgordnetQueryHandler {

    private NGramMap ngm;

    public HyponymsHandler(NGramMap n) {
        ngm = n;
    }

    @Override
    public String handle(NgordnetQuery q) {
        return "Hello!";
    }

}

package designpattern.adapter.webrequest;

import java.util.HashMap;
import java.util.Map;

public class WebRequestTest {
    private static final Map<String, WebRequest> webRequestMap = new HashMap<>();
    public static void main(String[] args) {
//        WebRequest webRequest = new OldWebRequest();
//        webRequest.requestHandler();
//
//        FancyRequest fancyRequest = new FancyRequest();
//        fancyRequest.fancyRequestHandler();
//
////        WebRequest webRequest1 = new FancyRequest();
//        WebRequest webRequest1 = new WebRequestAdapter(fancyRequest);
//        webRequest1.requestHandler();

        init();
        sendRequest("fancyRequest");
        sendRequest("oldRequest");
        sendRequest("noRequest");
    }

    private static void init() {
        webRequestMap.put("oldRequest", new OldWebRequest());
        webRequestMap.put("fancyRequest", new WebRequestAdapter(new FancyRequest()));
    }

    private static void sendRequest(String request) {
        WebRequest webRequest = webRequestMap.get(request);
        if (webRequest == null) {
            throw new IllegalArgumentException("지원하지 않는 request 입니다." + request);
        }
        webRequest.requestHandler();
    }
}

package designpattern.adapter.webrequest;

public class WebClient {
    private WebRequest webRequest;

    public WebClient(WebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public void doWork() {
        webRequest.requestHandler();
    }
}

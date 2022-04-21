package designpattern.adapter.webrequest;

public class WebRequestAdapter implements WebRequest {

    private FancyRequest fancyRequest;

    public WebRequestAdapter(FancyRequest fancyRequest) {
        this.fancyRequest = fancyRequest;
    }

    @Override
    public void requestHandler() {
        fancyRequest.fancyRequestHandler();
    }
}

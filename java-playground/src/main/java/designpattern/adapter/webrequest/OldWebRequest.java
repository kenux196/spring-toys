package designpattern.adapter.webrequest;

public class OldWebRequest implements WebRequest {
    @Override
    public void requestHandler() {
        System.out.println("OldWebRequest.requestHandler");
    }
}

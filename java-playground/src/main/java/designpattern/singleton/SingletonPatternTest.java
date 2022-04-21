package designpattern.singleton;

public class SingletonPatternTest {

    public static void main(String[] args) {
        SingleObject singleObject = SingleObject.getInstance();
        System.out.println("singleObject = " + singleObject);
    }
}

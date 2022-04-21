package designpattern.factory.ex2;

public abstract class Pizza {

    protected String name = "피자";

    public void getName() {
        System.out.println(name);
    }

    public void bake() {
        System.out.println("굽는중");
    }
}

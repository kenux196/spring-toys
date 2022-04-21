package object.chapter1;

public class Audience {
    private final Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public int buy(Ticket ticket) {
        return bag.hold(ticket);
    }
}
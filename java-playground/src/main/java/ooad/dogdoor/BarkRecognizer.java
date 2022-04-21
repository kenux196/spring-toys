package ooad.dogdoor;

public class BarkRecognizer {

    private DogDoor door;

    public BarkRecognizer(DogDoor door) {
        this.door = door;
    }

    public void recognize(Bark bark) {
        System.out.println("    BarkRecognizer: Heard a '" + bark + "'");

        if (door.getAllowedBarks().contains(bark)) {
            door.open();
        } else {
            System.out.println("This dog is not allowed.");
        }
    }
}

package ooad.dogdoor;

import java.lang.Thread;

public class DogDoorSimulator {
    public static void main(String[] args) {
        DogDoor dogDoor = new DogDoor();
        Remote remote = new Remote(dogDoor);
        BarkRecognizer recognizer = new BarkRecognizer(dogDoor);
        dogDoor.addAllowedBark(new Bark("왕왕"));
        dogDoor.addAllowedBark(new Bark("멍멍"));

        System.out.println("Fido starts barking.");
        recognizer.recognize(new Bark("멍멍"));
//        remote.pressButton();
        System.out.println("\nFido has gone outside...");
        System.out.println("\nFido's all done...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("...but he's stuck outside!");
        System.out.println("\nFido starts barking...");
        recognizer.recognize(new Bark("멍멍"));
//        System.out.println("\n... so Gina grabs the remote control.");
//        remote.pressButton();
        System.out.println("\nFido's back inside...");
    }
}
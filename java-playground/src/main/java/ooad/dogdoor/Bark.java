package ooad.dogdoor;

public class Bark {

    private String sound;

    public Bark(String sound) {
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bark bark = (Bark) o;

        return sound != null ? sound.equalsIgnoreCase(bark.sound) : bark.sound == null;
    }

    @Override
    public int hashCode() {
        return sound != null ? sound.hashCode() : 0;
    }
}

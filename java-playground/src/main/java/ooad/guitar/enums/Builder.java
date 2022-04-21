package ooad.guitar.enums;

public enum Builder {
    FENDER("fender"),
    MARTIN("martin"),
    GIBSON("gibson"),
    COLLING("colling"),
    OLSON("olson"),
    RYAN("ryan"),
    PRS("prs"),
    ANY("any");

    private final String value;

    Builder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Builder{" +
                "value='" + value + '\'' +
                '}';
    }
}
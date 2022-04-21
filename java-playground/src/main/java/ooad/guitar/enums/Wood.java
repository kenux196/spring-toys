package ooad.guitar.enums;

public enum Wood {
    MAPLE("maple"),
    COCOBOLO("cocobolo"),
    CEDAR("cedar"),
    ALDER("alder")
    ;

    private final String value;

    Wood(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Wood{" +
                "value='" + value + '\'' +
                '}';
    }
}

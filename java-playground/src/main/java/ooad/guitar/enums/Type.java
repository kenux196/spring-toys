package ooad.guitar.enums;

public enum Type {
    ACOUSTIC("acoustic"),
    ELECTRIC("electric");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Type{" +
                "value='" + value + '\'' +
                '}';
    }
}

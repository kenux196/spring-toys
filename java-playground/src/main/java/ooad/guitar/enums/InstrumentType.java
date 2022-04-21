package ooad.guitar.enums;

public enum InstrumentType {
    GUITAR("Guitar"),
    BANJO("Banjo"),
    MANDOLIN("Mandolin");

    private String value;

    InstrumentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "InstrumentType{" +
                "value='" + value + '\'' +
                '}';
    }
}

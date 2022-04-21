package ooad.gameframework;

import java.util.HashMap;

public class Unit {

    private String type;
    private HashMap<String, String> properties;

    public Unit(String type, HashMap<String, String> properties) {
        this.type = type;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }
}

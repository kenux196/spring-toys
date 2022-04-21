package ooad.guitar;

import java.util.HashMap;
import java.util.Map;

public class InstrumentSpec {

    private Map<String, Object> properties;

    public InstrumentSpec(Map<String, Object> properties) {
        if (properties == null) {
            this.properties = new HashMap<>();
        } else {
            this.properties = new HashMap<>(properties);
        }
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public boolean matches(InstrumentSpec otherSpec) {
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String propertyName = entry.getKey();
            Object value = entry.getValue();
            if (!value.equals(otherSpec.getProperty(propertyName))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "InstrumentSpec{" +
                "properties=" + properties +
                '}';
    }
}

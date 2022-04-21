package ooad.guitar;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Instrument> inventory = new ArrayList<>();

    public void addInstrument(String serialNumber, double price, InstrumentSpec spec) {
        Instrument instrument = new Instrument(serialNumber, price, spec);
        inventory.add(instrument);
    }

    public Instrument get(String serialNumber) {
        for (Instrument instrument : inventory) {
            if (instrument.getSerialNumber().equals(serialNumber))
                return instrument;
        }
        return null;
    }

    public List<Instrument> search(InstrumentSpec searchSpec) {
        List<Instrument> matchInstruments = new ArrayList<>();
        for (Instrument instrument : inventory) {
            if (instrument.getSpec().matches(searchSpec)) {
                matchInstruments.add(instrument);
            }
        }
        return matchInstruments;
    }
}
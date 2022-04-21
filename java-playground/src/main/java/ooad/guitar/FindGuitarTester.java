package ooad.guitar;

import ooad.guitar.enums.Builder;
import ooad.guitar.enums.InstrumentType;
import ooad.guitar.enums.Type;
import ooad.guitar.enums.Wood;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindGuitarTester {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        initializeInventory(inventory);

        findGuitarTest(inventory);

        findMandolinTest(inventory);
    }

    private static void findGuitarTest(Inventory inventory) {
        System.out.println("=== Search Guitar ===");


        InstrumentSpec guitarInstrumentSpec =
                createGuitarInstrumentSpec(InstrumentType.GUITAR, Builder.FENDER, "Stratocastor",
                        Type.ELECTRIC, Wood.ALDER, Wood.ALDER, 12);

        List<Instrument> result = inventory.search(guitarInstrumentSpec);
        if (result.isEmpty()) {
            System.out.println("Sorry, we have nothing for you.");
        } else {
            for (Instrument instrument : result) {
                System.out.println("instrument = " + instrument);
            }
        }
    }

    private static void findMandolinTest(Inventory inventory) {
        System.out.println("=== Search Mandolin ===");
        InstrumentSpec mandolinInstrumentSpec =
                createMandolinInstrumentSpec(InstrumentType.MANDOLIN, Builder.FENDER, "abc-Mandolin",
                        Type.ACOUSTIC, Wood.CEDAR, Wood.CEDAR, Style.A);

        List<Instrument> result = inventory.search(mandolinInstrumentSpec);
        if (result.isEmpty()) {
            System.out.println("Sorry, we have nothing for you.");
        } else {
            for (Instrument instrument : result) {
                System.out.println("instrument = " + instrument);
            }
        }
    }

    private static void initializeInventory(Inventory inventory) {
        InstrumentSpec instrumentSpec =
                createGuitarInstrumentSpec(InstrumentType.GUITAR, Builder.FENDER, "Stratocastor",
                        Type.ELECTRIC, Wood.ALDER, Wood.ALDER, 12);
        inventory.addInstrument("a12345", 100, instrumentSpec);

        instrumentSpec =
                createGuitarInstrumentSpec(InstrumentType.GUITAR, Builder.FENDER, "Stratocastor",
                        Type.ELECTRIC, Wood.ALDER, Wood.ALDER,  6);
        inventory.addInstrument("a22344", 500, instrumentSpec);

        InstrumentSpec mandolinInstrumentSpec =
                createMandolinInstrumentSpec(InstrumentType.MANDOLIN, Builder.FENDER, "abc-Mandolin",
                        Type.ACOUSTIC, Wood.CEDAR, Wood.CEDAR, Style.A);
        inventory.addInstrument("m12345", 140, mandolinInstrumentSpec);
    }

    private static InstrumentSpec createGuitarInstrumentSpec(InstrumentType instrumentType,
                                                             Builder builder,
                                                             String vendor,
                                                             Type type,
                                                             Wood backWood,
                                                             Wood topWood,
                                                             int numberOfString) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("instrument-type", instrumentType);
        properties.put("builder", builder);
        properties.put("vendor", vendor);
        properties.put("type", type);
        properties.put("back-wood", backWood);
        properties.put("top-wood", topWood);
        properties.put("string-count", numberOfString);
        return new InstrumentSpec(properties);
    }

    private static InstrumentSpec createMandolinInstrumentSpec(InstrumentType instrumentType, Builder builder, String vendor,
                                                             Type type, Wood backWood, Wood topWood,
                                                             Style style) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("instrument-type", instrumentType);
        properties.put("builder", builder);
        properties.put("vendor", vendor);
        properties.put("type", type);
        properties.put("back-wood", backWood);
        properties.put("top-wood", topWood);
        properties.put("style", style);
        return new InstrumentSpec(properties);
    }
}
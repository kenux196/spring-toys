package designpattern.delegation;

import java.util.Vector;

public class ClientCompartment {

    private Vector patients = new Vector();

    void checkPatient(Client patient) {
        patients.addElement(patient);
    }
}
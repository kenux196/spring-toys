package designpattern.delegation;

public class ReservationManager {

    ClientCompartment client;

    void checkPatient(Client patient) {
        client.checkPatient(patient);
    }
}
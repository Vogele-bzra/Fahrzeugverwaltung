package controller;

import model.Fahrzeug;
import java.util.ArrayList;
import java.util.List;

public class FahrzeugVerwaltung {

    private List<Fahrzeug> fahrzeugListe;

    public FahrzeugVerwaltung() {
        this.fahrzeugListe = new ArrayList<>();
    }

    // Die GUI ruft genau DIESEN Namen auf:
    public void fahrzeugHinzufuegen(Fahrzeug fahrzeug) {
        fahrzeugListe.add(fahrzeug);
        System.out.println("Fahrzeug hinzugefügt: " + fahrzeug.getMarke());
    }

    // Und diesen hier auch:
    public List<Fahrzeug> getAlleFahrzeuge() {
        return fahrzeugListe;
    }

    // Suchfunktion (brauchen wir später)
    public List<Fahrzeug> sucheNachMarke(String marke) {
        List<Fahrzeug> ergebnis = new ArrayList<>();
        for (Fahrzeug f : fahrzeugListe) {
            if (f.getMarke().equalsIgnoreCase(marke)) {
                ergebnis.add(f);
            }
        }
        return ergebnis;
    }

    public void fahrzeugLoeschen(Fahrzeug f) {
        fahrzeugListe.remove(f);
    }
}
package controller;

import model.Fahrzeug;
import Service.JsonSpeicherservice;

import java.util.ArrayList;
import java.util.List;

public class FahrzeugVerwaltung {

    private List<Fahrzeug> fahrzeugListe;
    private JsonSpeicherservice speicherService;

    public FahrzeugVerwaltung() {
        this.speicherService = new JsonSpeicherservice();

        this.fahrzeugListe = speicherService.ladeFahrzeuge();
    }

    public void fahrzeugHinzufuegen(Fahrzeug fahrzeug) {
        fahrzeugListe.add(fahrzeug);
        speicherService.speichereFahrzeuge(fahrzeugListe); // Speichern bei jeder Änderung
    }

    public void fahrzeugLoeschen(Fahrzeug f) {
        fahrzeugListe.remove(f);
        speicherService.speichereFahrzeuge(fahrzeugListe); // Speichern bei jeder Änderung
    }

    public List<Fahrzeug> getAlleFahrzeuge() {
        return fahrzeugListe;
    }

    public List<Fahrzeug> sucheNachMarke(String marke) {
        List<Fahrzeug> ergebnis = new ArrayList<>();
        for (Fahrzeug f : fahrzeugListe) {
            if (f.getMarke().equalsIgnoreCase(marke)) {
                ergebnis.add(f);
            }
        }
        return ergebnis;
    }
}
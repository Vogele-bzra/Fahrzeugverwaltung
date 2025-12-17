package controller; // Achte auf dein Package! (evtl. nur "controller")

import model.Fahrzeug;
import Service.JsonSpeicherservice; // Importiert deinen neuen JSON-Dienst

import java.util.ArrayList;
import java.util.List;

public class FahrzeugVerwaltung {

    private List<Fahrzeug> fahrzeugListe;
    private JsonSpeicherservice speicherService; // Hier nutzen wir direkt die Klasse, kein Interface mehr

    public FahrzeugVerwaltung() {
        this.speicherService = new JsonSpeicherservice();
        // Beim Start direkt laden
        this.fahrzeugListe = speicherService.ladeFahrzeuge();
    }

    public void fahrzeugHinzufuegen(Fahrzeug fahrzeug) {
        fahrzeugListe.add(fahrzeug);
        speicherService.speichereFahrzeuge(fahrzeugListe); // Speichern bei Änderung
    }

    public void fahrzeugLoeschen(Fahrzeug f) {
        fahrzeugListe.remove(f);
        speicherService.speichereFahrzeuge(fahrzeugListe); // Speichern bei Änderung
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
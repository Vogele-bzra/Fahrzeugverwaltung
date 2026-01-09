package controller;

import model.Kunde;
import Service.JsonSpeicherservice;

import java.util.ArrayList;
import java.util.List;

public class KundenVerwaltung {

    private List<Kunde> kundenListe;
    private JsonSpeicherservice speicherService;

    public KundenVerwaltung() {
        this.speicherService = new JsonSpeicherservice();
        // Lade vorhandene Kunden beim Start
        this.kundenListe = speicherService.ladeKunden();
    }

    public void kundeHinzufuegen(Kunde k) {
        kundenListe.add(k);
        speicherService.speichereKunden(kundenListe);
    }

    public void kundeLoeschen(Kunde k) {
        kundenListe.remove(k);
        speicherService.speichereKunden(kundenListe);
    }

    public List<Kunde> getAlleKunden() {
        return kundenListe;
    }

    public List<Kunde> sucheKunde(String suchText) {
        List<Kunde> treffer = new ArrayList<>();
        String gesucht = suchText.toLowerCase();

        for (Kunde k : kundenListe) {
            // Suche nach Nachname (case-insensitive)
            if (k.getNachname().toLowerCase().contains(gesucht)) {
                treffer.add(k);
            }
        }
        return treffer;
    }

    // --- HIER IST DIE NEUE METHODE ---
    public void aenderungenSpeichern() {
        // Speichert die aktuelle Liste (mit den Änderungen) in die JSON-Datei
        speicherService.speichereKunden(kundenListe);
    }
    // ---------------------------------
}
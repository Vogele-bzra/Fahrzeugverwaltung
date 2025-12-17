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

    // --- NEU: DIE SUCHFUNKTION ---
    public List<Kunde> sucheKunde(String suchText) {
        List<Kunde> treffer = new ArrayList<>();
        // Wir machen alles klein (.toLowerCase), damit "Meier" und "meier" gefunden wird
        String gesucht = suchText.toLowerCase();

        for (Kunde k : kundenListe) {
            if (k.getNachname().toLowerCase().contains(gesucht)) {
                treffer.add(k);
            }
        }
        return treffer;
    }
}
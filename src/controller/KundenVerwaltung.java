package controller; // Achte auf dein Package!

import model.Kunde;
import Service.JsonSpeicherservice;

import java.util.ArrayList;
import java.util.List;

public class KundenVerwaltung {

    private List<Kunde> kundenListe;
    private JsonSpeicherservice speicherService;

    public KundenVerwaltung() {
        this.speicherService = new JsonSpeicherservice();
        // Beim Start direkt laden
        this.kundenListe = speicherService.ladeKunden();
    }

    public void kundeHinzufuegen(Kunde k) {
        kundenListe.add(k);
        speicherService.speichereKunden(kundenListe); // Speichern bei Änderung
    }

    public void kundeLoeschen(Kunde k) {
        kundenListe.remove(k);
        speicherService.speichereKunden(kundenListe); // Speichern bei Änderung
    }

    public List<Kunde> getAlleKunden() {
        return kundenListe;
    }

    // Falls du eine Suche für Kunden hast:
    public List<Kunde> sucheKunde(String nachname) {
        List<Kunde> treffer = new ArrayList<>();
        for (Kunde k : kundenListe) {
            if (k.getNachname().equalsIgnoreCase(nachname)) {
                treffer.add(k);
            }
        }
        return treffer;
    }
}
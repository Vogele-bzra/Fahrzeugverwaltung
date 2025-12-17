package controller;

import model.Kunde;
import java.util.ArrayList;
import java.util.List;

public class KundenVerwaltung {

    private List<Kunde> kundenListe;

    public KundenVerwaltung() {
        this.kundenListe = new ArrayList<>();
    }

    public void kundeHinzufuegen(Kunde k) {
        kundenListe.add(k);
        System.out.println("Kunde gespeichert: " + k.getNachname());
    }

    public void kundeLoeschen(Kunde k) {
        kundenListe.remove(k);
    }

    public List<Kunde> getAlleKunden() {
        return kundenListe;
    }

    // Suchfunktion für später (nach Nachname)
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

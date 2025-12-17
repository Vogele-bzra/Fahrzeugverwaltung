package model;

import java.time.LocalDate;

public class Kunde {
    private String vorname;
    private String nachname;
    private String strasse; // inkl. Nummer
    private String plz;
    private String ort;
    private String telefon;
    private String email;
    private LocalDate geburtsdatum;

    public Kunde(){}

    public Kunde(String vorname, String nachname, String strasse, String plz, String ort, String telefon, String email, LocalDate geburtsdatum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
        this.telefon = telefon;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
    }

    public String getVorname() { return vorname; }
    public String getNachname() { return nachname; }
    public String getStrasse() { return strasse; }
    public String getPlz() { return plz; }
    public String getOrt() { return ort; }
    public String getTelefon() { return telefon; }
    public String getEmail() { return email; }
    public LocalDate getGeburtsdatum() { return geburtsdatum; }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    @Override
    public String toString() {
        // Das wird später in der Liste angezeigt
        return nachname + " " + vorname + " (" + ort + ")";
    }
}
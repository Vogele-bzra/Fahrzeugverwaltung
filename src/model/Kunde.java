package model;

import java.time.LocalDate;

public class Kunde {
    private String nachname;
    private String vorname;
    private String Strasse;
    private String hausnummer;
    private String plz;
    private String ort;
    private String telefonPrivat;
    private String telefonMobil;
    private String email;
    private LocalDate geburtsdatum;

    public Kunde(String nachname, String vorname, String strasse, String hausnummer, String plz, String ort, String telefonPrivat, String telefonMobil, String email, LocalDate geburtsdatum) {
        this.nachname = nachname;
        this.vorname = vorname;
        Strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.telefonPrivat = telefonPrivat;
        this.telefonMobil = telefonMobil;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getStrasse() {
        return Strasse;
    }

    public void setStrasse(String strasse) {
        Strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getTelefonPrivat() {
        return telefonPrivat;
    }

    public void setTelefonPrivat(String telefonPrivat) {
        this.telefonPrivat = telefonPrivat;
    }

    public String getTelefonMobil() {
        return telefonMobil;
    }

    public void setTelefonMobil(String telefonMobil) {
        this.telefonMobil = telefonMobil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    @Override
    public String toString() {
        return nachname +" "+ vorname + " ";
    }
}

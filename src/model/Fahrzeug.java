package model;

import java.time.LocalDate;

public abstract class Fahrzeug {
    private String marke;
    private String modell;
    private int hubraum;
    private String treibstoff;
    private int kmStand;
    private int ps;
    private String erstzulassung; // Als String gespeichert
    private String aussenfarbe;
    private double leergewicht;
    private double preis;

    public Fahrzeug() {
    }

    public Fahrzeug(String marke, String modell, int hubraum, String treibstoff, int kmStand, int ps, String erstzulassung, String aussenfarbe, double leergewicht, double preis) {
        this.marke = marke;
        this.modell = modell;
        this.hubraum = hubraum;
        this.treibstoff = treibstoff;
        this.kmStand = kmStand;
        this.ps = ps;
        this.erstzulassung = erstzulassung;
        this.aussenfarbe = aussenfarbe;
        this.leergewicht = leergewicht;
        this.preis = preis;
    }

    // --- GETTER ---
    public String getMarke() { return marke; }
    public String getModell() { return modell; }
    public int getHubraum() { return hubraum; }
    public String getTreibstoff() { return treibstoff; }
    public int getKmStand() { return kmStand; }
    public int getPs() { return ps; }
    public String getErstzulassung() { return erstzulassung; }
    public String getAussenfarbe() { return aussenfarbe; }
    public double getLeergewicht() { return leergewicht; }
    public double getPreis() { return preis; }

    // --- SETTER (DIE HABEN GEFEHLT!) ---
    public void setMarke(String marke) { this.marke = marke; }
    public void setModell(String modell) { this.modell = modell; }
    public void setHubraum(int hubraum) { this.hubraum = hubraum; }
    public void setTreibstoff(String treibstoff) { this.treibstoff = treibstoff; }
    public void setKmStand(int kmStand) { this.kmStand = kmStand; }
    public void setPs(int ps) { this.ps = ps; }
    public void setErstzulassung(String erstzulassung) { this.erstzulassung = erstzulassung; }
    public void setAussenfarbe(String aussenfarbe) { this.aussenfarbe = aussenfarbe; }
    public void setLeergewicht(double leergewicht) { this.leergewicht = leergewicht; }
    public void setPreis(double preis) { this.preis = preis; }

    @Override
    public String toString() {
        return marke + " " + modell + " (" + ps + " PS)";
    }
}
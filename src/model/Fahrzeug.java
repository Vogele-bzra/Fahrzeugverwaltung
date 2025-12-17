package model;

public abstract class Fahrzeug {
    private String marke;
    private String modell;
    private int hubraum;
    private String treibstoff;
    private int kmStand;
    private int leistung;
    private String erstzulassung;
    private String aussenfarbe;
    private double leergewicht;
    private double preis;

    public Fahrzeug(String marke, String modell, int hubraum, String treibstoff, int kmStand, int leistung, String erstzulassung, String aussenfarbe, double leergewicht, double preis) {
        this.marke = marke;
        this.modell = modell;
        this.hubraum = hubraum;
        this.treibstoff = treibstoff;
        this.kmStand = kmStand;
        this.leistung = leistung;
        this.erstzulassung = erstzulassung;
        this.aussenfarbe = aussenfarbe;
        this.leergewicht = leergewicht;
        this.preis = preis;
    }

    public Fahrzeug() {

    }


    public String getMarke() { return marke; }
    public String getModell() { return modell; }
    public int getHubraum() { return hubraum; }
    public String getTreibstoff() { return treibstoff; }
    public int getKmStand() { return kmStand; }
    public int getLeistung() { return leistung; }
    public String getErstzulassung() { return erstzulassung; }
    public String getAussenfarbe() { return aussenfarbe; }
    public double getLeergewicht() { return leergewicht; }
    public double getPreis() { return preis; }

    public void setMarke(String marke) {
        this.marke = marke;
    }
    public void setModell(String modell) {
        this.modell = modell;
    }
    public void setHubraum(int hubraum) {
        this.hubraum = hubraum;
    }
    public void setTreibstoff(String treibstoff) {
        this.treibstoff = treibstoff;
    }
    public void setKmStand(int kmStand) {
        this.kmStand = kmStand;
    }
    public void setLeistung(int leistung) {
        this.leistung = leistung;
    }
    public void setErstzulassung(String erstzulassung) {
        this.erstzulassung = erstzulassung;
    }
    public void setAussenfarbe(String aussenfarbe) {
        this.aussenfarbe = aussenfarbe;
    }
    public void setLeergewicht(double leergewicht) {
        this.leergewicht = leergewicht;
    }
    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        // Das wird in der Liste angezeigt
        return marke + " " + modell + " (" + leistung + " PS) | " + aussenfarbe + " | CHF " + preis;
    }
}
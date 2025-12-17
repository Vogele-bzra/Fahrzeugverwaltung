package model;

public abstract class Fahrzeug {
    private String marke;
    private String modell;
    private int hubraum;
    private String treibstoff;
    private int kmStand;
    private int leistungPS;
    private String erstzulassung;
    private String aussenfarbe;
    private double leergewicht;
    private double preis;

    public Fahrzeug(String marke, String modell, int hubraum, String treibstoff, int kmStand, int leistungPS, String erstzulassung, String aussenfarbe, double leergewicht, double preis) {
        this.marke = marke;
        this.modell = modell;
        this.hubraum = hubraum;
        this.treibstoff = treibstoff;
        this.kmStand = kmStand;
        this.leistungPS = leistungPS;
        this.erstzulassung = erstzulassung;
        this.aussenfarbe = aussenfarbe;
        this.leergewicht = leergewicht;
        this.preis = preis;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getHubraum() {
        return hubraum;
    }

    public void setHubraum(int hubraum) {
        this.hubraum = hubraum;
    }

    public String getTreibstoff() {
        return treibstoff;
    }

    public void setTreibstoff(String treibstoff) {
        this.treibstoff = treibstoff;
    }

    public int getKmStand() {
        return kmStand;
    }

    public void setKmStand(int kmStand) {
        this.kmStand = kmStand;
    }

    public int getLeistungPS() {
        return leistungPS;
    }

    public void setLeistungPS(int leistungPS) {
        this.leistungPS = leistungPS;
    }

    public String getErstzulassung() {
        return erstzulassung;
    }

    public void setErstzulassung(String erstzulassung) {
        this.erstzulassung = erstzulassung;
    }

    public String getAussenfarbe() {
        return aussenfarbe;
    }

    public void setAussenfarbe(String aussenfarbe) {
        this.aussenfarbe = aussenfarbe;
    }

    public double getLeergewicht() {
        return leergewicht;
    }

    public void setLeergewicht(double leergewicht) {
        this.leergewicht = leergewicht;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return marke + " " + modell + " " +"(" +preis +"CHF )";
    }
}

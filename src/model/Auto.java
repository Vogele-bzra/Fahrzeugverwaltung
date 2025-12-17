package model;

public class Auto extends Fahrzeug{
    private String art;
    private boolean hatNavi;

    public Auto(String marke, String modell, int hubraum, String treibstoff,
                int kmStand, int leistungPS, String erstzulassung,
                String aussenfarbe, double leergewicht, double preis,
                String aufbau, boolean hatNavi) {

        // Weitergabe an die Basisklasse
        super(marke, modell, hubraum, treibstoff, kmStand, leistungPS,
                erstzulassung, aussenfarbe, leergewicht, preis);

        this.art = art;
        this.hatNavi = hatNavi;
    }

    public String getArt() {
        return art;
    }

    public boolean isHatNavi() {
        return hatNavi;
    }
}

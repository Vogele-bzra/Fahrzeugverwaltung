package model;

import java.time.LocalDate;

public class Auto extends Fahrzeug {
    private String aufbau;
    private boolean hatNavi;

    public Auto() {
        super();
    }

    public Auto(String marke, String modell, int hubraum, String treibstoff,
                int kmStand, int ps, LocalDate erstzulassung,
                String aussenfarbe, int leergewicht, double preis,
                String aufbau, boolean hatNavi) {

        super(marke, modell, hubraum, treibstoff, kmStand, ps, erstzulassung.toString(), aussenfarbe, (double) leergewicht, preis);
        this.aufbau = aufbau;
        this.hatNavi = hatNavi;
    }

    public String getAufbau() { return aufbau; }
    public boolean isHatNavi() { return hatNavi; }

    public void setAufbau(String aufbau) { this.aufbau = aufbau; }
    public void setHatNavi(boolean hatNavi) { this.hatNavi = hatNavi; }
}
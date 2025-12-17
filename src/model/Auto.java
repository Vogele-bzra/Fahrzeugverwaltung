package model;

import java.time.LocalDate;

public class Auto extends Fahrzeug {
    private String aufbau;
    private boolean hatNavi;

    // Konstruktor 2: Dieser wird von der GUI aufgerufen
    public Auto(String marke, String modell, int hubraum, String treibstoff,
                int kmStand, int ps, LocalDate erstzulassung,
                String aussenfarbe, int leergewicht, double preis,
                String aufbau, boolean hatNavi) {

        super(
                marke,
                modell,
                hubraum,
                treibstoff,
                kmStand,
                ps,
                erstzulassung.toString(), // WICHTIG: Datum zu Text machen!
                aussenfarbe,
                (double) leergewicht,     // WICHTIG: int zu double machen!
                preis                     // WICHTIG: Standard-Preis mitgeben!
        );

        this.aufbau = aufbau;
        this.hatNavi = hatNavi;
    }

    public String getAufbau() { return aufbau; }
    public boolean isHatNavi() { return hatNavi; }
}
package model;

import java.time.LocalDate;

public class Transporter extends Fahrzeug {
    private int maximaleZuladung; // kg

    // Konstruktor für die GUI
    public Transporter(String marke, String modell, int hubraum, String treibstoff,
                       int kmStand, int ps, LocalDate erstzulassung,
                       String aussenfarbe, int leergewicht,
                       int maximaleZuladung) {

        super(
                marke,
                modell,
                hubraum,
                treibstoff,
                kmStand,
                ps,
                erstzulassung.toString(), // Datum zu String
                aussenfarbe,
                (double) leergewicht,     // int zu double
                0.0                       // Standard-Preis
        );

        this.maximaleZuladung = maximaleZuladung;
    }

    public int getMaximaleZuladung() { return maximaleZuladung; }
}
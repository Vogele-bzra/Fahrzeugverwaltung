package model;

import java.time.LocalDate;

public class Transporter extends Fahrzeug {
    private int maximaleZuladung; // kg

    public Transporter() {
        super();
    }


    public Transporter(String marke, String modell, int hubraum, String treibstoff,
                       int kmStand, int ps, LocalDate erstzulassung,
                       String aussenfarbe, int leergewicht, double preis,
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
                preis                  // Standard-Preis
        );

        this.maximaleZuladung = maximaleZuladung;
    }

    public int getMaximaleZuladung() { return maximaleZuladung; }
}
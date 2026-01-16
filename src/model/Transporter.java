package model;

import java.time.LocalDate;

public class Transporter extends Fahrzeug {
    private int maxZuladung;

    public Transporter() {
        super();
    }

    public Transporter(String marke, String modell, int hubraum, String treibstoff,
                       int kmStand, int ps, LocalDate erstzulassung,
                       String aussenfarbe, int leergewicht, double preis,
                       int maxZuladung) {

        super(marke, modell, hubraum, treibstoff, kmStand, ps, erstzulassung.toString(), aussenfarbe, (double) leergewicht, preis);
        this.maxZuladung = maxZuladung;
    }

    public int getMaxZuladung() { return maxZuladung; }

    public void setMaxZuladung(int maxZuladung) { this.maxZuladung = maxZuladung; }
}
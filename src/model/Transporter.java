package model;

public class Transporter extends Fahrzeug{
    private double maxZuladung;

    public Transporter(String marke, String modell, int hubraum, String treibstoff,
                       int kmStand, int leistungPS, String erstzulassung,
                       String aussenfarbe, double leergewicht, double preis,
                       double maxZuladung) {

        super(marke, modell, hubraum, treibstoff, kmStand, leistungPS,
                erstzulassung, aussenfarbe, leergewicht, preis);

        this.maxZuladung = maxZuladung;
    }

    public double getMaxZuladung() {
        return maxZuladung;
    }
}

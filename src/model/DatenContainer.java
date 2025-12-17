package model;
import java.util.List;

public class DatenContainer {
    private List<Auto> autos;
    private List<Transporter> transporter;

    // Leerer Konstruktor (wichtig für Gson)
    public DatenContainer() {}

    public DatenContainer(List<Auto> autos, List<Transporter> transporter) {
        this.autos = autos;
        this.transporter = transporter;
    }

    public List<Auto> getAutos() { return autos; }
    public void setAutos(List<Auto> autos) { this.autos = autos; }

    public List<Transporter> getTransporter() { return transporter; }
    public void setTransporter(List<Transporter> transporter) { this.transporter = transporter; }
}
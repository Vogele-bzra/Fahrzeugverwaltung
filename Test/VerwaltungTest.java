import controller.FahrzeugVerwaltung;
import controller.KundenVerwaltung;
import model.Auto;
import model.Fahrzeug;
import model.Kunde;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class VerwaltungTest {

    @Test
    public void testFahrzeugVerwaltung() {
        System.out.println("--- Start: Test Fahrzeug Logik ---");

        FahrzeugVerwaltung verwaltung = new FahrzeugVerwaltung();
        int anzahlVorher = verwaltung.getAlleFahrzeuge().size();

        Auto meinTestAuto = new Auto(
                "JUnitMarke",
                "TestModell",
                2000,
                "Benzin",
                10000,
                150,
                LocalDate.now(),
                "Schwarz",
                1400,
                29999.99,
                "Kombi",
                true
        );


        verwaltung.fahrzeugHinzufuegen(meinTestAuto);


        Assertions.assertEquals(anzahlVorher + 1, verwaltung.getAlleFahrzeuge().size(), "Ein Fahrzeug sollte hinzugefügt worden sein.");


        List<Fahrzeug> suchErgebnis = verwaltung.sucheNachMarke("JUnitMarke");
        Assertions.assertFalse(suchErgebnis.isEmpty(), "Das Auto sollte über die Marke gefunden werden.");
        Assertions.assertEquals("TestModell", suchErgebnis.get(0).getModell());


        verwaltung.fahrzeugLoeschen(meinTestAuto);
        Assertions.assertEquals(anzahlVorher, verwaltung.getAlleFahrzeuge().size(), "Nach dem Löschen sollte die Anzahl wieder gleich sein.");

        System.out.println("--- Ende: Fahrzeug Test erfolgreich ---");
    }

    @Test
    public void testKundenVerwaltung() {
        System.out.println("--- Start: Test Kunden Logik ---");

        KundenVerwaltung verwaltung = new KundenVerwaltung();
        int anzahlVorher = verwaltung.getAlleKunden().size();

        Kunde testKunde = new Kunde(
                "Max",
                "UnitTester",
                "Teststrasse 1",
                "1234",
                "Testcity",
                "0791234567",
                "test@junit.org",
                LocalDate.of(2000, 1, 1)
        );

        verwaltung.kundeHinzufuegen(testKunde);

        List<Kunde> ergebnis = verwaltung.sucheKunde("UnitTester");
        Assertions.assertEquals(1, ergebnis.size(), "Genau ein Kunde sollte gefunden werden.");
        Assertions.assertEquals("Max", ergebnis.get(0).getVorname());

        verwaltung.kundeLoeschen(testKunde);
        Assertions.assertEquals(anzahlVorher, verwaltung.getAlleKunden().size());

        System.out.println("--- Ende: Kunden Test erfolgreich ---");
    }

    @Test
    public void testPersistenz() {
        System.out.println("--- Start: Test Speichern & Laden (JSON) ---");

        FahrzeugVerwaltung v1 = new FahrzeugVerwaltung();
        Auto saveAuto = new Auto("SaveMarke", "SaveModell", 1000, "Diesel", 500, 100, LocalDate.now(), "Weiss", 1000, 5000, "Limousine", false);
        v1.fahrzeugHinzufuegen(saveAuto);

        FahrzeugVerwaltung v2 = new FahrzeugVerwaltung();

        Fahrzeug geladenesAuto = v2.getAlleFahrzeuge().stream()
                .filter(f -> f.getMarke().equals("SaveMarke") && f.getModell().equals("SaveModell"))
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(geladenesAuto, "Das gespeicherte Auto muss nach dem Neuladen vorhanden sein.");

        v2.fahrzeugLoeschen(geladenesAuto);

        System.out.println("--- Ende: Persistenz Test erfolgreich ---");
    }
}
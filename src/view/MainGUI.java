package view;

import javax.swing.*;

public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("IdealCar4You - Verwaltungssystem");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Das Herzstück: Die Tab-Leiste
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: Deine Fahrzeug-Verwaltung
        tabbedPane.addTab("Fahrzeugverwaltung", new FahrzeugPanel());

        // Tab 2: Die neue Kunden-Verwaltung
        tabbedPane.addTab("Kundenverwaltung", new KundenPanel());

        // Tabs zum Fenster hinzufügen
        add(tabbedPane);
    }
}
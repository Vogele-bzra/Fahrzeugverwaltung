package view;

import javax.swing.*;

public class MainGUI extends JFrame {

    // Konstruktor ist wieder leer (kein isAdmin mehr)
    public MainGUI() {
        setTitle("IdealCar4You - Fahrzeug & Kundenverwaltung");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Wir rufen die Panels jetzt ohne Parameter auf
        tabbedPane.addTab("Fahrzeugverwaltung", new FahrzeugPanel());
        tabbedPane.addTab("Kundenverwaltung", new KundenPanel());

        add(tabbedPane);
    }
}
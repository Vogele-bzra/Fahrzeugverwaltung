package view;

import javax.swing.*;

public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("IdealCar4You - Verwaltungssystem");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Fahrzeugverwaltung", new FahrzeugPanel());

        tabbedPane.addTab("Kundenverwaltung", new KundenPanel());

        add(tabbedPane);
    }
}
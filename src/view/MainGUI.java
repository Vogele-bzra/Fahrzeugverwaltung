package view;

import javax.swing.*;

public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("IdealCar4You - Fahrzeug & Kundenverwaltung");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Fahrzeugverwaltung", new FahrzeugPanel());
        tabbedPane.addTab("Kundenverwaltung", new KundenPanel());

        add(tabbedPane);

        setVisible(true);
    }
}
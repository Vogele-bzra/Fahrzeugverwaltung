package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    public MainGUI() {
        setTitle("IdealCar4You - Fahrzeug & Kundenverwaltung");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuDatei = new JMenu("Datei");
        JMenu menuAccount = new JMenu("Account");

        JMenuItem itemBeenden = new JMenuItem("Beenden");
        itemBeenden.addActionListener(e -> System.exit(0));

        JMenuItem itemLogout = new JMenuItem("Abmelden");
        itemLogout.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });

        menuDatei.add(itemBeenden);
        menuAccount.add(itemLogout);

        menuBar.add(menuDatei);
        menuBar.add(menuAccount);
        setJMenuBar(menuBar);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Fahrzeugverwaltung", new FahrzeugPanel());
        tabbedPane.addTab("Kundenverwaltung", new KundenPanel());

        add(tabbedPane);

        setVisible(true);
    }
}
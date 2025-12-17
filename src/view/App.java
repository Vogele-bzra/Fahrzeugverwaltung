package view;

import javax.swing.*;
import view.MainGUI;

public class App {
    public static void main(String[] args) {
        // Startet die GUI sicher im Event-Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGUI gui = new MainGUI();
                gui.setVisible(true); // Mach das Fenster sichtbar
            }
        });
    }
}
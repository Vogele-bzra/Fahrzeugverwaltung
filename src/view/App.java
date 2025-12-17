package view;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Direkt das Hauptfenster starten (keine Parameter nötig)
            new MainGUI().setVisible(true);
        });
    }
}
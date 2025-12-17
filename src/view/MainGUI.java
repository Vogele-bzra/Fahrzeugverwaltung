package view;

import controller.FahrzeugVerwaltung;
import model.Auto;
import model.Fahrzeug;
import model.Transporter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MainGUI extends JFrame {

    private FahrzeugVerwaltung verwaltung;

    // --- GUI Komponenten ---
    private JComboBox<String> cmbTyp;
    private JTextField txtMarke, txtModell, txtPS, txtHubraum, txtKm, txtFarbe, txtGewicht, txtPreis;
    private JComboBox<String> cmbTreibstoff;

    // Spezifisch für Auto
    private JTextField txtAufbau;
    private JCheckBox chkNavi;

    // Spezifisch für Transporter
    private JTextField txtZuladung;

    private JTextArea ausgabeBereich;

    public MainGUI() {
        verwaltung = new FahrzeugVerwaltung();

        setTitle("IdealCar4You - Erfassungsmaske");
        setSize(500, 700); // Fenster etwas höher machen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Eingabe Panel (Formular) ---
        // 0 Zeilen bedeutet: "So viele wie nötig"
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Zeile 1: Typ
        formPanel.add(new JLabel("Fahrzeugtyp:"));
        String[] typen = {"Auto", "Transporter"};
        cmbTyp = new JComboBox<>(typen);
        formPanel.add(cmbTyp);

        // Zeile 2: Marke
        formPanel.add(new JLabel("Marke:"));
        txtMarke = new JTextField();
        formPanel.add(txtMarke);

        // Zeile 3: Modell
        formPanel.add(new JLabel("Modell:"));
        txtModell = new JTextField();
        formPanel.add(txtModell);

        // Zeile 4: PS
        formPanel.add(new JLabel("Leistung (PS):"));
        txtPS = new JTextField();
        formPanel.add(txtPS);

        // Zeile 5: Hubraum
        formPanel.add(new JLabel("Hubraum (ccm):"));
        txtHubraum = new JTextField();
        formPanel.add(txtHubraum);

        // Zeile 6: Treibstoff
        formPanel.add(new JLabel("Treibstoff:"));
        String[] treibstoffe = {"Benzin", "Diesel", "Elektro", "Hybrid"};
        cmbTreibstoff = new JComboBox<>(treibstoffe);
        formPanel.add(cmbTreibstoff);

        // Zeile 7: KM-Stand
        formPanel.add(new JLabel("KM-Stand:"));
        txtKm = new JTextField();
        formPanel.add(txtKm);

        // Zeile 8: Farbe
        formPanel.add(new JLabel("Aussenfarbe:"));
        txtFarbe = new JTextField();
        formPanel.add(txtFarbe);

        // Zeile 9: Leergewicht
        formPanel.add(new JLabel("Leergewicht (kg):"));
        txtGewicht = new JTextField();
        formPanel.add(txtGewicht);

        // Zeile 10: Preis (fehlt im Model noch als Variable, aber wir erfassen ihn schon mal)
        formPanel.add(new JLabel("Preis (CHF):"));
        txtPreis = new JTextField();
        formPanel.add(txtPreis);

        // --- Trenner ---
        formPanel.add(new JSeparator());
        formPanel.add(new JLabel("--- Spezifische Daten ---"));

        // Zeile 11: Aufbau (Nur für Auto relevant)
        formPanel.add(new JLabel("Aufbau (nur Auto):"));
        txtAufbau = new JTextField("Kombi");
        formPanel.add(txtAufbau);

        // Zeile 12: Navi (Nur für Auto relevant)
        formPanel.add(new JLabel("Navigationssystem:"));
        chkNavi = new JCheckBox("Vorhanden");
        formPanel.add(chkNavi);

        // Zeile 13: Zuladung (Nur für Transporter relevant)
        formPanel.add(new JLabel("Max. Zuladung (kg, nur Transp.):"));
        txtZuladung = new JTextField("0");
        formPanel.add(txtZuladung);

        // Buttons
        JButton btnSpeichern = new JButton("Speichern");
        JButton btnListe = new JButton("Liste aktualisieren");

        // Ein extra Panel für die Buttons unten
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnSpeichern);
        buttonPanel.add(btnListe);

        // Alles zusammenbauen
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER); // Buttons in die Mitte unter das Formular

        ausgabeBereich = new JTextArea(10, 30);
        ausgabeBereich.setEditable(false);
        add(new JScrollPane(ausgabeBereich), BorderLayout.SOUTH); // Liste ganz unten

        // --- Logik ---
        btnSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speichern();
            }
        });

        btnListe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listeAnzeigen();
            }
        });
    }

    private void speichern() {
        try {
            // 1. Alle Texte auslesen
            String typ = (String) cmbTyp.getSelectedItem();
            String marke = txtMarke.getText();
            String modell = txtModell.getText();
            String farbe = txtFarbe.getText();
            String treibstoff = (String) cmbTreibstoff.getSelectedItem();

            // 2. Zahlen umwandeln (kann abstürzen, wenn Text drin steht -> try/catch)
            int ps = Integer.parseInt(txtPS.getText());
            int hubraum = Integer.parseInt(txtHubraum.getText());
            int km = Integer.parseInt(txtKm.getText());
            int gewicht = Integer.parseInt(txtGewicht.getText());
            // Preis ignorieren wir kurz für das Objekt, da Konstruktor angepasst wurde
            // double preis = Double.parseDouble(txtPreis.getText());

            Fahrzeug neuesFahrzeug;

            // 3. Je nach Typ das richtige Objekt bauen
            if (typ.equals("Auto")) {
                String aufbau = txtAufbau.getText();
                boolean hatNavi = chkNavi.isSelected();

                neuesFahrzeug = new Auto(marke, modell, hubraum, treibstoff, km, ps, LocalDate.now(), farbe, gewicht, aufbau, hatNavi);
            } else {
                int zuladung = Integer.parseInt(txtZuladung.getText());

                neuesFahrzeug = new Transporter(marke, modell, hubraum, treibstoff, km, ps, LocalDate.now(), farbe, gewicht, zuladung);
            }

            // 4. Ab zum Controller
            verwaltung.fahrzeugHinzufuegen(neuesFahrzeug);

            // 5. Feedback
            JOptionPane.showMessageDialog(this, "Fahrzeug gespeichert!");
            listeAnzeigen();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Bitte bei Zahlenfeldern (PS, KM, Gewicht) nur Ziffern eingeben!", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fehler: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void listeAnzeigen() {
        ausgabeBereich.setText("");
        ausgabeBereich.append("--- Bestand ---\n");
        for (Fahrzeug f : verwaltung.getAlleFahrzeuge()) {
            ausgabeBereich.append(f.getMarke() + " " + f.getModell() + " (" + f.getLeistung() + " PS), Farbe: " + f.getAussenfarbe() + "\n");
        }
    }
}
package view;

import controller.KundenVerwaltung;
import model.Kunde;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class KundenPanel extends JPanel {

    private KundenVerwaltung verwaltung;

    private JTextField txtVorname, txtNachname, txtStrasse, txtPlz, txtOrt, txtTelefon, txtEmail, txtGeburtsdatum;
    private JTextField txtSuche;

    private JList<Kunde> kundenListe;
    private DefaultListModel<Kunde> listModel;

    // --- NEU: Variablen für das Editieren ---
    private Kunde aktuellBearbeiteterKunde = null; // Merkt sich, wen wir bearbeiten
    private JButton btnSpeichern;                  // Button ist jetzt hier oben definiert
    // ----------------------------------------

    public KundenPanel() {
        this.verwaltung = new KundenVerwaltung();
        setLayout(new BorderLayout(10, 10));

        // --- FORMULAR ---
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Kunden erfassen / bearbeiten"));

        formPanel.add(new JLabel("Vorname:"));
        txtVorname = new JTextField();
        formPanel.add(txtVorname);

        formPanel.add(new JLabel("Nachname:"));
        txtNachname = new JTextField();
        formPanel.add(txtNachname);

        formPanel.add(new JLabel("Strasse & Nr:"));
        txtStrasse = new JTextField();
        formPanel.add(txtStrasse);

        formPanel.add(new JLabel("PLZ:"));
        txtPlz = new JTextField();
        formPanel.add(txtPlz);

        formPanel.add(new JLabel("Ort:"));
        txtOrt = new JTextField();
        formPanel.add(txtOrt);

        formPanel.add(new JLabel("Telefon:"));
        txtTelefon = new JTextField();
        formPanel.add(txtTelefon);

        formPanel.add(new JLabel("E-Mail:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Geburtsdatum (YYYY-MM-DD):"));
        txtGeburtsdatum = new JTextField("2000-01-01");
        formPanel.add(txtGeburtsdatum);

        // --- BUTTONS ---
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // HIER GEÄNDERT: Variable wurde oben schon deklariert
        btnSpeichern = new JButton("Speichern");

        JLabel lblSuche = new JLabel("Suche (Nachname):");
        txtSuche = new JTextField(8);
        JButton btnSuchen = new JButton("Go");
        JButton btnReset = new JButton("Alle / Reset");

        JButton btnLoeschen = new JButton("Löschen");
        btnLoeschen.setForeground(Color.RED);

        buttonPanel.add(btnSpeichern);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(lblSuche);
        buttonPanel.add(txtSuche);
        buttonPanel.add(btnSuchen);
        buttonPanel.add(btnReset);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(btnLoeschen);

        // --- LISTE ---
        listModel = new DefaultListModel<>();
        kundenListe = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(kundenListe);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Kundenliste"));

        // --- NEU: Listener für Klick auf die Liste (Editieren starten) ---
        kundenListe.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Kunde k = kundenListe.getSelectedValue();
                if (k != null) {
                    kundenLaden(k); // Methode ganz unten
                }
            }
        });

        // --- LAYOUT FIX (damit die Liste sichtbar ist) ---
        // Wir packen Formular und Buttons zusammen nach OBEN (North)
        JPanel obenContainer = new JPanel(new BorderLayout());
        obenContainer.add(formPanel, BorderLayout.CENTER);
        obenContainer.add(buttonPanel, BorderLayout.SOUTH);

        add(obenContainer, BorderLayout.NORTH); // Alles Eingabe-Zeug oben
        add(scrollPane, BorderLayout.CENTER);   // Liste bekommt den ganzen Rest-Platz!
        // ------------------------------------------------

        listeAktualisieren(verwaltung.getAlleKunden());

        // --- EVENT LISTENER ---
        btnSpeichern.addActionListener(e -> kundeSpeichern());
        btnLoeschen.addActionListener(e -> kundeLoeschen());

        btnSuchen.addActionListener(e -> {
            String suchText = txtSuche.getText();
            List<Kunde> ergebnis = verwaltung.sucheKunde(suchText);
            listeAktualisieren(ergebnis);
        });

        btnReset.addActionListener(e -> {
            txtSuche.setText("");
            felderLeeren(); // Setzt alles zurück
            listeAktualisieren(verwaltung.getAlleKunden());
        });
    }

    // --- NEU: Die intelligente Speichern-Methode ---
    private void kundeSpeichern() {
        try {
            // Daten auslesen
            String vorname = txtVorname.getText();
            String nachname = txtNachname.getText();
            String strasse = txtStrasse.getText();
            String plz = txtPlz.getText();
            String ort = txtOrt.getText();
            String telefon = txtTelefon.getText();
            String email = txtEmail.getText();
            LocalDate geburt = LocalDate.parse(txtGeburtsdatum.getText());

            // ENTSCHEIDUNG: Neu oder Update?
            if (aktuellBearbeiteterKunde == null) {
                // FALL A: Neu anlegen
                Kunde k = new Kunde(vorname, nachname, strasse, plz, ort, telefon, email, geburt);
                verwaltung.kundeHinzufuegen(k);
                JOptionPane.showMessageDialog(this, "Neuer Kunde gespeichert!");
            } else {
                // FALL B: Existierenden Kunden bearbeiten (Setter)
                aktuellBearbeiteterKunde.setVorname(vorname);
                aktuellBearbeiteterKunde.setNachname(nachname);
                aktuellBearbeiteterKunde.setStrasse(strasse);
                aktuellBearbeiteterKunde.setPlz(plz);
                aktuellBearbeiteterKunde.setOrt(ort);
                aktuellBearbeiteterKunde.setTelefon(telefon);
                aktuellBearbeiteterKunde.setEmail(email);
                aktuellBearbeiteterKunde.setGeburtsdatum(geburt);

                // Speichern in Datei auslösen
                verwaltung.aenderungenSpeichern();
                JOptionPane.showMessageDialog(this, "Änderungen gespeichert!");
            }

            felderLeeren(); // Formular leeren & Modus zurücksetzen
            listeAktualisieren(verwaltung.getAlleKunden());

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Bitte Datum im Format YYYY-MM-DD eingeben!", "Datumsfehler", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fehler: " + ex.getMessage());
        }
    }

    // --- NEU: Hilfsmethode zum Laden eines Kunden in die Textfelder ---
    private void kundenLaden(Kunde k) {
        aktuellBearbeiteterKunde = k; // Merken!

        txtVorname.setText(k.getVorname());
        txtNachname.setText(k.getNachname());
        txtStrasse.setText(k.getStrasse());
        txtPlz.setText(k.getPlz());
        txtOrt.setText(k.getOrt());
        txtTelefon.setText(k.getTelefon());
        txtEmail.setText(k.getEmail());
        txtGeburtsdatum.setText(k.getGeburtsdatum().toString());

        // Optik ändern
        btnSpeichern.setText("Änderungen speichern");
        btnSpeichern.setForeground(Color.BLUE);
    }

    // --- NEU: Hilfsmethode zum Zurücksetzen ---
    private void felderLeeren() {
        txtVorname.setText("");
        txtNachname.setText("");
        txtStrasse.setText("");
        txtPlz.setText("");
        txtOrt.setText("");
        txtTelefon.setText("");
        txtEmail.setText("");
        txtGeburtsdatum.setText("2000-01-01");

        // Modus Reset
        aktuellBearbeiteterKunde = null;
        btnSpeichern.setText("Speichern");
        btnSpeichern.setForeground(Color.BLACK);
        kundenListe.clearSelection();
    }

    private void kundeLoeschen() {
        Kunde k = kundenListe.getSelectedValue();
        if (k != null) {
            // Sicherheit: Falls wir den gerade bearbeiten, Reset machen
            if (k == aktuellBearbeiteterKunde) {
                felderLeeren();
            }
            verwaltung.kundeLoeschen(k);
            listeAktualisieren(verwaltung.getAlleKunden());
        } else {
            JOptionPane.showMessageDialog(this, "Bitte erst einen Kunden auswählen.");
        }
    }

    private void listeAktualisieren(List<Kunde> daten) {
        listModel.clear();
        for (Kunde k : daten) {
            listModel.addElement(k);
        }
    }
}
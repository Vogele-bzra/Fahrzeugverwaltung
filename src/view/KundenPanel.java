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

    // Konstruktor ohne Parameter
    public KundenPanel() {
        this.verwaltung = new KundenVerwaltung();
        setLayout(new BorderLayout(10, 10));

        // --- Formular ---
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Neuen Kunden erfassen"));

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

        // --- Buttons ---
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnSpeichern = new JButton("Speichern");

        // Suchelemente
        JLabel lblSuche = new JLabel("Suche (Nachname):");
        txtSuche = new JTextField(8);
        JButton btnSuchen = new JButton("Go");
        JButton btnReset = new JButton("Alle");

        JButton btnLoeschen = new JButton("Löschen");
        btnLoeschen.setForeground(Color.RED);
        // Button ist immer aktiv!

        buttonPanel.add(btnSpeichern);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(lblSuche);
        buttonPanel.add(txtSuche);
        buttonPanel.add(btnSuchen);
        buttonPanel.add(btnReset);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(btnLoeschen);

        // --- Liste ---
        listModel = new DefaultListModel<>();
        kundenListe = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(kundenListe);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Kundenliste"));

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Liste initial füllen
        listeAktualisieren(verwaltung.getAlleKunden());

        // --- Logik (Listener) ---
        btnSpeichern.addActionListener(e -> kundeSpeichern());
        btnLoeschen.addActionListener(e -> kundeLoeschen());

        btnSuchen.addActionListener(e -> {
            String suchText = txtSuche.getText();
            List<Kunde> ergebnis = verwaltung.sucheKunde(suchText);
            listeAktualisieren(ergebnis);
        });

        btnReset.addActionListener(e -> {
            txtSuche.setText("");
            listeAktualisieren(verwaltung.getAlleKunden());
        });
    }

    private void kundeSpeichern() {
        try {
            String vorname = txtVorname.getText();
            String nachname = txtNachname.getText();
            String strasse = txtStrasse.getText();
            String plz = txtPlz.getText();
            String ort = txtOrt.getText();
            String telefon = txtTelefon.getText();
            String email = txtEmail.getText();
            LocalDate geburt = LocalDate.parse(txtGeburtsdatum.getText());

            Kunde k = new Kunde(vorname, nachname, strasse, plz, ort, telefon, email, geburt);
            verwaltung.kundeHinzufuegen(k);

            listeAktualisieren(verwaltung.getAlleKunden());
            JOptionPane.showMessageDialog(this, "Kunde gespeichert!");

            txtVorname.setText("");
            txtNachname.setText("");
            txtStrasse.setText("");
            txtPlz.setText("");
            txtOrt.setText("");
            txtTelefon.setText("");
            txtEmail.setText("");

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Bitte Datum im Format YYYY-MM-DD eingeben!", "Datumsfehler", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fehler: " + ex.getMessage());
        }
    }

    private void kundeLoeschen() {
        Kunde k = kundenListe.getSelectedValue();
        if (k != null) {
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
        if (listModel.isEmpty()) {
            // Optional: JOptionPane.showMessageDialog(this, "Keine Kunden gefunden.");
        }
    }
}
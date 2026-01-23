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

    private Kunde aktuellBearbeiteterKunde = null;
    private JButton btnSpeichern;

    public KundenPanel() {
        this.verwaltung = new KundenVerwaltung();
        setLayout(new BorderLayout(10, 10));

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

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnSpeichern = new JButton("Speichern");

        JButton btnLoeschen = new JButton("Löschen");
        btnLoeschen.setForeground(Color.RED);

        JButton btnReset = new JButton("Reset / Neu");

        JLabel lblSuche = new JLabel("Suche (Nachname):");
        txtSuche = new JTextField(10);
        JButton btnSuchen = new JButton("Go");

        buttonPanel.add(btnSpeichern);
        buttonPanel.add(Box.createHorizontalStrut(5));
        buttonPanel.add(btnLoeschen);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(btnReset);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(lblSuche);
        buttonPanel.add(txtSuche);
        buttonPanel.add(btnSuchen);

        listModel = new DefaultListModel<>();
        kundenListe = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(kundenListe);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Kundenliste"));

        kundenListe.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Kunde k = kundenListe.getSelectedValue();
                if (k != null) {
                    kundenLaden(k);
                }
            }
        });

        JPanel obenContainer = new JPanel(new BorderLayout());
        obenContainer.add(formPanel, BorderLayout.CENTER);
        obenContainer.add(buttonPanel, BorderLayout.SOUTH);

        add(obenContainer, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        listeAktualisieren(verwaltung.getAlleKunden());

        btnSpeichern.addActionListener(e -> kundeSpeichern());

        btnLoeschen.addActionListener(e -> kundeLoeschen());

        btnSuchen.addActionListener(e -> {
            String suchText = txtSuche.getText();
            List<Kunde> ergebnis = verwaltung.sucheKunde(suchText);
            listeAktualisieren(ergebnis);
        });

        btnReset.addActionListener(e -> {
            txtSuche.setText("");
            felderLeeren();
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

            if (aktuellBearbeiteterKunde == null) {
                Kunde k = new Kunde(vorname, nachname, strasse, plz, ort, telefon, email, geburt);
                verwaltung.kundeHinzufuegen(k);
                JOptionPane.showMessageDialog(this, "Neuer Kunde gespeichert!");
            } else {
                aktuellBearbeiteterKunde.setVorname(vorname);
                aktuellBearbeiteterKunde.setNachname(nachname);
                aktuellBearbeiteterKunde.setStrasse(strasse);
                aktuellBearbeiteterKunde.setPlz(plz);
                aktuellBearbeiteterKunde.setOrt(ort);
                aktuellBearbeiteterKunde.setTelefon(telefon);
                aktuellBearbeiteterKunde.setEmail(email);
                aktuellBearbeiteterKunde.setGeburtsdatum(geburt);

                verwaltung.aenderungenSpeichern();
                JOptionPane.showMessageDialog(this, "Änderungen gespeichert!");
            }

            felderLeeren();
            listeAktualisieren(verwaltung.getAlleKunden());

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Bitte Datum im Format YYYY-MM-DD eingeben!", "Datumsfehler", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fehler: " + ex.getMessage());
        }
    }

    private void kundenLaden(Kunde k) {
        aktuellBearbeiteterKunde = k;

        txtVorname.setText(k.getVorname());
        txtNachname.setText(k.getNachname());
        txtStrasse.setText(k.getStrasse());
        txtPlz.setText(k.getPlz());
        txtOrt.setText(k.getOrt());
        txtTelefon.setText(k.getTelefon());
        txtEmail.setText(k.getEmail());
        txtGeburtsdatum.setText(k.getGeburtsdatum().toString());

        btnSpeichern.setText("Änderungen speichern");
        btnSpeichern.setForeground(Color.BLUE);
    }

    private void felderLeeren() {
        txtVorname.setText("");
        txtNachname.setText("");
        txtStrasse.setText("");
        txtPlz.setText("");
        txtOrt.setText("");
        txtTelefon.setText("");
        txtEmail.setText("");
        txtGeburtsdatum.setText("2000-01-01");

        aktuellBearbeiteterKunde = null;
        btnSpeichern.setText("Speichern");
        btnSpeichern.setForeground(Color.BLACK);
        kundenListe.clearSelection();
    }

    private void kundeLoeschen() {
        Kunde k = kundenListe.getSelectedValue();
        if (k != null) {
            if (k == aktuellBearbeiteterKunde) {
                felderLeeren();
            }
            verwaltung.kundeLoeschen(k);
            listeAktualisieren(verwaltung.getAlleKunden());
            JOptionPane.showMessageDialog(this, "Kunde gelöscht.");
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
package view;

import controller.FahrzeugVerwaltung;
import model.Auto;
import model.Fahrzeug;
import model.Transporter;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class FahrzeugPanel extends JPanel {

    private FahrzeugVerwaltung verwaltung;

    private JComboBox<String> cmbTyp;
    private JTextField txtMarke, txtModell, txtPS, txtHubraum, txtKm, txtFarbe, txtGewicht, txtPreis;
    private JComboBox<String> cmbTreibstoff;
    private JTextField txtAufbau;
    private JCheckBox chkNavi;
    private JTextField txtZuladung;
    private JTextField txtSuche;

    private JList<Fahrzeug> anzeigeListe;
    private DefaultListModel<Fahrzeug> listModel;

    private Fahrzeug aktuellBearbeitetesFahrzeug = null;
    private JButton btnSpeichern;
    public FahrzeugPanel() {
        verwaltung = new FahrzeugVerwaltung();
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Fahrzeugdaten"));

        formPanel.add(new JLabel("Fahrzeugtyp:"));
        String[] typen = {"Auto", "Transporter"};
        cmbTyp = new JComboBox<>(typen);
        formPanel.add(cmbTyp);

        formPanel.add(new JLabel("Marke:"));
        txtMarke = new JTextField();
        formPanel.add(txtMarke);

        formPanel.add(new JLabel("Modell:"));
        txtModell = new JTextField();
        formPanel.add(txtModell);

        formPanel.add(new JLabel("Leistung (PS):"));
        txtPS = new JTextField();
        formPanel.add(txtPS);

        formPanel.add(new JLabel("Hubraum (ccm):"));
        txtHubraum = new JTextField();
        formPanel.add(txtHubraum);

        formPanel.add(new JLabel("Treibstoff:"));
        String[] treibstoffe = {"Benzin", "Diesel", "Elektro", "Hybrid"};
        cmbTreibstoff = new JComboBox<>(treibstoffe);
        formPanel.add(cmbTreibstoff);

        formPanel.add(new JLabel("KM-Stand:"));
        txtKm = new JTextField();
        formPanel.add(txtKm);

        formPanel.add(new JLabel("Aussenfarbe:"));
        txtFarbe = new JTextField();
        formPanel.add(txtFarbe);

        formPanel.add(new JLabel("Leergewicht (kg):"));
        txtGewicht = new JTextField();
        formPanel.add(txtGewicht);

        formPanel.add(new JLabel("Preis (CHF):"));
        txtPreis = new JTextField();
        formPanel.add(txtPreis);

        formPanel.add(new JSeparator());
        formPanel.add(new JLabel("--- Spezifische Daten ---"));

        formPanel.add(new JLabel("Aufbau (nur Auto):"));
        txtAufbau = new JTextField("Kombi");
        formPanel.add(txtAufbau);

        formPanel.add(new JLabel("Navigationssystem:"));
        chkNavi = new JCheckBox("Vorhanden");
        formPanel.add(chkNavi);

        formPanel.add(new JLabel("Max. Zuladung (kg, nur Transp.):"));
        txtZuladung = new JTextField("0");
        formPanel.add(txtZuladung);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        btnSpeichern = new JButton("Speichern");

        JButton btnLoeschen = new JButton("Löschen");
        btnLoeschen.setForeground(Color.RED);

        JLabel lblSuche = new JLabel("Suche:");
        txtSuche = new JTextField(8);
        JButton btnSuchen = new JButton("Go");
        JButton btnAlle = new JButton("Alle / Reset");

        buttonPanel.add(btnSpeichern);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(lblSuche);
        buttonPanel.add(txtSuche);
        buttonPanel.add(btnSuchen);
        buttonPanel.add(btnAlle);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(btnLoeschen);

        listModel = new DefaultListModel<>();
        anzeigeListe = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(anzeigeListe);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Fahrzeugbestand"));

        anzeigeListe.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Fahrzeug f = anzeigeListe.getSelectedValue();
                if (f != null) {
                    fahrzeugLaden(f);
                }
            }
        });

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(formPanel, BorderLayout.CENTER);
        topContainer.add(buttonPanel, BorderLayout.SOUTH);

        add(topContainer, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnSpeichern.addActionListener(e -> speichern());
        btnSuchen.addActionListener(e -> suchErgebnisseAnzeigen());

        btnAlle.addActionListener(e -> {
            txtSuche.setText("");
            felderLeeren();
            listeAnzeigen();
        });

        btnLoeschen.addActionListener(e -> eintragLoeschen());

        listeAnzeigen();
    }

    private void speichern() {
        try {
            String typ = (String) cmbTyp.getSelectedItem();
            String marke = txtMarke.getText();
            String modell = txtModell.getText();
            String farbe = txtFarbe.getText();
            String treibstoff = (String) cmbTreibstoff.getSelectedItem();

            int ps = Integer.parseInt(txtPS.getText());
            int hubraum = Integer.parseInt(txtHubraum.getText());
            int km = Integer.parseInt(txtKm.getText());
            int gewicht = Integer.parseInt(txtGewicht.getText());
            double preis = Double.parseDouble(txtPreis.getText());

            if (aktuellBearbeitetesFahrzeug != null) {
                aktuellBearbeitetesFahrzeug.setMarke(marke);
                aktuellBearbeitetesFahrzeug.setModell(modell);
                aktuellBearbeitetesFahrzeug.setPs(ps);
                aktuellBearbeitetesFahrzeug.setHubraum(hubraum);
                aktuellBearbeitetesFahrzeug.setTreibstoff(treibstoff);
                aktuellBearbeitetesFahrzeug.setKmStand(km);
                aktuellBearbeitetesFahrzeug.setAussenfarbe(farbe);
                aktuellBearbeitetesFahrzeug.setLeergewicht((double)gewicht);
                aktuellBearbeitetesFahrzeug.setPreis(preis);

                if (aktuellBearbeitetesFahrzeug instanceof Auto) {
                    ((Auto) aktuellBearbeitetesFahrzeug).setAufbau(txtAufbau.getText());
                    ((Auto) aktuellBearbeitetesFahrzeug).setHatNavi(chkNavi.isSelected());
                } else if (aktuellBearbeitetesFahrzeug instanceof Transporter) {
                    ((Transporter) aktuellBearbeitetesFahrzeug).setMaxZuladung(Integer.parseInt(txtZuladung.getText()));
                }

                verwaltung.aenderungenSpeichern();
                JOptionPane.showMessageDialog(this, "Änderungen gespeichert!");

            } else {
                Fahrzeug neuesFahrzeug;
                if (typ.equals("Auto")) {
                    String aufbau = txtAufbau.getText();
                    boolean hatNavi = chkNavi.isSelected();
                    neuesFahrzeug = new Auto(marke, modell, hubraum, treibstoff, km, ps, LocalDate.now(), farbe, gewicht, preis, aufbau, hatNavi);
                } else {
                    int zuladung = Integer.parseInt(txtZuladung.getText());
                    neuesFahrzeug = new Transporter(marke, modell, hubraum, treibstoff, km, ps, LocalDate.now(), farbe, gewicht, preis, zuladung);
                }
                verwaltung.fahrzeugHinzufuegen(neuesFahrzeug);
                JOptionPane.showMessageDialog(this, "Neues Fahrzeug gespeichert!");
            }

            felderLeeren();
            listeAnzeigen();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Bitte Zahlen korrekt eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fehler: " + ex.getMessage());
        }
    }

    private void fahrzeugLaden(Fahrzeug f) {
        aktuellBearbeitetesFahrzeug = f;

        txtMarke.setText(f.getMarke());
        txtModell.setText(f.getModell());
        txtPS.setText(String.valueOf(f.getPs()));
        txtHubraum.setText(String.valueOf(f.getHubraum()));
        cmbTreibstoff.setSelectedItem(f.getTreibstoff());
        txtKm.setText(String.valueOf(f.getKmStand()));
        txtFarbe.setText(f.getAussenfarbe());
        txtGewicht.setText(String.valueOf((int)f.getLeergewicht()));
        txtPreis.setText(String.valueOf(f.getPreis()));

        if (f instanceof Auto) {
            Auto a = (Auto) f;
            cmbTyp.setSelectedItem("Auto");
            txtAufbau.setText(a.getAufbau());
            chkNavi.setSelected(a.isHatNavi());
            txtZuladung.setText("0");
        } else if (f instanceof Transporter) {
            Transporter t = (Transporter) f;
            cmbTyp.setSelectedItem("Transporter");
            txtZuladung.setText(String.valueOf(t.getMaxZuladung()));
            txtAufbau.setText("-");
            chkNavi.setSelected(false);
        }

        btnSpeichern.setText("Änderungen speichern");
        btnSpeichern.setForeground(Color.BLUE);
    }

    private void felderLeeren() {
        txtMarke.setText("");
        txtModell.setText("");
        txtPS.setText("");
        txtHubraum.setText("");
        txtKm.setText("");
        txtFarbe.setText("");
        txtGewicht.setText("");
        txtPreis.setText("");
        txtAufbau.setText("Kombi");
        chkNavi.setSelected(false);
        txtZuladung.setText("0");

        aktuellBearbeitetesFahrzeug = null;
        btnSpeichern.setText("Speichern");
        btnSpeichern.setForeground(Color.BLACK);
        anzeigeListe.clearSelection();
    }

    private void eintragLoeschen() {
        Fahrzeug gewaehltes = anzeigeListe.getSelectedValue();
        if (gewaehltes != null) {
            if (gewaehltes == aktuellBearbeitetesFahrzeug) {
                felderLeeren();
            }
            verwaltung.fahrzeugLoeschen(gewaehltes);
            listeAnzeigen();
        }
    }

    private void listeAnzeigen() {
        listModel.clear();
        for (Fahrzeug f : verwaltung.getAlleFahrzeuge()) {
            listModel.addElement(f);
        }
    }

    private void suchErgebnisseAnzeigen() {
        listModel.clear();
        for (Fahrzeug f : verwaltung.sucheNachMarke(txtSuche.getText())) {
            listModel.addElement(f);
        }
    }
}
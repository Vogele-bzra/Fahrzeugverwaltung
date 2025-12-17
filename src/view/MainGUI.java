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

    private JComboBox<String> cmbTyp;
    private JTextField txtMarke, txtModell, txtPS, txtHubraum, txtKm, txtFarbe, txtGewicht, txtPreis;
    private JComboBox<String> cmbTreibstoff;

    private JTextField txtAufbau;
    private JCheckBox chkNavi;
    private JTextField txtZuladung;

    private JTextField txtSuche;

    private JList<Fahrzeug> anzeigeListe;
    private DefaultListModel<Fahrzeug> listModel;

    public MainGUI() {
        verwaltung = new FahrzeugVerwaltung();

        setTitle("IdealCar4You - Erfassungsmaske");
        setSize(550, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        JButton btnSpeichern = new JButton("Speichern");
        JButton btnLoeschen = new JButton("Markiertes löschen");
        btnLoeschen.setForeground(Color.RED);

        JLabel lblSuche = new JLabel("Suche:");
        txtSuche = new JTextField(8);
        JButton btnSuchen = new JButton("Go");
        JButton btnAlle = new JButton("Alle");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnSpeichern);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(lblSuche);
        buttonPanel.add(txtSuche);
        buttonPanel.add(btnSuchen);
        buttonPanel.add(btnAlle);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(btnLoeschen);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        listModel = new DefaultListModel<>();
        anzeigeListe = new JList<>(listModel);
        anzeigeListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(anzeigeListe), BorderLayout.SOUTH);

        btnSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speichern();
            }
        });

        btnSuchen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suchErgebnisseAnzeigen();
            }
        });

        btnAlle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSuche.setText("");
                listeAnzeigen();
            }
        });

        btnLoeschen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eintragLoeschen();
            }
        });
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

            JOptionPane.showMessageDialog(this, "Gespeichert!");
            listeAnzeigen();

            txtMarke.setText("");
            txtModell.setText("");
            txtPS.setText("");
            txtPreis.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Bitte bei Zahlenfeldern nur Ziffern eingeben! (Preis mit Punkt)", "Fehler", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fehler: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void eintragLoeschen() {
        Fahrzeug gewaehltes = anzeigeListe.getSelectedValue();

        if (gewaehltes != null) {
            int antwort = JOptionPane.showConfirmDialog(this,
                    "Möchten Sie " + gewaehltes.getMarke() + " " + gewaehltes.getModell() + " wirklich löschen?",
                    "Löschen bestätigen", JOptionPane.YES_NO_OPTION);

            if (antwort == JOptionPane.YES_OPTION) {
                verwaltung.fahrzeugLoeschen(gewaehltes);
                listeAnzeigen();
                JOptionPane.showMessageDialog(this, "Gelöscht!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie erst ein Fahrzeug in der Liste aus!");
        }
    }

    private void listeAnzeigen() {
        listModel.clear();
        for (Fahrzeug f : verwaltung.getAlleFahrzeuge()) {
            listModel.addElement(f);
        }
    }

    private void suchErgebnisseAnzeigen() {
        String gesuchteMarke = txtSuche.getText();
        listModel.clear();

        java.util.List<Fahrzeug> treffer = verwaltung.sucheNachMarke(gesuchteMarke);

        if (treffer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keine Fahrzeuge gefunden.");
            listeAnzeigen();
        } else {
            for (Fahrzeug f : treffer) {
                listModel.addElement(f);
            }
        }
    }
}
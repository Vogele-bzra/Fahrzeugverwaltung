package view;

import Service.UserSpeicherService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginGUI extends JFrame {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JButton btnRegister;

    private UserSpeicherService userService;

    public LoginGUI() {
        userService = new UserSpeicherService();

        setTitle("Login - IdealCar4You");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        JLabel lblHeader = new JLabel("Willkommen", SwingConstants.CENTER);
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(lblHeader, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 0, 10),
                BorderFactory.createTitledBorder("Anmeldedaten")
        ));

        formPanel.add(new JLabel("Benutzername:"));
        txtUser = new JTextField();
        formPanel.add(txtUser);

        formPanel.add(new JLabel("Passwort:"));
        txtPass = new JPasswordField();
        formPanel.add(txtPass);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnLogin = new JButton("Einloggen");
        btnRegister = new JButton("Registrieren");

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        add(buttonPanel, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> checkLogin());
        btnRegister.addActionListener(e -> registerUser());

        txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkLogin();
                }
            }
        });

        setVisible(true);
    }

    private void checkLogin() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        if (userService.checkCredentials(user, pass)) {
            dispose();
            SwingUtilities.invokeLater(() -> {
                try {
                    new MainGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage());
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "Falsche Zugangsdaten!", "Login Fehler", JOptionPane.ERROR_MESSAGE);
            txtPass.setText("");
        }
    }

    private void registerUser() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte Name und Passwort eingeben!");
            return;
        }

        userService.createUser(user, pass);
        JOptionPane.showMessageDialog(this, "Benutzer '" + user + "' wurde angelegt!");
    }
}
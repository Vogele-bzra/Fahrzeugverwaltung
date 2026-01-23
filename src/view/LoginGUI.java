package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginGUI extends JFrame {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginGUI() {
        setTitle("Login - IdealCar4You");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel panelCenter = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCenter.add(new JLabel("Benutzername:"));
        txtUser = new JTextField();
        panelCenter.add(txtUser);

        panelCenter.add(new JLabel("Passwort:"));
        txtPass = new JPasswordField();
        panelCenter.add(txtPass);

        JPanel panelSouth = new JPanel(new FlowLayout());
        btnLogin = new JButton("Einloggen");
        panelSouth.add(btnLogin);

        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> checkLogin());

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

        if (user.equals("admin") && pass.equals("1234")) {
            dispose();
            SwingUtilities.invokeLater(() -> {
                try {
                    new MainGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Fehler beim Starten: " + ex.getMessage() + "\nDetails siehe Konsole.");
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "Falsche Zugangsdaten!", "Login Fehler", JOptionPane.ERROR_MESSAGE);
            txtPass.setText("");
        }
    }
}
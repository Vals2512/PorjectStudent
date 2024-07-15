package co.edu.uptc.student.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.User;
import co.edu.uptc.student.persistence.UserManagement;

public class LoginScreen extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel labelImage;
    private JLabel labelNameUser;
    private JTextField txtNameUSER;
    private JLabel labelPassword;
    private JPasswordField txtPassword;
    private JButton buttonLogin;

    private UserManagement um;

    public LoginScreen() {
        this.um = new UserManagement();
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
    }

    private void configureScreen() {
        setTitle("Login");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.GRAY);

    }

    private void buildComponents() {

        this.labelImage = new JLabel(new ImageIcon("resources/images/loginImage.png"));
        this.labelNameUser = new JLabel("User");
        this.labelPassword = new JLabel("Password");

        this.txtNameUSER = new JTextField(15);
        this.txtPassword = new JPasswordField(15);
        this.txtNameUSER.setBackground(Color.lightGray);
        this.txtPassword.setBackground(Color.lightGray);

        this.buttonLogin = new JButton("Sign in");

        Font boldFont = new Font("Times", Font.BOLD, 24);
        this.labelNameUser.setFont(boldFont);
        this.labelPassword.setFont(boldFont);

    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Inserción de la imagen en la parte izquierda
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(labelImage, gbc);

        gbc.gridheight = 1; // Reset gridheight
        gbc.anchor = GridBagConstraints.WEST; // Align to the left

        // Inserción del título "User"
        gbc.gridx = 1;
        gbc.gridy = 1;

        this.add(labelNameUser, gbc);

        // Inserción del input del usuario
        gbc.gridx = 2;
        gbc.gridy = 1;

        this.add(txtNameUSER, gbc);

        // Inserción del título "Password"
        gbc.gridx = 1;
        gbc.gridy = 2;

        this.add(labelPassword, gbc);

        // Inserción del input contraseña
        gbc.gridx = 2;
        gbc.gridy = 2;

        this.add(txtPassword, gbc);

        // Inserción del botón "Sign in"
        gbc.gridx = 2;
        gbc.gridy = 3;

        this.add(buttonLogin, gbc);
    }

    private void configureEvents() {
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtNameUSER.getText();
                String password = new String(txtPassword.getPassword());

                um.loadFile(ETypeFile.SERIALIZATE);
                // Buscar el usuario ingresado en los archivos serializados
                User user = um.findUserByUserName(username);

                if (user != null && user.getPasswordCreate().equals(password)) {

                    PrincipalScreen principalScreen = new PrincipalScreen();
                    principalScreen.setVisible(true);

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}

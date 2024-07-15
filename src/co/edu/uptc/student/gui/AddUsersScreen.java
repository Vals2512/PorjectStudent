package co.edu.uptc.student.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.Subject;
import co.edu.uptc.student.model.User;
import co.edu.uptc.student.persistence.UserManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUsersScreen extends JFrame {
    private static final long serialVersionUID = 1L;
    UserManagement um;
    private JLabel labelUser;
    private JLabel labelUsers;
    private JTextField txtNameUSER;
    private JLabel labelPassword;
    private JPasswordField txtPassword;
    private JButton buttonAdd;
    private JButton buttonCancel;

    public AddUsersScreen(UserManagement uaerManagement) {
        this.um = new UserManagement();
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
    }

    private void configureScreen() {
        setTitle("Add users");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.LIGHT_GRAY);

    }

    private void buildComponents() {
        this.labelUsers = new JLabel("Users");
        this.labelUser = new JLabel("User");
        Font boldFont = new Font("Times", Font.BOLD, 20);
        this.labelUsers.setFont(boldFont);

        this.labelPassword = new JLabel("Password");
        this.txtNameUSER = new JTextField(20);
        this.txtPassword = new JPasswordField(20);

        this.txtNameUSER.setBackground(Color.WHITE);
        this.txtPassword.setBackground(Color.WHITE);
        this.buttonAdd = new JButton("Add");
        this.buttonCancel = new JButton("Cancel");
        this.buttonAdd.setBackground(Color.GREEN);
        this.buttonCancel.setBackground(Color.RED);

        Font Font2 = new Font("Times", Font.ITALIC, 16);
        this.labelUser.setFont(Font2);
        this.labelPassword.setFont(Font2);
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Inserción del título "Usuarios"
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(labelUsers, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Inserción del título "Usuario"
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(labelUser, gbc);

        // Inserción del input del usuario
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(txtNameUSER, gbc);

        // Inserción del título "Contraseña"
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.add(labelPassword, gbc);

        // Inserción del input contraseña
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(txtPassword, gbc);

        // Inserción del botón "Cancelar"
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttonCancel, gbc);

        // Inserción del botón "Agregar"
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttonAdd, gbc);

    }

    private void configureEvents() {
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(txtPassword.getPassword());
                User user = new User(txtNameUSER.getText(), password);

                // Cargar usuarios existentes
                um.loadFile(ETypeFile.SERIALIZATE);

                if (um.addUser(user)) {
                    // Guardar todos los usuarios, incluido el nuevo
                    um.dumpFile(ETypeFile.SERIALIZATE);
                    JOptionPane.showMessageDialog(null, "User added successfully!");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Failed to add user. Please check the details and ensure no duplicates.");
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana de agregar usuario
            }
        });
    }
}

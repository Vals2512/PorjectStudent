package co.edu.uptc.student.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.Subject;
import co.edu.uptc.student.persistence.SubjectManagement;

public class AddSubjectsScreen extends JFrame {

    private SubjectManagement subjectManagement;
    private JLabel labelCodeSubject;
    private JLabel labelName;
    private JLabel labelNumberCredits;
    private JLabel labelSubject;

    private JTextField txtCodeSubjetc;
    private JTextField txtName;
    private JTextField txtNumberCredits;

    private JButton buttonAdd;
    private JButton buttonCancel;

    public AddSubjectsScreen(SubjectManagement subjectManagement) {
        this.subjectManagement = subjectManagement;
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
    }

    private void configureScreen() {
        setTitle("Add Subject");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.lightGray);

    }

    private void buildComponents() {

        this.labelSubject = new JLabel("Subject");
        this.labelCodeSubject = new JLabel("Code Subject");
        this.labelName = new JLabel("Name");
        this.labelNumberCredits = new JLabel("Credit´s number");

        Font boldFont = new Font("Times", Font.BOLD, 40);
        this.labelSubject.setFont(boldFont);

        Font boldFont2 = new Font("Times", Font.ITALIC, 20);
        this.labelCodeSubject.setFont(boldFont2);
        this.labelName.setFont(boldFont2);
        this.labelNumberCredits.setFont(boldFont2);

        this.txtCodeSubjetc = new JTextField(20);
        this.txtName = new JTextField(20);
        this.txtNumberCredits = new JTextField(20);

        this.buttonAdd = new JButton("Add");
        this.buttonCancel = new JButton("Cancel");
        this.buttonAdd.setBackground(Color.GREEN);
        this.buttonCancel.setBackground(Color.RED);

    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Inserción del título "subject"
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(labelSubject, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Inserción del título "code"
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(labelCodeSubject, gbc);

        // Inserción del input del id
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(txtCodeSubjetc, gbc);

        // Inserción del título "name"
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        this.add(labelName, gbc);

        // Inserción del input name
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(txtName, gbc);

        // Inserción del título "numebrCredits"
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        this.add(labelNumberCredits, gbc);

        // Inserción del input LAST ANME
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(txtNumberCredits, gbc);

        // Inserción del botón "Cancelar"
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttonCancel, gbc);

        // Inserción del botón "Agregar"
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(buttonAdd, gbc);

    }

    private void configureEvents() {
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Subject subject = new Subject(txtCodeSubjetc.getText(), txtName.getText(), txtNumberCredits.getText());

                // Cargar objetos Subject existentes}
                subjectManagement.loadFile(ETypeFile.TXT);
                subjectManagement.loadFile(ETypeFile.XML);
                subjectManagement.loadFile(ETypeFile.JSON);
                subjectManagement.loadFile(ETypeFile.CSV);
                subjectManagement.loadFile(ETypeFile.SERIALIZATE);

                if (subjectManagement.addSubject(subject)) {
                    // Guardar todos los objetos Subject, incluido el nuevo
                    subjectManagement.dumpFile(ETypeFile.TXT);
                    subjectManagement.dumpFile(ETypeFile.XML);
                    subjectManagement.dumpFile(ETypeFile.JSON);
                    subjectManagement.dumpFile(ETypeFile.CSV);
                    subjectManagement.dumpFile(ETypeFile.SERIALIZATE);
                    JOptionPane.showMessageDialog(null, "Subject added successfully!");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Failed to add subject. Please check the details and ensure no duplicates.");
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana de agregar objeto Subject
            }
        });
    }

}

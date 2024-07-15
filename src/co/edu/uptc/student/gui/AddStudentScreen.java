package co.edu.uptc.student.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.Student;
import co.edu.uptc.student.persistence.StudentManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentScreen extends JFrame {
    private static final long serialVersionUID = 1L;

    StudentManagement studentManagement;
    private JLabel labelStudentID;
    private JLabel labelName;
    private JLabel labelLastname;
    private JLabel labelCode;
    private JLabel labelCareer;
    private JLabel labelEmail;
    private JLabel labelStudent;

    private JTextField txtStudentId;
    private JTextField txtName;
    private JTextField txtLastname;
    private JTextField txtCode;
    private JTextField txtCareer;
    private JTextField txtEmail;

    private JButton buttonAdd;
    private JButton buttonCancel;
    private StudentManagement sm = StudentManagement.getInstance();
    public AddStudentScreen() {
        
        this.configureScreen();
        this.buildComponents();
        this.addComponents();
        this.configureEvents();
    }

    private void configureScreen() {
        setTitle("Add Student");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.lightGray);

    }

    private void buildComponents() {

        this.labelStudent = new JLabel("Student");
        this.labelStudentID = new JLabel("ID");
        this.labelName = new JLabel("Name");
        this.labelLastname = new JLabel("Lastname");
        this.labelCode = new JLabel("Code");
        this.labelCareer = new JLabel("Career");
        this.labelEmail = new JLabel("Email");

        Font boldFont = new Font("Times", Font.BOLD, 40);
        this.labelStudent.setFont(boldFont);

        Font boldFont2 = new Font("Times", Font.ITALIC, 20);
        this.labelStudentID.setFont(boldFont2);
        this.labelName.setFont(boldFont2);
        this.labelLastname.setFont(boldFont2);
        this.labelCode.setFont(boldFont2);
        this.labelCareer.setFont(boldFont2);
        this.labelEmail.setFont(boldFont2);

        this.txtStudentId = new JTextField(20);
        this.txtName = new JTextField(20);
        this.txtLastname = new JTextField(20);
        this.txtCode = new JTextField(20);
        this.txtCareer = new JTextField(20);
        this.txtEmail = new JTextField(20);

        this.txtStudentId.setBackground(Color.WHITE);
        this.txtLastname.setBackground(Color.WHITE);
        this.buttonAdd = new JButton("Add");
        this.buttonCancel = new JButton("Cancel");
        this.buttonAdd.setBackground(Color.GREEN);
        this.buttonCancel.setBackground(Color.RED);

    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Inserción del título "student"
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(labelStudent, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Inserción del título "id"
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(labelStudentID, gbc);

        // Inserción del input del id
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(txtStudentId, gbc);

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

        // Inserción del título "last naem"
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        this.add(labelLastname, gbc);

        // Inserción del input LAST ANME
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        this.add(txtLastname, gbc);

        // Inserción del título code
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        this.add(labelCode, gbc);

        // Inserción del input code
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        this.add(txtCode, gbc);

        // Inserción del título carrer
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        this.add(labelCareer, gbc);

        // Inserción del input carrer
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        this.add(txtCareer, gbc);

        // Inserción del título cemail
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        this.add(labelEmail, gbc);

        // Inserción del input email
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        this.add(txtEmail, gbc);

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
                // Cargar la lista existente de estudiantes desde los archivos
                studentManagement.loadFile(ETypeFile.TXT);
                studentManagement.loadFile(ETypeFile.XML);
                studentManagement.loadFile(ETypeFile.JSON);
                studentManagement.loadFile(ETypeFile.CSV);
                studentManagement.loadFile(ETypeFile.SERIALIZATE);

                Student student = new Student(
                        txtStudentId.getText(),
                        txtName.getText(),
                        txtLastname.getText(),
                        txtCode.getText(),
                        txtCareer.getText(),
                        txtEmail.getText());

                if (studentManagement.addStudent(student)) {
                    // Guardar la lista completa de estudiantes después de agregar el nuevo
                    // estudiante
                    studentManagement.dumpFile(ETypeFile.TXT);
                    studentManagement.dumpFile(ETypeFile.XML);
                    studentManagement.dumpFile(ETypeFile.JSON);
                    studentManagement.dumpFile(ETypeFile.CSV);
                    studentManagement.dumpFile(ETypeFile.SERIALIZATE);

                    JOptionPane.showMessageDialog(null, "Student added successfully!");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Failed to add student. Please check the details and ensure no duplicates.");
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the AddStudentScreen window
            }
        });
    }
}
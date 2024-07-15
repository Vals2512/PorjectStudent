package co.edu.uptc.student.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import co.edu.uptc.student.model.Student;

public class PanelMiddle extends JPanel {

    private static final long serialVersionUID = 1L;

    private DefaultTableModel dtm;

    private JTable table;

    public PanelMiddle() {
        setLayout(new BorderLayout());
        this.buildComponents();
        this.addComponents();

    }

    private void buildComponents() {
        String[] titles = { "ID", "Name", "Last name", "Code", "Career", "Email" };
        dtm = new DefaultTableModel(titles, 0);
        table = new JTable(dtm);
        JTableHeader header = table.getTableHeader();

        header.setBackground(Color.blue);
        header.setForeground(Color.WHITE);

    }

    private void addComponents() {
        // Create left and right empty panels with background color
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setPreferredSize(new Dimension(80, 100)); // Establecer tamaño preferido
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setPreferredSize(new Dimension(80, 100)); // Establecer tamaño preferido

        this.add(leftPanel, BorderLayout.WEST);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }

    public void addRow(Object[] row) {
        dtm.addRow(row);
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    public void setDefaultTableModel(String[] titles) {
        dtm = new DefaultTableModel(titles, 0);
        this.table.setModel(dtm);
    }
    public void updateStudentList(List<Student> students) {
        dtm.setRowCount(0); // Clear existing rows
        for (Student student : students) {
            dtm.addRow(new Object[]{
                student.getId(),
                student.getName(),
                student.getLastName(),
                student.getCode(),
                student.getCareer(),
                student.getEmail()
            });
        }
    }
}

package co.edu.uptc.student.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JPanel;

public class PanelSouthButtons extends JPanel {

	private static final long serialVersionUID = 1L;

	private ManagementEventsStudent mes;
	private JButton buttonAddStudent;
	private JButton buttonShowStudent;
	private JButton buttonDeleteStudent;

	public PanelSouthButtons(PrincipalScreen ps) {

		this.buildComponents(ps);
		this.addComponents();
		this.configureEvents();
	}

	private void buildComponents(PrincipalScreen ps) {
		this.buttonAddStudent = new JButton("Add");
		this.buttonShowStudent = new JButton("Show");
		this.buttonDeleteStudent = new JButton("Delete");
		this.mes = new ManagementEventsStudent(ps);
	}

	private void addComponents() {
		this.add(this.buttonAddStudent);
		this.add(this.buttonShowStudent);
		this.add(this.buttonDeleteStudent);

	}

	private void configureEvents() {
		// ActionListener para el botón Add
		this.buttonAddStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear e iniciar la ventana de agregar estudiante
				AddStudentScreen addStudentFrame = new AddStudentScreen();
				addStudentFrame.setVisible(true);
			}
		});
		this.buttonShowStudent.setActionCommand(ManagementEventsStudent.SHOW_STUDENT);
		this.buttonShowStudent.addActionListener(this.mes);

		this.buttonDeleteStudent.setActionCommand(ManagementEventsStudent.DELETE_STUDENT);
		this.buttonDeleteStudent.addActionListener(this.mes);
	}

}

/*
 * private void clearTable() {
 * for (int i = (this.principalscreen.getPanelMiddle().getDtm().getRowCount() -
 * 1); i >= 0; i--) {
 * this.principalscreen.getPanelMiddle().getDtm().removeRow(i);
 * }
 * 
 * }
 */

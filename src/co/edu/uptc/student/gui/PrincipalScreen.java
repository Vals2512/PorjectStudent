package co.edu.uptc.student.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import co.edu.uptc.student.model.Student;
import co.edu.uptc.student.persistence.StudentManagement;
import co.edu.uptc.student.persistence.SubjectManagement;
import co.edu.uptc.student.persistence.UserManagement;

public class PrincipalScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private LoginScreen ls;
    private AddUsersScreen us;
    private JMenuBarOperations menuBarOperations;
    private PanelSouthButtons southButtons;
    private PanelMiddle panelMiddle;
    private SubjectManagement subjectManagement;
    private UserManagement userManagement;
    private AddStudentScreen asc;
    private AddSubjectsScreen sc;
    private ManagementKeyEvents mke;

    public PrincipalScreen() {
        this.setUpScreen();
        this.subjectManagement = new SubjectManagement();
        this.userManagement = new UserManagement();

        this.buildComponents();
        this.addComponents();
        this.addKeyEventManager();
    }
    public void updateStudentList(List<Student> students) {
        panelMiddle.updateStudentList(students);
    }
    private void addKeyEventManager() {
        this.mke = new ManagementKeyEvents(this);
        this.addKeyListener(this.mke);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    private void setUpScreen() {
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void buildComponents() {
        this.menuBarOperations = new JMenuBarOperations(this);
        this.southButtons = new PanelSouthButtons(this);
        this.panelMiddle = new PanelMiddle();
        this.ls = new LoginScreen();
        this.us = new AddUsersScreen(userManagement);
        this.asc = new AddStudentScreen();

        this.sc = new AddSubjectsScreen(this.subjectManagement);
    }

    private void addComponents() {
        setJMenuBar(menuBarOperations);
        add(this.southButtons, BorderLayout.SOUTH);
        add(this.panelMiddle, BorderLayout.CENTER);

    }

    public JMenuBarOperations getMenuBarOperations() {
        return menuBarOperations;
    }

    public void setMenuBarOperations(JMenuBarOperations menuBarOperations) {
        this.menuBarOperations = menuBarOperations;
    }

    public PanelSouthButtons getSouthButtons() {
        return southButtons;
    }

    public void setSouthButtons(PanelSouthButtons southButtons) {
        this.southButtons = southButtons;
    }

    public PanelMiddle getPanelMiddle() {
        return panelMiddle;
    }

    public void setPanelMiddle(PanelMiddle panelMiddle) {
        this.panelMiddle = panelMiddle;
    }

    public LoginScreen getLs() {
        return ls;
    }

    public void setLs(LoginScreen ls) {
        this.ls = ls;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public AddUsersScreen getUs() {
        return us;
    }

    public void setUs(AddUsersScreen us) {
        this.us = us;
    }

    public AddStudentScreen getAsc() {
        return asc;
    }

    public void setAsc(AddStudentScreen asc) {
        this.asc = asc;
    }

    public AddSubjectsScreen getSc() {
        return sc;
    }

    public void setSc(AddSubjectsScreen sc) {
        this.sc = sc;
    }

}
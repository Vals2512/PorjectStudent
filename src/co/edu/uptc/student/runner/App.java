package co.edu.uptc.student.runner;

import co.edu.uptc.student.gui.PrincipalScreen;

public class App {

    public static void main(String[] args) {
        PrincipalScreen ps = new PrincipalScreen();
        ps.setVisible(Boolean.TRUE);
        ps.getLs().setVisible(false);
        ps.getUs().setVisible(false);
        ps.getAsc().setVisible(false);
        ps.getSc().setVisible(false);
    }
}

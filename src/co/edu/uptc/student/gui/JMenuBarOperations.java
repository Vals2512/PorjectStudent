package co.edu.uptc.student.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import co.edu.uptc.student.model.User;

public class JMenuBarOperations extends JMenuBar {

    private ManagementEventsStudent mes;
    List<User> userList = new ArrayList<>();

    private static final long serialVersionUID = 1L;
    private JMenu menuUser;
    private JMenuItem menuItemAddUser;
    private JMenuItem menuItemDeleteUser;

    private JMenu menuLoadData;
    private JMenu menuloadDataPlain;
    private JMenu menuLoadDataXML;
    private JMenu menuLoadDataJSON;
    private JMenu menuLoadDataCSV;
    private JMenu menuLoadDataSER;
    private JMenuItem menuItemLoadPlainobj1;
    private JMenuItem menuItemLoadPlainobj2;

    private JMenuItem menuItemLoadXMLobj1;
    private JMenuItem menuItemLoadXMLobj2;
    private JMenuItem menuItemLoadJSONobj1;
    private JMenuItem menuItemLoadJSONobj2;
    private JMenuItem menuItemLoadCSVobj1;
    private JMenuItem menuItemLoadCSVobj2;
    private JMenuItem menuItemLoadSERobj1;
    private JMenuItem menuItemLoadSERobj2;

    private JCheckBoxMenuItem menuItemLoadPlain1;
    private JCheckBoxMenuItem menuItemLoadPlain2;

    private JMenu menuSubject;
    private JMenuItem menuItemAddSubject;
    private JMenuItem menuItemDeleteSubject;
    private JMenuItem menuItemShowSubject;

    private JMenu menuCharge;
    private JMenu menuJSON;
    private JMenu menuCSV;
    private JMenu menuTXT;
    private JMenu menuXML;
    private JMenu menuSERIALIZABLE;

    private JMenuItem menuItemChargePlainobj1;
    private JMenuItem menuItemChargePlainobj2;
    private JMenuItem menuItemChargeXMLobj1;
    private JMenuItem menuItemChargeXMLobj2;
    private JMenuItem menuItemChargeJSONobj1;
    private JMenuItem menuItemChargeJSONobj2;
    private JMenuItem menuItemChargeCSVobj1;
    private JMenuItem menuItemChargeCSVobj2;
    private JMenuItem menuItemChargeSERobj1;
    private JMenuItem menuItemChargeSERobj2;

    private JMenu organize;
    private JMenuItem organizeStu;
    private JMenuItem organizeSub;

    public JMenuBarOperations(PrincipalScreen ps) {

        this.buildComponents(ps);
        this.addComponents();

    }

    private void buildComponents(PrincipalScreen ps) {

        // user
        this.menuUser = new JMenu("Users");
        this.menuItemAddUser = new JMenuItem(" Add User");

        this.menuItemDeleteUser = new JMenuItem(" Delete User");

        // subejct
        this.menuSubject = new JMenu("Subjects");
        this.menuItemAddSubject = new JMenuItem("Add subject");

        this.menuItemShowSubject = new JMenuItem("Show subject");
        this.menuItemDeleteSubject = new JMenuItem("Delete subject");

        // cargar
        this.menuLoadData = new JMenu("Charge");

        this.menuloadDataPlain = new JMenu("TXT");
        this.menuItemLoadPlainobj1 = new JMenuItem("Student");
        this.menuItemLoadPlainobj2 = new JMenuItem("Subject");

        this.menuLoadDataXML = new JMenu("XML");
        this.menuItemLoadXMLobj1 = new JMenuItem("Student");
        this.menuItemLoadXMLobj2 = new JMenuItem("Subject");

        this.menuLoadDataJSON = new JMenu("JSON");
        this.menuItemLoadJSONobj1 = new JMenuItem("Student");
        this.menuItemLoadJSONobj2 = new JMenuItem("Subject");

        this.menuLoadDataCSV = new JMenu("CSV");
        this.menuItemLoadCSVobj1 = new JMenuItem("Student");
        this.menuItemLoadCSVobj2 = new JMenuItem("Subject");

        this.menuLoadDataSER = new JMenu("SER");
        this.menuItemLoadSERobj1 = new JMenuItem("Student");
        this.menuItemLoadSERobj2 = new JMenuItem("Subject");

        // reportes
        this.menuCharge = new JMenu("Reports");

        this.menuTXT = new JMenu("TXT");
        this.menuItemChargePlainobj1 = new JMenuItem("Student");
        this.menuItemChargePlainobj2 = new JMenuItem("Subject");

        this.menuXML = new JMenu("XML");
        this.menuItemChargeXMLobj1 = new JMenuItem("Student");
        this.menuItemChargeXMLobj2 = new JMenuItem("Subject");

        this.menuJSON = new JMenu("JSON");
        this.menuItemChargeJSONobj1 = new JMenuItem("Student");
        this.menuItemChargeJSONobj2 = new JMenuItem("Subject");

        this.menuCSV = new JMenu("CSV");
        this.menuItemChargeCSVobj1 = new JMenuItem("Student");
        this.menuItemChargeCSVobj2 = new JMenuItem("Subject");

        this.menuSERIALIZABLE = new JMenu("SERIALIZABLE");
        this.menuItemChargeSERobj1 = new JMenuItem("Student");

        this.menuItemChargeSERobj2 = new JMenuItem("Subject");

        this.organize = new JMenu("Organize");
        this.organizeStu = new JMenuItem("Organize students");
        this.organizeSub = new JMenuItem("Organize subjects");

        this.mes = new ManagementEventsStudent(ps);

    }

    private void addComponents() {
        this.menuUser.add(this.menuItemAddUser);
        this.menuUser.add(this.menuItemDeleteUser);
        this.add(this.menuUser);

        this.menuSubject.add(this.menuItemAddSubject);
        this.menuSubject.add(this.menuItemShowSubject);
        this.menuSubject.add(this.menuItemDeleteSubject);
        this.add(this.menuSubject);

        this.menuloadDataPlain.add(this.menuItemLoadPlainobj1);

        this.menuloadDataPlain.add(this.menuItemLoadPlainobj2);
        this.menuLoadData.add(this.menuloadDataPlain);
        this.add(this.menuLoadData);

        this.menuLoadDataXML.add(this.menuItemLoadXMLobj1);
        this.menuLoadDataXML.add(this.menuItemLoadXMLobj2);
        this.menuLoadData.add(this.menuLoadDataXML);
        this.add(this.menuLoadData);

        this.menuLoadDataJSON.add(this.menuItemLoadJSONobj1);
        this.menuLoadDataJSON.add(this.menuItemLoadJSONobj2);
        this.menuLoadData.add(this.menuLoadDataJSON);
        this.add(this.menuLoadData);

        this.menuLoadDataCSV.add(this.menuItemLoadCSVobj1);
        this.menuLoadDataCSV.add(this.menuItemLoadCSVobj2);
        this.menuLoadData.add(this.menuLoadDataCSV);
        this.add(this.menuLoadData);

        this.menuLoadDataSER.add(this.menuItemLoadSERobj1);
        this.menuLoadDataSER.add(this.menuItemLoadSERobj2);
        this.menuLoadData.add(this.menuLoadDataSER);
        this.add(this.menuLoadData);

        // reports

        this.menuTXT.add(this.menuItemChargePlainobj1);
        this.menuTXT.add(this.menuItemChargePlainobj2);
        this.menuCharge.add(this.menuTXT);

        this.menuXML.add(this.menuItemChargeXMLobj1);
        this.menuXML.add(this.menuItemChargeXMLobj2);
        this.menuCharge.add(this.menuXML);

        this.menuJSON.add(this.menuItemChargeJSONobj1);
        this.menuJSON.add(this.menuItemChargeJSONobj2);
        this.menuCharge.add(this.menuJSON);

        this.menuCSV.add(this.menuItemChargeCSVobj1);
        this.menuCSV.add(this.menuItemChargeCSVobj2);
        this.menuCharge.add(this.menuCSV);

        this.menuSERIALIZABLE.add(this.menuItemChargeSERobj1);
        this.menuSERIALIZABLE.add(this.menuItemChargeSERobj2);
        this.menuCharge.add(this.menuSERIALIZABLE);

        this.add(this.menuCharge);

        this.organize.add(this.organizeStu);
        this.organize.add(this.organizeSub);
        this.add(this.organize);

        // asiganciónn de eventos student and subject load
        this.menuItemLoadPlainobj1.setActionCommand(ManagementEventsStudent.LOAD_STUDENT_FILE_PLAIN_OBJ1);
        this.menuItemLoadPlainobj1.addActionListener(this.mes);

        this.menuItemLoadPlainobj2.setActionCommand(ManagementEventsStudent.LOAD_STUDENT_FILE_PLAIN_OBJ2);
        this.menuItemLoadPlainobj2.addActionListener(this.mes);

        this.menuItemLoadXMLobj1.setActionCommand(ManagementEventsStudent.LOAD_STUDENT_FILE_XML_OBJ1);
        this.menuItemLoadXMLobj1.addActionListener(this.mes);

        this.menuItemLoadXMLobj2.setActionCommand(ManagementEventsStudent.LOAD_STUDENT_FILE_XML_OBJ2);
        this.menuItemLoadXMLobj2.addActionListener(this.mes);

        this.menuItemLoadJSONobj1.setActionCommand(ManagementEventsStudent.LOAD_STUDENT_FILE_JSON_OBJ1);
        this.menuItemLoadJSONobj1.addActionListener(this.mes);

        this.menuItemLoadJSONobj2.setActionCommand(ManagementEventsStudent.LOAD_STUDENT_FILE_JSON_OBJ2);
        this.menuItemLoadJSONobj2.addActionListener(this.mes);

        this.menuItemLoadCSVobj1.setActionCommand(ManagementEventsStudent.LOAD_STUDENT_FILE_CSV_OBJ1);
        this.menuItemLoadCSVobj1.addActionListener(this.mes);

        this.menuItemLoadCSVobj2.setActionCommand(ManagementEventsStudent.LOAD_STUDENT_FILE_CSV_OBJ2);
        this.menuItemLoadCSVobj2.addActionListener(this.mes);

        this.menuItemLoadSERobj1.setActionCommand(ManagementEventsStudent.LOAD_STUDENT_FILE_SER_OBJ1);
        this.menuItemLoadSERobj1.addActionListener(this.mes);

        this.menuItemLoadSERobj2.setActionCommand(ManagementEventsStudent.LOAD_STUDENT_FILE_SER_OBJ2);
        this.menuItemLoadSERobj2.addActionListener(this.mes);

        /// asigancion de eventos reports
        // asiganciónn de eventos student and subject load
        this.menuItemChargePlainobj1.setActionCommand(ManagementEventsStudent.REPORT_STUDENT_FILE_PLAIN);
        this.menuItemChargePlainobj1.addActionListener(this.mes);

        this.menuItemChargePlainobj2.setActionCommand(ManagementEventsStudent.REPORT_SUBJECT_FILE_PLAIN);
        this.menuItemChargePlainobj2.addActionListener(this.mes);

        this.menuItemChargeXMLobj1.setActionCommand(ManagementEventsStudent.REPORT_STUDENT_FILE_XML);
        this.menuItemChargeXMLobj1.addActionListener(this.mes);

        this.menuItemChargeXMLobj2.setActionCommand(ManagementEventsStudent.REPORT_SUBJECT_FILE_XML);
        this.menuItemChargeXMLobj2.addActionListener(this.mes);

        this.menuItemChargeJSONobj1.setActionCommand(ManagementEventsStudent.REPORT_STUDENT_FILE_JSON);
        this.menuItemChargeJSONobj1.addActionListener(this.mes);

        this.menuItemChargeJSONobj2.setActionCommand(ManagementEventsStudent.REPORT_SUBJECT_FILE_JSON);
        this.menuItemChargeJSONobj2.addActionListener(this.mes);

        this.menuItemChargeCSVobj1.setActionCommand(ManagementEventsStudent.REPORT_STUDENT_FILE_CSV);
        this.menuItemChargeCSVobj1.addActionListener(this.mes);

        this.menuItemChargeCSVobj2.setActionCommand(ManagementEventsStudent.REPORT_SUBJECT_FILE_CSV);
        this.menuItemChargeCSVobj2.addActionListener(this.mes);

        this.menuItemChargeSERobj1.setActionCommand(ManagementEventsStudent.REPORT_STUDENT_FILE_SER);
        this.menuItemChargeSERobj1.addActionListener(this.mes);

        this.menuItemChargeSERobj2.setActionCommand(ManagementEventsStudent.REPORT_SUBJECT_FILE_SER);
        this.menuItemChargeSERobj2.addActionListener(this.mes);

        // asignacion eventos subject menu
        this.menuItemAddSubject.setActionCommand(ManagementEventsStudent.ADD_SUBJECT);
        this.menuItemAddSubject.addActionListener(this.mes);

        this.menuItemShowSubject.setActionCommand(ManagementEventsStudent.SHOW_SUBJECT);
        this.menuItemShowSubject.addActionListener(this.mes);

        this.menuItemDeleteSubject.setActionCommand(ManagementEventsStudent.DELETE_SUBJECT);
        this.menuItemDeleteSubject.addActionListener(this.mes);

        // asigancion eventos user menu

        this.menuItemAddUser.setActionCommand(ManagementEventsStudent.ADD_USER);
        this.menuItemAddUser.addActionListener(this.mes);

        this.menuItemDeleteUser.setActionCommand(ManagementEventsStudent.DELETE_USER);
        this.menuItemDeleteUser.addActionListener(this.mes);

        this.organizeStu.setActionCommand(ManagementEventsStudent.ORGANIZE_STUDENT);
        this.organizeStu.addActionListener(this.mes);
    }

}

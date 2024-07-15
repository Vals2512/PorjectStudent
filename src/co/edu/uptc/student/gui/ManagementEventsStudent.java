package co.edu.uptc.student.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.Student;
import co.edu.uptc.student.model.Subject;
import co.edu.uptc.student.model.User;
import co.edu.uptc.student.persistence.StudentManagement;
import co.edu.uptc.student.persistence.SubjectManagement;
import co.edu.uptc.student.persistence.UserManagement;

public class ManagementEventsStudent implements ActionListener {

	 private StudentManagement sm = StudentManagement.getInstance();
    private SubjectManagement subm;
    private UserManagement um;
    private PrincipalScreen principalscreen;

    // STUDENT AND SUBJECT LOAD

    static final String LOAD_STUDENT_FILE_PLAIN_OBJ1 = "LOAD_STUDENT_FILE_PLAIN_OBJ1 ";
    public static final String LOAD_STUDENT_FILE_PLAIN_OBJ2 = "LOAD_STUDENT_FILE_PLAIN_OBJ2 ";

    static final String LOAD_STUDENT_FILE_XML_OBJ1 = "LOAD_STUDENT_FILE_XML_OBJ1 ";
    public static final String LOAD_STUDENT_FILE_XML_OBJ2 = "LOAD_STUDENT_FILE_XML_OBJ2 ";

    static final String LOAD_STUDENT_FILE_JSON_OBJ1 = "LOAD_STUDENT_FILE_JSON_OBJ1 ";
    public static final String LOAD_STUDENT_FILE_JSON_OBJ2 = "LOAD_STUDENT_FILE_JSON_OBJ2 ";

    static final String LOAD_STUDENT_FILE_CSV_OBJ1 = "LOAD_STUDENT_FILE_CSV_OBJ1 ";
    public static final String LOAD_STUDENT_FILE_CSV_OBJ2 = "LOAD_STUDENT_FILE_CSV_OBJ2 ";

    static final String LOAD_STUDENT_FILE_SER_OBJ1 = "LOAD_STUDENT_FILE_SER_OBJ1 ";
    public static final String LOAD_STUDENT_FILE_SER_OBJ2 = "LOAD_STUDENT_FILE_SER_OBJ2 ";

    // STUDENT SHOW
    static final String SHOW_STUDENT = "SHOW_STUDENT";
    static final String DELETE_STUDENT = "DELETE_STUDENT";
    // reports

    static final String REPORT_STUDENT_FILE_PLAIN = "REPORT_STUDENT_FILE_PLAIN_OBJ1 ";
    public static final String REPORT_SUBJECT_FILE_PLAIN = "REPORT_SUBJECT_FILE_PLAIN_OBJ2 ";

    static final String REPORT_STUDENT_FILE_XML = "REPORT_STUDENT_FILE_XML_OBJ1 ";
    public static final String REPORT_SUBJECT_FILE_XML = "REPORT_SUBJECT_FILE_XML_OBJ2 ";

    static final String REPORT_STUDENT_FILE_JSON = "REPORT_STUDENT_FILE_JSON_OBJ1 ";
    public static final String REPORT_SUBJECT_FILE_JSON = "REPORT_SUBJECT_FILE_JSON_OBJ2 ";

    static final String REPORT_STUDENT_FILE_CSV = "REPORT_STUDENT_FILE_CSV_OBJ1 ";
    public static final String REPORT_SUBJECT_FILE_CSV = "REPORT_SUBJECT_FILE_CSV_OBJ2 ";

    static final String REPORT_STUDENT_FILE_SER = "REPORT_STUDENT_FILE_SER_OBJ1 ";
    public static final String REPORT_SUBJECT_FILE_SER = "REPORT_SUBJECT_FILE_SER_OBJ2 ";

    // organize
    static final String ORGANIZE_STUDENT = "ORGANIZE_STUDENT";
    static final String ORGANIZE_SUBJECT = "ORGANIZE_SUBJECT";

    // SUBJECT
    static final String ADD_SUBJECT = "ADD_SUBJECT";
    static final String SHOW_SUBJECT = "SHOW_SUBJECT ";
    public static final String DELETE_SUBJECT = "DELETE_SUBJECT ";

    // user
    // SUBJECT
    static final String ADD_USER = "ADD_USER";
    public static final String DELETE_USER = "DELETE_USER";

    public ManagementEventsStudent(PrincipalScreen ps) {
       
        this.subm = new SubjectManagement();
        this.um = new UserManagement();
        this.principalscreen = ps;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case LOAD_STUDENT_FILE_PLAIN_OBJ1:
                String[] titlesShow1 = { "Id", "Name", "Last name", "Code", "Career", "Email" };
                this.sm.setListStudents(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow1);

                this.sm.loadFile(ETypeFile.TXT);
                this.clearTable();
                this.sm.getStudents().forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(), student.getLastName(),
                            student.getCode(), student.getCareer(), student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;

            case LOAD_STUDENT_FILE_PLAIN_OBJ2:
                String[] titlesShow4 = { "Code", "Name", "Credit´s number" };
                this.subm.setSubjects(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow4);
                this.subm.loadFile(ETypeFile.TXT);
                this.clearTable();
                this.subm.getSubjects().forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(), subject.getNumberCredits()
                    };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;

            case LOAD_STUDENT_FILE_XML_OBJ1:

                String[] titlesShow2 = { "Id", "Name", "Last name", "Code", "Career", "Email" };
                this.sm.setListStudents(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow2);

                this.sm.loadFile(ETypeFile.XML);
                this.clearTable();
                this.sm.getStudents().forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(), student.getLastName(),
                            student.getCode(), student.getCareer(), student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;

            case LOAD_STUDENT_FILE_XML_OBJ2:
                String[] titlesShow5 = { "Code", "Name", "Credit´s number" };
                this.subm.setSubjects(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow5);
                this.subm.loadFile(ETypeFile.XML);
                this.clearTable();
                this.subm.getSubjects().forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(), subject.getNumberCredits()
                    };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;

            case LOAD_STUDENT_FILE_JSON_OBJ1:
                String[] titlesShow3 = { "Id", "Name", "Last name", "Code", "Career", "Email" };
                this.sm.setListStudents(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow3);

                this.sm.loadFile(ETypeFile.JSON);
                this.clearTable();
                this.sm.getStudents().forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(), student.getLastName(),
                            student.getCode(), student.getCareer(), student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;

            case LOAD_STUDENT_FILE_JSON_OBJ2:
                String[] titlesShow6 = { "Code", "Name", "Credit´s number" };
                this.subm.setSubjects(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow6);
                this.subm.loadFile(ETypeFile.JSON);
                this.clearTable();
                this.subm.getSubjects().forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(), subject.getNumberCredits()
                    };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;

            case LOAD_STUDENT_FILE_CSV_OBJ1:
                String[] titlesShow11 = { "Id", "Name", "Last name", "Code", "Career", "Email" };
                this.sm.setListStudents(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow11);

                this.sm.loadFile(ETypeFile.CSV);
                this.clearTable();
                this.sm.getStudents().forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(), student.getLastName(),
                            student.getCode(), student.getCareer(), student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;

            case LOAD_STUDENT_FILE_CSV_OBJ2:
                String[] titlesShow7 = { "Code", "Name", "Credit´s number" };
                this.subm.setSubjects(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow7);

                this.subm.loadFile(ETypeFile.CSV);
                this.clearTable();
                this.subm.getSubjects().forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(), subject.getNumberCredits()
                    };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;

            case LOAD_STUDENT_FILE_SER_OBJ1:
                String[] titlesShow9 = { "Id", "Name", "Last name", "Code", "Career", "Email" };
                this.sm.setListStudents(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow9);

                this.sm.loadFile(ETypeFile.SERIALIZATE);
                this.clearTable();
                this.sm.getStudents().forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(), student.getLastName(),
                            student.getCode(), student.getCareer(), student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;

            case LOAD_STUDENT_FILE_SER_OBJ2:

                String[] titlesShow8 = { "Code", "Name", "Credit´s number" };
                this.subm.setSubjects(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow8);

                this.subm.loadFile(ETypeFile.SERIALIZATE);
                this.clearTable();
                this.subm.getSubjects().forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(), subject.getNumberCredits()
                    };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;

            case SHOW_STUDENT:
                String[] titlesShow = { "Id", "Name", "Last name", "Code", "Career", "Email" };
                this.sm.setListStudents(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow);

                String careerStudent = JOptionPane.showInputDialog("Enter the career's student");

                this.sm.loadFile(ETypeFile.TXT);
                this.sm.loadFile(ETypeFile.XML);
                this.sm.loadFile(ETypeFile.JSON);
                this.sm.loadFile(ETypeFile.CSV);
                this.sm.loadFile(ETypeFile.SERIALIZATE);

                List<Student> listStudentSAux = this.sm.getStudents();
                this.clearTable();
                listStudentSAux = listStudentSAux.stream()
                        .filter(student -> student.getCareer().equalsIgnoreCase(careerStudent))
                        .collect(Collectors.toList());
                listStudentSAux.forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(),
                            student.getLastName(),
                            student.getCode(), student.getCareer(), student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });
                break;
            case DELETE_STUDENT:

                String[] titlesShow12 = { "Id", "Name", "Last name", "Code", "Career", "Email" };
                this.sm.setListStudents(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow12);

                this.sm.loadFile(ETypeFile.TXT);
                this.sm.loadFile(ETypeFile.XML);
                this.sm.loadFile(ETypeFile.JSON);
                this.sm.loadFile(ETypeFile.CSV);
                this.sm.loadFile(ETypeFile.SERIALIZATE);
                String codeStudent = JOptionPane.showInputDialog("Enter the student code");

                // Buscar y eliminar la materia
                Student studentToDelete = this.sm.findStudentByCode(codeStudent);
                if (studentToDelete != null) {
                    this.sm.deleteStudentByCode(codeStudent);

                    // Guardar las materias actualizadas en los archivos
                    this.sm.dumpFile(ETypeFile.TXT);

                    this.sm.dumpFile(ETypeFile.XML);

                    this.sm.dumpFile(ETypeFile.JSON);

                    this.sm.dumpFile(ETypeFile.CSV);

                    this.sm.dumpFile(ETypeFile.SERIALIZATE);

                    // Actualizar la tabla en la interfaz de usuario
                    this.clearTable();
                    this.sm.getStudents().forEach(student -> {
                        Object[] row = new Object[] { student.getId(), student.getName(), student.getLastName(),
                                student.getCode(),
                                student.getCareer(), student.getEmail() };
                        this.principalscreen.getPanelMiddle().addRow(row);
                    });

                    JOptionPane.showMessageDialog(null, "Student deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Student not found!");
                }
                break;

            // reports menu
            // STUDENT
            case REPORT_STUDENT_FILE_CSV:
                // Cargar la lista de estudiantes existente desde los archivos
                this.sm.setListStudents(new ArrayList<>());

                // Actualizar la tabla en la interfaz de usuario
                this.clearTable();

                this.sm.loadFile(ETypeFile.CSV);
                this.sm.getStudents().forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(),
                            student.getLastName(), student.getCode(), student.getCareer(),
                            student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                JOptionPane.showMessageDialog(null, "Student list updated successfully!");
                break;

            case REPORT_STUDENT_FILE_PLAIN:
                // Cargar la lista de estudiantes existente desde los archivos

                this.sm.setListStudents(new ArrayList<>());
                this.sm.loadFile(ETypeFile.TXT);

                // Actualizar la tabla en la interfaz de usuario
                this.clearTable();
                this.sm.getStudents().forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(),
                            student.getLastName(), student.getCode(), student.getCareer(),
                            student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                JOptionPane.showMessageDialog(null, "Student list updated successfully!");
                break;

            case REPORT_STUDENT_FILE_XML:
                // Cargar la lista de estudiantes existente desde los archivos
                this.sm.setListStudents(new ArrayList<>());
                this.sm.loadFile(ETypeFile.XML);

                // Actualizar la tabla en la interfaz de usuario
                this.clearTable();
                this.sm.getStudents().forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(),
                            student.getLastName(), student.getCode(), student.getCareer(),
                            student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                JOptionPane.showMessageDialog(null, "Student list updated successfully!");
                break;

            case REPORT_STUDENT_FILE_JSON:
                // Cargar la lista de estudiantes existente desde los archivos
                this.sm.setListStudents(new ArrayList<>());
                this.sm.loadFile(ETypeFile.JSON);

                // Actualizar la tabla en la interfaz de usuario
                this.clearTable();
                this.sm.getStudents().forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(),
                            student.getLastName(), student.getCode(), student.getCareer(),
                            student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                JOptionPane.showMessageDialog(null, "Student list updated successfully!");
                break;
            case REPORT_STUDENT_FILE_SER:
                // Cargar la lista de estudiantes existente desde los archivos
                this.sm.setListStudents(new ArrayList<>());
                this.sm.loadFile(ETypeFile.SERIALIZATE);

                // Actualizar la tabla en la interfaz de usuario
                this.clearTable();
                this.sm.getStudents().forEach(student -> {
                    Object[] row = new Object[] { student.getId(), student.getName(),
                            student.getLastName(), student.getCode(), student.getCareer(),
                            student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                JOptionPane.showMessageDialog(null, "Student list updated successfully!");
                break;
            // SUBJECT

            case REPORT_SUBJECT_FILE_CSV:
                // Cargar la lista de materias existente desde los archivos
                this.subm.setSubjects(new ArrayList<>());
                this.subm.loadFile(ETypeFile.CSV);

                // Actualizar la tabla en la interfaz de usuario
                this.clearTable();
                this.subm.getSubjects().forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(),
                            subject.getNumberCredits() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                JOptionPane.showMessageDialog(null, "Subject list updated successfully!");
                break;

            case REPORT_SUBJECT_FILE_PLAIN:
                // Cargar la lista de materias existente desde los archivos
                this.subm.setSubjects(new ArrayList<>());
                this.subm.loadFile(ETypeFile.TXT);

                // Actualizar la tabla en la interfaz de usuario
                this.clearTable();
                this.subm.getSubjects().forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(),
                            subject.getNumberCredits() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                JOptionPane.showMessageDialog(null, "Subject list updated successfully!");
                break;
            case REPORT_SUBJECT_FILE_XML:
                // Cargar la lista de materias existente desde los archivos
                this.subm.setSubjects(new ArrayList<>());
                this.subm.loadFile(ETypeFile.XML);

                // Actualizar la tabla en la interfaz de usuario
                this.clearTable();
                this.subm.getSubjects().forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(),
                            subject.getNumberCredits() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                JOptionPane.showMessageDialog(null, "Subject list updated successfully!");
                break;
            case REPORT_SUBJECT_FILE_JSON:
                // Cargar la lista de materias existente desde los archivos
                this.subm.setSubjects(new ArrayList<>());
                this.subm.loadFile(ETypeFile.JSON);

                // Actualizar la tabla en la interfaz de usuario
                this.clearTable();
                this.subm.getSubjects().forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(),
                            subject.getNumberCredits() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                JOptionPane.showMessageDialog(null, "Subject list updated successfully!");
                break;
            case REPORT_SUBJECT_FILE_SER:
                // Cargar la lista de materias existente desde los archivos
                this.subm.setSubjects(new ArrayList<>());
                this.subm.loadFile(ETypeFile.SERIALIZATE);

                // Actualizar la tabla en la interfaz de usuario
                this.clearTable();
                this.subm.getSubjects().forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(),
                            subject.getNumberCredits() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                JOptionPane.showMessageDialog(null, "Subject list updated successfully!");
                break;
            // subject menu

            case ADD_SUBJECT:
                AddSubjectsScreen addSubjectsScreen = new AddSubjectsScreen(this.subm);
                addSubjectsScreen.setVisible(true);
                break;

            case SHOW_SUBJECT:

                String[] titlesShow10 = { "Code", "Name", "Credit´s number" };
                this.subm.setSubjects(new ArrayList<>());
                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow10);

                String nameSub = JOptionPane.showInputDialog("Enter the subject's name");

                this.subm.loadFile(ETypeFile.TXT);
                this.subm.loadFile(ETypeFile.XML);
                this.subm.loadFile(ETypeFile.JSON);
                this.subm.loadFile(ETypeFile.CSV);
                this.subm.loadFile(ETypeFile.SERIALIZATE);

                List<Subject> listSubjectsAux = this.subm.getSubjects();

                this.clearTable();
                listSubjectsAux = listSubjectsAux.stream()
                        .filter(subject -> subject.getName().equalsIgnoreCase(nameSub))
                        .collect(Collectors.toList());
                listSubjectsAux.forEach(subject -> {
                    Object[] row = new Object[] { subject.getCode(), subject.getName(), subject.getNumberCredits() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                });

                break;

            case DELETE_SUBJECT:

                String codeToDelete = JOptionPane.showInputDialog("Enter the subject's code");
                // Cargar materias desde los archivos de persistencia
                this.subm.loadFile(ETypeFile.TXT);
                this.subm.loadFile(ETypeFile.XML);
                this.subm.loadFile(ETypeFile.JSON);
                this.subm.loadFile(ETypeFile.CSV);
                this.subm.loadFile(ETypeFile.SERIALIZATE);
                // Buscar y eliminar la materia
                Subject subjectToDelete = this.subm.findSubjectByCode(codeToDelete);
                if (subjectToDelete != null) {
                    this.subm.deleteSubjectByCode(codeToDelete);

                    // Guardar las materias actualizadas en los archivos
                    this.subm.dumpFile(ETypeFile.TXT);
                    this.subm.dumpFile(ETypeFile.XML);
                    this.subm.dumpFile(ETypeFile.JSON);
                    this.subm.dumpFile(ETypeFile.CSV);
                    this.subm.dumpFile(ETypeFile.SERIALIZATE);

                    // Actualizar la tabla en la interfaz de usuario
                    this.clearTable();
                    this.subm.getSubjects().forEach(subject -> {
                        Object[] row = new Object[] { subject.getCode(), subject.getName(),
                                subject.getNumberCredits() };
                        this.principalscreen.getPanelMiddle().addRow(row);
                    });

                    JOptionPane.showMessageDialog(null, "Subject deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Subject not found!");
                }

                break;

            case ADD_USER:
                AddUsersScreen addUsersScreen = new AddUsersScreen(this.um);
                addUsersScreen.setVisible(true);
                break;

            case DELETE_USER:
                // Cargar USUARIOS desde los archivos de persistencia

                this.um.loadFile(ETypeFile.SERIALIZATE);

                String userNameToDelete = JOptionPane.showInputDialog("Enter the user name");

                // Buscar y eliminar el usuario
                User userToDelete = this.um.findUserByUserName(userNameToDelete);
                if (userToDelete != null) {
                    this.um.deleteUserByUserName(userNameToDelete);

                    // Guardar las materias actualizadas en los archivos

                    this.um.dumpFile(ETypeFile.SERIALIZATE);

                    // Actualizar la tabla en la interfaz de usuario
                    this.clearTable();
                    this.um.getUsers().forEach(user -> {
                        Object[] row = new Object[] { user.getUserCreate(), user.getPasswordCreate()
                        };
                        this.principalscreen.getPanelMiddle().addRow(row);
                    });

                    JOptionPane.showMessageDialog(null, "User deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "User not found!");
                }
                break;

            case ORGANIZE_STUDENT:

                //esto seria solo para ordenar csv en especifico
               // si yo no le mando load, sino que en la aplicacion hago directamemnetevcharge y luego organizar, los orgniza independientemente del tipo de archivo 
           /*  sm.setListStudents(new ArrayList<>());
            this.sm.loadFile(ETypeFile.CSV);
            */
            

                for (int i = 0; i < sm.getStudents().size(); i++) {
                    for (int j = 0; j < sm.getStudents().size() - 1 - i; j++) {
                        if (sm.getStudents().get(j).getLastName()
                                .compareTo(sm.getStudents().get(j + 1).getLastName()) > 0) {

                            Student aux = sm.getStudents().get(j);
                            sm.getStudents().set(j, sm.getStudents().get(j + 1));
                            sm.getStudents().set(j + 1, aux);
                        }

                    }
                }

             this.clearTable();

                String[] titlesShow20 = { "Id", "Name", "Last name", "Code", "Career", "Email" };

                this.principalscreen.getPanelMiddle().setDefaultTableModel(titlesShow20);
        
                for (Student student : sm.getStudents()) {
                    Object[] row = new Object[] { student.getId(), student.getName(), student.getLastName(),
                            student.getCode(), student.getCareer(), student.getEmail() };
                    this.principalscreen.getPanelMiddle().addRow(row);
                }
                break;
        }
       
    }
    

    private void clearTable() {
        for (int i = (this.principalscreen.getPanelMiddle().getDtm().getRowCount() - 1); i >= 0; i--) {
            this.principalscreen.getPanelMiddle().getDtm().removeRow(i);
        }

    }

}

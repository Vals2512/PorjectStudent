package co.edu.uptc.student.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.Student;
import co.edu.uptc.student.persistence.StudentManagement;

public class StudentManagementTest {
    StudentManagement sm = (StudentManagement) StudentManagement.getInstance().getStudents();

    private void escenarieTXT() {
        sm.addStudent(new Student("1", "Juan", "Perez", "123", "Systems", "juan.perez@uptc.edu.co"));
        sm.addStudent(new Student("2", "Pedro", "Gomez", "456", "Industrial", "pedro.gomez@uptc.edu.co"));
        sm.addStudent(new Student("3", "Maria", "Rodriguez", "789", "Civil", "maria.rodriguez@uptc.edu.co"));
        sm.dumpFile(ETypeFile.TXT);
    }

    private void getTXT() {
        sm.setListStudents(new ArrayList<>());
        sm.loadFile(ETypeFile.TXT);
    }

    @Test
    void testFileTXT() {
        this.escenarieTXT();
        this.getTXT();

        List<Student> students = sm.getStudents();

        assertNotNull(students);
        // verifica que existan 3 estduiantes agregados
        assertEquals(3, students.size());

    }

    private void escenarieXML() {
        sm.addStudent(new Student("1", "Juan", "Perez", "123", "Systems", "juan.perez@uptc.edu.co"));
        sm.addStudent(new Student("2", "Pedro", "Gomez", "456", "Industrial", "pedro.gomez@uptc.edu.co"));
        sm.addStudent(new Student("3", "Maria", "Rodriguez", "789", "Civil", "maria.rodriguez@uptc.edu.co"));
        sm.addStudent(new Student("4", "Pepito", "hernandez", "1011", "Minas", "pepito.perez@uptc.edu.co"));
        sm.dumpFile(ETypeFile.XML);
    }

    private void getXML() {
        sm.setListStudents(new ArrayList<>());
        sm.loadFile(ETypeFile.XML);
    }

    @Test
    void testFileXML() {
        this.escenarieXML();
        this.getXML();

        List<Student> students = sm.getStudents();

        assertNotNull(students);
        // verifica que existan 4 estduiantes agregados
        assertEquals(4, students.size());

    }

    private void escenarieJSON() {
        sm.addStudent(new Student("1", "Juan", "Perez", "123", "Systems", "juan.perez@uptc.edu.co"));
        sm.addStudent(new Student("2", "Pedro", "Gomez", "456", "Industrial", "pedro.gomez@uptc.edu.co"));
        sm.addStudent(new Student("4", "Pepito", "Velandia", "1011", "Minas", "pepito.perez@uptc.edu.co"));
        sm.dumpFile(ETypeFile.JSON);
    }

    private void getJSON() {
        sm.setListStudents(new ArrayList<>());
        sm.loadFile(ETypeFile.JSON);
    }

    @Test
    void testFileJSON() {
        this.escenarieJSON();
        this.getJSON();

        List<Student> students = sm.getStudents();

        assertNotNull(students);
        // verifica que existan 4 estduiantes agregados
        assertEquals(3, students.size());

    }

    private void escenarieSerializate() {
        sm.addStudent(new Student("1", "Juan", "Perez", "123", "Systems", "juan.perez@uptc.edu.co"));
        sm.addStudent(new Student("2", "Pedro", "Gomez", "456", "Industrial", "pedro.gomez@uptc.edu.co"));

        sm.dumpFile(ETypeFile.SERIALIZATE);
    }

    private void getSer() {
        sm.setListStudents(new ArrayList<>());
        sm.loadFile(ETypeFile.SERIALIZATE);
    }

    @Test
    void testFileSerializable() {
        this.escenarieSerializate();
        this.getSer();

        List<Student> students = sm.getStudents();

        assertNotNull(students);
        // verifica que existan 2 estduiantes agregados
        assertEquals(2, students.size());

    }

    private void escenarieCSV() {
        sm.addStudent(new Student("1", "Juan", "Perez", "123", "Systems", "juan.perez@uptc.edu.co"));
        sm.addStudent(new Student("2", "Pedro", "Gomez", "456", "Industrial", "pedro.gomez@uptc.edu.co"));
        sm.addStudent(new Student("3", "Maria", "Rodriguez", "789", "Civil", "maria.rodriguez@uptc.edu.co"));
        sm.addStudent(new Student("4", "Pepito", "vergara", "1011", "Minas", "pepito.perez@uptc.edu.co"));
        sm.addStudent(new Student("5", "Julian", "vargas", "1705", "Electronica", "julian.vargas@uptc.edu.co"));
        sm.dumpFile(ETypeFile.CSV);
    }

    private void getCSV() {
        sm.setListStudents(new ArrayList<>());
        sm.loadFile(ETypeFile.CSV);
    }

    @Test
    void testFileCSV() {
        this.escenarieCSV();
        this.getCSV();

        List<Student> students = sm.getStudents();

        assertNotNull(students);
        // verifica que existan 5 estduiantes agregados
        assertEquals(5, students.size());

    }

}

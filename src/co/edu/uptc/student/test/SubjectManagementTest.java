package co.edu.uptc.student.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.Student;
import co.edu.uptc.student.model.Subject;
import co.edu.uptc.student.persistence.StudentManagement;
import co.edu.uptc.student.persistence.SubjectManagement;

public class SubjectManagementTest {

    private SubjectManagement subjectManagement;

    @BeforeEach
    void setUp() {
        subjectManagement = new SubjectManagement();
    }

    private void escenarieTXT() {
        subjectManagement.addSubject(new Subject("1", "Algebra", "4", "123"));
        subjectManagement.dumpFile(ETypeFile.TXT);
    }

    private void getTXT() {
        subjectManagement.setSubjects(new ArrayList<>());
        subjectManagement.loadFile(ETypeFile.TXT);
    }

    @Test
    void testFileTXT() {
        this.escenarieTXT();
        this.getTXT();

        List<Subject> subjects = subjectManagement.getSubjects();

        assertNotNull(subjects);
        // verifica que exista 1 materia agregada
        assertEquals(1, subjects.size());
    }

    private void escenarieXML() {
        subjectManagement.addSubject(new Subject("2", "Calculus", "3", "123"));
        subjectManagement.addSubject(new Subject("3", "Physics", "4", "124"));
        subjectManagement.dumpFile(ETypeFile.XML);
    }

    private void getXML() {
        subjectManagement.setSubjects(new ArrayList<>());
        subjectManagement.loadFile(ETypeFile.XML);
    }

    @Test
    void testFileXML() {
        this.escenarieXML();
        this.getXML();

        List<Subject> subjects = subjectManagement.getSubjects();

        assertNotNull(subjects);
        // verifica que existan 2 materias agregadas
        assertEquals(2, subjects.size());
    }

    private void escenarieJSON() {
        subjectManagement.addSubject(new Subject("4", "Algebra", "4", "123"));
        subjectManagement.addSubject(new Subject("5", "Calculus", "3", "124"));
        subjectManagement.addSubject(new Subject("6", "Physics", "4", "125"));
        subjectManagement.dumpFile(ETypeFile.JSON);
    }

    private void getJSON() {
        subjectManagement.setSubjects(new ArrayList<>());
        subjectManagement.loadFile(ETypeFile.JSON);
    }

    @Test
    void testFileJSON() {
        this.escenarieJSON();
        this.getJSON();

        List<Subject> subjects = subjectManagement.getSubjects();

        assertNotNull(subjects);
        // verifica que existan 3 materias agregadas
        assertEquals(3, subjects.size());
    }

    private void escenarieCSV() {
        subjectManagement.addSubject(new Subject("7", "Algebra", "4", "123"));
        subjectManagement.addSubject(new Subject("8", "Calculus", "3", "124"));
        subjectManagement.addSubject(new Subject("9", "Physics", "4", "125"));
        subjectManagement.addSubject(new Subject("10", "Chemistry", "3", "123"));
        subjectManagement.dumpFile(ETypeFile.CSV);
    }

    private void getCSV() {
        subjectManagement.setSubjects(new ArrayList<>());
        subjectManagement.loadFile(ETypeFile.CSV);
    }

    @Test
    void testFileCSV() {
        this.escenarieCSV();
        this.getCSV();

        List<Subject> subjects = subjectManagement.getSubjects();

        assertNotNull(subjects);
        // verifica que existan 4 materias agregadas
        assertEquals(4, subjects.size());
    }

    private void escenarieSER() {
        subjectManagement.addSubject(new Subject("11", "Algebra", "4", "123"));
        subjectManagement.addSubject(new Subject("12", "Calculus", "3", "124"));
        subjectManagement.addSubject(new Subject("13", "Physics", "4", "125"));
        subjectManagement.addSubject(new Subject("14", "Chemistry", "3", "123"));
        subjectManagement.addSubject(new Subject("15", "Biology", "1", "124"));

        subjectManagement.dumpFile(ETypeFile.SERIALIZATE);
    }

    private void getSER() {
        subjectManagement.setSubjects(new ArrayList<>());
        subjectManagement.loadFile(ETypeFile.SERIALIZATE);
    }

    @Test
    void testFileSER() {
        this.escenarieSER();
        this.getSER();

        List<Subject> subjects = subjectManagement.getSubjects();

        assertNotNull(subjects);
        // verifica que existan 5 materias agregadas
        assertEquals(5, subjects.size());
    }
}

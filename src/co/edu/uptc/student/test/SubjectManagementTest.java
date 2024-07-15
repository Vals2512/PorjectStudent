package co.edu.uptc.student.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.Subject;
import co.edu.uptc.student.persistence.SubjectManagement;

public class SubjectManagementTest {
    private SubjectManagement sm = new SubjectManagement();

    private void escenarieTXT() {
        sm.addSubject(new Subject("1", "Algebra", "4"));

        sm.dumpFile(ETypeFile.TXT);
    }

    private void getTXT() {
        sm.setSubjects(new ArrayList<>());
        sm.loadFile(ETypeFile.TXT);
    }

    @Test
    void testFileTXT() {
        this.escenarieTXT();
        this.getTXT();

        List<Subject> subjects = sm.getSubjects();

        assertNotNull(subjects);
        // verifica que existan 1 materia agregados
        assertEquals(1, subjects.size());

    }

    private void escenarieXML() {
        sm.addSubject(new Subject("2", "Algebra", "4"));
        sm.addSubject(new Subject("3", "Calculo", "3"));
        sm.dumpFile(ETypeFile.XML);
    }

    private void getXML() {
        sm.setSubjects(new ArrayList<>());
        sm.loadFile(ETypeFile.XML);
    }

    @Test
    void testFileXML() {
        this.escenarieXML();
        this.getXML();

        List<Subject> subjects = sm.getSubjects();

        assertNotNull(subjects);
        // verifica que existan 2 materia agregados
        assertEquals(2, subjects.size());

    }

    private void escenarieJSON() {
        sm.addSubject(new Subject("4", "Algebra", "4"));
        sm.addSubject(new Subject("5", "Calculo", "3"));
        sm.addSubject(new Subject("6", "Fisica", "4"));
        sm.dumpFile(ETypeFile.JSON);
    }

    private void getJSON() {
        sm.setSubjects(new ArrayList<>());
        sm.loadFile(ETypeFile.JSON);
    }

    @Test
    void testFileJSON() {
        this.escenarieJSON();
        this.getJSON();

        List<Subject> subjects = sm.getSubjects();

        assertNotNull(subjects);
        // verifica que existan 2 materia agregados
        assertEquals(3, subjects.size());

    }

    private void escenarieCSV() {
        sm.addSubject(new Subject("7", "Algebra", "4"));
        sm.addSubject(new Subject("8", "Calculo", "3"));
        sm.addSubject(new Subject("9", "Fisica", "4"));
        sm.addSubject(new Subject("10", "Catedra", "3"));
        sm.dumpFile(ETypeFile.CSV);
    }

    private void getCSV() {
        sm.setSubjects(new ArrayList<>());
        sm.loadFile(ETypeFile.CSV);
    }

    @Test
    void testFileCSV() {
        this.escenarieCSV();
        this.getCSV();

        List<Subject> subjects = sm.getSubjects();

        assertNotNull(subjects);
        // verifica que existan 2 materia agregados
        assertEquals(4, subjects.size());

    }

    private void escenarieSER() {
        sm.addSubject(new Subject("11", "Algebra", "4"));
        sm.addSubject(new Subject("12", "Calculo", "3"));
        sm.addSubject(new Subject("13", "Fisica", "4"));
        sm.addSubject(new Subject("14", "Catedra", "3"));
        sm.addSubject(new Subject("15", "Ingles", "1"));

        sm.dumpFile(ETypeFile.SERIALIZATE);
    }

    private void getSER() {
        sm.setSubjects(new ArrayList<>());
        sm.loadFile(ETypeFile.SERIALIZATE);
    }

    @Test
    void testFileSER() {
        this.escenarieSER();
        this.getSER();

        List<Subject> subjects = sm.getSubjects();

        assertNotNull(subjects);
        // verifica que existan 2 materia agregados
        assertEquals(5, subjects.size());

    }

}
package co.edu.uptc.student.persistence;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import co.edu.uptc.student.constants.CommonConstants;
import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.interfaces.IActionFile;
import co.edu.uptc.student.model.Student;
import co.edu.uptc.student.model.Subject;

public class SubjectManagement extends FilePlain implements IActionFile {
    private final String NAME_TAG_SUBJECT = "subject";
    private List<Subject> subjects;
    private List<Student> students;
    private StudentManagement sm = StudentManagement.getInstance();
    
   
    public SubjectManagement() {
        this.subjects = new ArrayList<>();
        sm.loadFile(ETypeFile.TXT);
        sm.loadFile(ETypeFile.XML);
        sm.loadFile(ETypeFile.JSON);
        sm.loadFile(ETypeFile.CSV);
        sm.loadFile(ETypeFile.SERIALIZATE);
        this.students = sm.getStudents();
    }

    public List<Student> getStudents() {
        if (this.students == null) {
            this.students = StudentManagement.getInstance().getStudents();
        }
        return students;
    }

    public boolean addSubject(Subject subject) {
    	
  
    	
        if (subject.getCodeSubject().isEmpty() || subject.getName().isEmpty() || subject.getNumberCredits().isEmpty()) {
            return false; // Campos vacíos no permitidos
        }
        
    

        for (Subject su : this.subjects) {
            if (su.getName().equals(subject.getName()) || su.getCodeSubject().equals(subject.getCodeSubject())) {
                return false; // Evitar duplicados
            }
        }

        boolean codeExists = false;
        for (Student student : getStudents()) {
            if (student.getCode().equals(subject.getCodeStudent())) {
                codeExists = true;
                break;
            }
        }

        if (!codeExists) {
            return false; // El código de la asignatura no coincide con ningún código de estudiante
        }

        this.subjects.add(subject);
        return true;
    }

    public void deleteSubjectByCode(String code) {
        this.subjects = this.subjects.stream()
                .filter(subject -> !subject.getCodeSubject().equalsIgnoreCase(code))
                .collect(Collectors.toList());
    }

    public Subject findSubjectByCode(String code) {
        return this.subjects.stream()
                .filter(subject -> subject.getCodeSubject().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    // Manejo persistencia archivos TXT
    public void dumpFilePlain() {
        StringBuilder pathFileName = new StringBuilder();
        pathFileName.append(confValue.getPath());
        pathFileName.append(confValue.getNameSubjectFileTXT());

        List<String> records = new ArrayList<>();
        for (Subject subject : subjects) {
            StringBuilder contentContact = new StringBuilder();
            contentContact.append(subject.getCodeSubject()).append(CommonConstants.SEMI_COLON);
            contentContact.append(subject.getName()).append(CommonConstants.SEMI_COLON);
            contentContact.append(subject.getNumberCredits()).append(CommonConstants.SEMI_COLON);
            contentContact.append(subject.getCodeStudent());

            records.add(contentContact.toString());
        }
        this.writer(pathFileName.toString(), records);
    }

    public void loadFilePlain() {
        List<String> contentInLine = this.reader(confValue.getPath().concat(confValue.getNameSubjectFileTXT()));
        contentInLine.forEach(row -> {
            StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
            if (tokens.countTokens() < 4) {
                System.err.println("Formato incorrecto en la línea: " + row);
                return;
            }
            String code = tokens.nextToken();
            String name = tokens.nextToken();
            String credits = tokens.nextToken();
            String codeStudent = tokens.nextToken();
            subjects.add(new Subject(code, name, credits, codeStudent));
        });
    }

    // Manejo persistencia archivos XML
    private void dumpFileXML() {
        String filePath = confValue.getPath().concat(confValue.getNameSubjectFileXML());
        StringBuilder lines = new StringBuilder();
        List<Subject> subjects = this.subjects.stream().collect(Collectors.toList());
        lines.append("<XML version=\"1.0\" encoding=\"UTF-8\"> \n");
        for (Subject subject : subjects) {
            lines.append("<subject>\n");
            lines.append("<code>" + subject.getCodeSubject() + "</code>\n");
            lines.append("<name>" + subject.getName() + "</name>\n");
            lines.append("<credits>" + subject.getNumberCredits() + "</credits>\n");
            lines.append("<codeStudent>" + subject.getCodeStudent() + "</codeStudent>\n");
            lines.append("</subject>\n");
        }
        lines.append("</XML>");
        this.writeFile(filePath, lines.toString());
    }

    public void loadFileXML() {
        try {
            File file = new File(confValue.getPath().concat(confValue.getNameSubjectFileXML()));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            NodeList list = document.getElementsByTagName(NAME_TAG_SUBJECT);
            for (int i = 0; i < list.getLength(); i++) {
                String code = document.getElementsByTagName("code").item(i).getTextContent();
                String name = document.getElementsByTagName("name").item(i).getTextContent();
                String credits = document.getElementsByTagName("credits").item(i).getTextContent();
                String codeStudent = document.getElementsByTagName("codeStudent").item(i).getTextContent();
                subjects.add(new Subject(code, name, credits, codeStudent));
            }
        } catch (Exception e) {
            System.out.println("Se presentó un error en el cargue del archivo XML");
            e.printStackTrace();
        }
    }

    // Manejo persistencia archivos JSON
    private void dumpFileJSON() {
        String filePath = confValue.getPath().concat(confValue.getNameSubjectFileJSON());
        StringBuilder stringJSON = new StringBuilder();
        stringJSON.append("[\n");
        for (int i = 0; i < this.subjects.size(); i++) {
            stringJSON.append("{\n");
            stringJSON.append("\"code\" : \"").append(subjects.get(i).getCodeSubject()).append("\", \n");
            stringJSON.append("\"name\" : \"").append(subjects.get(i).getName()).append("\", \n");
            stringJSON.append("\"credits\" : \"").append(subjects.get(i).getNumberCredits()).append("\", \n");
            stringJSON.append("\"codeStudent\" : \"").append(subjects.get(i).getCodeStudent()).append("\" \n");
            stringJSON.append("}");
            if (i < subjects.size() - 1) {
                stringJSON.append(", \n");
            } else {
                stringJSON.append("\n");
            }
        }
        stringJSON.append("]");
        this.writeFile(filePath, stringJSON.toString());
    }

    public void loadFileJSON() {
        StringBuilder filename = new StringBuilder();
        filename.append(this.confValue.getPath());
        filename.append(this.confValue.getNameSubjectFileJSON());
        String content = this.readFile(filename.toString());
        content = content.trim();
        if (content.startsWith("[") && content.endsWith("]")) {
            content = content.substring(1, content.length() - 1);
        }

        String[] objects = content.split("\\},\\s*\\{");
        for (String obj : objects) {
            obj = obj.replace("{", "").replace("}", "");
            String[] fields = obj.split(",");
            if (fields.length < 4) {
                System.err.println("Formato incorrecto en la línea: " + obj);
                continue;
            }
            String code = "", name = "", credits = "", codeStudent = "";
            for (String field : fields) {
                String[] keyValue = field.split(":");
                if (keyValue.length < 2) {
                    System.err.println("Formato incorrecto en el campo: " + field);
                    continue;
                }
                String key = keyValue[0].trim().replace("\"", "");
                String value = keyValue[1].trim().replace("\"", "");
                switch (key) {
                    case "code":
                        code = value;
                        break;
                    case "name":
                        name = value;
                        break;
                    case "credits":
                        credits = value;
                        break;
                    case "codeStudent":
                        codeStudent = value;
                        break;
                }
            }
            this.subjects.add(new Subject(code, name, credits, codeStudent));
        }
    }

    // Manejo persistencia archivos Serializate
    private void dumpFileSerializate() {
        try (FileOutputStream fileOut = new FileOutputStream(
                this.confValue.getPath().concat(this.confValue.getNameSubjectFileSer()));
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this.subjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFileSerializate() {
        try (FileInputStream fileIn = new FileInputStream(
                this.confValue.getPath().concat(this.confValue.getNameSubjectFileSer()));
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            
            this.subjects = (List<Subject>) in.readObject();

        } catch (EOFException e) {
            System.err.println("Error: Archivo serializado incompleto.");
        
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Manejo archivos CSV
    private void dumpFileCSV() {
        StringBuilder pathFileName = new StringBuilder();
        pathFileName.append(confValue.getPath());
        pathFileName.append(confValue.getNameSubjectFileCSV());

        List<String> records = new ArrayList<>();
        for (Subject subject : subjects) {
            StringBuilder contentContact = new StringBuilder();
            contentContact.append(subject.getCodeSubject()).append(CommonConstants.SEMI_COLON);
            contentContact.append(subject.getName()).append(CommonConstants.SEMI_COLON);
            contentContact.append(subject.getNumberCredits()).append(CommonConstants.SEMI_COLON);
            contentContact.append(subject.getCodeStudent());

            records.add(contentContact.toString());
        }
        this.writer(pathFileName.toString(), records);
    }

    private void loadFileCSV() {
        List<String> contentInLine = this.reader(confValue.getPath().concat(confValue.getNameSubjectFileCSV()));
        contentInLine.forEach(row -> {
            StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
            if (tokens.countTokens() < 4) {
                System.err.println("Formato incorrecto en la línea: " + row);
                return;
            }
            String code = tokens.nextToken();
            String name = tokens.nextToken();
            String credits = tokens.nextToken();
            String codeStudent = tokens.nextToken();
            subjects.add(new Subject(code, name, credits, codeStudent));
        });
    }

    @Override
    public void dumpFile(ETypeFile etypefile) {
        if (ETypeFile.TXT.equals(etypefile)) {
            this.dumpFilePlain();
        }
        if (ETypeFile.XML.equals(etypefile)) {
            this.dumpFileXML();
        }
        if (ETypeFile.JSON.equals(etypefile)) {
            this.dumpFileJSON();
        }
        if (ETypeFile.SERIALIZATE.equals(etypefile)) {
            this.dumpFileSerializate();
        }
        if (ETypeFile.CSV.equals(etypefile)) {
            this.dumpFileCSV();
        }
    }

    @Override
    public void loadFile(ETypeFile etypefile) {
        if (ETypeFile.TXT.equals(etypefile)) {
            this.loadFilePlain();
        }
        if (ETypeFile.XML.equals(etypefile)) {
            this.loadFileXML();
        }
        if (ETypeFile.JSON.equals(etypefile)) {
            this.loadFileJSON();
        }
        if (ETypeFile.SERIALIZATE.equals(etypefile)) {
            this.loadFileSerializate();
        }
        if (ETypeFile.CSV.equals(etypefile)) {
            this.loadFileCSV();
        }
    }

    public String getNAME_TAG_SUBJECT() {
        return NAME_TAG_SUBJECT;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}


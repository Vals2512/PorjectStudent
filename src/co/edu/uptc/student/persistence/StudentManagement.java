package co.edu.uptc.student.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import co.edu.uptc.student.constants.CommonConstants;
import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.interfaces.IActionFile;
import co.edu.uptc.student.model.Student;

public class StudentManagement extends FilePlain implements IActionFile {
    private final String NAME_TAG_STUDENT = "student";
    private List<Student> students;

    private static StudentManagement instance;
    

   
    private StudentManagement() {
        students = new ArrayList<>();
    }

    // Método estático para obtener la única instancia de la clase
    public static StudentManagement getInstance() {
        if (instance == null) {
            synchronized (StudentManagement.class) {
                if (instance == null) {
                    instance = new StudentManagement();
                }
            }
        }
        return instance;
    }


    public boolean addStudent(Student student) {
        if (student.getId().isEmpty() || student.getName().isEmpty() ||
                student.getLastName().isEmpty() || student.getCode().isEmpty() ||
                student.getCareer().isEmpty() || student.getEmail().isEmpty()) {
            return false; // Campos vacíos no permitidos
        }

        for (Student s : this.students) {
            if (s.getId().equals(student.getId()) || s.getCode().equals(student.getCode())) {
                return false; // Evitar duplicados
            }
        }
    

        this.students.add(student);
        return true;
    }

    public void deleteStudentByCode(String code) {
        this.students = this.students.stream()
                .filter(student -> !student.getCode().equals(code))
                .collect(Collectors.toList());
    }

    public Student findStudentByCode(String code) {
        return this.students.stream()
                .filter(student -> student.getCode().equals(code))
                .findFirst()
                .orElse(null);

    }

    public List<Student> getStudents() {
        return students;
    }

    public void setListStudents(List<Student> students) {
        this.students = students;
    }

    // manejo peristencia archivos txt
    public void dumpFilePlain() {

        StringBuilder pathFileName = new StringBuilder();
        pathFileName.append(confValue.getPath());
        pathFileName.append(confValue.getNameFileTXT());

        List<String> records = new ArrayList<>();
        for (Student student : students) {
            StringBuilder contentContact = new StringBuilder();
            contentContact.append(student.getId()).append(CommonConstants.SEMI_COLON);
            contentContact.append(student.getName()).append(CommonConstants.SEMI_COLON);
            contentContact.append(student.getLastName()).append(CommonConstants.SEMI_COLON);
            contentContact.append(student.getCode()).append(CommonConstants.SEMI_COLON);
            contentContact.append(student.getCareer()).append(CommonConstants.SEMI_COLON);
            contentContact.append(student.getEmail());

            records.add(contentContact.toString());

        }
        this.writer(pathFileName.toString(), records);
    }

    public void loadFilePlain() {
        List<String> contentInLine = this.reader(confValue.getPath().concat(confValue.getNameFileTXT()));
        contentInLine.forEach(row -> {
            StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
            while (tokens.hasMoreElements()) {
                String id = tokens.nextToken();
                String name = tokens.nextToken();
                String lastName = tokens.nextToken();
                String code = tokens.nextToken();
                String career = tokens.nextToken();
                String email = tokens.nextToken();
                students.add(new Student(id, name, lastName, code, career, email));
            }

        });

    }

    // Manejo persistencia archvios XML

    // volcado
    private void dumpFileXML() {
        String filePath = confValue.getPath().concat(confValue.getNameFileXML());
        StringBuilder lines = new StringBuilder();
        List<Student> students = this.students.stream().collect(Collectors.toList());
        lines.append("<XML version=\"1.0\" encoding=\"UTF-8\"> \n");
        for (Student student : students) {
            lines.append("<student>\n");
            lines.append("<id>" + student.getId() + "</id>\n");
            lines.append("<name>" + student.getName() + "</name>\n");
            lines.append("<lastName>" + student.getLastName() + "</lastName>\n");
            lines.append("<code>" + student.getCode() + "</code>\n");
            lines.append("<career>" + student.getCareer() + "</career>\n");
            lines.append("<email>" + student.getEmail() + "</email>\n");
            lines.append("</student>\n");
        }
        lines.append("</XML>");
        this.writeFile(filePath, lines.toString());
    }

    // cargado
    public void loadFileXML() {
        try {
            File file = new File(confValue.getPath().concat(confValue.getNameFileXML()));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file); // Viene de org.w3c.dom.Document
            NodeList list = document.getElementsByTagName(NAME_TAG_STUDENT);
            for (int i = 0; i < list.getLength(); i++) {
                String id = document.getElementsByTagName("id").item(i).getTextContent();
                String name = document.getElementsByTagName("name").item(i).getTextContent();
                String lastName = document.getElementsByTagName("lastName").item(i).getTextContent();
                String code = document.getElementsByTagName("code").item(i).getTextContent();
                String career = document.getElementsByTagName("career").item(i).getTextContent();
                String email = document.getElementsByTagName("email").item(i).getTextContent();
                students.add(new Student(id, name, lastName, code, career, email));
            }
        } catch (Exception e) {
            System.out.println("Se presentó un error en el cargue del archivo XML");
        }

    }

    // Manejo persistencia archivos JSON
    // volcado

    private void dumpFileJSON() {
        String filePath = confValue.getPath().concat(confValue.getNameFileJSON());
        StringBuilder stringJSON = new StringBuilder();
        stringJSON.append("[\n");

        for (int i = 0; i < this.students.size(); i++) {
         
            stringJSON.append("{\n");
            stringJSON.append("\"id\" : \"").append(students.get(i).getId()).append("\", \n");
            stringJSON.append("\"name\" : \"").append(students.get(i).getName()).append("\", \n");
            stringJSON.append("\"lastName\" : \"").append(students.get(i).getLastName()).append("\", \n");
            stringJSON.append("\"code\" : \"").append(students.get(i).getCode()).append("\", \n");
            stringJSON.append("\"career\" : \"").append(students.get(i).getCareer()).append("\", \n");
            stringJSON.append("\"email\" : \"").append(students.get(i).getEmail()).append("\" \n");
            stringJSON.append("}");
            if (i < students.size() - 1) {
                stringJSON.append(",\n");
            } else {
                stringJSON.append("\n");
            }
        }

        stringJSON.append("]");
        this.writeFile(filePath, stringJSON.toString());
    }

    // cargado

    public void loadFileJSON() {

        StringBuilder fileName = new StringBuilder();
        fileName.append(this.confValue.getPath());
        fileName.append(this.confValue.getNameFileJSON());

        String content = this.readFile(fileName.toString());
        content = content.trim();
        // eliminar los corchetes inicial y final
        if (content.startsWith("[") && content.endsWith("]")) {
            content = content.substring(1, content.length() - 1);
        }
        // dividir poe "},{" para obtener cada objeto individual

        String[] objects = content.split("\\}, \n\\{");
        for (String obj : objects) {
            // eliminar posibles llaves restantes
            obj = obj.replace("{", "");
            String[] fields = obj.split(",");
            String id = "", name = "", lastName = "", code = "", career = "", email = "";

            for (String field : fields) {
                String[] keyValue = field.split(":");
                String key = keyValue[0].trim().replace("\"", "");
                String value = keyValue[1].trim().replace("\"", "");

                switch (key) {
                    case "id":
                        id = value;
                        break;

                    case "name":
                        name = value;
                        break;

                    case "lastName":
                        lastName = value;
                        break;

                    case "code":
                        code = value;
                        break;
                    case "career":
                        career = value;
                        break;

                    case "email":
                        email = value;
                        break;

                }

            }
            this.students.add(new Student(id, name, lastName, code, career, email));

        }

    }

    // Manejo persistencia archivos Serializate

    // volcado
    private void dumpFileSerializate() {
        try (FileOutputStream fileOut = new FileOutputStream(
                this.confValue.getPath().concat(this.confValue.getNameFileSer()));
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this.students);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // caragdo

    @SuppressWarnings("unchecked")
    private void loadFileSerializate() {
        try (FileInputStream fileIn = new FileInputStream(
                this.confValue.getPath().concat(this.confValue.getNameFileSer()));
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            this.students = (List<Student>) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }

    // Manejo archivos (es la msima estructura que patra archivos planos)
    // volcado
    private void dumpFileCSV() {
        StringBuilder pathFileName = new StringBuilder();
        pathFileName.append(confValue.getPath());
        pathFileName.append(confValue.getNameFileCSV());

        List<String> records = new ArrayList<>();
        for (Student student : students) {
            StringBuilder contentContact = new StringBuilder();
            contentContact.append(student.getId()).append(CommonConstants.SEMI_COLON);
            contentContact.append(student.getName()).append(CommonConstants.SEMI_COLON);
            contentContact.append(student.getLastName()).append(CommonConstants.SEMI_COLON);
            contentContact.append(student.getCode()).append(CommonConstants.SEMI_COLON);
            contentContact.append(student.getCareer()).append(CommonConstants.SEMI_COLON);
            contentContact.append(student.getEmail());

            records.add(contentContact.toString());

        }
        this.writer(pathFileName.toString(), records);
    }

    // cargado
    private void loadFileCSV() {
        List<String> contentInLine = this.reader(confValue.getPath().concat(confValue.getNameFileCSV()));
        contentInLine.forEach(row -> {
            StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
            while (tokens.hasMoreElements()) {
                String id = tokens.nextToken();
                String name = tokens.nextToken();
                String lastName = tokens.nextToken();
                String code = tokens.nextToken();
                String career = tokens.nextToken();
                String email = tokens.nextToken();
                students.add(new Student(id, name, lastName, code, career, email));
            }

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

}

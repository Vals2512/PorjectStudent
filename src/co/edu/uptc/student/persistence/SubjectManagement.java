package co.edu.uptc.student.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import co.edu.uptc.student.constants.CommonConstants;
import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.interfaces.IActionFile;

import co.edu.uptc.student.model.Subject;

public class SubjectManagement extends FilePlain implements IActionFile {
    private final String NAME_TAG_SUBJECT = "subject";
    private List<Subject> subjects;

    public SubjectManagement() {
        this.subjects = new ArrayList<Subject>();
    }

    public boolean addSubject(Subject subject) {
        if (subject.getCode().isEmpty() || subject.getName().isEmpty() ||
                subject.getNumberCredits().isEmpty()) {
            return false; // Campos vacíos no permitidos
        }

        for (Subject su : this.subjects) {
            if (su.getName().equals(subject.getName()) || su.getCode().equals(subject.getCode())) {
                return false; // Evitar duplicados
            }
        }

        this.subjects.add(subject);
        return true;
    }

    public void deleteSubjectByCode(String code) {
        this.subjects = this.subjects.stream()
                .filter(subject -> !subject.getCode().equalsIgnoreCase(code))
                .collect(Collectors.toList());
    }

    public Subject findSubjectByCode(String code) {
        return this.subjects.stream()
                .filter(subject -> subject.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    // manejo peristencia archivos txt
    public void dumpFilePlain() {

        StringBuilder pathFileName = new StringBuilder();
        pathFileName.append(confValue.getPath());
        pathFileName.append(confValue.getNameSubjectFileTXT());

        List<String> records = new ArrayList<>();
        for (Subject subject : subjects) {
            StringBuilder contentContact = new StringBuilder();
            contentContact.append(subject.getCode()).append(CommonConstants.SEMI_COLON);
            contentContact.append(subject.getName()).append(CommonConstants.SEMI_COLON);
            contentContact.append(subject.getNumberCredits());

            records.add(contentContact.toString());

        }
        this.writer(pathFileName.toString(), records);
    }

    public void loadFilePlain() {
        List<String> contentInLine = this.reader(confValue.getPath().concat(confValue.getNameSubjectFileTXT()));
        contentInLine.forEach(row -> {
            StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
            while (tokens.hasMoreElements()) {
                String code = tokens.nextToken();
                String name = tokens.nextToken();
                String credits = tokens.nextToken();
                subjects.add(new Subject(code, name, credits));
            }

        });

    }

    // Manejo persistencia archvios XML

    // volcado
    private void dumpFileXML() {
        String filePath = confValue.getPath().concat(confValue.getNameSubjectFileXML());
        StringBuilder lines = new StringBuilder();
        List<Subject> subjects = this.subjects.stream().collect(Collectors.toList());
        lines.append("<XML version=\"1.0\" encoding=\"UTF-8\"> \n");
        for (Subject subject : subjects) {
            lines.append("<subject>\n");
            lines.append("<code>" + subject.getCode() + "</code>\n");
            lines.append("<name>" + subject.getName() + "</name>\n");
            lines.append("<credits>" + subject.getNumberCredits() + "</credits>\n");
            lines.append("</subject>\n");
        }
        lines.append("</XML>");
        this.writeFile(filePath, lines.toString());
    }

    // cargado
    public void loadFileXML() {
        try {
            File file = new File(confValue.getPath().concat(confValue.getNameSubjectFileXML()));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file); // Viene de org.w3c.dom.Document
            NodeList list = document.getElementsByTagName(NAME_TAG_SUBJECT);
            for (int i = 0; i < list.getLength(); i++) {
                String code = document.getElementsByTagName("code").item(i).getTextContent();
                String name = document.getElementsByTagName("name").item(i).getTextContent();
                String credits = document.getElementsByTagName("credits").item(i).getTextContent();
                subjects.add(new Subject(code, name, credits));
            }
        } catch (Exception e) {
            System.out.println("Se presentó un error en el cargue del archivo XML");
        }

    }

    // Manejo persistencia archivos JSON
    // volcado

    private void dumpFileJSON() {

        String filePath = confValue.getPath().concat(confValue.getNameSubjectFileJSON());
        StringBuilder stringJSON = new StringBuilder();
        stringJSON.append("[\n");
        for (int i = 0; i < this.subjects.size(); i++) {
            stringJSON.append("{\n");
            stringJSON.append("\"code\" : \"").append(subjects.get(i).getCode()).append("\", \n");
            stringJSON.append("\"name\" : \"").append(subjects.get(i).getName()).append("\", \n");
            stringJSON.append("\"credits\" : \"").append(subjects.get(i).getNumberCredits()).append("\" \n");
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

    // cargado

    public void loadFileJSON() {
        StringBuilder filename = new StringBuilder();
        filename.append(this.confValue.getPath());
        filename.append(this.confValue.getNameSubjectFileJSON());
        String content = this.readFile(filename.toString());
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
            String code = "", name = "", credits = "";
            for (String field : fields) {
                String[] keyValue = field.split(":");
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

                }

            }
            this.subjects.add(new Subject(code, name, credits));
        }
    }

    // Manejo persistencia archivos Serializate

    // volcado
    private void dumpFileSerializate() {
        try (FileOutputStream fileOut = new FileOutputStream(
                this.confValue.getPath().concat(this.confValue.getNameSubjectFileSer()));
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this.subjects);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // caragdo

    @SuppressWarnings("unchecked")
    private void loadFileSerializate() {
        try (FileInputStream fileIn = new FileInputStream(
                this.confValue.getPath().concat(this.confValue.getNameSubjectFileSer()));
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            this.subjects = (List<Subject>) in.readObject();
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
        pathFileName.append(confValue.getNameSubjectFileCSV());

        List<String> records = new ArrayList<>();
        for (Subject subject : subjects) {
            StringBuilder contentContact = new StringBuilder();
            contentContact.append(subject.getCode()).append(CommonConstants.SEMI_COLON);
            contentContact.append(subject.getName()).append(CommonConstants.SEMI_COLON);
            contentContact.append(subject.getNumberCredits());

            records.add(contentContact.toString());

        }
        this.writer(pathFileName.toString(), records);
    }

    // cargado
    private void loadFileCSV() {
        List<String> contentInLine = this.reader(confValue.getPath().concat(confValue.getNameSubjectFileCSV()));
        contentInLine.forEach(row -> {
            StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
            while (tokens.hasMoreElements()) {
                String code = tokens.nextToken();
                String name = tokens.nextToken();
                String credits = tokens.nextToken();
                subjects.add(new Subject(code, name, credits));
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

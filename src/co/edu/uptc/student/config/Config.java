package co.edu.uptc.student.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Config config;
    private Properties properties;
    private String path;
    private String nameFileTXT;
    private String nameFileXML;
    private String nameFileJSON;
    private String nameFileSer;
    private String nameFileCSV;
    private String nameFileUserSER;
    private String nameSubjectFileTXT;
    private String nameSubjectFileXML;
    private String nameSubjectFileJSON;
    private String nameSubjectFileSer;
    private String nameSubjectFileCSV;

    private Config() {
        this.properties = new Properties();

        try (FileInputStream entrada = new FileInputStream("resources/conf/app.config.properties")) {
            properties.load(entrada);
            this.path = properties.getProperty("app.file.path");
            this.nameFileTXT = properties.getProperty("app.file.name.txt");
            this.nameFileXML = properties.getProperty("app.file.name.xml");
            this.nameFileJSON = properties.getProperty("app.file.name.json");
            this.nameFileSer = properties.getProperty("app.file.name.ser");
            this.nameFileCSV = properties.getProperty("app.file.name.csv");
            this.nameFileUserSER = properties.getProperty("app.file.user.name.ser");
            this.nameSubjectFileTXT = properties.getProperty("app.file.subject.name.txt");
            this.nameSubjectFileXML = properties.getProperty("app.file.subject.name.xml");
            this.nameSubjectFileCSV = properties.getProperty("app.file.subject.name.csv");
            this.nameSubjectFileJSON = properties.getProperty("app.file.subject.name.json");
            this.nameSubjectFileSer = properties.getProperty("app.file.subject.name.ser");

        } catch (IOException ex) {
            System.err.println("Error al cargar el archivo properties de configuración: " + ex.getMessage());
        }
    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;

    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNameFileTXT() {
        return nameFileTXT;
    }

    public void setNameFileTXT(String nameFileTXT) {
        this.nameFileTXT = nameFileTXT;
    }

    public String getNameFileXML() {
        return nameFileXML;
    }

    public void setNameFileXML(String nameFileXML) {
        this.nameFileXML = nameFileXML;
    }

    public String getNameFileJSON() {
        return nameFileJSON;
    }

    public void setNameFileJSON(String nameFileJSON) {
        this.nameFileJSON = nameFileJSON;
    }

    public static Config getConfig() {
        return config;
    }

    public static void setConfig(Config config) {
        Config.config = config;
    }

    public String getNameFileSer() {
        return nameFileSer;
    }

    public void setNameFileSer(String nameFileSer) {
        this.nameFileSer = nameFileSer;
    }

    public String getNameFileCSV() {
        return nameFileCSV;
    }

    public void setNameFileCSV(String nameFileCSV) {
        this.nameFileCSV = nameFileCSV;
    }

    public String getNameFileUserSER() {
        return nameFileUserSER;
    }

    public void setNameFileUserSER(String nameFileUserSER) {
        this.nameFileUserSER = nameFileUserSER;
    }

    public String getNameSubjectFileTXT() {
        return nameSubjectFileTXT;
    }

    public void setNameSubjectFileTXT(String nameSubjectFileTXT) {
        this.nameSubjectFileTXT = nameSubjectFileTXT;
    }

    public String getNameSubjectFileXML() {
        return nameSubjectFileXML;
    }

    public void setNameSubjectFileXML(String nameSubjectFileXML) {
        this.nameSubjectFileXML = nameSubjectFileXML;
    }

    public String getNameSubjectFileJSON() {
        return nameSubjectFileJSON;
    }

    public void setNameSubjectFileJSON(String nameSubjectFileJSON) {
        this.nameSubjectFileJSON = nameSubjectFileJSON;
    }

    public String getNameSubjectFileSer() {
        return nameSubjectFileSer;
    }

    public void setNameSubjectFileSer(String nameSubjectFileSer) {
        this.nameSubjectFileSer = nameSubjectFileSer;
    }

    public String getNameSubjectFileCSV() {
        return nameSubjectFileCSV;
    }

    public void setNameSubjectFileCSV(String nameSubjectFileCSV) {
        this.nameSubjectFileCSV = nameSubjectFileCSV;
    }

}

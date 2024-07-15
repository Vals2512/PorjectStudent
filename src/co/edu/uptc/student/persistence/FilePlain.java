package co.edu.uptc.student.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import co.edu.uptc.student.config.Config;
import co.edu.uptc.student.constants.CommonConstants;

public class FilePlain {
    protected Config confValue;

    public FilePlain() {
        confValue = Config.getInstance();
    }

    protected String readFile(String namePath) {
        StringBuilder contenido = new StringBuilder();
        try {
            FileReader fr = new FileReader(namePath);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append(CommonConstants.NEXT_LINE);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Se presentó un error al leer el archivo especificado");
        }

        return contenido.toString();
    }

    public void writeFile(String namePathFile, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namePathFile))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Se presentó un error al leer el archivo especificado");
        }

    }

    protected List<String> reader(String pathName) {
        List<String> output = new ArrayList<String>();
        StringTokenizer tokens = new StringTokenizer(this.readFile(pathName), CommonConstants.NEXT_LINE);
        while (tokens.hasMoreElements()) {
            output.add(tokens.nextToken());
        }
        return output;

    }

    protected void writer(String pathName, List<String> file) {
        StringBuilder strContent = new StringBuilder();
        file.forEach(record -> strContent.append(record).append(CommonConstants.NEXT_LINE));
        writeFile(pathName, strContent.toString());

    }
}
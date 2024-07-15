package co.edu.uptc.student.model;

import java.io.Serializable;

public class Subject implements Serializable {

  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codeSubject;
    private String name;
    private String numberCredits;

    public Subject() {
    }

    public Subject(String code, String name, String numberCredits) {

        this.codeSubject = code;
        this.name = name;
        this.numberCredits = numberCredits;
    }

    public String getCode() {
        return codeSubject;
    }

    public void setCode(String code) {
        this.codeSubject = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberCredits() {
        return numberCredits;
    }

    public void setNumberCredits(String numberCredits) {
        this.numberCredits = numberCredits;
    }

    @Override
    public String toString() {
        return "Subject [code=" + codeSubject + ", name=" + name + ", numberCredits=" + numberCredits + "]";
    }

}

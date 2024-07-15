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
    private String codeStudent;

    public Subject() {
    }

    public Subject(String codeSubject, String name, String numberCredits, String codeStu) {

        this.codeSubject = codeSubject;
        this.name = name;
        this.numberCredits = numberCredits;
        this.codeStudent= codeStu;
    }
    
   
    
    

    public String getCodeSubject() {
		return codeSubject;
	}

	public String getCodeStudent() {
		return codeStudent;
	}

	public void setCodeSubject(String codeSubject) {
		this.codeSubject = codeSubject;
	}

	public void setCodeStudent(String codeStudent) {
		this.codeStudent = codeStudent;
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

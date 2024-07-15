package co.edu.uptc.student.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.Student;
import co.edu.uptc.student.persistence.StudentManagement;
import co.edu.uptc.student.sort.StudentSort;


public class ManagementKeyEvents implements KeyListener {

    private PrincipalScreen principalScreen;
    private StudentManagement studentManagement = StudentManagement.getInstance();
    private StudentSort ss= new StudentSort();
    private boolean isControlPressed;
	private boolean isAPressed;
	private boolean isBPressed;
	private boolean isCPressed;
	private boolean isOnePressed;
	private boolean isTwoPressed;

    
    public ManagementKeyEvents(PrincipalScreen ps) {
        this.principalScreen = ps;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			this.isControlPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.isAPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_B) {
			this.isBPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			this.isCPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_1) {
			this.isOnePressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_2) {
			this.isTwoPressed = true;
		}
		if(isControlPressed == true && isAPressed && isOnePressed==true) {
			loadData();
			
			List<Student> students = studentManagement.getStudents();
		
			List<Student> stu =ss.sortByCodigoDescendente(students);
		
			actualizarInterfaz(stu);
		}
		if(isControlPressed == true && isAPressed && isTwoPressed==true) {
			loadData();
			List<Student> students = studentManagement.getStudents();
            List<Student> stu = ss.sortByCodigoAscendente(students);
            actualizarInterfaz(stu);
		
	}
		if(isControlPressed == true && isBPressed && isOnePressed==true) {
			loadData();
			List<Student> students = studentManagement.getStudents();
			List<Student> stu = ss.sortByNameDescendenteInsertion(students);
            actualizarInterfaz(stu);
		}
		
		if(isControlPressed == true && isBPressed && isTwoPressed==true) {
			loadData();
			List<Student> students = studentManagement.getStudents();
			List<Student> stu =  ss.sortByNameAscendenteInsertion(students);
              actualizarInterfaz(stu);
		}
		
		if(isControlPressed == true && isCPressed && isOnePressed==true) {
			loadData();
			List<Student> students = studentManagement.getStudents();
			List<Student> stu =  ss.sortByLastNameDescendente(students);
             actualizarInterfaz(stu);
		}
		
		if(isControlPressed == true && isCPressed && isTwoPressed==true) {
			loadData();
			List<Student> students = studentManagement.getStudents();
			List<Student> stu =  ss.sortByLastNameAscendente(students);
            actualizarInterfaz(stu);
			
		}
		
    }

 
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			this.isControlPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			this.isAPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_B) {
			this.isBPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			this.isCPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_1) {
			this.isOnePressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_2) {
			this.isTwoPressed = false;
		}
		
		
	}
	
	private void loadData() {
		studentManagement.setListStudents(new ArrayList<>());
		studentManagement.loadFile(ETypeFile.TXT);
		studentManagement.loadFile(ETypeFile.XML);
		studentManagement.loadFile(ETypeFile.JSON);
		studentManagement.loadFile(ETypeFile.CSV);
		studentManagement.loadFile(ETypeFile.SERIALIZATE);
		
	}


	private void actualizarInterfaz(List<Student> students) {
        principalScreen.updateStudentList(students);
    }
}



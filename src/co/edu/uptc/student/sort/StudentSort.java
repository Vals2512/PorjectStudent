package co.edu.uptc.student.sort;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.Student;
import co.edu.uptc.student.persistence.StudentManagement;

import java.util.List;

public class StudentSort {
    
	 private StudentManagement sm = StudentManagement.getInstance();

    public void sortByCodigoAscendente(List<Student> students) {
    	
        int n = students.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (students.get(j).getCode().compareTo(students.get(j+1).getCode()) > 0) {
                    // Intercambiar students[j] y students[j+1]
                    Student temp = students.get(j);
                    students.set(j, students.get(j+1));
                    students.set(j+1, temp);
                }
            }
        }
    }

    public void sortByCodigoDescendente(List<Student> students) {
        sm.loadFile(ETypeFile.TXT);
        int n = students.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (students.get(j).getCode().compareTo(students.get(j+1).getCode()) < 0) {
                    // Intercambiar students[j] y students[j+1]
                    Student temp = students.get(j);
                    students.set(j, students.get(j+1));
                    students.set(j+1, temp);
                }
            }
        }
    }

    public void sortByNameAscendenteInsertion(List<Student> students) {
        sm.loadFile(ETypeFile.TXT);
        int n = students.size();
        for (int i = 1; i < n; ++i) {
            Student key = students.get(i);
            int j = i - 1;

            // Mover los elementos de students[0..i-1] que son
            // mayores que key, a una posición adelante de su
            // posición actual
            while (j >= 0 && students.get(j).getName().compareTo(key.getName()) > 0) {
                students.set(j + 1, students.get(j));
                j = j - 1;
            }
            students.set(j + 1, key);
        }
    }

    public void sortByNameDescendenteInsertion(List<Student> students) {
        sm.loadFile(ETypeFile.TXT);
        int n = students.size();
        for (int i = 1; i < n; ++i) {
            Student key = students.get(i);
            int j = i - 1;

            while (j >= 0 && students.get(j).getName().compareTo(key.getName()) < 0) {
                students.set(j + 1, students.get(j));
                j = j - 1;
            }
            students.set(j + 1, key);
        }
    }

    public void sortByLastNameAscendente(List<Student> students) {
        sm.loadFile(ETypeFile.TXT);
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            // Encontrar el índice del elemento mínimo
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).getLastName().compareTo(students.get(minIndex).getLastName()) < 0) {
                    minIndex = j;
                }
            }
            // Intercambiar el elemento mínimo con el primero
            Student temp = students.get(minIndex);
            students.set(minIndex, students.get(i));
            students.set(i, temp);
        }
    }

    public void sortByLastNameDescendente(List<Student> students) {
        sm.loadFile(ETypeFile.TXT);

        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            // Encontrar el índice del elemento máximo
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).getLastName().compareTo(students.get(maxIndex).getLastName()) > 0) {
                    maxIndex = j;
                }
            }
            // Intercambiar el elemento máximo con el primero
            Student temp = students.get(maxIndex);
            students.set(maxIndex, students.get(i));
            students.set(i, temp);
        }
    }
}

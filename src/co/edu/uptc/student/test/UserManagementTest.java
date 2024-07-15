package co.edu.uptc.student.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.model.User;
import co.edu.uptc.student.persistence.UserManagement;

public class UserManagementTest {
    private UserManagement um = new UserManagement();

    private void escenarieSerializate() {

        um.addUser(new User("user", "user"));
        um.dumpFile(ETypeFile.SERIALIZATE);
    }

    private void getSer() {
        um.setListUsers(new ArrayList<>());
        um.loadFile(ETypeFile.SERIALIZATE);
    }

    @Test
    void testFileSerializable() {
        this.escenarieSerializate();
        this.getSer();

        List<User> users = um.getUsers();

        assertNotNull(users);
        // verifica que existan 2 usuarios agregados
        assertEquals(2, users.size());

    }
}

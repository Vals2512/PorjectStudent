package co.edu.uptc.student.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import co.edu.uptc.student.enums.ETypeFile;
import co.edu.uptc.student.interfaces.IActionFile;

import co.edu.uptc.student.model.User;

public class UserManagement extends FilePlain implements IActionFile {

    private List<User> users;

    public UserManagement() {
        this.users = new ArrayList<User>();

    }

    public boolean addUser(User user) {
        if (user.getUserCreate().isEmpty() ||
                user.getPasswordCreate().isEmpty()) {
            return false; // Campos vacíos no permitidos
        }

        for (User us : this.users) {
            if (us.getUserCreate().equals(user.getUserCreate())
                    || us.getPasswordCreate().equals(user.getPasswordCreate())) {
                return false; // Evitar duplicados
            }
        }

        this.users.add(user);
        return true;
    }

    public void deleteUserByUserName(String userName) {
        this.users = this.users.stream()
                .filter(user -> !user.getUserCreate().equalsIgnoreCase(userName))
                .collect(Collectors.toList());
    }

    public User findUserByUserName(String userName) {
        return this.users.stream()
                .filter(user -> user.getUserCreate().equals(userName))
                .findFirst()
                .orElse(null);

    }

    public List<User> getUsers() {
        return users;
    }

    public void setListUsers(List<User> users) {
        this.users = users;
    }

    // Manejo persistencia archivos Serializate

    // volcado
    private void dumpFileSerializate() {
        try (FileOutputStream fileOut = new FileOutputStream(
                this.confValue.getPath().concat(this.confValue.getNameFileUserSER()));
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this.users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // caragdo

    @SuppressWarnings("unchecked")
    private void loadFileSerializate() {
        try (FileInputStream fileIn = new FileInputStream(
                this.confValue.getPath().concat(this.confValue.getNameFileUserSER()));
                ObjectInputStream in = new ObjectInputStream(fileIn)) {
            this.users = (List<User>) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }

    @Override
    public void dumpFile(ETypeFile etypefile) {

        if (ETypeFile.SERIALIZATE.equals(etypefile)) {
            this.dumpFileSerializate();
        }

    }

    @Override
    public void loadFile(ETypeFile etypefile) {

        if (ETypeFile.SERIALIZATE.equals(etypefile)) {
            this.loadFileSerializate();

        }
    }

}
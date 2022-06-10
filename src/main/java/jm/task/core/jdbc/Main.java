package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("ADAM", "SMITH", (byte) 17);
        userService.saveUser("JONES", "DOE", (byte) 44);
        userService.saveUser("MARTIN", "VAN HARTEN", (byte) 64);
        userService.saveUser("CLARK", "KENT", (byte) 33);*/
        //userService.removeUserById(5l);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

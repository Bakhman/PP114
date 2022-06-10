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

        /*Util util = new Util();
        Session session = null;*/

        // ===SAVE==
        /*session = util.getSession();
        session.beginTransaction();
        User user = new User("Arnold", "Shwarcenneger", (byte) 66);
        System.out.println(user);
        session.save(user);
        System.out.println(user);
        session.getTransaction().commit();*/

        // === create LIST USERS ==
        /*session = util.getSession();
        session.beginTransaction();
        List<User> userList = session.createQuery("select i from User i", User.class)
                .getResultList();
        System.out.println(userList);
        session.getTransaction().commit();*/

        // === DELETE ===
        /*session = util.getSession();
        session.beginTransaction();
        session.delete(userList.get(0));
        session.getTransaction().commit();*/

        // === SQLQuery===
       /* session = util.getSession();
        session.beginTransaction();
        session.createSQLQuery("create table users2 (id int not null auto_increment primary key);");
        session.getTransaction().commit();*/




        //session.close();
        UserService userService = new UserServiceImpl();
        /*userService.createUsersTable();
        userService.saveUser("ADAM", "SMITH", (byte) 17);
        userService.saveUser("JONES", "DOE", (byte) 44);
        userService.saveUser("MARTIN", "VAN HARTEN", (byte) 64);
        userService.saveUser("CLARK", "KENT", (byte) 33);*/
        //userService.removeUserById(5l);
        //userService.getAllUsers();
        userService.cleanUsersTable();
        //userService.dropUsersTable();
    }
}

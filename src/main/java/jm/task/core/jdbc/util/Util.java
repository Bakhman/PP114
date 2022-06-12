package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/db113";
    private static final String USERNAME = "bakhman";
    private static final String PASSWORD = "bakhmai";
    
    public static Session getSession(){
        SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(User.class)
            .buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }

    public Util() {
    }

    public static Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            //System.out.println("Connection OK!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR!!!");
        }
        return connection;
    }

}

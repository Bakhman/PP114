package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    Session session = null;
    private final Connection conn = util.openConnection();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "id       BIGINT          NOT NULL AUTO_INCREMENT, " +
                "name     VARCHAR(255)  NOT NULL, " +
                "lastName VARCHAR(255)  NOT NULL, " +
                "age      TINYINT      NOT NULL, PRIMARY KEY (id));";

        try(PreparedStatement pStat = conn.prepareStatement(query)){
            pStat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users;";

        try(PreparedStatement pStat = conn.prepareStatement(query)){
            pStat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = util.getSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        session = util.getSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session = util.getSession();
        session.beginTransaction();
        List<User> userList = session.createQuery("select i from User i", User.class)
                .getResultList();
        session.getTransaction().commit();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        session = util.getSession();
        session.beginTransaction();
        List<User> userList = session.createQuery("select i from User i", User.class)
                .getResultList();
        for (User user : userList) {
            user = session.get(User.class, user.getId());
            session.delete(user);

        }
        session.getTransaction().commit();
    }
}

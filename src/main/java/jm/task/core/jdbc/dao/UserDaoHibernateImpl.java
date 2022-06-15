package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Connection conn = Util.openConnection();
    
    private Transaction transaction = null;
    
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
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users;";

        try(PreparedStatement pStat = conn.prepareStatement(query)){
            pStat.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age){
        try (Session session = Util.getSession().getCurrentSession()) {
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession().getCurrentSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.getSession().getCurrentSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("select i from User i", User.class)
                    .getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession().getCurrentSession()){
            transaction = session.beginTransaction();
            List<User> userList = session.createQuery("select i from User i", User.class)
                    .getResultList();
            for (User user : userList) {
                user = session.get(User.class, user.getId());
                session.delete(user);

            }
            session.getTransaction().commit();

        } catch (HibernateException e) {
            transaction.rollback();
        }
    }
}

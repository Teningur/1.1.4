package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createSQLQuery("""
                    create table users
                    (
                        id       int auto_increment
                            primary key,
                        name     varchar(40) not null,
                        lastName varchar(40) not null,
                        age      tinyint       not null
                    )
                    """).executeUpdate();

            session.getTransaction().commit();
            System.out.println("Таблица создана");
        } catch (PersistenceException e){
        }
   }


    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createSQLQuery("drop table users").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Таблица удалена!");
        } catch (PersistenceException e){
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("Пользователь " + name + " создан!");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            userList = session.createQuery("from User").getResultList();

            session.getTransaction().commit();


        } catch (PersistenceException e) {
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("delete from User").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Таблица очищена!");
        }
    }
}

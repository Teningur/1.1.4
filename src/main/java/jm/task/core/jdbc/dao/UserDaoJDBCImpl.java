package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String query = """
                create table users
                (
                    id       int auto_increment
                        primary key,
                    name     varchar(40) not null,
                    lastName varchar(40) not null,
                    age      varbinary(1024)       not null
                )
                """;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Таблица создана!");
        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        String query = "drop table users";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        String query = "insert into dbtest.users (name, lastName, age) values (?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.executeUpdate();
            System.out.println("User c именем " + name + " успешно добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM dbtest.users WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User c ID " + id + " успешно удален из базы данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "select id, name, lastName, age from dbtest.users";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {

        String query = "DELETE FROM dbtest.users";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            System.out.println("Таблица очищена!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

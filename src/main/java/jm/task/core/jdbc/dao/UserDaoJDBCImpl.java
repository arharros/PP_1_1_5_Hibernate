package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connectionDb = Util.connection();
             Statement statement = connectionDb.createStatement()) {
            statement.execute("CREATE TABLE  if not exists users(\n" +
                    "id_user serial Primary Key,\n" +
                    "name VARCHAR(50),\n" +
                    "lastname VARCHAR(50),\n" +
                    "age Integer);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connectionDb = Util.connection();
             Statement statement = connectionDb.createStatement()) {
            statement.executeUpdate("drop table if exists users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connectionDb = Util.connection();
             Statement statement = connectionDb.createStatement()) {
            statement.executeUpdate(String.format("insert into users(name,lastname,age) " +
                    "values ('%s','%s',%s);", name, lastName, age));
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connectionDb = Util.connection();
             Statement statement = connectionDb.createStatement()) {
            statement.execute(String.format("delete from users where id_user = %s;", id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connectionDb = Util.connection();
             Statement statement = connectionDb.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from users;")) {
            while (resultSet.next()) {
                User user = new User();
                Long id = resultSet.getLong("id_user");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connectionDb = Util.connection();
             Statement statement = connectionDb.createStatement()) {
            statement.executeUpdate("delete from users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

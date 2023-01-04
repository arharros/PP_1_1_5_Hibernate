package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ruslan", "Tulumbaev", (byte) 40);
        userService.saveUser("Alina", "Tulumbaeva", (byte) 35);
        userService.saveUser("Ivan", "Popov", (byte) 32);
        userService.saveUser("Zaur", "Tregulov", (byte) 29);
        System.out.println(userService.getAllUsers());

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());
        userService.dropUsersTable();
    }
}

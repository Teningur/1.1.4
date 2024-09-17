package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        System.out.println();

        userService.saveUser("Name1", "LastName1", (byte) 14);
        userService.saveUser("Name2", "LastName2", (byte) 42);
        userService.saveUser("Name3", "LastName3", (byte) 69);
        userService.saveUser("Name4", "LastName4", (byte) 54);

        System.out.println();

        userService.removeUserById(1);

        System.out.println();

        userService.getAllUsers();

        System.out.println();

        userService.cleanUsersTable();

        System.out.println();

        userService.dropUsersTable();
    }
}

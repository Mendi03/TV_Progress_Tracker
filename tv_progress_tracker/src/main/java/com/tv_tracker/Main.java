package com.tv_tracker;

import java.sql.SQLException;

import com.tv_tracker.dao.ShowDaoImpl;

public class Main {
    public static void main(String[] args) {

        ShowDaoImpl test = new ShowDaoImpl();

        //System.out.println(test.getAll());
        System.out.println(test.userDoesExist("Boi", "678"));

        Menu.mainMenu();

        try {
            test.closeConnection();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            System.out.println("Could not close connection properly");
        }
    }
}
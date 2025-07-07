package com.tv_tracker;

import java.sql.SQLException;

import com.tv_tracker.dao.ShowDaoImpl;
import com.tv_tracker.dao.ShowException;
import com.tv_tracker.dao.User;

public class Main {
    public static void main(String[] args) {

        ShowDaoImpl test = new ShowDaoImpl();
        //User user1 = new User(1, "Boi", "kratos@gmai.com", "678");


        //System.out.println(test.getAll());
        //System.out.println(test.userDoesExist("Boi", "678"));
        //System.out.println(test.getAllMyShows(user1));

        try {
            Menu.mainMenu();
            test.closeConnection();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            System.out.println("Could not close connection properly");
        } catch (ShowException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
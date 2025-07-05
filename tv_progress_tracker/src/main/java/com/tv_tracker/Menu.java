package com.tv_tracker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tv_tracker.dao.Show;
import com.tv_tracker.dao.ShowDaoImpl;

public class Menu {
    // single scanner we will use through our program.
	private static Scanner sc;

	// use service class you created to handle all the CRUD operations
	public static ShowDaoImpl db_connection = new ShowDaoImpl();

	public static void mainMenu() {

		// once we enter menu, can initialize scanner
		sc = new Scanner(System.in);

        String username, password;

        do {
            System.out.println("Welcome to the TV Show Progress Tracker Application!\n");
            System.out.println("Please enter your Username:");
            username = sc.nextLine();
            System.out.println("\nPlease enter your Password:");
            password = sc.nextLine();

        } while (!db_connection.userDoesExist(username, password));
        
        boolean exit = false;

        while(!exit) {

            System.out.println("\nWhat would you like to do?");
            System.out.println("1. List all available shows");
            System.out.println("2. List my shows");
            System.out.println("3. Add Entry");
            System.out.println("4. Update Show Progress");
            System.out.println("5. Delete Entry");
            System.out.println("6. Exit");

            int input = sc.nextInt();
            sc.nextLine(); // will stop issue with an infinite loop w/ scanner (new line character can get stuck in buffer)

            switch (input) {

            case 1:
                showsList();
                break;
            case 2:
                getUserShows();
                break;
            case 3:
                addShowEntry();
                break;
            case 4:
                updateEntryStatus();
                break;
            case 5:
                deleteShowEntry();
                break;
            case 6:
                exit = true;
                break;
            default:
                System.out.println("\nPlease enter an option listed (number 1 - 6)");
                break;
            }
        }
            System.out.println("\n\nGoodBye!");
        // once we exit, will close the scanner
            sc.close();
	}

    public static void showsList(){

        List<Show> ShowList = db_connection.getAll();
        for (Show show : ShowList) {
            System.out.println("Show ID: " + show.getShow_id() + ", Title: " + show.getTitle() + ", Description: " + show.getDescription());
        }
    }

    public static void getUserShows(){

    }

    public static void addShowEntry(){

    }

    public static void updateEntryStatus(){

    }

    public static void deleteShowEntry(){

    }
}

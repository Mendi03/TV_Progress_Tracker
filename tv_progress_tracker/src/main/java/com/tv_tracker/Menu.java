package com.tv_tracker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tv_tracker.dao.Show;
import com.tv_tracker.dao.ShowDaoImpl;
import com.tv_tracker.dao.ShowException;
import com.tv_tracker.dao.User;

public class Menu {
    // single scanner we will use through our program.
	private static Scanner sc;

	// use service class you created to handle all the CRUD operations
	public static ShowDaoImpl db_connection = new ShowDaoImpl();

	public static void mainMenu() throws ShowException {

		// once we enter menu, can initialize scanner
		sc = new Scanner(System.in);

        String username, password;
        System.out.println("Welcome to the TV Show Progress Tracker Application!\n");

        System.out.println("Please enter your Username:");
        username = sc.nextLine();
        System.out.println("\nPlease enter your Password:");
        password = sc.nextLine();
    
        if(!db_connection.userDoesExist(username, password)){
            throw new ShowException("Invalid Username or Password. Please run the application again");
        }

        User user = db_connection.createUser(username, password);
        
        boolean exit = false;

        while(!exit) {

            System.out.println("\nWhat would you like to do?");
            System.out.println("1. List all available shows");
            System.out.println("2. List my shows");
            System.out.println("3. Add Entry");
            System.out.println("4. Update Show Progress");
            System.out.println("5. Delete Entry");
            System.out.println("6. Exit\n");

            int input = sc.nextInt();
            sc.nextLine(); // will stop issue with an infinite loop w/ scanner (new line character can get stuck in buffer)

            switch (input) {

            case 1:
                showsList();
                break;
            case 2:
                getUserShows(user);
                break;
            case 3:
                addShowEntry(user);
                break;
            case 4:
                updateEntryStatus(user);
                break;
            case 5:
                deleteShowEntry(user);
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
        // once we exit, will close the scanner and conncection
        try {
            sc.close();
            db_connection.closeConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

    public static void showsList(){

        // Get list for all shows using getAll function
        List<Show> ShowList = db_connection.getAll();

        // Print all shows
        for (Show show : ShowList) {
            System.out.println("Show ID: " + show.getShow_id() + ", Title: " + show.getTitle() + ", Description: " + show.getDescription());
        }
    }

    public static void getUserShows(User user){
        List<Show> ShowList = db_connection.getAllMyShows(user);
        System.out.println("Status key: ");
        System.out.println("1. Not Started, 2. In Progress, 3. Completed\n");

        int count = 1;
        // Print all shows
        for (Show show : ShowList) {
            System.out.println(count + ". Title: " + show.getTitle() + ", Status: " + show.getStatus() + ", Description: " + show.getDescription());
            count++;
        }
    }

    public static void addShowEntry(User user) throws ShowException{
        System.out.println("Enter title for the show you want to track: \n");
        String show_name = sc.nextLine();

        List<Show> ShowList;

        ShowList = db_connection.findByTitle(show_name);

        if(ShowList.isEmpty()){
            System.out.println("No results found for: " + show_name + "\n");
            addShowEntry(user);
        }

        else if(ShowList.size() == 1){
            try {
                confirm_show(user, ShowList, 0);
            } catch (ShowException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }

        else if(ShowList.size() > 1){
            System.out.println("Found multiple entries for '" + show_name + "'");
            System.out.println("Which of the following would you like to add?");
            System.out.println("(Please enter the number associated with the entry)\n");

            int counter = 1;

            for (Show show : ShowList) {
                System.out.println(counter + ". " + show.getTitle());
                counter++;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            if ((choice > ShowList.size()) || choice < 1 ) {
                System.out.println("Incorrect input, going back to the menu");
                return;
            }
            else{
                try {
                    confirm_show(user, ShowList, choice - 1);
                } catch (ShowException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    public static void updateEntryStatus(User user){
        List<Show> ShowList = db_connection.getAllMyShows(user);

        System.out.println("Which of the following would you like to update?");
        System.out.println("(Please enter the number associated with the entry)\n");
        System.out.println("Status key: ");
        System.out.println("1. Not Started, 2. In Progress, 3. Completed");

        int count = 1;
        // Print all shows
        for (Show show : ShowList) {
            System.out.println(count + ". Title: " + show.getTitle() + ", Status: " + show.getStatus() + ", Description: " + show.getDescription());
            count++;
        }

        System.out.println("");

        int choice = sc.nextInt();
        sc.nextLine();

        Show chosen = ShowList.get(choice - 1);

        System.out.println("What will the new status be for '" + chosen.getTitle() + "'?");
        System.out.println("1. Not Started, 2. In Progress, 3. Completed");

        int progress = sc.nextInt();
        sc.nextLine();

        switch (progress) {
            case 1:
                chosen.setStatus(1);
                db_connection.update(user, chosen);
                return;
            case 2:
                chosen.setStatus(2);
                db_connection.update(user, chosen);
                return;
            case 3:
                chosen.setStatus(3);
                db_connection.update(user, chosen);
                return;  
            default:
                System.out.println("Incorrect input, show has been marked as '1. Not started'");
                chosen.setStatus(1);
                db_connection.update(user, chosen);
                return;
        }


    }

    public static void deleteShowEntry(User user){
        List<Show> ShowList = db_connection.getAllMyShows(user);

        System.out.println("Which of the following would you like to delete?");
        System.out.println("(Please enter the number associated with the entry)\n");
        System.out.println("Status key: ");
        System.out.println("1. Not Started, 2. In Progress, 3. Completed");

        int count = 1;
        // Print all shows
        for (Show show : ShowList) {
            System.out.println(count + ". Title: " + show.getTitle() + ", Status: " + show.getStatus() + ", Description: " + show.getDescription());
            count++;
        }

        System.out.println("");

        int choice = sc.nextInt();
        sc.nextLine();

        Show chosen = ShowList.get(choice - 1);

        System.out.println("Would you like to delete '" + chosen.getTitle() + "' from your tracker? ('Y' for yes, 'N' for no)");
        String answer = sc.nextLine().toUpperCase();

        if(answer.equals("Y")){
            db_connection.delete(user, chosen);
            return;
        }
        else{
            System.out.println("No entries were deleted");
            return;
        }
    }

    // repeated code
    public static void confirm_show(User user, List<Show> ShowList, int index) throws ShowException{
        System.out.println("Would you like to add '" + ShowList.get(index).getTitle() + "' to your tracked shows? ('Y' for yes, 'N' for no)");
        String answer = sc.nextLine().toUpperCase();

        if(answer.equals("Y")){
            System.out.println("Please enter the integer matching the status of the show");
            System.out.println("1. Not started");
            System.out.println("2. In progress");
            System.out.println("3. Completed");

            Show newShow = ShowList.get(index);

            int progress = sc.nextInt();
            sc.nextLine();

            switch (progress) {
                case 1:
                    newShow.setStatus(1);
                    db_connection.add(user, newShow);
                    return;
                case 2:
                    newShow.setStatus(2);
                    db_connection.add(user, newShow);
                    return;
                case 3:
                    newShow.setStatus(3);
                    db_connection.add(user, newShow);
                    return;  
                default:
                    System.out.println("Incorrect input, show has been marked as '1. Not started'");
                    newShow.setStatus(1);
                    db_connection.add(user, newShow);
                    return;
            }
        }

        else{
            System.out.println("No new entries were tracked");
            return;
        }
    }
}

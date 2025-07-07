package com.tv_tracker.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*
	DAO INTERFACE
	--------------------
	Do NOT change any code in this file. The interface already has all the methods listed out as they
	should be written. Just keep in mind the arguments and return types for each method and read what the
	purpose of each method should be.
*/

public interface ShowDao {
	
	// needed for later so we make sure that the connection manager gets called
	public void establishConnection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException;
	
	// as well, this method will help with closing the connection
	public void closeConnection() throws SQLException ;
	
	
	// Return all shows in the table
	public List<Show> getAll();

    // Return all shows user is tracking
	public List<Show> getAllMyShows(User user);
	
	// Find a shows by its id and returns an Optional of show (check notes/code from Java Streams to rereview
	// Optional and how to use them)
	public Optional<Show> findById(int id) throws ShowException;
	
	// Updates values for a given shows, assume that the shows object contains the id for the shows to change.
	// Any values that aren't the id could have been changed, so update them all. As well, as long as the shows
	// exists (id is found in the table), you should be returning a true
	public boolean update(User user,Show show);
	
	// Removes a shows by its id. As long as the id given is in the table, return true that you removed
	// that shows
	public boolean delete(User user, Show show);
	
	// Create a new shows in the table. Remember, there will be autoincrement on the table, so no need
	// to give an id. However, keep in mind that you may need to throw this custom exception if shows
	// can't be created
	public void add(User user, Show show);

	// Return a list of shows who all work in the same restaurant, if no showss are in the restaurant given
	// it is okay to return an empty list
	public List<Show> findByTitle(String title) throws ShowException;

    public boolean userDoesExist(String username, String password);

	public User createUser(String username, String password);
	
}










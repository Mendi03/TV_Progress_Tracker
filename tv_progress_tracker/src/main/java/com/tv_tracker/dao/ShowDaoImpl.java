package com.tv_tracker.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tv_tracker.connection.ConnectionManager;

/*
	DAO CLASS
	-------------------
	- Class already implements the DAO interface
	
	- Finish implementing all the methods inherited from the DAO Interface
	
	- Do not make changes to establishConnection() or closeConnection(), those methods have already
	  been completed for you
	
	- Hint: Take a look at the DAO interface file for extra details on how to complete methods
*/

public class ShowDaoImpl implements ShowDao {
	
	private Connection connection = null;

	@Override
	public void establishConnection() throws ClassNotFoundException, SQLException, IOException {
		
		if(connection == null) {
			connection = ConnectionManager.getConnection();
		}
	}
	
	@Override
	public void closeConnection() throws SQLException {
		connection.close();
	}
	
	// -----------------------------------------------
	// PROVIDE IMPLEMENTATIONS FOR ALL METHODS BELOW
	// -----------------------------------------------
	
	@Override
	public List<Show> getAll() {
		// TODO Auto-generated method stub
		try {
            establishConnection();
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM shows");

            ResultSet rs = pStmt.executeQuery();

            List<Show> ShowList = new ArrayList<>();

            while(rs.next()) {
                int Show_id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);

				Show c =  new Show(Show_id, title, description);

                ShowList.add(c);
            }

            return ShowList;

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
 
		return null;
	}

    @Override
    public List<Show> getAllMyShows(){
        try {
            establishConnection();
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM shows");

            ResultSet rs = pStmt.executeQuery();

            List<Show> ShowList = new ArrayList<>();

            while(rs.next()) {
                int Show_id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);

				Show c =  new Show(Show_id, title, description);

                ShowList.add(c);
            }

            return ShowList;

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
 
		return null;
    }

	@Override
	public Optional<Show> findById(int id) {
		// TODO Auto-generated method stub
		try {
            establishConnection();
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM Shows WHERE show_id = ?");

			pStmt.setInt(1, id);

            ResultSet rs = pStmt.executeQuery();

			if(rs.next()){
				int Show_id = rs.getInt(1);
				String title = rs.getString(2);
                String description = rs.getString(3);

				Show c =  new Show(Show_id, title, description);

            	return Optional.of(c);
			}

			else{
				return Optional.empty();
			}

	

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

		return Optional.empty();
	}

	@Override
	public boolean update(Show Show) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(Show Show) throws ShowNotCreatedException {
		// TODO Auto-generated method stub
		

	}

	@Override
	public List<Show> findByTitle(String Show) {
		// TODO Auto-generated method stub
	
		return null;
	}

	public boolean userDoesExist(String username, String password){
        try {
            establishConnection();
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password_hash = ?");

            pStmt.setString(1, username);
            pStmt.setString(2, password);

            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
}


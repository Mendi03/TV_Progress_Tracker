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
    public List<Show> getAllMyShows(User user){
        try {
            establishConnection();
            PreparedStatement pStmt = connection.prepareStatement("SELECT s.show_id, s.title, s.description, ss.status_id AS 'status'\n" + //
								"FROM shows s\n" + //
								"        LEFT JOIN\n" + //
								"    usershowstatus uss ON s.show_id = uss.show_id\n" + //
								"        LEFT JOIN\n" + //
								"    statustypes ss ON uss.status_id = ss.status_id\n" + //
								"WHERE user_id = ?;");

			pStmt.setInt(1, user.getUser_id());

            ResultSet rs = pStmt.executeQuery();

            List<Show> ShowList = new ArrayList<>();

            while(rs.next()) {
                int Show_id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                int status = rs.getInt(4);

				Show c =  new Show(Show_id, title, description, status);

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
	public boolean update(User user, Show show) {
		// TODO Auto-generated method stub
		try {
            establishConnection();
            PreparedStatement pStmt = connection.prepareStatement(
				"UPDATE usershowstatus\n" + 
				"SET status_id = ?\n" +
				"WHERE user_id = ? AND show_id = ?");

			pStmt.setInt(1, show.getStatus());
			pStmt.setInt(2, user.getUser_id() );
			pStmt.setInt(3, show.getShow_id());
			

            pStmt.executeUpdate();
			System.out.println("Entry successfully updated!\n");


        } catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

		return false;
	}

	@Override
	public boolean delete(User user, Show show) {
		// TODO Auto-generated method stub
		try {
			establishConnection();

			PreparedStatement pStmt = connection.prepareStatement("DELETE FROM usershowstatus \n" + //
								"WHERE\n" + //
								"    user_id = ? AND show_id = ?");

			pStmt.setInt(1, user.getUser_id());
			pStmt.setInt(2, show.getShow_id());

			pStmt.executeUpdate();

			System.out.println("Entry deleted.\n");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		return false;
	}

	@Override
	public void add(User user, Show show) {
		// TODO Auto-generated method stub
		try {
            establishConnection();
            PreparedStatement pStmt = connection.prepareStatement(
				"INSERT INTO usershowstatus (user_id, show_id, status_id)\n" +
				 "VALUES (?, ?, ?);");

			pStmt.setInt(1, user.getUser_id());
			pStmt.setInt(2, show.getShow_id());
			pStmt.setInt(3, show.getStatus());

            pStmt.executeUpdate();
			System.out.println("Entry successfully inserted!\n");


        } catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

	@Override
	public List<Show> findByTitle(String show) {
		// TODO Auto-generated method stub
	try {
            establishConnection();
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM shows WHERE title like ?");

			pStmt.setString(1, "%" + show + "%");

            ResultSet rs = pStmt.executeQuery();

			List<Show> showList = new ArrayList<>();

			while(rs.next()){
				int show_id = rs.getInt(1);
				String title = rs.getString(2);
				String description = rs.getString(3);

				Show s =  new Show(show_id, title, description);
				//System.out.println(s);

				showList.add(s);
			}

			return showList;
	

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

	public User createUser(String username, String password){
		try {
            establishConnection();
            PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password_hash = ?");

            pStmt.setString(1, username);
            pStmt.setString(2, password);

            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {

				int user_id = rs.getInt(1);
				String email = rs.getString(3);

				User user = new User(user_id, username, email, password);

                return user;
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
}


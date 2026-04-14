package activities.db;

import java.sql.*;
import java.util.ArrayList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DBInteraction {
    Query q;
    Connection con;

    // Constructor that connects to the Data Base

	public DBInteraction () throws SQLException {
		String url="jdbc:mysql://localhost:3306/sporting_manager";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }
        try {
            System.out.println("Trying to connect...");
            con = DriverManager.getConnection(url, "root", "cangrejo02");
            System.out.println("Connected!");
		}
		catch(SQLException ex) {
            System.err.print("SQLException: ");
            System.err.println(ex.getMessage());
            throw ex;
        }
        q=new Query(con);
	}    

    //method to close the Statement and the Connection objects
    
	public void close()throws Exception{
        q.close();
		con.close();
	}

    // Method to add a new user to the CLIENTS table
	public void addusr(String login, String pwd, String name, String surname, String address, String phone)throws Exception{
		String addusr = "INSERT INTO CLIENTS (LOGIN, PASSWD, NAME, SURNAME, ADDRESS, PHONE) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(addusr)) {
            pstmt.setString(1, login);
            pstmt.setString(2, hashPwdSHA512(pwd));
            pstmt.setString(3, name);
            pstmt.setString(4, surname);
            pstmt.setString(5, address);
            pstmt.setString(6, phone);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al añadir usuario.", e);
        }
	}

    // This method returns 'true' in case there exist a row in the CLIENTS table with the login and password passed as parameters
	// If there is not exist a row with such login and password, then it returns 'false'
	
	public boolean authentication(String login, String pwd)throws Exception{
		String list = "SELECT PASSWD FROM CLIENTS WHERE LOGIN = ?";
		String password=null;
		try (PreparedStatement pstmt = con.prepareStatement(list)) {
			pstmt.setString(1, login);
			ResultSet rs=q.doSelect(list); //rs will contain the row with login passed as parameter
			if (rs.next()){ //Check if the Resultset is empty
				password = rs.getString(2);
			}
			if (password == null){
				return(false);
			}
            // In case the password for this login in the table is the same as the one passed as parameter
            return password.equals(pwd);

		} catch (SQLException e) {
			throw new Exception("Error durante la autenticación.", e);
	}
		
    }
	
	//This method delete a user from the CLIENTS table. This is the user with login passed as parameter
	
	public void delusr(String login) throws Exception{
		String delusr = "DELETE FROM CLIENTS WHERE LOGIN = ?";
		try (PreparedStatement pstmt = con.prepareStatement(delusr)) {
            pstmt.setString(1, login);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al borrar usuario.", e);
        }
	}

    //This method adds a new activity in the ACTIVITIES table with the data passed as parameters

	public void addact( String name, String description, String initial, float price, String pav_name, int total, int occ)throws Exception{
		String addactivity = "INSERT INTO ACTIVITIES (NAME, DESCRIPTION, START_DATE, COST, PAVILLION_NAME, TOTAL_PLACES, OCCUPIED_PLACES) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(addactivity)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, initial);
            pstmt.setFloat(4, price);
            pstmt.setString(5, pav_name);
            pstmt.setInt(6, total);
            pstmt.setInt(7, occ);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al añadir actividad.", e);
        }
	}
	
	//This method deletes a row in the ACTIVITIES table, that is the one that has the id passed as parameter
	public void delact(int id) throws Exception{
		String delact = "DELETE FROM ACTIVITIES WHERE ID = ?";
		try (PreparedStatement pstmt = con.prepareStatement(delact)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error al borrar actividad.", e);
		}
	}
	
	//This method updates an activity in the ACTIVITIES table with the given SQL query
	// Cambios para evitar SQL Injection	
	public void updateActivity(int id, String name, String description, String initial, float cost, String pavname, int total, int occupied) throws Exception{
		String updateQuery = "UPDATE ACTIVITIES SET NAME = ?, DESCRIPTION = ?, START_DATE = ?, COST = ?, PAVILLION_NAME = ?, TOTAL_PLACES = ?, OCCUPIED_PLACES = ? WHERE ID = ?";
		try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setString(3, initial);
			pstmt.setFloat(4, cost);
			pstmt.setString(5, pavname);
			pstmt.setInt(6, total);
			pstmt.setInt(7, occupied);
			pstmt.setInt(8, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error al actualizar actividad.", e);
		}
	}

    //This method adds a new pavillion in the PAVILLIONS table with the data passed as parameters

    public void addpav(String pavname, String pavlocation) throws Exception{
		String addpavillion = "INSERT INTO PAVILLIONS (PAVELLION, LOCATION) VALUES (?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(addpavillion)) {
			pstmt.setString(1, pavname);
			pstmt.setString(2, pavlocation);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error al añadir pabellón.", e);
		}
	}

	//This method deletes a pavillion from the PAVILLIONS table. The deleted pavillion is the one with pavillion name passed as parameter

	public void delpav(String pavname) throws Exception{
		String delpav = "DELETE FROM PAVILLIONS WHERE PAVELLION = ?";
		try (PreparedStatement pstmt = con.prepareStatement(delpav)) {
			pstmt.setString(1, pavname);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error al borrar pabellón.", e);
		}
	}

	//This method requests the execution of a SQL sentence for listing all the clients
	//and it retrieves all the information for each client, storing each client as an element
	//of an array. Each element contains an object of the type Client
		
	public ArrayList<Client> listallusr() throws Exception{
		ArrayList<Client> data = new ArrayList<>();
		String selection = "SELECT * FROM CLIENTS";
		try (PreparedStatement pstmt = con.prepareStatement(selection);
			ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				String login = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				String surname = rs.getString(4);
				String address = rs.getString(5);
				String phone = rs.getString(6);
				data.add(new Client(login, password, name, surname, address, phone));
			}
		} catch (SQLException e) {
			throw new Exception("Error al listar clientes.", e);
		}
		return data;
	}

	//This method requests the execution of a SQL sentence for listing activities depending on some criterion
	//This method is common to all the listing activities operations
	//and it retrieves all the information for each activity, storing each activity as an element
	//of an array. Each element contains an object of the type Activity

    public ArrayList<Activity> listactivities(PreparedStatement pstmt) throws Exception{
		ArrayList<Activity> data = new ArrayList<>();
		try (ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				String initial = rs.getString(4);
				float cost = rs.getFloat(5);
				String pavname = rs.getString(6);
				int total = rs.getInt(7);
				int occupied = rs.getInt(8);
				data.add(new Activity(id, name, description, initial, cost, pavname, total, occupied));
			}
			return data;
		} catch (SQLException e) {
			throw new Exception("Error al listar actividades.", e);
		}
	}

	// This method conforms a SQL sentence for listing all the activities
		
	public ArrayList<Activity> listallact() throws Exception{
		String selection = "SELECT * FROM ACTIVITIES";
		try (PreparedStatement pstmt = con.prepareStatement(selection)) {
			return this.listactivities(pstmt);
		} catch (SQLException e) {
			throw new Exception("Error al listar todas las actividades.", e);
		}
	}

    // This method conforms a SQL sentence for listing the activities that have free places
	
	public ArrayList<Activity> listactfreeplaces() throws Exception{
		String selection="SELECT * FROM ACTIVITIES WHERE ACTIVITIES.TOTAL_PLACES > ACTIVITIES.OCCUPIED_PLACES";
		try (PreparedStatement pstmt = con.prepareStatement(selection)) {
			return this.listactivities(pstmt);
		} catch (SQLException e) {
			throw new Exception("Error al listar actividades con plazas libres.", e);
		}
	}

	// This method conforms a SQL sentence for listing the activities which have less cost that certain amount

	public ArrayList<Activity> listactprice(Float price) throws Exception{
		String selection = "SELECT * FROM ACTIVITIES, PAVILLIONS WHERE ACTIVITIES.COST <= ? AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		try (PreparedStatement pstmt = con.prepareStatement(selection)) {
			pstmt.setFloat(1, price);
			return this.listactivities(pstmt);
		} catch (SQLException e) {
			throw new Exception("Error al listar actividades por precio.", e);
		}
	}

	// This method conforms a SQL sentence for listing the activities that take place in a certain pavillion

	public ArrayList<Activity> listactpav(String namepav) throws Exception{
		String selection = "SELECT * FROM ACTIVITIES, PAVILLIONS WHERE ACTIVITIES.PAVILLION_NAME = ? AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		try (PreparedStatement pstmt = con.prepareStatement(selection)) {
			pstmt.setString(1, namepav);
			return this.listactivities(pstmt);
		} catch (SQLException e) {
			throw new Exception("Error al listar actividades por pavillón.", e);
		}
	}

	// This method conforms a SQL sentence for listing the activities that have a specific name

	public ArrayList<Activity> listactname(String nameact) throws Exception{
		String selection = "SELECT * FROM ACTIVITIES, PAVILLIONS WHERE ACTIVITIES.NAME = ? AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		try (PreparedStatement pstmt = con.prepareStatement(selection)) {
			pstmt.setString(1, nameact);
			return this.listactivities(pstmt);
		} catch (SQLException e) {
			throw new Exception("Error al listar actividades por nombre.", e);
		}
	}

	// This method conforms a SQL sentence for listing the activities in which a specific client is registered

	public ArrayList<Activity> listactusr(String login) throws Exception{
		String selection="SELECT ID, NAME, DESCRIPTION, START_DATE, COST, PAVILLION_NAME, TOTAL_PLACES, OCCUPIED_PLACES FROM REGISTRATIONS, ACTIVITIES, PAVILLIONS WHERE REGISTRATIONS.CLIENT_LOGIN=? AND REGISTRATIONS.ACTIVITY_ID = ACTIVITIES.ID AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		try (PreparedStatement pstmt = con.prepareStatement(selection)) {
			pstmt.setString(1, login);
			return this.listactivities(pstmt);
		} catch (SQLException e) {
			throw new Exception("Error al listar actividades por usuario.", e);
		}
	}

	//This method requests the execution of a SQL sentence for listing all the pavillions
	//and it retrieves all the information for each pavillion, storing each pavillion as an element
	//of an array. Each element contains an object of the type pavillion

	public ArrayList<Pavillion> listallpav() throws Exception{
		ArrayList<Pavillion> data = new ArrayList<>();
		String selection = "SELECT * FROM PAVILLIONS";
		try (PreparedStatement pstmt = con.prepareStatement(selection);
			ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				String name = rs.getString(1);
				String location = rs.getString(2);
				data.add(new Pavillion(name, location));
			}
		} catch (SQLException e) {
			throw new Exception("Error al listar pabellones.", e);
		}
		return data;
	}

	//This method registers a client for a specific activity

    public void regactivity(String login, String id) throws Exception{
		String regactivity = "INSERT INTO REGISTRATIONS (CLIENT_LOGIN, ACTIVITY_ID) VALUES (?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(regactivity)) {
			pstmt.setString(1, login);
			pstmt.setInt(2, Integer.parseInt(id));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error al registrar actividad.", e);
		}

	}

	//This method unregisters a client from a specific activity

	public void unregactivity(String login, String id) throws Exception{
		String unregactivity="DELETE FROM REGISTRATIONS WHERE REGISTRATIONS.CLIENT_LOGIN='"+login+"'AND REGISTRATIONS.ACTIVITY_ID="+id;
		q.doUpdate(unregactivity);
	}

	// AÑADIDO PARTE 4
	public ArrayList<Activity> listactbynameordesc(String text) throws Exception {
		String selection = "SELECT * FROM ACTIVITIES, PAVILLIONS " +
							"WHERE (ACTIVITIES.NAME LIKE ? OR ACTIVITIES.DESCRIPTION LIKE ?) " +
							"AND ACTIVITIES.PAVILLION_NAME = PAVILLIONS.PAVILLION";
		ArrayList<Activity> data = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(selection)) {
			String likeText = "%" + text + "%";
			pstmt.setString(1, likeText);
			pstmt.setString(2, likeText);
			data = this.listactivities(pstmt);
			return data;
		} catch (SQLException e) {
			throw new Exception("Error al listar actividades por nombre o descripción.", e);
		}
	}

	public static String hashPwdSHA512(String pwd) throws NoSuchAlgorithmException {
		String pepper = "base de datos";
		String pwdPepper = pwd + pepper;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			byte[] hashBytes = digest.digest(pwdPepper.getBytes());
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}


}

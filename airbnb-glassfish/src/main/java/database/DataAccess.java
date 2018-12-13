package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Apartment;
import model.ApartmentPK;
import model.Message;
import model.Reservation;
import model.User;


/**
 * DataAccess allows system to create, read, update and delete data from database.
 * Get methods return objects or arrays if succeeded and null or empty array if not
 * Create, Update and Remove methods return true if succeeded and false if not.
 * 
 * @author Mateusz Kobierski
 *
 */

public class DataAccess 
{	
	private static EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("airbnb");
	private static Connection connection = null;
	
	// *********************  USERS  *********************

	
	public static List<User> getAllUsers() {
		return HttpClientUser.getAllUsers();
	}
	
	public static User getUserByEmail(String email) {
		return HttpClientUser.getUserByEmail(email);
	}
	
	public static boolean createUser(User user)  {
		return HttpClientUser.createOrUpdateUser(user) != null;
	}
	
	public static boolean updateUser(User user) {
		return HttpClientUser.createOrUpdateUser(user) != null;
	}
	
	public static boolean removeUser(User user)  {
		return HttpClientUser.deleteUser(user);
	}
	
	
	// *********************  MESSAGES  *********************
	
	public static List<Message> getNewestMessages(String email) {
		return  HttpClientMessage.getNewestMessagesByEmail(email);

	}
	
	
	public static boolean createMessage(Message message) {
		Message messageReturned = HttpClientMessage.createMessage(message);
		System.out.println("Returne message from API" +messageReturned);
		if(messageReturned != null)
			return true;
		
		
		return false;
	}
	
	public static boolean removeMessage(Message message) {
		EntityManager manager = managerFactory.createEntityManager();
		Message managed = null;
		try {
			manager.getTransaction().begin();
			managed = manager.find(Message.class, message.getId());
			manager.remove(managed);
			manager.getTransaction().commit();
		} catch(Exception ex) {
			try {
				if (manager.getTransaction().isActive()) {
					manager.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				e.printStackTrace();
			}
			manager.close();
			return false;
		}
		
		manager.close();
		return true;
	}
	
	
	
	
	
	// *********************  APARTMENTS  *********************

	
	
	public static List<Apartment> getAllApartments() {
		return HttpClientApartment.getAllApartments();
	}
	
	public static Apartment getApartmentById(ApartmentPK apartmentKey) {
		return HttpClientApartment.getApartmentById(apartmentKey);
	}
	
	public static List<Apartment> getApartmentByHost(String email) {
		return HttpClientApartment.getApartmentByHost(email);
	}
	
	public static List<Apartment> getApartmentByName(String name) {
		return HttpClientApartment.getApartmentByName(name);
	}
	
	public static List<Apartment> getApartmentByCountry(String country) {
		return HttpClientApartment.getApartmentByCounry(country);
	}
	
	public static List<Apartment> getApartmentByCity(String city) {
		return HttpClientApartment.getApartmentByCity(city);
	}
	
	public static boolean createApartment(Apartment apartment) {
		return HttpClientApartment.createApartment(apartment);
	}
	
	public static boolean updateApartment(Apartment apartment) {
		return HttpClientApartment.updateApartment(apartment);
	}
	
	public static boolean removeApartment(Apartment apartment) {
		return HttpClientApartment.removeApartment(apartment);
	}
	
	
	public static List<Reservation> getAllReservations() {
		List<Reservation> results;
		EntityManager manager = managerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("Reservation.findAll", Reservation.class);
			results = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
			results = new ArrayList();
		} finally {
			manager.close();
		}
		return results;
	}
	
	public static boolean createReservation(Reservation reservation) {
		PreparedStatement stmt = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/airbnbdb?user=userLQE&password=2dAlhk2RqPhVlFOK" + 
							"&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			
			stmt = connection.prepareStatement("INSERT INTO Reservation VALUES(?,?,?,?,?,?,?)");
			
			stmt.setString(1, reservation.getUser().getEmail());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			stmt.setString(2, dateFormat.format(reservation.getDate()));
			stmt.setString(3, reservation.getApartment().getHost().getEmail());
			stmt.setString(4, reservation.getApartment().getBuildingNumber());
			stmt.setString(5, reservation.getApartment().getStreet());
			stmt.setString(6, reservation.getApartment().getFlatNumber());
			stmt.setString(7, reservation.getApartment().getCity());
			
		    stmt.executeUpdate();
		     
		} catch(Exception ex) {
			
			try {
				
				if (connection != null) {
					connection.close();
				}
				
				if (stmt != null) {
					stmt.close();
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			ex.printStackTrace();
			return false;
		}
		
		try {
			
			if (connection != null) {
				connection.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static boolean removeReservation(Reservation reservation)  {
		EntityManager manager = managerFactory.createEntityManager();
		Reservation managed = null;
		try {
			manager.getTransaction().begin();
			managed = manager.find(Reservation.class, reservation.getId());
			manager.remove(managed);
			manager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				if (manager.getTransaction().isActive()) {
					manager.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				e.printStackTrace();
			}
			manager.close();
			return false;
		}
		
		manager.close();
		return true;
	}

}
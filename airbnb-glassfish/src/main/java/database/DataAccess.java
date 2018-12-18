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
		if(messageReturned != null)
			return true;
		
		return false;
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
	
// ********************** RESERVATIONS *******************************


	public static List<Reservation> getAllReservations() {
		return HttpClientReservation.getAllReservations();
	}
	
	public static boolean createReservation(Reservation reservation)  {
		return HttpClientReservation.createReservation(reservation) != null;
	}
	
	public static boolean removeReservation(Reservation reservation)  {
		return HttpClientReservation.removeReservation(reservation);
	}
	
	public static boolean updateReservation(Reservation reservation)  {
		return HttpClientReservation.createOrUpdateReservation(reservation) != null;
	}
}
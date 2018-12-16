package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import database.DataAccess;
import database.HttpClientUser;
import model.Apartment;
import model.ApartmentPK;
import model.ApartmentType;
import model.Reservation;
import model.User;

/**
 * ApartmentLogic wraps DataAccess methods on Apartment objects.
 * Moreover this class is used for all logic operations on Apartment objects.
 * It allows user to operate on higher level of abstraction and work with Apartments seamlessly.
 * 
 * @author Mateusz Kobierski, Piotr Szylar
 *
 */

public class ApartmentLogic {

	public static List<Apartment> search(String fromPlace, String price, 
			ApartmentType typeOfAccom, Integer adults, Integer children, Date dateStart, Date dateEnd){
		
		List<Apartment> availableApartmentsList = DataAccess.getAllApartments();
		List<Apartment> resultApartmentsList = new ArrayList<Apartment>();
		
		for(Apartment apartment : availableApartmentsList) {
			
			if(checkApartmentName(apartment, fromPlace)==true 
					&& checkApartmentPrice(apartment, price)==true 
					&& checkApartmentType(apartment, typeOfAccom)==true 
					&& checkApartmentBeds(apartment, adults, children)==true 
					&& checkApartmentDates(apartment, dateStart, dateEnd)==true) {
					
					resultApartmentsList.add(apartment);
			}	
		}
		
		return resultApartmentsList;
	}

	public static String correctApartmentTypeDisplay (String apartmentType) {
		
		return ApartmentType.fromString(apartmentType).toString();
	}
	
	public static double countTotalPrice (String dateStart, String dateEnd, Apartment apartment) {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		double totalPrice = 0;
		
		try {
			totalPrice = DateUtils.getDatesBetween(myFormat.parse(dateStart), myFormat.parse(dateEnd)).size() * apartment.getPrice();
		} 
		catch (ParseException e) {
		    e.printStackTrace();
		}
		return totalPrice;
	}
	
	private static boolean checkApartmentName(Apartment apartment, String obtainedName) {
		if (apartment == null || obtainedName == null) {
			return false;
		}
		
		if(obtainedName.contains(apartment.getCity().toLowerCase())==true ){
			return true;
		} else if(obtainedName.contains(apartment.getCountry().toLowerCase())==true) {
			return true;
		} else if(obtainedName.equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean checkApartmentPrice(Apartment apartment, String price) {
		
		double pricePerDay = apartment.getPrice();
		
		if(price == null){
			return true;
		} else if(price.equals("P1")==true && pricePerDay < 35) {
			return true;
		} else if(price.equals("P2")==true && pricePerDay >= 35 && pricePerDay < 70) {
			return true;
		} else if(price.equals("P3")==true && pricePerDay >= 70 && pricePerDay < 131) {
			return true;
		} else if(price.equals("P4")==true && pricePerDay >= 131) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean checkApartmentType(Apartment apartment, ApartmentType typeOfAccom) {
	
		if(typeOfAccom == null) {
			return true;
		} else if(apartment.getType().equals(typeOfAccom)==true){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean checkApartmentBeds(Apartment apartment, Integer numberOfAdults, Integer numberOfChildren) {
		Integer bedsForAdults = new Integer(apartment.getBedsAdult());
		Integer bedsForChildren = new Integer(apartment.getBedsChild());
		
		if(numberOfAdults == null && numberOfChildren == null){
			return true;
		} else if((numberOfAdults != null && numberOfAdults <= bedsForAdults) && numberOfChildren == null) {
			return true;
		} else if(numberOfAdults == null && (numberOfChildren != null && numberOfChildren <= bedsForChildren)) {
			return true;
		} else if((numberOfAdults != null && numberOfAdults <= bedsForAdults) && (numberOfChildren != null && numberOfChildren <= bedsForChildren)){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean checkApartmentDates(Apartment apartment, Date dateStart, Date dateEnd) {
		List<Reservation> reservations = apartment.getReservations();
		List<Date> bookedDays = new ArrayList<Date>();
		
		for(Reservation reservation : reservations) {
			bookedDays.add(reservation.getDate());
		}
		
		if(dateStart != null && dateEnd != null) {
			if(dateStart.before(dateEnd)==true) {
				
				for(Date date : bookedDays) {
					if(DateUtils.isDateBetweenTwoDates(dateStart, dateEnd, date)==true) {
						return false;
					}
				}
				return true;
			} else {
				return true;
			}
		} else if (dateStart != null && dateEnd == null) {
			
			for(Date date : bookedDays) {
				if((date.compareTo(dateStart) >= 0)==true) {
					return false;
				}
			}
			return true;
		} else if(dateStart == null && dateEnd != null) {
			Date currentDate = new Date(new Date().getTime());
			
			for(Date date : bookedDays) {
				if(DateUtils.isDateBetweenTwoDates(currentDate, dateEnd, date)==true) {
					return false;
				}
			}
			return true;
		} else {
			return true;
		}	
		
	}
  
	private static boolean isDateBetweenTwoDates(Date dateStart, Date dateEnd, Date examinedDate) {
		
		return dateStart.compareTo(examinedDate) * dateEnd.compareTo(examinedDate) <= 0;
	}

	public static boolean bookApartment(User user, Apartment apartment, Date start, Date end) {
		if (user == null || apartment == null || start == null || end == null || end.before(start)) {
			return false;
		}
		
		Map<Date,Reservation> bookings = new HashMap<>();
		for (Reservation r : apartment.getReservations()) bookings.put(r.getDate(), r);
		
		List<Reservation> reservations = new ArrayList<>();
		List<Date> dates = DateUtils.getDatesBetween(start, end);
		
		for (Date date : dates) {
			Reservation reservation = new Reservation(user, apartment, date,"pending");
			reservations.add(reservation);
			
			if (bookings.containsKey(date)) {
				return false;
			} else {
				bookings.put(date, reservation);
			}
		}
		
		List<Reservation> userReservations = user.getReservations();
		userReservations.addAll(reservations);
		
		user.setReservations(new ArrayList(new HashSet(userReservations)));
		apartment.setReservations(new ArrayList(bookings.values()));
		
		boolean updatedAp = DataAccess.updateApartment(apartment);
		boolean updatedUs = DataAccess.updateUser(user);
		boolean createdRes = true;
		for (Reservation r : reservations) {
			
			if (!DataAccess.createReservation(r)) {
				createdRes = false;
			}
		}
		
		return updatedAp && updatedUs && createdRes;
	}
	
	public static boolean addApartment(Apartment apartment) {
		if (apartment == null) {
			return false;
		}
		
		if (!UserLogic.isUserRegistered(apartment.getHost())) {
			UserLogic.registerUser(apartment.getHost());
		}
		
		return DataAccess.createApartment(apartment);
	}
  
	public static boolean modifyApartment(Apartment apartment) {
		if (apartment == null || !isApartmentRegistered(apartment)) {
			return false;
		}
		
		return DataAccess.updateApartment(apartment);
	}
	
	public static boolean removeApartment(ApartmentPK apartmentPk) {
		if (apartmentPk == null) {
			return false;
		}
		Apartment toDelete = DataAccess.getApartmentById(apartmentPk);
		if (toDelete == null) {
			return false;
		}
		return removeApartment(toDelete);
	}
	
	public static boolean removeApartment(Apartment apartment) {
		if (apartment == null) {
			return false;
		}

		
		User host = HttpClientUser.getUserByEmail(apartment.getHost().getEmail());
		host.removeApartment(apartment);
		System.out.println("3");
		DataAccess.updateUser(host);
		System.out.println("4");

				
		return DataAccess.removeApartment(apartment);
	}
	
	public static boolean isApartmentRegistered(Apartment apartment) {
		return apartment != null && DataAccess.getApartmentById(apartment.getId()) != null;
	}
}

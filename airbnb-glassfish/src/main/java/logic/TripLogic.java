package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import database.DataAccess;
import model.Apartment;
import model.Reservation;
import model.Trip;
import model.User;

/**
 * TripLogic handles Trip objects which are wrappers of Reservations.
 * This class allows user to get user trips from list of Reservations, remove Reservation and book Trip.
 * It allows user to work with high level of abstraction objects (Trip) instead of database types (Reservation).
 * 
 * @author Mateusz Kobierski
 *
 */
public class TripLogic {
	
	public static boolean removeReservation(Reservation reservation) {
		if (reservation == null) {
			return false;
		}
		
		User user = reservation.getUser();
		user.removeReservation(reservation);
		boolean updatedUs = DataAccess.updateUser(user);
		
		Apartment apartment = reservation.getApartment();
		apartment.removeReservation(reservation);
		
		
		return updatedUs && DataAccess.updateApartment(apartment) && DataAccess.removeReservation(reservation);
	}
	
	public static List<Trip> getUserTrips(User user) {
		List<Reservation> userReservations = user.getReservations();
		List<Trip> userTrips = new ArrayList<>();
		
		Collections.sort(userReservations);
		
		Reservation last = null;
		Trip trip = null;
		
		for (Reservation r : userReservations) {
			if (last == null || DateUtils.getDatesBetween(r.getDate(), last.getDate()).size() > 1) {
				if (trip != null) {
					userTrips.add(trip);
				}
				
				trip = new Trip();
				trip.setApartment(r.getApartment());
				trip.setBeginning(r.getDate());
				trip.setEnd(r.getDate());
				trip.addReservation(r);
				last = r;
				
			} else {
				trip.addReservation(r);
				trip.setEnd(r.getDate());
				last = r;
			}
		}
		
		if (trip != null) {
			userTrips.add(trip);
		}
		
		return userTrips;	
	}
	
	public static boolean bookTrip(Trip trip) {
		if (trip == null || trip.getReservations().isEmpty()) {
			return false;
		}
		User user = trip.getReservations().get(0).getUser();
		return ApartmentLogic.bookApartment(user, trip.getApartment(), trip.getBeginning(), trip.getEnd());
	}

}

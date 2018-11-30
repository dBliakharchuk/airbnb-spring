package airbnb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Trip is a wrapper of Reservation class. It operates on higher level of abstraction making it more user-friendly.
 * Trip consist of two dates marking a beginning and an end of a Trip and list of reservations which correspond to database entries.
 * 
 * @author Mateusz
 *
 */

public class Trip {
	Date beginning;
	Date end;
	Apartment apartment;
	List<Reservation> reservations = new ArrayList();
	
	public Trip() {
		
	}
	
	public Trip(Date beginning, Date end, List<Reservation> reservations) {
		super();
		this.beginning = beginning;
		this.end = end;
		this.reservations = reservations;
	}

	public Date getBeginning() {
		return beginning;
	}

	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}
}

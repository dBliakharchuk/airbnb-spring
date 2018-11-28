package airbnb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;


@JsonIgnoreProperties({"apartment", "user"})
public class Reservation implements Serializable, Comparable<Reservation> {
	private static final long serialVersionUID = 1L;

	private ReservationPK id;
	private Apartment apartment;
	private User user;

	public Reservation() {
	
	}

	public Reservation(ReservationPK id, Apartment apartment, User user) {
		super();
		this.id = id;
		this.apartment = apartment;
		this.user = user;
	}
	
	public Reservation(User user, Apartment apartment, Date date) {
		this.id = new ReservationPK(user, apartment, date);
		this.apartment = apartment;
		this.user = user;
	}

	public ReservationPK getId() {
		return this.id;
	}

	public void setId(ReservationPK id) {
		this.id = id;
	}

	public Date getDate() {
		return id.getDate();
	}

	public void setDate(Date date) {
		id.getDate();
	}

	public Apartment getApartment() {
		return this.apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id.toString() + ", apartment=" + apartment + ", user=" + user + "]";
	}

	@Override
	public int compareTo(Reservation arg0) {
		if (this.getDate().after(arg0.getDate())) {
			return 1;
		} else if (this.getDate().before(arg0.getDate())) {
			return -1;
		} else {
			return 0;
		}
	}
	
	

}
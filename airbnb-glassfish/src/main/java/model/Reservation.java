package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * Reservation class is a Java representative of Reservation table in database.
 * One Reservation can only be per one day and apartment.
 * 
 * @author Mateusz
 */
@Entity
@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r")
public class Reservation implements Serializable, Comparable<Reservation> {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReservationPK id;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="apartmentBuildingNumber", referencedColumnName="buildingNumber"),
		@JoinColumn(name="apartmentCity", referencedColumnName="city"),
		@JoinColumn(name="apartmentFlatNumber", referencedColumnName="flatNumber"),
		@JoinColumn(name="apartmentHost", referencedColumnName="host"),
		@JoinColumn(name="apartmentStreet", referencedColumnName="street")
		})
	private Apartment apartment;

	@ManyToOne
	@JoinColumn(name="userEmail")
	private User user;
	
	private String reservationStatus;

	public Reservation() {
	
	}

	public Reservation(ReservationPK id, Apartment apartment, User user, String reservationStatus) {
		super();
		this.id = id;
		this.apartment = apartment;
		this.user = user;
		this.reservationStatus = reservationStatus;
	}
	
	public Reservation(User user, Apartment apartment, Date date, String reservationStatus) {
		this.id = new ReservationPK(user, apartment, date);
		this.apartment = apartment;
		this.user = user;
		this.reservationStatus = reservationStatus;
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
	
	public String getReservationStatus() {
		return this.reservationStatus;
	}

	public void setReservation(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id.toString() + ", apartment=" + apartment + ", user=" + user +
				", status=" + reservationStatus + "]";
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
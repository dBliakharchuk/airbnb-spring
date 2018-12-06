package airbnb.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties({"password", "apartments", "messagesSent", "messagesReceived", "reservations"}) // what to ignore
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String adminLogin = "admin";
	private static final String adminPassword = "admin123";

	@Id
	private String email;
	private String name;
	private String password;
	private String phone;
	private String surname;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="host")
	private List<Apartment> apartments;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="sender")
	private List<Message> messagesSent;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="receiver")
	private List<Message> messagesReceived;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private List<Reservation> reservations;

	public User() {
		
	}

	public User(String email, String name, String surname, String password, String phone, List<Apartment> apartments,
			List<Message> messagesSent, List<Message> messagesReceived, List<Reservation> reservations) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.surname = surname;
		this.apartments = apartments;
		this.messagesSent = messagesSent;
		this.messagesReceived = messagesReceived;
		this.reservations = reservations;
	}
	
	public User(String email, String name, String surname, String password, String phone) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.surname = surname;
		this.apartments = new ArrayList<Apartment>();
		this.messagesSent = new ArrayList<Message>();
		this.messagesReceived = new ArrayList<Message>();
		this.reservations = new ArrayList<Reservation>();
	}
	
	public User(String email, String name, String phone, String surname)
	{
		super();
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.surname = surname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Apartment> getApartments() {
		return this.apartments;
	}

	public void setApartments(List<Apartment> apartments) {
		this.apartments = apartments;
	}

	public Apartment addApartment(Apartment apartment) {
		getApartments().add(apartment);
		apartment.setHost(this);

		return apartment;
	}

	public Apartment removeApartment(Apartment apartment) {
		getApartments().remove(apartment);
		apartment.setHost(null);

		return apartment;
	}

	public List<Message> getMessagesSent() {
		return this.messagesSent;
	}

	public void setMessagesSent(List<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

	public Message addMessagesSent(Message messagesSent) {
		getMessagesSent().add(messagesSent);
		messagesSent.setSender(this);

		return messagesSent;
	}

	public Message removeMessagesSent(Message messagesSent) {
		getMessagesSent().remove(messagesSent);
		messagesSent.setSender(null);

		return messagesSent;
	}

	public List<Message> getMessagesReceived() {
		return this.messagesReceived;
	}

	public void setMessagesReceived(List<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	public Message addMessagesReceived(Message messagesReceived) {
		getMessagesReceived().add(messagesReceived);
		messagesReceived.setReceiver(this);

		return messagesReceived;
	}

	public Message removeMessagesReceived(Message messagesReceived) {
		getMessagesReceived().remove(messagesReceived);
		messagesReceived.setReceiver(null);

		return messagesReceived;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setUser(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setUser(null);

		return reservation;
	}

	public static String getAdminlogin() {
		return adminLogin;
	}

	public static String getAdminpassword() {
		return adminPassword;
	}

	@Override
	public String toString() {
		return "User{" +
				"email='" + email + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				", surname='" + surname + '\'' +
				'}';
	}
}
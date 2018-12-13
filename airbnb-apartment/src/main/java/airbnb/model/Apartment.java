package airbnb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
@JsonIgnoreProperties({"host"})
public class Apartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApartmentPK id;
	
	@Column(name="beds_adult")
	private int bedsAdult;
	@Column(name="beds_child")
	private int bedsChild;
	private String country;
	private String description;
	private String name;
	@Lob
	private byte[] picture = new byte[1];
	private double price;
	@Enumerated(EnumType.STRING)
	private ApartmentType type;

	@ManyToOne
	@JoinColumn(name="host", insertable=false, updatable=false)
	private User host;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="apartment")
	private List<Reservation> reservations;

	public Apartment() {
	}
	
	

	public Apartment(ApartmentPK id, int bedsAdult, int bedsChild, String country, String description, String name,
			double price, ApartmentType type)
	{
		super();
		this.id = id;
		this.bedsAdult = bedsAdult;
		this.bedsChild = bedsChild;
		this.country = country;
		this.description = description;
		this.name = name;
		this.price = price;
		this.type = type;
	}



	public Apartment(User host, String buildingNumber, String street, String flatNumber, 
			String city, int bedsAdult, int bedsChild, String country, String description, String name,
			byte[] picture, double price, ApartmentType type, List<Reservation> reservations) {
		
		this.id = new ApartmentPK(host.getEmail(), buildingNumber, street, flatNumber, city);
		this.bedsAdult = bedsAdult;
		this.bedsChild = bedsChild;
		this.country = country;
		this.description = description;
		this.name = name;
		this.picture = picture;
		this.price = price;
		this.type = type;
		this.host = host;
		this.reservations = reservations;
	}

	public Apartment(User host, String buildingNumber, String street, String flatNumber, 
			String city, int bedsAdult, int bedsChild, String country, String description, String name,
			byte[] picture, double price, ApartmentType type) {
		
		this.id = new ApartmentPK(host.getEmail(), buildingNumber, street, flatNumber, city);
		this.bedsAdult = bedsAdult;
		this.bedsChild = bedsChild;
		this.country = country;
		this.description = description;
		this.name = name;
		this.picture = picture;
		this.price = price;
		this.type = type;
		this.host = host;
		this.reservations = new ArrayList<Reservation>();
	}

	public ApartmentPK getId() {
		return this.id;
	}

	public void setId(ApartmentPK id) {
		this.id = id;
	}

	public int getBedsAdult() {
		return this.bedsAdult;
	}

	public void setBedsAdult(int bedsAdult) {
		this.bedsAdult = bedsAdult;
	}

	public int getBedsChild() {
		return this.bedsChild;
	}

	public void setBedsChild(int bedsChild) {
		this.bedsChild = bedsChild;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ApartmentType getType() {
		return this.type;
	}

	public void setType(ApartmentType type) {
		this.type = type;
	}
	
	public void setType(String type) {
		this.type = ApartmentType.fromString(type);
	}

	public User getHost() {
		return this.host;
	}

	public void setHost(User host) {
		this.host = host;
	}
	
	public String getBuildingNumber() {
		return id.getBuildingNumber();
	}
	
	public void setBuildingNumber(String buildingNumber) {
		id.setBuildingNumber(buildingNumber);
	}
	
	public String getStreet() {
		return id.getStreet();
	}
	
	public void setStreet(String street) {
		id.setStreet(street);
	}
	
	public String getFlatNumber() {
		return id.getFlatNumber();
	}
	
	public void setFlatNumber(String flatNumber) {
		id.setFlatNumber(flatNumber);
	}
	
	public String getCity() {
		return id.getCity();
	}
	
	public void setCity(String city) {
		id.setCity(city);
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setApartment(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setApartment(null);

		return reservation;
	}

	@Override
	public String toString() {
		return "Apartment{" +
				"id=" + id +
				", bedsAdult=" + bedsAdult +
				", bedsChild=" + bedsChild +
				", country='" + country + '\'' +
				", description='" + description + '\'' +
				", name='" + name + '\'' +
				", picture=" + Arrays.toString(picture) +
				", price=" + price +
				", type=" + type +
				'}';
	}
}
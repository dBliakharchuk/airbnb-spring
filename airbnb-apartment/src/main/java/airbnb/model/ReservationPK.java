package airbnb.model;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


@Embeddable
public class ReservationPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userEmail;
	private String apartmentHost;
	private String apartmentBuildingNumber;
	private String apartmentStreet;
	private String apartmentFlatNumber;
	private String apartmentCity;
	@Temporal(TemporalType.DATE)
	private Date date;

	public ReservationPK() {
	}
	
	public ReservationPK(String userEmail, String apartmentHost, String apartmentBuildingNumber, String apartmentStreet,
			String apartmentFlatNumber, String apartmentCity, Date date) {
		super();
		this.userEmail = userEmail;
		this.apartmentHost = apartmentHost;
		this.apartmentBuildingNumber = apartmentBuildingNumber;
		this.apartmentStreet = apartmentStreet;
		this.apartmentFlatNumber = apartmentFlatNumber;
		this.apartmentCity = apartmentCity;
		this.date = date;
	}
	
	public ReservationPK(User user, Apartment apartment, Date date) {
		this.userEmail = user.getEmail();
		this.apartmentHost = apartment.getHost().getEmail();
		this.apartmentBuildingNumber = apartment.getBuildingNumber();
		this.apartmentStreet = apartment.getStreet();
		this.apartmentFlatNumber = apartment.getFlatNumber();
		this.apartmentCity = apartment.getCity();
		this.date = date;
	}

	public String getUserEmail() {
		return this.userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getApartmentHost() {
		return this.apartmentHost;
	}
	
	public void setApartmentHost(String apartmentHost) {
		this.apartmentHost = apartmentHost;
	}
	
	public String getApartmentBuildingNumber() {
		return this.apartmentBuildingNumber;
	}
	
	public void setApartmentBuildingNumber(String apartmentBuildingNumber) {
		this.apartmentBuildingNumber = apartmentBuildingNumber;
	}
	
	public String getApartmentStreet() {
		return this.apartmentStreet;
	}
	
	public void setApartmentStreet(String apartmentStreet) {
		this.apartmentStreet = apartmentStreet;
	}
	
	public String getApartmentFlatNumber() {
		return this.apartmentFlatNumber;
	}
	
	public void setApartmentFlatNumber(String apartmentFlatNumber) {
		this.apartmentFlatNumber = apartmentFlatNumber;
	}
	
	public String getApartmentCity() {
		return this.apartmentCity;
	}
	
	public void setApartmentCity(String apartmentCity) {
		this.apartmentCity = apartmentCity;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReservationPK)) {
			return false;
		}
		ReservationPK castOther = (ReservationPK)other;
		return 
			this.userEmail.equals(castOther.userEmail)
			&& this.apartmentHost.equals(castOther.apartmentHost)
			&& this.apartmentBuildingNumber.equals(castOther.apartmentBuildingNumber)
			&& this.apartmentStreet.equals(castOther.apartmentStreet)
			&& this.apartmentFlatNumber.equals(castOther.apartmentFlatNumber)
			&& this.apartmentCity.equals(castOther.apartmentCity);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userEmail.hashCode();
		hash = hash * prime + this.apartmentHost.hashCode();
		hash = hash * prime + this.apartmentBuildingNumber.hashCode();
		hash = hash * prime + this.apartmentStreet.hashCode();
		hash = hash * prime + this.apartmentFlatNumber.hashCode();
		hash = hash * prime + this.apartmentCity.hashCode();
		
		return hash;
	}

	@Override
	public String toString() {
		return "ReservationPK [userEmail=" + userEmail + ", apartmentHost=" + apartmentHost
				+ ", apartmentBuildingNumber=" + apartmentBuildingNumber + ", apartmentStreet=" + apartmentStreet
				+ ", apartmentFlatNumber=" + apartmentFlatNumber + ", apartmentCity=" + apartmentCity + ", date=" + date
				+ "]";
	}
	
	
}
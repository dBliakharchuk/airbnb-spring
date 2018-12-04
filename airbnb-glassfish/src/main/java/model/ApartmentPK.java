package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the apartment database table.
 * 
 */
@Embeddable
public class ApartmentPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String host;
	private String buildingNumber;
	private String street;
	private String flatNumber;
	private String city;

	public ApartmentPK() {
	}
	
	public ApartmentPK(String host, String buildingNumber, String street, String flatNumber, String city) {
		super();
		this.host = host;
		this.buildingNumber = buildingNumber;
		this.street = street;
		this.flatNumber = flatNumber;
		this.city = city;
	}

	public String getHost() {
		return this.host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getBuildingNumber() {
		return this.buildingNumber;
	}
	
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	
	public String getStreet() {
		return this.street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getFlatNumber() {
		return this.flatNumber;
	}
	
	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApartmentPK)) {
			return false;
		}
		ApartmentPK castOther = (ApartmentPK)other;
		return 
			this.host.equals(castOther.host)
			&& this.buildingNumber.equals(castOther.buildingNumber)
			&& this.street.equals(castOther.street)
			&& this.flatNumber.equals(castOther.flatNumber)
			&& this.city.equals(castOther.city);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.host.hashCode();
		hash = hash * prime + this.buildingNumber.hashCode();
		hash = hash * prime + this.street.hashCode();
		hash = hash * prime + this.flatNumber.hashCode();
		hash = hash * prime + this.city.hashCode();
		
		return hash;
	}
	
	

	@Override
	public String toString() {
		return "ApartmentPK [host=" + host + ", buildingNumber=" + buildingNumber + ", street=" + street
				+ ", flatNumber=" + flatNumber + ", city=" + city + "]";
	}
	
	
	public String toUrl() {
		
		String newStreet = street.replace(" ", "-");
		return host + "/" + buildingNumber + "/" + newStreet
				+ "/" + flatNumber + "/" + city;
		
		
	}
	
	static public ApartmentPK fromUlr(String url)
	{
		String[] parts = url.split("/");
		String host = parts[0];
		String buildingNumber = parts[1];
		String street= parts[2];
		String flatNumber= parts[3];
		String city = parts[4];
		
		
		String newStreet = street.replace("-", " ");
		
		return new ApartmentPK(host, buildingNumber,  newStreet,  flatNumber,  city);
		
	}
	
	
}
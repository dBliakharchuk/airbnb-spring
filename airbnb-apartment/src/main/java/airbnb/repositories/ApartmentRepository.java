package airbnb.repositories;

import airbnb.model.Apartment;
import airbnb.model.ApartmentPK;
import airbnb.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.persistence.NamedQuery;

public interface ApartmentRepository extends CrudRepository<Apartment, ApartmentPK> {
    
	@Query("SELECT a FROM Apartment a where a.id.host = :host")
	List<Apartment> findByHost(@Param("host") String host);
	
	@Query("SELECT a FROM Apartment a where a.name = :name")
	List<Apartment> findByName(@Param("name") String name);
	
	@Query("SELECT a FROM Apartment a where a.country = :country")
	List<Apartment> findByCountry(@Param("country") String country);
	
	@Query("SELECT a FROM Apartment a where a.id.city = :city")
	List<Apartment> findByCity(@Param("city") String city);
	
	@Query("SELECT a FROM Apartment a where a.price <= :price")
	List<Apartment> findByPrice(@Param("price") double price);
}

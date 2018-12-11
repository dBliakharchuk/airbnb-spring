package airbnb.api;

import airbnb.database.DataAccess;
import airbnb.model.Apartment;
import airbnb.model.ApartmentPK;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/apartment")
public class ApartmentApi{
	
	@Autowired
	DataAccess data;
	
	
	/*******************get mapping*******************/
	@GetMapping
	public Iterable<Apartment> getAllApartment(){
		return data.getAllApartment();
	}
	
	/*maybe i should put some additional parameter for exapmle */
	@PostMapping(path = "/getById")
	public Optional<Apartment> getApartmentById(@RequestBody ApartmentPK apartmentID) {
		return data.getApartmentById(apartmentID);
	}
	
	@GetMapping(params = "name")
	public List<Apartment> getApartmentByName(@RequestParam("name") String name) {
		return data.getApartmentByName(name);
	}
	
	@GetMapping(params = "city")
	public List<Apartment> getApartmentByCity(@RequestParam("city") String city) {
		return data.getApartmentByCity(city);
	}
	
	@GetMapping(params = "host")
	public List<Apartment> getApartmentByHost(@RequestParam("host") String host) {
		return data.getApartmentByHost(host);
	}
	
	@GetMapping(params = "country")
	public List<Apartment> getApartmentByCountry(@RequestParam("country") String country) {
		return data.getApartmentByCountry(country);
	}
	
	@GetMapping(params = "price")
	public List<Apartment> getApartmentByPrice(@RequestParam("price") double price) {
		return data.getApartmentCheaperThan(price);
	}
	
	
	@PostMapping
	public boolean createApartment(@RequestBody Apartment apartment) {
		return data.updateApartment(apartment);
	}
	
	@PutMapping
	public boolean updateApartment(@RequestBody Apartment apartment) {
		return data.saveOrUpdateApartment(apartment);
	}
	
	@DeleteMapping
	public boolean removeApartment(@RequestBody Apartment apartment) {
		return data.deleteApartment(apartment);
	}
	
	
	
	
	
}
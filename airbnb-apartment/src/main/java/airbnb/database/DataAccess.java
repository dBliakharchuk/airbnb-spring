package airbnb.database;

import airbnb.model.Apartment;
import airbnb.model.ApartmentPK;
import airbnb.repositories.ApartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class DataAccess {

    @Autowired
    private ApartmentRepository apartmentRepository;

    /**************Get Data********************/
    public Iterable<Apartment> getAllApartment(){
        return apartmentRepository.findAll();
    }
   
    public Optional<Apartment> getApartmentById(ApartmentPK apartmentId){
    	Optional<Apartment> apartment = apartmentRepository.findById(apartmentId);
 	    	
    	return apartment;
    }
    
    public List<Apartment> getApartmentByName(String name){
    	return apartmentRepository.findByName(name);
    }
    
    public List<Apartment> getApartmentByCity(String city){
    	return apartmentRepository.findByCity(city);
    }
    
    public List<Apartment> getApartmentByCountry(String country){
    	return apartmentRepository.findByCountry(country);
    }
    
    public List<Apartment> getApartmentByHost(String host){
    	return apartmentRepository.findByHost(host);
    }
    
    public List<Apartment> getApartmentCheaperThan(double price){
    	return apartmentRepository.findByPrice(price);
    }
    
    
    /*************Update apartment***************/
    public boolean saveOrUpdateApartment(Apartment apartment) {
    	return apartmentRepository.save(apartment) != null;
    }
    
    /************Create new Apartment************/
    public boolean updateApartment(Apartment apartment) {
    	if (apartmentRepository.existsById(apartment.getId())) {
    		return false;
    	}
    	return apartmentRepository.save(apartment) != null;
    }
    
    /***********Delete apartment****************/
    public boolean deleteApartment(Apartment apartment) {
    	if (! apartmentRepository.existsById(apartment.getId())) {
    		return false;
    	}
    	apartmentRepository.deleteById(apartment.getId());
    	return true;
    }
    

}

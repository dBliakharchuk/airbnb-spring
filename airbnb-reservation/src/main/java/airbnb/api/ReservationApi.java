package airbnb.api;

import airbnb.database.DataAccess;
import airbnb.model.Reservation;
import airbnb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationApi {
    private Logger logger = LoggerFactory.getLogger(ReservationApi.class);

    @Autowired
    DataAccess data;

    @GetMapping(consumes = "application/json", produces = "application/json")
    public Iterable<Reservation> getAllReservations() {
        return data.getAllReservations();
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Reservation createReservation(@RequestBody Reservation reservation) {

    	return data.createReservation(reservation);
    }
    
    @PutMapping(consumes = "application/json", produces = "application/json")
    public Reservation createOrUpdatedReservation(@RequestBody Reservation reservation) {

        return data.saveOrUpdateReservation(reservation);
    }
    
    @DeleteMapping(consumes = "application/json", produces = "application/json")
    public boolean deleteReservation(@RequestBody Reservation reservation) {
    	
        return data.removeReservation(reservation);
    }
}

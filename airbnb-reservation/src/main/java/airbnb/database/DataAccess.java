package airbnb.database;

import airbnb.model.Reservation;
import airbnb.model.User;
import airbnb.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataAccess {
    private Logger logger = LoggerFactory.getLogger(DataAccess.class);

    @Autowired
    private ReservationRepository reservationRepository;

    public Iterable<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public boolean removeReservation(Reservation reservation) {
    	if (! reservationRepository.existsById(reservation.getId())) {
    		return false;
    	}
    	reservationRepository.deleteById(reservation.getId());
    	return true;
    } 
    
    public Reservation saveOrUpdateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}

package airbnb.repositories;

import airbnb.model.Reservation;
import airbnb.model.ReservationPK;
import airbnb.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, ReservationPK> {

}

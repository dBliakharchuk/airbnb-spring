package airbnb.repositories;

import airbnb.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    @Query("select u from User u where u.name = :name and u.surname = :surname")
    List<User> findByNameSurname(@Param("name") String name, @Param("surname") String surname);

}

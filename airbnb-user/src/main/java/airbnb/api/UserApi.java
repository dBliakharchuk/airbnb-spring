package airbnb.api;

import airbnb.database.DataAccess;
import airbnb.model.User;
import airbnb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserApi {

    @Autowired
    DataAccess data;

    @Autowired
    UserRepository users;

    @Autowired
    DataSource dataSource;

    @RequestMapping(path = "/")
    public Iterable<User> test() {
        return users.findAll();
    }

    @RequestMapping(path = "/user")
    public Optional<User> getUserByEmail(@RequestParam("email") String email) {
        return users.findById(email);
    }

}

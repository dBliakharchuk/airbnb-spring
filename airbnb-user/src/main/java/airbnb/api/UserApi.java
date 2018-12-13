package airbnb.api;

import airbnb.database.DataAccess;
import airbnb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserApi {
    private Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    DataAccess data;

    @GetMapping(consumes = "application/json", produces = "application/json")
    public Iterable<User> getAllUsers() {

        return data.getAllUsers();
    }

    @GetMapping(params = "email", consumes = "application/json", produces = "application/json")
    public Optional<User> getUserByEmail(@RequestParam("email") String email) {

        return data.getUserByEmail(email);
    }

    @GetMapping(params = {"name", "surname"}, consumes = "application/json", produces = "application/json")
    public List<User> getUserByNameSurname(@RequestParam("name") String name,
                                           @RequestParam("surname") String surname) {

        return data.getUserByNameSurname(name, surname);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public boolean createUser(@RequestBody User user) {

        return data.createUser(user);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public User createOrUpdateUser(@RequestBody User user) {

        return data.saveOrUpdateUser(user);
    }

    @DeleteMapping(params = "email", consumes = "application/json", produces = "application/json")
    public boolean deleteUserByEmail(@RequestParam String email) {

        return data.deleteUserByEmail(email);
    }

    @DeleteMapping(consumes = "application/json", produces = "application/json")
    public boolean deleteUser(@RequestBody User user) {
        return data.deleteUserByEmail(user.getEmail());
    }


    private boolean isUserAuthorized(String email, String password) {
        //TODO admin only
        Optional<User> optionalUser = data.getUserByEmail(email);
        return optionalUser.isPresent() && optionalUser.get().getPassword().equals(password);
    }

}

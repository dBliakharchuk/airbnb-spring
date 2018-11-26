package airbnb.api;

import airbnb.database.DataAccess;
import airbnb.model.User;
import airbnb.model.UserPublicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(path = "/user")
public class UserApi {
    private Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    DataAccess data;

    @GetMapping
    public List<UserPublicInfo> getAllUsersPublic() {
        return StreamSupport.stream(data.getAllUsers().spliterator(), false).map(UserPublicInfo::new).collect(Collectors.toList());
    }

    @GetMapping(headers = {"email", "password"})
    public Iterable<User> getAllUsers(@RequestHeader("email") String em,
                                      @RequestHeader("password") String pass) {
        if (!isUserAuthorized(em, pass)) {
            return Collections.emptyList();
        }

        return data.getAllUsers();
    }

    @GetMapping(params = "email", headers = {"email", "password"})
    public Optional<User> getUserByEmail(@RequestParam("email") String email,
                                         @RequestHeader("email") String em,
                                         @RequestHeader("password") String pass) {
        if (!isUserAuthorized(em, pass)) {
            return Optional.empty();
        }

        return data.getUserByEmail(email);
    }

    @GetMapping(params = {"name", "surname"}, headers = {"email", "password"})
    public List<User> getUserByNameSurname(@RequestParam("name") String name,
                                           @RequestParam("surname") String surname,
                                           @RequestHeader("email") String em,
                                           @RequestHeader("password") String pass) {
        if (!isUserAuthorized(em, pass)) {
            return Collections.emptyList();
        }

        return data.getUserByNameSurname(name, surname);
    }

    @PostMapping(headers = {"email", "password"})
    public boolean updateUser(@RequestBody User user,
                              @RequestHeader("email") String em,
                              @RequestHeader("password") String pass) {
        if (!isUserAuthorized(em, pass)) {
            return false;
        }

        return data.updateUser(user);
    }

    @PutMapping(headers = {"email", "password"})
    public User createOrUpdateUser(@RequestBody User user,
                                   @RequestHeader("email") String em,
                                   @RequestHeader("password") String pass) {
        if (!isUserAuthorized(em, pass)) {
            return null;
        }

        return data.saveOrUpdateUser(user);
    }

    @DeleteMapping(params = "email", headers = {"email", "password"})
    public boolean deleteUserByEmail(@RequestParam String email,
                                     @RequestHeader("email") String em,
                                     @RequestHeader("password") String pass) {
        if (!isUserAuthorized(em, pass)) {
            return false;
        }

        return data.deleteUserByEmail(email);
    }

    @DeleteMapping(headers = {"email", "password"})
    public boolean deleteUser(@RequestBody User user,
                              @RequestHeader("email") String em,
                              @RequestHeader("password") String pass) {
        if (!isUserAuthorized(em, pass)) {
            return false;
        }

        return data.deleteUser(user);
    }


    private boolean isUserAuthorized(String email, String password) {
        //TODO admin only
        Optional<User> optionalUser = data.getUserByEmail(email);
        return optionalUser.isPresent() && optionalUser.get().getPassword().equals(password);
    }

}

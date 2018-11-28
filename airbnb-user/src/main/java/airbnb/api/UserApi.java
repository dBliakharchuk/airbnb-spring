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

//    @GetMapping
//    public List<UserPublicInfo> getAllUsersPublic() {
//        return StreamSupport.stream(data.getAllUsers().spliterator(), false).map(UserPublicInfo::new).collect(Collectors.toList());
//    }

    @GetMapping(produces = "application/json")
    public Iterable<User> getAllUsers() {
//        if (!isUserAuthorized(em, pass)) {
//            return Collections.emptyList();
//        }

        return data.getAllUsers();
    }

    @GetMapping(params = "email")
    public Optional<User> getUserByEmail(@RequestParam("email") String email) {
//        if (!isUserAuthorized(em, pass)) {
//            return Optional.empty();
//        }

        return data.getUserByEmail(email);
    }

    @GetMapping(params = {"name", "surname"})
    public List<User> getUserByNameSurname(@RequestParam("name") String name,
                                           @RequestParam("surname") String surname) {
//        if (!isUserAuthorized(em, pass)) {
//            return Collections.emptyList();
//        }

        return data.getUserByNameSurname(name, surname);
    }

    @PostMapping
    public boolean updateUser(@RequestBody User user) {
//        if (!isUserAuthorized(em, pass)) {
//            return false;
//        }

        return data.updateUser(user);
    }

    @PutMapping
    public User createOrUpdateUser(@RequestBody User user) {
//        if (!isUserAuthorized(em, pass)) {
//            return null;
//        }

        return data.saveOrUpdateUser(user);
    }

    @DeleteMapping(params = "email")
    public boolean deleteUserByEmail(@RequestParam String email) {
//        if (!isUserAuthorized(em, pass)) {
//            return false;
//        }

        return data.deleteUserByEmail(email);
    }

    @DeleteMapping
    public boolean deleteUser(@RequestBody User user) {
//        if (!isUserAuthorized(em, pass)) {
//            return false;
//        }

        return data.deleteUser(user);
    }


    private boolean isUserAuthorized(String email, String password) {
        //TODO admin only
        Optional<User> optionalUser = data.getUserByEmail(email);
        return optionalUser.isPresent() && optionalUser.get().getPassword().equals(password);
    }

}

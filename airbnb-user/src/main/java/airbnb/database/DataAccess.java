package airbnb.database;

import airbnb.model.User;
import airbnb.repositories.UserRepository;
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
    private UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findById(email);
    }

    public List<User> getUserByNameSurname(String name, String surname) {
        return userRepository.findByNameSurname(name, surname);
    }

    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    public boolean createUser(User user) {
        if (userRepository.existsById(user.getEmail())) {
            return false;
        }
        return userRepository.save(user) != null;
    }

    public boolean deleteUser(User user) {
        if (!userRepository.existsById(user.getEmail())) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    public boolean deleteUserByEmail(String email) {
        if (!userRepository.existsById(email)) {
            return false;
        }
        userRepository.deleteById(email);
        return true;
    }

    
}

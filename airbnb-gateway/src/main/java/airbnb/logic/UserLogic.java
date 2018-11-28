package airbnb.logic;

import airbnb.model.Apartment;
import airbnb.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * UserLogic allows to change User data and remove User account if it is possible.
 * It also handles data validation.
 *
 * @author Mateusz
 */
public class UserLogic {

    public static boolean modifyUser(User user) {
//        if (user == null || !isUserValid(user) || !isUserRegistered(user)) {
//            return false;
//        }
//
//        return DataAccess.updateUser(user);
        return true;
    }

    public static boolean registerUser(User user) {
//        if (user == null || !isUserValid(user) || isUserRegistered(user)) {
//            return false;
//        }
//
//        return DataAccess.createUser(user);
        return true;
    }

    public static boolean removeUser(User user) {
//        if (user == null || !isUserRegistered(user) || user.getApartments().size() > 0) {
//            return false;
//        }
//
//        return DataAccess.removeUser(user);
        return true;
    }

    public static boolean removeUser(String email) {
//        if (email == null || email.equals("")) {
//            return false;
//        }
//        User toDelete = DataAccess.getUserByEmail(email);
//        return removeUser(toDelete);
        return true;
    }

    public static boolean changeUserName(String email, String name) {
//        User toChange = DataAccess.getUserByEmail(email);
//        toChange.setName(name);
//        if (!isUserValid(toChange)) {
//            return false;
//        }
//        return DataAccess.updateUser(toChange);
        return true;
    }

    public static boolean changeUserSurname(String email, String surname) {
//        User toChange = DataAccess.getUserByEmail(email);
//        toChange.setSurname(surname);
//        if (!isUserValid(toChange)) {
//            return false;
//        }
//        return DataAccess.updateUser(toChange);
        return true;
    }

    public static boolean changeUserPassword(String email, String password) {
//        User toChange = DataAccess.getUserByEmail(email);
//        toChange.setPassword(password);
//        if (!isUserValid(toChange)) {
//            return false;
//        }
//
//        return DataAccess.updateUser(toChange);
        return true;
    }

    public static boolean changeUserPhone(String email, String phone) {
//        User toChange = DataAccess.getUserByEmail(email);
//        toChange.setPhone(phone);
//        if (!isUserValid(toChange)) {
//            return false;
//        }
//        return DataAccess.updateUser(toChange);
        return true;
    }

    public static Set<Apartment> getRentedApartmentsByUser(User user) {
//        if (user == null) {
//            return null;
//        }
//
//        List<Reservation> reservations = DataAccess.getAllReservations();
//        Set<Apartment> apartmentsRented = new HashSet<>();
//        for (Reservation r : reservations) {
//            if (r.getUser().equals(user)) {
//                apartmentsRented.add(r.getApartment());
//            }
//        }
//
//        return apartmentsRented;
        return new HashSet<>();
    }

    public static boolean isUserRegistered(User user) {
//        return user != null && DataAccess.getUserByEmail(user.getEmail()) != null;
        return true;
    }

    public static boolean isAdmin(User user) {
        if (user != null && user.getEmail().equals("admin")) {
            return true;
        }
        return false;
    }

    public static boolean isUserValid(User user) {

        return (user.getEmail().contains("@") || user.getEmail().equals("admin")) &&
                !(user.getName().matches(".*\\d+.*")) &&
                !(user.getSurname().matches(".*\\d+.*")) &&
                user.getPassword().length() >= 8 &&
                user.getPhone().length() <= 12 && StringUtils.isNumeric(user.getPhone());
    }

}

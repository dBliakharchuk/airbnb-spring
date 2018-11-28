package airbnb.logic;

import airbnb.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ApartmentLogic {

    public static List<Apartment> search(String fromPlace, String price,
                                         ApartmentType typeOfAccom, Integer adults, Integer children, Date dateStart, Date dateEnd) {

//        List<Apartment> availableApartmentsList = DataAccess.getAllApartments();
//        List<Apartment> resultApartmentsList = new ArrayList<Apartment>();
//
//        for (Apartment apartment : availableApartmentsList) {
//
//            if (checkApartmentName(apartment, fromPlace)
//                    && checkApartmentPrice(apartment, price)
//                    && checkApartmentType(apartment, typeOfAccom)
//                    && checkApartmentBeds(apartment, adults, children)
//                    && checkApartmentDates(apartment, dateStart, dateEnd)) {
//
//                resultApartmentsList.add(apartment);
//            }
//        }
//
//        return resultApartmentsList;
        return new ArrayList<>();
    }

    public static String correctApartmentTypeDisplay(String apartmentType) {

        return ApartmentType.fromString(apartmentType).toString();
    }

    public static double countTotalPrice(String dateStart, String dateEnd, Apartment apartment) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        double totalPrice = 0;

        try {
            totalPrice = DateUtils.getDatesBetween(myFormat.parse(dateStart), myFormat.parse(dateEnd)).size() * apartment.getPrice();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    private static boolean checkApartmentName(Apartment apartment, String obtainedName) {
        if (apartment == null || obtainedName == null) {
            return false;
        }

        if (obtainedName.contains(apartment.getCity().toLowerCase())) {
            return true;
        } else if (obtainedName.contains(apartment.getCountry().toLowerCase())) {
            return true;
        } else if (obtainedName.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkApartmentPrice(Apartment apartment, String price) {

        double pricePerDay = apartment.getPrice();

        if (price == null) {
            return true;
        } else if (price.equals("P1") && pricePerDay < 35) {
            return true;
        } else if (price.equals("P2") && pricePerDay >= 35 && pricePerDay < 70) {
            return true;
        } else if (price.equals("P3") && pricePerDay >= 70 && pricePerDay < 131) {
            return true;
        } else if (price.equals("P4") && pricePerDay >= 131) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkApartmentType(Apartment apartment, ApartmentType typeOfAccom) {

        if (typeOfAccom == null) {
            return true;
        } else if (apartment.getType().equals(typeOfAccom)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkApartmentBeds(Apartment apartment, Integer numberOfAdults, Integer numberOfChildren) {
        Integer bedsForAdults = apartment.getBedsAdult();
        Integer bedsForChildren = apartment.getBedsChild();

        if (numberOfAdults == null && numberOfChildren == null) {
            return true;
        } else if ((numberOfAdults != null && numberOfAdults <= bedsForAdults) && numberOfChildren == null) {
            return true;
        } else if (numberOfAdults == null && numberOfChildren <= bedsForChildren) {
            return true;
        } else if (numberOfAdults != null && numberOfAdults <= bedsForAdults && (numberOfChildren <= bedsForChildren)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkApartmentDates(Apartment apartment, Date dateStart, Date dateEnd) {
        List<Reservation> reservations = apartment.getReservations();
        List<Date> bookedDays = new ArrayList<Date>();

        for (Reservation reservation : reservations) {
            bookedDays.add(reservation.getDate());
        }

        if (dateStart != null && dateEnd != null) {
            if (dateStart.before(dateEnd)) {

                for (Date date : bookedDays) {
                    if (DateUtils.isDateBetweenTwoDates(dateStart, dateEnd, date)) {
                        return false;
                    }
                }
                return true;
            } else {
                return true;
            }
        } else if (dateStart != null) {

            for (Date date : bookedDays) {
                if ((date.compareTo(dateStart) >= 0)) {
                    return false;
                }
            }
            return true;
        } else if (dateEnd != null) {
            Date currentDate = new Date(new Date().getTime());

            for (Date date : bookedDays) {
                if (DateUtils.isDateBetweenTwoDates(currentDate, dateEnd, date)) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }

    }

    private static boolean isDateBetweenTwoDates(Date dateStart, Date dateEnd, Date examinedDate) {

        return dateStart.compareTo(examinedDate) * dateEnd.compareTo(examinedDate) <= 0;
    }

    public static boolean bookApartment(User user, Apartment apartment, Date start, Date end) {
//        if (user == null || apartment == null || start == null || end == null || end.before(start)) {
//            return false;
//        }
//
//        Map<Date, Reservation> bookings = new HashMap<>();
//        for (Reservation r : apartment.getReservations()) bookings.put(r.getDate(), r);
//
//        List<Reservation> reservations = new ArrayList<>();
//        List<Date> dates = DateUtils.getDatesBetween(start, end);
//
//        for (Date date : dates) {
//            Reservation reservation = new Reservation(user, apartment, date);
//            reservations.add(reservation);
//
//            if (bookings.containsKey(date)) {
//                return false;
//            } else {
//                bookings.put(date, reservation);
//            }
//        }
//
//        List<Reservation> userReservations = user.getReservations();
//        userReservations.addAll(reservations);
//
//        user.setReservations(new ArrayList(new HashSet(userReservations)));
//        apartment.setReservations(new ArrayList(bookings.values()));
//
//        DataAccess.updateApartment(apartment);
//        DataAccess.updateUser(user);
//        for (Reservation r : reservations) DataAccess.createReservation(r);

        return true;
    }

    public static boolean addApartment(Apartment apartment) {
//        if (apartment == null) {
//            return false;
//        }
//
//        if (!UserLogic.isUserRegistered(apartment.getHost())) {
//            UserLogic.registerUser(apartment.getHost());
//        }
//
//        return DataAccess.createApartment(apartment);
        return true;
    }

    public static boolean modifyApartment(Apartment apartment) {
//        if (!isApartmentRegistered(apartment)) {
//            return false;
//        }
//
//        return DataAccess.updateApartment(apartment);
        return true;
    }

    public static boolean removeApartment(ApartmentPK apartmentPk) {
//        if (apartmentPk == null) {
//            return false;
//        }
//        Apartment toDelete = DataAccess.getApartmentById(apartmentPk);
//        return removeApartment(toDelete);
        return true;
    }

    public static boolean removeApartment(Apartment apartment) {
//        if (apartment == null) {
//            return false;
//        }
//
//        User host = apartment.getHost();
//        host.removeApartment(apartment);
//        DataAccess.updateUser(host);
//
//        return DataAccess.removeApartment(apartment);
        return true;
    }

    public static boolean isApartmentRegistered(Apartment apartment) {
//        return apartment != null && DataAccess.getApartmentById(apartment.getId()) != null;
        return true;
    }
}

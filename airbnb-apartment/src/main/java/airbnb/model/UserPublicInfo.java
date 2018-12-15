package airbnb.model;

import java.io.Serializable;
import java.util.List;

public class UserPublicInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private String name;
    private String surname;

    private List<Apartment> apartments;

    public UserPublicInfo(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.apartments = user.getApartments();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    @Override
    public String toString() {
        return "UserPublicInfo{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", apartments=" + apartments +
                '}';
    }


}

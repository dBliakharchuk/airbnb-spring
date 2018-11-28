package airbnb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class ApartmentApi {

    @PostMapping(value = "/accommodations")
    public String showApartment() {
        return "accommodation";
    }

    @GetMapping(value = "/editApartment")
    public String showEditApartment() {
        return "editApartment";
    }

    @PostMapping(value = "/editApartment")
    public String editApartment() {
        return "manageProfile";
    }

    @PostMapping(value = "/addApartment")
    public String addApartment() {
        return "manageProfile";
    }

    @PostMapping(value = "/deleteApartment")
    public boolean deleteApartment() {
        return true;
    }
}

package airbnb.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationApi {

    @PostMapping(value = "/reservations")
    public String bookApartment() {
        return "reservation";
    }

    @PostMapping(value = "/payments")
    public String pay() {
        return "payment";
    }
}

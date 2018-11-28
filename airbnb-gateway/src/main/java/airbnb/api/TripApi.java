package airbnb.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TripApi {

    @GetMapping(value = "/tripServlet")
    public String showTrips() {
        return "trips";
    }

    @PostMapping(value = "/tripServlet/info")
    public String showMoreInfo() {
        return "tripsMoreInf";
    }
}

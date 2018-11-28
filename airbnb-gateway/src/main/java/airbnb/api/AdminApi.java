package airbnb.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminApi {

    @GetMapping(value = "/administratorUsers")
    public String showUsers() {
        return "administratorUsers";
    }

    @GetMapping(value = "/administratorHomes")
    public String showApartments() {
        return "administratorHomes";
    }
}


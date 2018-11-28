package airbnb.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserApi {

    @GetMapping(value = "/manageProfile")
    public String showProfile() {
        return "manageProfile";
    }

    @PostMapping(value = "/updateUserServlet")
    public String updateUser() {
        return "manageProfile";
    }
}


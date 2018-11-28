package airbnb.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorizationApi {
    private Logger logger = LoggerFactory.getLogger(PublicApi.class);

    @PostMapping(value = "/registrationServlet")
    public String registerUser() {
        return "index";
    }

    @PostMapping(value = "/loginServlet")
    public String logInUser() {
        return "index";
    }

}

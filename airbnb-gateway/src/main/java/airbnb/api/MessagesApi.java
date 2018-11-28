package airbnb.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessagesApi {

    @GetMapping(value = "/messages")
    public String showMessages() {
        return "messages";
    }
}

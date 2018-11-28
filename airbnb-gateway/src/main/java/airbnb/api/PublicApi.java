package airbnb.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicApi {
    private Logger logger = LoggerFactory.getLogger(PublicApi.class);

    @GetMapping(value = {"/", "/index"})
    public String getIndex() {
        return "index";
    }

    @PostMapping(value = "/results")
    public String showResults() {
        return "results";
    }


}

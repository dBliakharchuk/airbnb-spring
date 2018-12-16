package airbnb.api;

import airbnb.model.PaymentInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/bank")
public class BankApi {

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> validate(@RequestBody PaymentInfo info) {
        if (info == null || !isPaymentValid(info)) {
            return new ResponseEntity<>(false, HttpStatus.PAYMENT_REQUIRED);
        } else {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
    }

    private boolean isPaymentValid(PaymentInfo info) {
    	
        return info.getNumber().replaceAll("\\s","").length() == 16
                && info.getNumber().replaceAll("\\s","").
                    replaceAll("\\d","").length() == 0
                && Long.valueOf(info.getNumber().replaceAll("\\s","").
                    replaceAll("\\D+","")) % 4 == 0
                && info.getCvc().replaceAll("\\s","").length() == 3
                && info.getCvc().replaceAll("\\s","").
                    replaceAll("\\d","").length() == 0
                && info.getExpiration().after(new Date());
//
    }
}

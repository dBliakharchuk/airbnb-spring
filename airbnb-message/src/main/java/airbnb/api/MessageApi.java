package airbnb.api;

import airbnb.database.DataAccess;
import airbnb.model.Message;
import airbnb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/messages")
public class MessageApi {

    @Autowired
    DataAccess data;
    
    
   
    User user1 = new User("pd@gmail.com", "Piotr", "Ducki", "123123", "123123123");
    User user2 = new User("test@gmail.com", "Adnrzej", "Olek", "123123", "123123123");
    Message msg = new Message(user1, user2, new Date(), "test wiadomosci");
    
    
    @GetMapping()
    public Iterable<Message> getAllMessages() {
    	return data.getAllMessages();

    }
    
    //need this
    @GetMapping( path = "/sent")
    public Iterable<Message> getSentMessages(@RequestParam("email") String email) {
    	return data.getSentMessagesByEmail(email);
    }
    
    //need this
    @GetMapping(path = "/recived")
    public Iterable<Message> getRecivedMessages(@RequestParam("email") String email) {

    	return data.getRecivedMessagesByEmail(email);

    }
    
    
    //need this
    @PutMapping()
    public Message createMessage(@RequestBody Message message) {

        return null;
    }
    
    
    @DeleteMapping()
    public boolean deleteMessage(@RequestBody Message message) {

        return false;
    }
    

}

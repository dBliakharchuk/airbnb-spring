package airbnb.database;

import airbnb.model.Message;
import airbnb.model.MessagePK;
import airbnb.repositories.MessageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataAccess {
	@Autowired
    private MessageRepository messageRepository;
	
	 public Iterable<Message> getAllMessages() {
	        return messageRepository.findAll();
	    }
    
	 public Iterable<Message> getSentMessagesByEmail(String email) {
		 return messageRepository.findSentMesseges(email);
	    }
	 
	 public Iterable<Message> getRecivedMessagesByEmail(String email) {
		 return messageRepository.findRecivedMesseges(email);
	    }
	 
	 public Message saveMessage(Message message) {
	        return messageRepository.save(message);
	    }
    
}

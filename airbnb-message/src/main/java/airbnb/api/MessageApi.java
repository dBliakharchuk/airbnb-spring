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
public class MessageApi
{

	@Autowired
	DataAccess data;

	@GetMapping(path = "/sent")
	public Iterable<Message> getSentMessages(@RequestParam("email") String email)
	{
		return data.getSentMessagesByEmail(email);
	}

	@GetMapping(path = "/recived")
	public Iterable<Message> getRecivedMessages(@RequestParam("email") String email)
	{
		return data.getRecivedMessagesByEmail(email);
	}

	@PutMapping()
	public Message createMessage(@RequestBody Message message)
	{
		return data.saveMessage(message);
	}

	
	@GetMapping()
	public Iterable<Message> getAllMessages()
	{
		return data.getAllMessages();
	}

	@DeleteMapping()
	public boolean deleteMessage(@RequestBody Message message)
	{
		return false;
	}

}

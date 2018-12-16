package airbnb.api;

import airbnb.database.DataAccess;
import airbnb.database.HttpClientUser;
import airbnb.model.Message;
import airbnb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/messages")
public class MessageApi
{
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{
		return new WebMvcConfigurerAdapter()
		{
			@Override
			public void addCorsMappings(CorsRegistry registry)
			{
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	@Autowired
	DataAccess data;

	@GetMapping(params = "email")
	public List<Message> getNewestMessagesByEmail(@RequestParam("email") String email)
	{

		return data.getNewestMessages(email);
	}

	@GetMapping(params = { "email", "selectedUser" })
	public List<Message> getConversation(@RequestParam("email") String email,
			@RequestParam("selectedUser") String selectedUser)
	{
		
		return data.getConversation(email, selectedUser);
	}

	@PutMapping()
	public Message createMessage(@RequestBody Message message)
	{
		
		System.out.println(message.getId().getReceiver());
		User reciver = (airbnb.model.User)HttpClientUser.getUserByEmail(message.getId().getReceiver());
		User sender = (airbnb.model.User)HttpClientUser.getUserByEmail(message.getId().getSender());
		
		message.setReceiver(reciver);
		message.setSender(sender);

		return data.saveMessage(message);
	}
	

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

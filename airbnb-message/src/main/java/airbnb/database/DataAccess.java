package airbnb.database;

import airbnb.model.Message;
import airbnb.model.MessagePK;
import airbnb.model.User;
import airbnb.repositories.MessageRepository;
import com.loopj.android.http.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;



@Service
public class DataAccess
{
	@Autowired
	private MessageRepository messageRepository;

	public Iterable<Message> getAllMessages()
	{
		return messageRepository.findAll();
	}

	public Iterable<Message> getSentMessagesByEmail(String email)
	{
		return messageRepository.findSentMesseges(email);
	}

	public Iterable<Message> getRecivedMessagesByEmail(String email)
	{
		return messageRepository.findRecivedMesseges(email);
	}

	//funcion looks for the newest message send beetwin selected user and other users
	public List <Message> getNewestMessages(String email)
	{
		Map<User, Message> conversations = new HashMap<>();
		getSentMessagesByEmail(email).forEach(msg ->
		{
			if (conversations.containsKey(msg.getReceiver()))
			{
				if (conversations.get(msg.getReceiver()).getDate().before(msg.getDate()))
				{
					conversations.put(msg.getReceiver(), msg);
				}

			} else
			{
				conversations.put(msg.getReceiver(), msg);
			}
		});

		
		getRecivedMessagesByEmail(email).forEach(msg ->
		{
			if (conversations.containsKey(msg.getSender()))
			{
				if (conversations.get(msg.getSender()).getDate().before(msg.getDate()))
				{
					conversations.put(msg.getSender(), msg);
				}

			} else
			{
				conversations.put(msg.getSender(), msg);
			}
		});
		
		List<Message> newestMessages = new ArrayList<Message>(conversations.values());
		Collections.sort(newestMessages);
		return newestMessages;
	}
	
	
	
	public List<Message> getConversation(String email, String selectedUser)
	{
		ArrayList<Message> messages  = new ArrayList<Message>();
		getSentMessagesByEmail(email).forEach(msg ->
		{
			if (msg.getReceiver().getEmail().equals(selectedUser))
			{
				messages.add(msg);
			}
		});
		
		getRecivedMessagesByEmail(email).forEach(msg ->
		{
			if (msg.getSender().getEmail().equals(selectedUser))
			{
				messages.add(msg);
			}
		});
		
		
		Collections.sort(messages);
		
		
		return messages;
	}
	

	public Message saveMessage(Message message)
	{
		User sender = HttpClientUser.getUserByEmail(message.getId().getReceiver());
		message.setDate(new Date());
//		message.setReceiver(HttpClientUser.getUserByEmail(message.getId().getReceiver()));
//		message.setSender(HttpClientUser.getUserByEmail(message.getId().getSender()));

		return messageRepository.save(message);
	}
	
	
	
	

}

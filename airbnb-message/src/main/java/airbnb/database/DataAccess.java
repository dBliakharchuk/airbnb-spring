package airbnb.database;

import airbnb.model.Message;
import airbnb.model.MessagePK;
import airbnb.model.User;
import airbnb.repositories.MessageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
			setMessageAsRead(msg);
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
		return messageRepository.save(message);
	}
	
	public void setMessageAsRead(Message message)
	{
		 message.setIsUnread(false);
		 saveMessage(message);
	}

}

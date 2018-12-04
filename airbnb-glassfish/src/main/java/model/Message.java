package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * Message class is a Java representative of Message table in database.
 * 
 * @author Mateusz
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m"),
	@NamedQuery(name="Message.findUserMessages", query="SELECT m FROM Message m where m.sender = :userEmail or m.receiver = :userEmail")})
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MessagePK id;
	private boolean isUnread;

	@ManyToOne
	@JoinColumn(name="sender")
	private User sender;

	@ManyToOne
	@JoinColumn(name="receiver")
	private User receiver;

	public Message() {
	
	}

	public Message(User sender, User receiver, Date date, String message) {
		this.id = new MessagePK(message, date, sender.getEmail(), receiver.getEmail());
		this.isUnread = true;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public static Message createNewMessage(User sender, User receiver, String message) {
		Date date = new Date();
		return new Message(sender, receiver, date, message);
	}

	public MessagePK getId() {
		return this.id;
	}

	public void setId(MessagePK id) {
		this.id = id;
	}

	public boolean getIsUnread() {
		return this.isUnread;
	}

	public void setIsUnread(boolean isUnread) {
		this.isUnread = isUnread;
	}

	public User getSender() {
		return this.sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return this.receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;		
	}
	
	public String getMessage() {
		return id.getMessage();
	}
	
	public void setMessage(String message) {
		id.setMessage(message);
	}
	
	public Date getDate() {
		return id.getDate();
	}
	
	public void setDate(Date date) {
		id.setDate(date);
	}

	@Override
	public String toString() {
		return "Message [id=" + id.toString() + ", isUnread=" + isUnread + ", sender=" + sender + ", receiver=" + receiver + "]";
	}
	
	

}
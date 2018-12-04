package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the message database table.
 * 
 */
@Embeddable
public class MessagePK implements Serializable {
	private static final long serialVersionUID = 1L;

	private String message;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Column(insertable=false, updatable=false)
	private String sender;
	@Column(insertable=false, updatable=false)
	private String receiver;

	public MessagePK() {
		
	}
	
	public MessagePK(String message, Date date, String sender, String receiver) {
		super();
		this.message = message;
		this.date = date;
		this.sender = sender;
		this.receiver = receiver;
	}

	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getSender() {
		return this.sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getReceiver() {
		return this.receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MessagePK)) {
			return false;
		}
		MessagePK castOther = (MessagePK)other;
		return 
			this.message.equals(castOther.message)
			&& this.date.equals(castOther.date)
			&& this.sender.equals(castOther.sender)
			&& this.receiver.equals(castOther.receiver);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.message.hashCode();
		hash = hash * prime + this.date.hashCode();
		hash = hash * prime + this.sender.hashCode();
		hash = hash * prime + this.receiver.hashCode();
		
		return hash;
	}

	@Override
	public String toString() {
		return "MessagePK [message=" + message + ", date=" + date + ", sender=" + sender + ", receiver=" + receiver
				+ "]";
	}
	
	
}
package logic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import database.DataAccess;
import model.Message;
import model.User;

/**
 * MessageLogic creates higher level of abstraction when dealing with Messaging subsystem.
 * 
 * @author Mateusz
 *
 */
public class MessageLogic {
	
	public static boolean sendMessage(Message msg) {
		if (msg == null) {
			return false;
		}
		
		//TODO JMS
		
		return DataAccess.createMessage(msg);
	}
	
//	
	
	public static void notifyReceiver(Message message) {
		try {
			
            InitialContext ctx = new InitialContext();  
            ConnectionFactory f = (ConnectionFactory) ctx.lookup("airbnbMessageQueueConnectionFactory");  
            Connection con = f.createConnection();  
            con.start();  
              
            Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);   
            
            Queue t= (Queue) ctx.lookup("messageQueue");  
            
            MessageProducer sender = ses.createProducer(t);  
            TextMessage msg = ses.createTextMessage(message.getMessage());  
              
            sender.send(msg);
            ses.close();
            con.close();  
        } catch (Exception e) {
        	e.printStackTrace();
        }  
	}
	
	
}

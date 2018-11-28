package airbnb.logic;

import airbnb.model.Message;

/**
 * MessageLogic creates higher level of abstraction when dealing with Messaging subsystem.
 *
 * @author Mateusz
 */
public class MessageLogic {

    public static boolean sendMessage(Message msg) {
//        if (msg == null) {
//            return false;
//        }
//
//        //TODO JMS
//
//        return DataAccess.createMessage(msg);
        return true;
    }

    public static boolean removeMessage(Message msg) {
//        if (msg == null) {
//            return false;
//        }
//
//        User sender = msg.getSender();
//        sender.removeMessagesSent(msg);
//        DataAccess.updateUser(sender);
//
//        User receiver = msg.getReceiver();
//        sender.addMessagesReceived(msg);
//        DataAccess.updateUser(receiver);
//
//        return DataAccess.removeMessage(msg);
        return true;
    }



}

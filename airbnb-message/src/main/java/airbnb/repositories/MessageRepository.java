package airbnb.repositories;

import org.springframework.data.repository.CrudRepository;

import airbnb.model.Message;
import airbnb.model.MessagePK;

public interface MessageRepository extends CrudRepository<Message, MessagePK> 
{

}

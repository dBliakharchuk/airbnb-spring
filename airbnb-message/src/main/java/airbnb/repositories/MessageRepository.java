package airbnb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import airbnb.model.Message;
import airbnb.model.MessagePK;
import airbnb.model.User;

public interface MessageRepository extends CrudRepository<Message, MessagePK> 
{
	@Query("select m from Message m where m.id.sender = :email")
    List<Message> findSentMesseges(@Param("email") String email);
	
	@Query("select m from Message m where m.id.receiver = :email")
    List<Message> findRecivedMesseges(@Param("email") String email);
}

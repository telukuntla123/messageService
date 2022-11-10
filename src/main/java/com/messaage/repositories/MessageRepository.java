package com.messaage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.message.entities.MessageInfo;



@Repository
public interface MessageRepository extends CrudRepository<MessageInfo, String>{

	@Query("SELECT c FROM MessageInfo c where deliveryStatus='0'")
	List<MessageInfo> queryAllOpenMessages();
	
}

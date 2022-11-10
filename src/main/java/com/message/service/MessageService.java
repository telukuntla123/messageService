package com.message.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.messaage.repositories.MessageRepository;
import com.messaage.scheduler.MessageScheduler;
import com.message.entities.MessageInfo;
import com.message.entities.MessageSenderReq;
import com.message.pojo.MessageAckReq;
import com.message.pojo.MessageReq;
import com.message.util.EncryptionUtils;

@Service
public class MessageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
	
	@Value("${app.message.encryption.key}")
	private String encyptionKey;

	@Autowired
	private MessageRepository messageRepository;

	public String saveEmailData(MessageReq messageReq) {
		MessageInfo messageInfo = new MessageInfo();

		BeanUtils.copyProperties(messageReq, messageInfo);
		messageInfo.setId(String.valueOf(UUID.randomUUID()));
		messageInfo.setCreateTime(Instant.now().toString());
		String msg = messageReq.getMsg();
		if (!StringUtils.isEmpty(msg)) {
			messageInfo.setMessageSize(msg.length());
			messageInfo.setMsg(EncryptionUtils.encrypt(msg, encyptionKey));

		}
		messageInfo.setDeliveryStatu("0");
		messageRepository.save(messageInfo);
		return messageInfo.getId();
	}

	public String getMessageStatusById(String messageId) {

		Optional<MessageInfo> messOptional = messageRepository.findById(messageId);
		MessageInfo messageInfo = messOptional.isPresent() ? messOptional.get() : null;
		return messageInfo == null ? "Invalid Message Id"
				: (messageInfo.getDeliveryStatu().equalsIgnoreCase("0") ? "Message Not Delivered"
						: "Message Delivered Sucessfully");

	}

	public String updateMessageDeliveryStatus(MessageAckReq messageAckReq) {
		Optional<MessageInfo> messageInfoOpt = messageRepository.findById(messageAckReq.getMessageId());
		if (messageInfoOpt.isPresent()) {
			MessageInfo msInfo = messageInfoOpt.get();
			msInfo.setDeliveryStatu(messageAckReq.getDeliverystauts());
			msInfo.setDeliveredTime(messageAckReq.getDeliveredTime());
			messageRepository.save(msInfo);
			return "sucess";
		} else
			return "Invalid Message Id";

	}

	public boolean sendMessage(MessageSenderReq messageReq) {
		LOGGER.debug("Send Message api called...." + messageReq.toString());
		return true;
	}

}

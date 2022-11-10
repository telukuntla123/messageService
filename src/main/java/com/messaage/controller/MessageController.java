package com.messaage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messaage.scheduler.MessageScheduler;
import com.message.pojo.MessageAckReq;
import com.message.pojo.MessageReq;
import com.message.service.MessageService;

@RestController
@RequestMapping("/messageService")
public class MessageController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageScheduler.class);
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "sendMessage", method = { RequestMethod.POST,
			RequestMethod.GET }, consumes = "application/json", produces = "application/json")
	public String sendMessage(@RequestBody MessageReq messageReq) {
		LOGGER.info("MessageController : sendMessage called.");
		return messageService.saveEmailData(messageReq);
	}

	@RequestMapping(value = "messageStatus", method = RequestMethod.GET)
	public String getMessageStatus(@RequestParam String messageId) {
		LOGGER.info("MessageController : getMessageStatus called." + messageId);
		return messageService.getMessageStatusById(messageId);
	}

	@RequestMapping(value = "ackByMessageId", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String acknowledgeMessage(@RequestBody MessageAckReq messageAckReq) {
		LOGGER.info("MessageController : acknowledgeMessage called.");
		return messageService.updateMessageDeliveryStatus(messageAckReq);
	}
}

package com.messaage.scheduler;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.messaage.repositories.MessageRepository;
import com.message.entities.MessageInfo;
import com.message.executor.MessageTask;
import com.message.service.MessageService;

@Component
public class MessageScheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageScheduler.class);

	@Autowired
	MessageRepository messageRepository;
	@Autowired
	MessageService messageService;

	@Value("${app.message.encryption.key}")
	private String encyptionKey;

	@Value("${app.message.slice.size}")
	int messageSplitSize;

	@Scheduled(fixedDelayString = "${app.messageScheduler.timer}")
	public void sendMessageService() {
		LOGGER.info("MessageScheduler : sendMessageService started...." + new Date());
		ExecutorService executor = Executors.newFixedThreadPool(10);

		List<MessageInfo> result = messageRepository.queryAllOpenMessages();
		LOGGER.info("MessageScheduler : sendMessageService : No of Open Message Count...." + result.size());

		for (MessageInfo messageInfo : result) {
			executor.execute(new MessageTask(messageInfo, messageService, messageSplitSize, encyptionKey));

		}
		LOGGER.info("MessageScheduler : sendMessageService end...." + new Date());

	}
}

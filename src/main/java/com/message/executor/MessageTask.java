package com.message.executor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.message.entities.MessageInfo;
import com.message.entities.MessageSenderReq;
import com.message.service.MessageService;
import com.message.util.EncryptionUtils;

public class MessageTask implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageTask.class);
	private MessageInfo messageInfo;

	private MessageService messageService;
	private int messageSplitSize;
	private String encyptionKey;

	public MessageTask(MessageInfo messageInfo, MessageService messageService, int messageSplitSize,
			String encyptionKey) {
		// TODO Auto-generated constructor stub
		this.messageInfo = messageInfo;
		this.messageService = messageService;
		this.messageSplitSize = messageSplitSize;
		this.encyptionKey = encyptionKey;

	}

	@Override
	public void run() {
		LOGGER.info("Message Task Started :" + messageInfo.toString());
		// TODO Auto-generated method stub
		if (messageInfo != null) {

			String messageTxt = messageInfo.getMsg();
			if (!StringUtils.isEmpty(messageTxt)) {
				messageTxt = EncryptionUtils.decrypt(messageTxt, encyptionKey);
				if (StringUtils.isEmpty(messageTxt)) {
					LOGGER.info("Message Task Started : Error in Decryption");
					return;

				}
				MessageSenderReq messageReq;
				List<String> msgArray = split(messageTxt, messageSplitSize);
				int totMsgCnt = msgArray.size();
				int counter = 1;
				for (String msg : msgArray) {
					messageReq = new MessageSenderReq();
					BeanUtils.copyProperties(messageInfo, messageReq);
					messageReq.setMsg(msg);
					messageReq.setTotalCount(totMsgCnt);
					messageReq.setCurrentNumber(counter);
					if (!messageService.sendMessage(messageReq)) {
						LOGGER.error("MessageTask :  Messag sending failed");
						break;
					}
					counter++;

				}

			}

		}
	}

	public static List<String> split(String s, int chunkSize) {
		return IntStream.iterate(0, i -> i < s.length(), i -> i + chunkSize)
				.mapToObj(i -> s.substring(i, Math.min(s.length(), i + chunkSize))).collect(Collectors.toList());

	}
}
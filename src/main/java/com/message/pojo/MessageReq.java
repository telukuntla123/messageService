package com.message.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessageReq {
	private String senderNumber;
	private String recipientNumber;
	private String msg;
	private String sentTime;
	
}

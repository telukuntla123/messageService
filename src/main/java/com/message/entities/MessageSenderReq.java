package com.message.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageSenderReq {
	
	private String id;
	private String senderNumber;
	private String recipientNumber;
	private String msg;
	private int totalCount;
	private int currentNumber; 
}

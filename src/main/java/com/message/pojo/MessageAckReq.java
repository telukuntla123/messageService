package com.message.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageAckReq {

	private String messageId;
	private String deliveredTime;
	private String deliverystauts;
	
	
}

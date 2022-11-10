package com.message.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MessageInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "senderNumber")
	private String senderNumber;
	@Column(name = "recipientNumber")
	private String recipientNumber;
	@Column(name = "msg")
	private String msg;
	@Column(name = "sentTime")
	private String sentTime;
	@Column(name = "deliveredTime")
	private String deliveredTime;
	@Column(name = "deliveryStatus")
	private String deliveryStatus;

	@Column(name = "createTime")
	private String createTime;
	@Column(name = "messageSize")
	private int messageSize;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSenderNumber() {
		return senderNumber;
	}

	public void setSenderNumber(String senderNumber) {
		this.senderNumber = senderNumber;
	}

	public String getRecipientNumber() {
		return recipientNumber;
	}

	public void setRecipientNumber(String recipientNumber) {
		this.recipientNumber = recipientNumber;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	public String getSentTime() {
		return sentTime;
	}

	public void setSentTime(String sentTime) {
		this.sentTime = sentTime;
	}

	public String getDeliveredTime() {
		return deliveredTime;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getMessageSize() {
		return messageSize;
	}

	public void setMessageSize(int messageSize) {
		this.messageSize = messageSize;
	}

	public void setDeliveredTime(String deliveredTime) {
		this.deliveredTime = deliveredTime;
	}

	public String getDeliveryStatu() {
		return deliveryStatus;
	}

	public void setDeliveryStatu(String deliveryStatu) {
		this.deliveryStatus = deliveryStatu;
	}

}

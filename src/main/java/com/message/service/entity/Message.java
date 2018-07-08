package com.message.service.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import com.fasterxml.jackson.annotation.JsonIgnore;


@KeySpace("Message")
public class Message implements Serializable{
	public Message() {
		
	}
	public Message(String message) {
		this.message = message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8460318004144491143L;
	@NotNull
	String message;
	@JsonIgnore
	@Id
	String digest;
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}

}

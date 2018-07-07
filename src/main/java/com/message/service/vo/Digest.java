package com.message.service.vo;

import com.message.service.entity.Message;

public class Digest {

	public Digest() {
		
	}
	public Digest(Message m) {
		this.digest =m.getDigest();
	}
	String digest;
	
	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

}

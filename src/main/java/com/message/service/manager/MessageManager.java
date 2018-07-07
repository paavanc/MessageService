package com.message.service.manager;

import com.message.service.entity.Message;
import com.message.service.vo.Digest;

public interface MessageManager {

	Digest save(Message m);

	Message findByDigest(String digest);

}
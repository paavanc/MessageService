package com.message.service.manager.impl;

import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.message.service.entity.Message;
import com.message.service.manager.MessageManager;
import com.message.service.repository.MessageRepository;
import com.message.service.vo.Digest;

public class MessageManagerImpl implements MessageManager {
	@Autowired
	MessageRepository messageRepository;
	
	/* (non-Javadoc)
	 * @see com.message.manager.impl.MessageManager#save(com.message.entity.Message)
	 */
	@Override
	public Digest save(Message m) {
		
		m.setDigest(DigestUtils.sha256Hex(m.getMessage()));
		
		return new Digest(messageRepository.save(m));
	}

	/* (non-Javadoc)
	 * @see com.message.manager.impl.MessageManager#findByDigest(java.lang.String)
	 */
	@Override
	public Message findByDigest(String digest) {
		Optional<Message>  messages = messageRepository.findById( digest);
		if (!messages.isPresent()) {
			return null;
		}
		return messages.get();
	}
}

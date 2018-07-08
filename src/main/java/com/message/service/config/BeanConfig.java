package com.message.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.message.service.manager.MessageManager;
import com.message.service.manager.impl.MessageManagerImpl;

@Configuration
public class BeanConfig {

	@Bean
	public MessageManager getMessageManager() {
		return new MessageManagerImpl();
	}

}

package com.message.service.repository;

import org.springframework.data.repository.CrudRepository;

import com.message.service.entity.Message;

public interface MessageRepository extends CrudRepository<Message, String> {

}

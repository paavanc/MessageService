package com.message.service.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.message.service.entity.Message;
import com.message.service.manager.MessageManager;
import com.message.service.vo.Digest;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	MessageManager messageManager;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Digest create(@Valid @RequestBody Message message) {
		return messageManager.save(message);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Message> get(@PathVariable(required = true, name = "id") String digest) {

		Message m = messageManager.findByDigest(digest);
		if (m == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<>(m, HttpStatus.OK);
	}
}

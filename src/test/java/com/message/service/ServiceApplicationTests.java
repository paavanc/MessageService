package com.message.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.message.service.entity.Message;
import com.message.service.utils.ApiUtils;
import com.message.service.vo.Digest;
import com.message.service.vo.MessageVo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = ServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class ServiceApplicationTests {

	@Autowired
	private MockMvc mvc;
	private static final String MESSAGE_URI = "/message";

	private static final String INPUT = "foo";
	private static final String OUTPUT = "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae";

	@Before()

	public void setUp() throws IOException, Exception {
		mvc.perform(MockMvcRequestBuilders.post(MESSAGE_URI).contentType(MediaType.APPLICATION_JSON)
				.content(ApiUtils.toJson(new Message(INPUT)))).andReturn();
	}

	@Test
	public void testCreate() throws IOException, Exception {

		Message message = new Message(INPUT);
		MvcResult rs = mvc.perform(MockMvcRequestBuilders.post(MESSAGE_URI).contentType(MediaType.APPLICATION_JSON)
				.content(ApiUtils.toJson(message))).andReturn();

		Digest result = ApiUtils.toObject(rs.getResponse().getContentAsString(), Digest.class);
		assertNotNull(result);
		assertEquals(OUTPUT, result.getDigest());
	}

	@Test
	public void testGet() throws Exception {
		MvcResult rs = mvc
				.perform(MockMvcRequestBuilders.get(MESSAGE_URI + "/" + OUTPUT).contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		MessageVo result = ApiUtils.toObject(rs.getResponse().getContentAsString(), MessageVo.class);
		assertNotNull(result);
		assertEquals(INPUT, result.getMessage());
	}

	@Test
	public void testNotFound() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(MESSAGE_URI + "/" + "WILLFAIL").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();
	}

}

package com.message.service.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtils {
	   private static final ObjectMapper mapper = new ObjectMapper();

	   public static byte[] toJson(Object object) throws IOException {

	       return mapper.writeValueAsBytes(object);

	   }

	   public static <T> T toObject(String json, Class<T> classType) throws IOException {

	       return mapper.readValue(json, classType);

	   }
}

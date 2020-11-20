package com.practice.demo.service;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.practice.demo.model.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XMLParser implements ParseStrategy {
	
	@Override
	public Users parseFile(InputStream inputStream) {
		ObjectMapper objectMapper = new XmlMapper();

		Users deserializedData = null;
		
		try {
			deserializedData = objectMapper.readValue(inputStream, Users.class);
			return deserializedData;
		} catch (IOException e) {
			log.error("Failed to parse XML file", e);
		}

		return deserializedData;
	}

}

package com.practice.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.practice.demo.model.User;
import com.practice.demo.model.Users;


public interface UploadService {
	
	List<User> loadCSV(InputStream inputStream) throws IOException;

	User loadXML(InputStream inputStream) throws JsonParseException, JsonMappingException, IOException;

	Users loadXMLRoot(InputStream inputStream) throws JsonParseException, JsonMappingException, IOException;

	Users load(MultipartFile file) throws IOException;

}

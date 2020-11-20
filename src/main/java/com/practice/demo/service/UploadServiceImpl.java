package com.practice.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.practice.demo.model.User;
import com.practice.demo.model.Users;
import com.practice.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public List<User> loadCSV(InputStream inputStream) throws IOException {

		final List<User> list = readCSVData(inputStream);
		return list;
	}

	/**
	 * Read data via csv
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private List<User> readCSVData(InputStream inputStream) throws IOException {

		log.debug("Read CSV Debug- {} Data", inputStream.toString());
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		ObjectReader oReader = csvMapper.readerFor(User.class).with(schema);

		MappingIterator<User> mi = oReader.readValues(inputStream);
		return mi.readAll();

	}

	@Override
	public User loadXML(InputStream inputStream) throws JsonParseException, JsonMappingException, IOException {
		final User list = readXMLData(inputStream);
		return list;
	}

	private User readXMLData(InputStream inputStream) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new XmlMapper();

		User deserializedData = objectMapper.readValue(inputStream, User.class);

		return deserializedData;
	}

	@Override
	public Users loadXMLRoot(InputStream inputStream) throws JsonParseException, JsonMappingException, IOException {
		final Users users = readXMLRootData(inputStream);
		return users;
	}

	private Users readXMLRootData(InputStream inputStream)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new XmlMapper();

		Users deserializedData = objectMapper.readValue(inputStream, Users.class);

		return deserializedData;
	}

	@Override
	public Users load(MultipartFile file) throws IOException {
		Context context;
		Users users = null;
		if (file.getOriginalFilename().endsWith(".xml")) {
			context = new Context(new XMLParser());
			users = context.executeStrategy(file.getInputStream());
		} else if (file.getOriginalFilename().endsWith(".csv")) {
			context = new Context(new CSVParser());
			users = context.executeStrategy(file.getInputStream());
		}
	
		userRepo.saveAll(Arrays.asList(users.getUser()));
		
		System.out.println(userRepo.findByNameAndEmail("Atta Shah", "atta@example.com").toString());
		return users;
	}

}

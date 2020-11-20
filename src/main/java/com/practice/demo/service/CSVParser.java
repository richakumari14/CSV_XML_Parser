package com.practice.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.practice.demo.model.User;
import com.practice.demo.model.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVParser implements ParseStrategy{

	
	@Override
	public Users parseFile(InputStream inputStream) {
		Users users = new Users();
		
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		ObjectReader oReader = csvMapper.readerFor(User.class).with(schema);

		MappingIterator<User> mi;
		try {
			mi = oReader.readValues(inputStream);
			List<User> list = (List<User>)mi.readAll();
			
			User[] userArray = new User[list.size()];
			for (int i = 0; i < userArray.length; i++) {
				userArray[i] = list.get(i);
			}
			users.setUser(userArray);
		} catch (IOException e) {
			log.error("Failed to parse CSV file", e);
		}
		return users;
		
	}

}

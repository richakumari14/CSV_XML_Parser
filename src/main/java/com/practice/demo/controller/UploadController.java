
package com.practice.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.practice.demo.controller.exception.InvalidInputFileException;
import com.practice.demo.model.User;
import com.practice.demo.model.Users;
import com.practice.demo.service.UploadService;

/**
 * @author Richa
 *
 */
@RestController
public class UploadController {
	
	@Autowired
	private UploadService service;

	  @PostMapping(value = "/upload-csv-file" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<List<User>> uploadCSVFile(@RequestParam("userCSVFile") final MultipartFile file) throws InvalidInputFileException, IOException
	  {
		 
		  if(file == null || file.isEmpty() || !file.getOriginalFilename().endsWith(".csv")) {
			  throw new InvalidInputFileException("Invalid User data input csv file");
		  }

		  final List<User> response = service.loadCSV(file.getInputStream());
	 
		  return ResponseEntity.ok(response);
	  }
	  
	  
	  @PostMapping(value = "/upload-xml-file" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<User> uploadXMLFile(@RequestParam("userXMLFile") final MultipartFile file) throws InvalidInputFileException, IOException
	  {
		 
		  if(file == null || file.isEmpty() || !file.getOriginalFilename().endsWith(".xml")) {
			  throw new InvalidInputFileException("Invalid User data input xml file");
		  }

		  final User response = service.loadXML(file.getInputStream());
	 
		  return ResponseEntity.ok(response);
	  }
	  
	  @PostMapping(value = "/upload-xml-root-file" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<?> uploadXMLRootFile(@RequestParam("userXMLFile") final MultipartFile file) throws InvalidInputFileException, IOException
	  {
		System.out.println("hi");
		  if(file == null || file.isEmpty() || !file.getOriginalFilename().endsWith(".xml")) {
			  throw new InvalidInputFileException("Invalid User data input xml file");
		  }

		  final Users response = service.loadXMLRoot(file.getInputStream());
		  System.out.println(response.toString());
	 
		  return new ResponseEntity<>(response, HttpStatus.OK);
		
	  }
	  @PostMapping(value = "/upload-file" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<?> uploadFile(@RequestParam("userFile") final MultipartFile file) throws InvalidInputFileException, IOException
	  {
		 
		  if(file == null || file.isEmpty()) {
			  throw new InvalidInputFileException("Invalid User data input file");
		  }

		  final Users response = service.load(file);
	 
		  return ResponseEntity.ok(response);
	  }
	  
}

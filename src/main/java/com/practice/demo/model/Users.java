package com.practice.demo.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "users")
public class Users {
	
	@JsonProperty("user")
	@JacksonXmlElementWrapper(localName = "user" , useWrapping = false)
	private User[] user;
	
}



package com.practice.demo.model;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author GL63
 *
 */
@Getter
@Setter
@ToString
@JsonPropertyOrder({"id", "name", "email", "countryCode", "age"})
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "USER")
public class User {

	@Id
	@JacksonXmlProperty(localName = "id")
	@JsonProperty("id")
    private long id;
    
	@JsonProperty("name")
    private String name;
    
	@JsonProperty("email")
    private String email;
    
	@JsonProperty("country")
    private String countryCode;
   
	@JsonProperty("age")
    private int age;

}

package com.practice.demo.service;

import java.io.InputStream;

import com.practice.demo.model.Users;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Context {
	
	private ParseStrategy strategy; 
	
	public Users executeStrategy(InputStream inputStream){
	      return strategy.parseFile(inputStream);
	   }
	

}

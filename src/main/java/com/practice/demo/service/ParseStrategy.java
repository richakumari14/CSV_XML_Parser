package com.practice.demo.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.practice.demo.model.Users;

public interface ParseStrategy {

	Users parseFile(InputStream inputStream) ;
}

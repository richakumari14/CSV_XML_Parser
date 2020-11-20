package com.practice.demo.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.practice.demo.controller.exception.InvalidInputFileException;
import com.practice.demo.model.User;
import com.practice.demo.service.UploadServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UploadControllerTest {

	@InjectMocks
	private final UploadController controller = new UploadController();

	@Mock
	private UploadServiceImpl service;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testUploadFile_returnStatusOK() throws Exception {
		when(service.loadCSV(Mockito.any(InputStream.class))).thenReturn(new ArrayList<User>());
		final MockMultipartFile mockMultipartFile = new MockMultipartFile("userCSVFile", "userCSVFile.csv",
				"test/plain", "100,Atta Shah,atta@example.com,PK,30".getBytes());
		final MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/upload-csv-file")
				.file(mockMultipartFile);

		mockMvc.perform(builder).andExpect(status().isOk());
		verify(service, times(1)).loadCSV(Mockito.any(InputStream.class));
	}

	
	@Test
	public void testUploadFileThrowsExceptionWhenInvalidFileUpload() throws Exception {
		when(service.loadCSV(Mockito.any(InputStream.class))).thenReturn(new ArrayList<User>());
		final MockMultipartFile mockMultipartFile = new MockMultipartFile("userCSVFile", "userCSVFile.xls",
				"test/plain", "100,Atta Shah,atta@example.com,PK,30".getBytes());
		final MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/upload-csv-file")
				.file(mockMultipartFile);

		try {
			mockMvc.perform(builder);
		} catch (Exception e) {
			verify(service, times(0)).loadCSV(Mockito.any(InputStream.class));
			assertNotNull(e.getCause());
			assertTrue((e.getCause() instanceof InvalidInputFileException));
			assertNotNull(e.getCause().getMessage());
			assertTrue(e.getCause().getMessage().startsWith("Invalid User data input csv file"));
		}
	}

	
}

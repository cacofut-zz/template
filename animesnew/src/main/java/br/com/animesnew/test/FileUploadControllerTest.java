package br.com.animesnew.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.animesnew.config.DataConfig;
import br.com.animesnew.config.SecurityConfig;
import br.com.animesnew.config.Webconfig;
import br.com.animesnew.controller.FileUploadController;

@WebAppConfiguration
@ContextConfiguration(classes = { Webconfig.class, SecurityConfig.class, DataConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class FileUploadControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Test
	public void testHomePage() throws Exception {
		
		
		FileUploadController fileUploadController = new FileUploadController();
		File file = new File( "/arquivos/animesnew/java.jpg" );
		System.out.println( file.isFile() + " " + file.getName() + " " +  file.exists() );
				
		FileInputStream in = new FileInputStream( file );
		MockMultipartFile mmf = new MockMultipartFile("file", file.getName(), "image/jpg", in );
		
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup( webApplicationContext ).build();		
		
		mockMvc.perform( MockMvcRequestBuilders.fileUpload( "/fileUpload/naruto" )
				.file( mmf ) ).andExpect( view().name( "home" ) );
		

	}
	
}

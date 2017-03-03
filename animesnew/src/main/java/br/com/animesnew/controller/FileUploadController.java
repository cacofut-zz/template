package br.com.animesnew.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.MultipartConfigElement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import br.com.animesnew.exception.DuplicateSpittleException;
import br.com.animesnew.util.ImageResizerService;
import br.com.animesnew.util.Upload;

@Controller
@RequestMapping( "/fileUpload" )
public class FileUploadController {

	@RequestMapping( method = RequestMethod.GET )
	public String uploadForm(){
		return "uploadForm";
	}
	
	@RequestMapping( value = "/naruto", method = RequestMethod.POST )
	public String narutoUpload( @RequestPart( "file" ) MultipartFile file ) throws Exception{
		
		System.out.println( "Aqui" );
		//throw new DuplicateSpittleException();
		
		
		Upload upload = new Upload( "/arquivos/teste/" );
		upload.setImgRS( new ImageResizerService() );
		boolean image = upload.image(file, "cristiano", 250, 250, "cristiano");
		
		if( image ){
			System.out.println( upload.getCompleteFileName() );
		}

		System.out.println(image );
		return "home";
	}
}

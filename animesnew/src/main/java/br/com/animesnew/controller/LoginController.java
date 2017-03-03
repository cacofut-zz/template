package br.com.animesnew.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class LoginController {

	@RequestMapping( value = "/login", method = RequestMethod.GET )
	public String form(){
		return "login";
	}
	

	@RequestMapping( value = "/403", method = RequestMethod.GET )
	public String denied(){
		return "403";
	}
}

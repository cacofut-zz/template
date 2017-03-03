package br.com.animesnew.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.animesnew.data.Naruto;

@Controller
@RequestMapping( "/naruto" )
public class NarutoController {

	@RequestMapping( value = "/registrar", method = POST )
	public String registrar( @Valid Naruto naruto, Errors erros ){
		if( erros.hasErrors() ){
			return "form-naruto";
		}
		return "redirect:/naruto/listar";
	}
	
	@RequestMapping( value = "/formulario", method = GET )
	public String formulario( Naruto naruto, Model model){
		return "form-naruto";
	}
	
	@RequestMapping( value = "/listar", method = GET )
	public String listar( Naruto naruto, Model model){
		
		System.out.println( !model.containsAttribute( "narutos" ) );
		if( !model.containsAttribute( "narutos" ) ){
			model.addAttribute("narutos", new Naruto("Cristiano", "Carvalho", "Rasegan"));
		}
		return "naruto-list";
	}
}

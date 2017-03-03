package br.com.animesnew.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.animesnew.data.NarutoRepository;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {

	
	private NarutoRepository narutoRepository;
	
	@Autowired
	public HomeController(NarutoRepository narutoRepository) {
		this.narutoRepository = narutoRepository;
	}


	@RequestMapping( method = GET )
	public String home( Model model ){
		model.addAttribute( "narutos" , narutoRepository.getList() );
		return "home";
	}
}

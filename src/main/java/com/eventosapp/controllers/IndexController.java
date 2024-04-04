package com.eventosapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Mostrando para o spring que é uma controller com essa anotação (Atalho para importar a biblioteca Ctrl+Shift+O)
@Controller
public class IndexController {

	//Quando o usuário digitar essa requisição, esse controller vai redirecionar para página index.html
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	
}

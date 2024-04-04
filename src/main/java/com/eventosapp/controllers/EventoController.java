package com.eventosapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventosapp.models.Evento;
import com.eventosapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	//Essa anotação vai fazer uma injeção de dependencia, ou seja, 
	//toda vez que nós precisarmos utilizar essa interface, ele vai criar uma nova instancia automaticamente
	@Autowired
	private EventoRepository er;
	
	//Método que retorno o formulário de cadastro do evento devido ao GET
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	//Ao clicar no salvar ele vai mandar os dados para essa requisição, que vai salva-los no banco
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	//Aqui ele vai receber um evento
	public String form(Evento evento) {
		
		//Ele vai persistir/salvar esse evento no banco de dados
		er.save(evento);
		
		//Redirecionando para o cadastrarEvento do metodo GET
		return "redirect:/cadastrarEvento";
	}
	
	
	
	
}

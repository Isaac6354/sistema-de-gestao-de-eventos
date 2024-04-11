package com.eventosapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;
import com.eventosapp.repository.ConvidadoRepository;
import com.eventosapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	//Essa anotação vai fazer uma injeção de dependencia, ou seja, 
	//toda vez que nós precisarmos utilizar essa interface, ele vai criar uma nova instancia automaticamente
	@Autowired
	private EventoRepository er;
	
	//Injeção de dependencia do Repository Convidado
	@Autowired
	private ConvidadoRepository cr;
	
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
	
	//Metodo para ele retornar a lista de eventos
	@RequestMapping("/eventos")
	public ModelAndView listaEventos(){
		//Passando para ele a página que vai renderizar de acordo com os dados do evento
		ModelAndView mv = new ModelAndView("index");
		
		//Buscando os eventos no banco de dados
		Iterable<Evento> eventos = er.findAll();
		
		//Passando para a view a minha lista de eventos, para que a página seja renderizada 
		//de acordo com os dados do evento que cadastramos no formulário
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	//Aqui ele vai retornar o código de cada evento.
	//Assim que o usuário clicar no evento ele será direcionado para os detalhes daquele evento
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		
		//Lista de convidados que estão sendo adicionados
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		
		//Enviando essa lista de convidados para a minha view evento/detalhesEvento
		mv.addObject("convidados", convidados);
		return mv;
	}
	
	//Método para receber esse formulário via post e posteriormente salvar este convidado no banco de acordo evento que ele vai participar
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado){
		//Ele vai buscar por esse código que estamos recebendo como parametro
		Evento evento = er.findByCodigo(codigo);
		//Passando o evento encontrado de acordo com o código passado como parametro
		convidado.setEvento(evento);
		//Salvando o convidado no banco
		cr.save(convidado);
		return "redirect:/{codigo}";
	}
}
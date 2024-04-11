package com.eventosapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	//Busca uma lista de convidados por evento
	Iterable<Convidado> findByEvento(Evento evento);
}

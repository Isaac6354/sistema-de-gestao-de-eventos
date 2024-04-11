package com.eventosapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventosapp.models.Evento;

//Ao extender da classe CrudRepository(), conseguiremos utilizar métodos como salvar, editar e deletar da classe
public interface EventoRepository extends CrudRepository<Evento, String>{
	Evento findByCodigo(long codigo);
}

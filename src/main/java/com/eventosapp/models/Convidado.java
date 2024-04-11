package com.eventosapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

//Cada evento vai ter uma lista de convidados, então precisamos montar essa relação entre a entidade convidado e a entidade evento
@Entity
public class Convidado {

	@Id
	private String rg;
	private String nomeConvidado;
	
	//Muitos convidados para um evento
	@ManyToOne
	private Evento evento;
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNomeConvidado() {
		return nomeConvidado;
	}
	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
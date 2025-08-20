package com.dionariao.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "tb_palavra")
public class Palavra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@ManyToOne
	@JoinColumn(name = "dicionario_id")
	@JsonBackReference
	private Dicionario dicionario;

	


	@OneToMany( mappedBy = "palavra", fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	private List<Significado> significados;

	@OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "origem_id", referencedColumnName = "id")
	@JsonManagedReference
	private Origem origem;

	@OneToMany(mappedBy = "palavra",  fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Frase> frases;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Significado> getSignificados() {
		return significados;
	}

	public void setSignificados(List<Significado> significados) {
		this.significados = significados;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public List<Frase> getFrases() {
		return frases;
	}

	public void setFrases(List<Frase> frases) {
		this.frases = frases;
	}
	
	public Dicionario getDicionario() {
		return dicionario;
	}

	public void setDicionario(Dicionario dicionario) {
		this.dicionario = dicionario;
	}

	

	
}

package com.example.api.model;

import java.time.OffsetDateTime;

import com.example.domain.model.Entrega;

public class OcorrenciaModel {
	private Long id;
	private String descricao;
	private OffsetDateTime dataRegistro;
	//private Entrega entrega;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public OffsetDateTime getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(OffsetDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
}

package com.example.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class OcorrenciaInput {
	@Valid
	@NotNull
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

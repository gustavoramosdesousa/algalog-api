package com.example.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Exception.EntidadeNaoEncontradaException;
import com.example.domain.model.Entrega;
import com.example.domain.repository.EntregaRepository;

@Service
public class BuscaEntregaService {
	@Autowired
	private EntregaRepository entregaRepository;
	
	public Entrega buscar (Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada!"));

	}
	
	
}

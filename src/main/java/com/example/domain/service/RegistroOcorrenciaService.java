package com.example.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Exception.NegocioException;
import com.example.domain.model.Entrega;
import com.example.domain.model.Ocorrencia;
import com.example.domain.repository.EntregaRepository;

@Service
public class RegistroOcorrenciaService {
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		//neste exemplo não precisa de um save, no ocorrência poqrue 
		//o jarkart já vai entender que precisa e sincroniza (por causa do @Transactional)
		return entrega.adicionarOcorrencia(descricao);
	}
}

package com.example.domain.service;

import org.hibernate.persister.walking.spi.AnyMappingDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Exception.NegocioException;
import com.example.domain.model.Cliente;
import com.example.domain.repository.ClienteRepository;

@Service
public class CatalogoClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente salvar (Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
								.stream()
								.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente buscar (Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrato"));

	}
	
	@Transactional
	public void excluir (Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}

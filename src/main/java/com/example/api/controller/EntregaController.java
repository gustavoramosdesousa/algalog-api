package com.example.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.assambler.EntregaAssambler;
import com.example.api.model.EntregaModel;
import com.example.api.model.input.EntregaInput;
import com.example.domain.model.Entrega;
import com.example.domain.repository.EntregaRepository;
import com.example.domain.service.FinalizacaoEntregaService;
import com.example.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
/*	@PersistenceContext
	private EntityManager manager;
*/	
	@Autowired
	private EntregaRepository entregaRepository;

	@Autowired
	private SolicitacaoEntregaService entregaService;
	@Autowired
	private FinalizacaoEntregaService finalizacaoEntregaService;
	@Autowired
	private EntregaAssambler entregaAssambler;

	@PutMapping("{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}
	
	@GetMapping
	public List<EntregaModel> listar() {
		return entregaAssambler.toCollectionModel(entregaRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega entregaSolicitada = entregaService.solicitar(
										entregaAssambler.toEntity(entregaInput));
		
		return entregaAssambler.toModel(entregaSolicitada);
	}
	/*
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if(!clienterepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteService.excluir(clienteId); 
		
		return ResponseEntity.noContent().build();
		
	}	
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
		
		if(!clienterepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteService.salvar(cliente); 
		
		return ResponseEntity.ok(cliente);
	}	
	*/
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
			return entregaRepository.findById(entregaId)
					.map(entrega -> ResponseEntity.ok(entregaAssambler.toModel(entrega)))
					.orElse(ResponseEntity.notFound().build());
		
	}
}

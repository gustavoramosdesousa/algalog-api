package com.example.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.model.Cliente;
import com.example.domain.repository.ClienteRepository;
import com.example.domain.service.CatalogoClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
/*	@PersistenceContext
	private EntityManager manager;
*/	
	@Autowired
	private ClienteRepository clienterepository;

	@Autowired
	private CatalogoClienteService clienteService;

	@GetMapping
	public List<Cliente> listar() {
//		return manager.createQuery("from Cliente", Cliente.class).getResultList();
		//return clienterepository.findByNomeContaining("sousa");
		return clienterepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
		
	}
	
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
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		/* labda expression
		return clienterepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(cliente))
				OU .map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
		*/
		//		return manager.createQuery("from Cliente", Cliente.class).getResultList();
		Optional<Cliente> cliente = clienterepository.findById(clienteId);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
		
	}
}

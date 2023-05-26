package com.apiFinal.eCommerce.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.apiFinal.eCommerce.entities.Cliente;
import com.apiFinal.eCommerce.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	ClienteService clienteService ;

	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes() {
		return new ResponseEntity<>(clienteService.getAllClientes(),HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@Valid @PathVariable Integer id) {
		return new ResponseEntity<>(clienteService.getClienteById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.saveCliente(cliente),HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<Cliente> updateCliente(@Valid @RequestBody Cliente cliente, @Valid Integer id) {
		return new ResponseEntity<>(clienteService.saveCliente(cliente),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCliente(@Valid @PathVariable Integer id) {
		Boolean resp = clienteService.deleteCliente(id);
		if(resp == true) {
			return new ResponseEntity<>(resp,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
		}
	}
	
}

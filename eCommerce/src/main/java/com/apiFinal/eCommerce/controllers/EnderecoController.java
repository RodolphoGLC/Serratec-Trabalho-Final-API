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

import com.apiFinal.eCommerce.entities.Endereco;
import com.apiFinal.eCommerce.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	@Autowired
	EnderecoService enderecoService ;

	@GetMapping
	public ResponseEntity<List<Endereco>> getAllEnderecos() {
		return new ResponseEntity<>(enderecoService.getAllEnderecos(),HttpStatus.OK);
		
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> getEnderecoById(@PathVariable Integer id) {
		return new ResponseEntity<>(enderecoService.getEnderecoById(id),HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Endereco> saveEndereco(@RequestBody Endereco endereco) {
		return new ResponseEntity<>(enderecoService.saveEndereco(endereco),HttpStatus.CREATED);
		
	}
	
	@PutMapping
	public ResponseEntity<Endereco> updateEndereco(@RequestBody Endereco endereco,Integer id) {
		return new ResponseEntity<>(enderecoService.saveEndereco(endereco),HttpStatus.CREATED);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteEndereco(@PathVariable Integer id){
		Boolean resp=enderecoService.deleteEndereco(id); 
		return new ResponseEntity<>(resp,HttpStatus.OK);
	}
	
}
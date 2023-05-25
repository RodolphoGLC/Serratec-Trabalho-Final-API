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

import com.apiFinal.eCommerce.entities.Categoria;
import com.apiFinal.eCommerce.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService ;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAllCategorias() {
		return new ResponseEntity<>(categoriaService.getAllCategorias(),HttpStatus.OK);
		
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
		return new ResponseEntity<>(categoriaService.getCategoriaById(id),HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
		return new ResponseEntity<>(categoriaService.saveCategoria(categoria),HttpStatus.CREATED);
		
	}
	
	@PutMapping
	public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria,Integer id) {
		return new ResponseEntity<>(categoriaService.saveCategoria(categoria),HttpStatus.CREATED);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteCategoria(@PathVariable Integer id){
		Boolean resp=categoriaService.deleteCategoria(id); 
		return new ResponseEntity<>(resp,HttpStatus.OK);
	}
	
}

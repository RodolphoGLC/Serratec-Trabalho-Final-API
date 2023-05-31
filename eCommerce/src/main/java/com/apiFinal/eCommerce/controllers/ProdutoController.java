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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apiFinal.eCommerce.entities.Categoria;
import com.apiFinal.eCommerce.entities.Produto;
import com.apiFinal.eCommerce.services.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService ;

	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos() {
		return new ResponseEntity<>(produtoService.getAllProdutos(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@Valid @PathVariable Integer id) {
		return new ResponseEntity<>(produtoService.getProdutoById(id),HttpStatus.OK);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping
	public ResponseEntity<Produto> saveProduto(@RequestParam("image")MultipartFile image, @Valid String nome, @Valid String descricao, @Valid Integer qtdEstoque, @Valid Double valorUnitario, Integer idCategoria) {
		return new ResponseEntity<>(produtoService.saveProduto(image, nome, descricao, qtdEstoque, valorUnitario, idCategoria),HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Produto> updateProduto(@RequestParam("image")MultipartFile image, Integer idProduto, String nome, String descricao, @Valid Integer qtdEstoque, @Valid Double valorUnitario, Integer idCategoria) {
		return new ResponseEntity<>(produtoService.updateProduto(image, idProduto, nome, descricao, qtdEstoque, valorUnitario, idCategoria),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteProduto(@Valid @PathVariable Integer id) {
		Boolean resp = produtoService.deleteProduto(id);
		if(resp == true) {
			return new ResponseEntity<>(resp,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
		}
	}
	
}

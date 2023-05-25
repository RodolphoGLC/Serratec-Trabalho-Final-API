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

import com.apiFinal.eCommerce.entities.ItemPedido;
import com.apiFinal.eCommerce.services.ItemPedidoService;

@RestController
@RequestMapping("/itemPedidos")
public class ItemPedidoController {
	@Autowired
	ItemPedidoService itemPedidoService ;

	@GetMapping
	public ResponseEntity<List<ItemPedido>> getAllItemPedidos() {
		return new ResponseEntity<>(itemPedidoService.getAllItemPedidos(),HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> getItemPedidoById(@PathVariable Integer id) {
		return new ResponseEntity<>(itemPedidoService.getItemPedidoById(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ItemPedido> saveItemPedido(@RequestBody ItemPedido itemPedido) {
		return new ResponseEntity<>(itemPedidoService.saveItemPedido(itemPedido),HttpStatus.CREATED);		
	}
	
	@PutMapping
	public ResponseEntity<ItemPedido> updateItemPedido(@RequestBody ItemPedido itemPedido,Integer id) {
		return new ResponseEntity<>(itemPedidoService.saveItemPedido(itemPedido),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteItemPedido(@PathVariable Integer id) {
		Boolean resp = itemPedidoService.deleteItemPedido(id);
		if(resp == true) {
			return new ResponseEntity<>(resp,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
		}
	}
	
}

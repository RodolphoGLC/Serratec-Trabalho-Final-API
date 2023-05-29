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

import com.apiFinal.eCommerce.dto.RealizarPedidoDTO;
import com.apiFinal.eCommerce.entities.Pedido;
import com.apiFinal.eCommerce.services.CamadaClienteService;
import com.apiFinal.eCommerce.services.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	@Autowired
	PedidoService pedidoService ;
	
	@Autowired
	CamadaClienteService camadaClienteService;

	@GetMapping
	public ResponseEntity<List<Pedido>> getAllPedidos() {
		return new ResponseEntity<>(pedidoService.getAllPedidos(),HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getPedidoById(@Valid @PathVariable Integer id) {
		return new ResponseEntity<>(pedidoService.getPedidoById(id),HttpStatus.OK);
	}
	
	@PostMapping("/carrinho")
	public ResponseEntity<Boolean> fazerPedido(@Valid @RequestBody List<RealizarPedidoDTO> lista, Integer idCliente) {
		Boolean check = camadaClienteService.fazerPedido(lista, idCliente);
		if (check == true) {
			return new ResponseEntity<>(camadaClienteService.fazerPedido(lista, idCliente), HttpStatus.CREATED);
			//Enviar email para cliente
		} else {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	public ResponseEntity<Pedido> savePedido(@Valid @RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.savePedido(pedido),HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Pedido> updatePedido(@Valid @RequestBody Pedido pedido, @Valid Integer id) {
		return new ResponseEntity<>(pedidoService.savePedido(pedido),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletePedido(@Valid @PathVariable Integer id) {
		Boolean resp = pedidoService.deletePedido(id);
		if(resp == true) {
			return new ResponseEntity<>(resp,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resp,HttpStatus.NOT_MODIFIED);
		}
	}
	
}

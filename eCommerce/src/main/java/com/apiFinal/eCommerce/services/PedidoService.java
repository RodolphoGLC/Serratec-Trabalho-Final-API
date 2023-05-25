package com.apiFinal.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.Pedido;
import com.apiFinal.eCommerce.entities.Pedido;
import com.apiFinal.eCommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	public List<Pedido> getAllPedidos() {
		return pedidoRepository.findAll();
	}
	
	public Pedido getPedidoById(Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	
	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido updatePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Boolean deletePedido(Integer id) {
		pedidoRepository.deleteById(id);
		Pedido pedidoDeletado = pedidoRepository.findById(id).orElse(null);
		if(pedidoDeletado == null) {
			return true;
		} else {
			return false;
		}
	}
	
}

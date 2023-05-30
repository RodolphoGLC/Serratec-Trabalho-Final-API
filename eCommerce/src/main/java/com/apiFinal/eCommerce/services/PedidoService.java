package com.apiFinal.eCommerce.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiFinal.eCommerce.entities.ItemPedido;
import com.apiFinal.eCommerce.entities.Pedido;
import com.apiFinal.eCommerce.exceptions.NoSuchElementException;
import com.apiFinal.eCommerce.exceptions.UniqueElementException;
import com.apiFinal.eCommerce.exceptions.UnmatchingIdsException;
import com.apiFinal.eCommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	RelatorioPedidoService relatorioPedidoService;
	
	public List<Pedido> getAllPedidos() {
		return pedidoRepository.findAll();
	}
	
	public Pedido getPedidoById(Integer id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("pedido", id));
	}
	
	public Pedido savePedido(Pedido pedido) {
		if (pedido.getIdPedido() == null) {
			try {
				pedido.setStatus("separação");
				LocalDateTime data = LocalDateTime.now();
				pedido.setDataPedido(data);
				Double valor = 0.0;
				if(pedido.getListaItemPedido() != null) {
					for(ItemPedido itemPedido: pedido.getListaItemPedido()) {
						valor += itemPedido.getValorLiquido();
					}					
				}
				pedido.setValorTotal(valor);
				return pedidoRepository.save(pedido);		
			} catch (Exception e) {
				throw new UniqueElementException();
			}
		} else {
			throw new UnmatchingIdsException(pedido.getIdPedido(), pedido.getStatus());
		}
	}
	
	public Pedido updatePedido(Pedido pedido) {
		Integer id = pedido.getIdPedido();
		if (id == null) {
			throw new UnmatchingIdsException();
		} else {
			if (pedidoRepository.findById(id).orElse(null) != null) {
				try {
					return pedidoRepository.save(pedido);		
				} catch (Exception e) {
					throw new UniqueElementException();
				}					
			} else {
				throw new UnmatchingIdsException(id);
			}
		}
	}
	
	public Boolean deletePedido(Integer id) {
		Pedido pedidoDeletada = pedidoRepository.findById(id).orElse(null);
		if(pedidoDeletada != null) {
			pedidoRepository.deleteById(id);
			return true;
		} else {
			throw new UnmatchingIdsException(id);
		}
	}
	
}

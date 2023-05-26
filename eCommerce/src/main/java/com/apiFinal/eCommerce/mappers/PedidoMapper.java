package com.apiFinal.eCommerce.mappers;

import com.apiFinal.eCommerce.dto.RelatorioPedidoDTO;
import com.apiFinal.eCommerce.entities.Pedido;

public class PedidoMapper {
	
	public Pedido toEntity(RelatorioPedidoDTO pedidoDTO) {
		
		Pedido pedido = new Pedido();
		
		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataPedido(pedidoDTO.getDataPedido());
		pedido.setValorTotal(pedidoDTO.getValorTotal());
		//pedido.setListaItemPedido(pedidoDTO.getListaItems());
		
		return pedido;
	}
	
	public RelatorioPedidoDTO toDto(Pedido pedido) {
		
		RelatorioPedidoDTO pedidoDTO = new RelatorioPedidoDTO();
		
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setDataPedido(pedido.getDataPedido());
		pedidoDTO.setValorTotal(pedido.getValorTotal());
		//pedidoDTO.setListaItems(pedido.getListaItemPedido());
		
		return pedidoDTO;
	}
}

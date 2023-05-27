package com.apiFinal.eCommerce.services;

import java.util.ArrayList;
import java.util.List;

import com.apiFinal.eCommerce.dto.ItemPedidoDTO;
import com.apiFinal.eCommerce.dto.RelatorioPedidoDTO;
import com.apiFinal.eCommerce.entities.ItemPedido;
import com.apiFinal.eCommerce.entities.Pedido;

public class RelatorioPedidoService {
	
	public RelatorioPedidoDTO gerarRelatorio(Pedido pedido) {
		
		RelatorioPedidoDTO relatorioDTO = new RelatorioPedidoDTO();
		
		relatorioDTO.setIdPedido(pedido.getIdPedido());
		relatorioDTO.setDataPedido(pedido.getDataPedido());
		relatorioDTO.setValorTotal(pedido.getValorTotal());
		
		List<ItemPedidoDTO> listaItemPedido = new ArrayList<>();
		
		for(ItemPedido itemPedido : pedido.getListaItemPedido()) {
			ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
			
			itemPedidoDTO.setIdProduto(itemPedido.getProduto().getIdProduto());
			itemPedidoDTO.setQuantidade(itemPedido.getQuantidade());
			itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
			itemPedidoDTO.setPercentualDesconto(itemPedido.getPorcentagemDesconto());
			itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
			itemPedidoDTO.setNomeProduto(itemPedido.getProduto().getNome());
			itemPedidoDTO.setPrecoVenda(itemPedido.getProduto().getValorUnitario());
			
			listaItemPedido.add(itemPedidoDTO);
		}
		
		relatorioDTO.setListaItems(listaItemPedido);
		
		return relatorioDTO;
	}
}

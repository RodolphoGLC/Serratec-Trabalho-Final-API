package com.apiFinal.eCommerce.services;

import java.util.ArrayList;
import java.util.List;

import com.apiFinal.eCommerce.dto.ItemPedidoDTO;
import com.apiFinal.eCommerce.dto.RelatorioPedidoDTO;
import com.apiFinal.eCommerce.entities.ItemPedido;
import com.apiFinal.eCommerce.entities.Pedido;
import com.apiFinal.eCommerce.entities.Produto;

public class RelatorioPedidoService {
	
	public RelatorioPedidoDTO gerarRelatorio(Pedido pedido) {
		
		RelatorioPedidoDTO relatorioDTO = new RelatorioPedidoDTO();
		
		relatorioDTO.setIdPedido(pedido.getIdPedido());
		relatorioDTO.setDataPedido(pedido.getDataPedido());
		relatorioDTO.setValorTotal(pedido.getValorTotal());
		
		List<ItemPedidoDTO> listaItemPedido = new ArrayList<>();
		
		for(ItemPedido itemPedido : pedido.getListaItemPedido()) {
			ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
			
			itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
			itemPedidoDTO.setQuantidade(itemPedido.getQuantidade());
			itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
			itemPedidoDTO.setPercentualDesconto(itemPedido.getPorcentagemDesconto());
			itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
		
			Produto produto = new Produto();
			
			itemPedidoDTO.setNomeProduto(produto.getNome());
			itemPedidoDTO.setPrecoVenda(produto.getValorUnitario());
			
			listaItemPedido.add(itemPedidoDTO);
		}
		
		relatorioDTO.setListaItems(listaItemPedido);
		
		return relatorioDTO;
	}
}

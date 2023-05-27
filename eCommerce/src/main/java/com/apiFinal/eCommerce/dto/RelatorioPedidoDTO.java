package com.apiFinal.eCommerce.dto;

import java.util.Date;
import java.util.List;

public class RelatorioPedidoDTO {
	
	//Pedido
	private Integer idPedido;
	private Date dataPedido;
	private Double valorTotal;
	
	//Item Pedido
	private List<ItemPedidoDTO> listaItems;
	
	public RelatorioPedidoDTO() {
		super();
	}

	public RelatorioPedidoDTO(Integer idPedido, Date dataPedido, Double valorTotal, List<ItemPedidoDTO> listaItems) {
		super();
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.listaItems = listaItems;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemPedidoDTO> getListaItems() {
		return listaItems;
	}

	public void setListaItems(List<ItemPedidoDTO> listaItems) {
		this.listaItems = listaItems;
	}

	public String notaFiscal() {
		
		String mensagem = "";
		
		mensagem += "==========================================" +
					"\nId do pedido: " + idPedido +
					"\nData do pedido: " + dataPedido + 
					"\nValor total: " + valorTotal + 
					"\n==========================================";
		
		for(ItemPedidoDTO itemPedido : listaItems) {
			mensagem += "\nId do produto: " + itemPedido.getIdProduto() +
					    "\nNome do produto: " + itemPedido.getNomeProduto() + 
					    "\nPreço venda: " + itemPedido.getPrecoVenda() + 
					    "\nQuantidade do produto: " + itemPedido.getQuantidade() +
					    "\nValor bruto do produto: " + itemPedido.getValorBruto() +
					    "\nPercentual de desconto: " + itemPedido.getPercentualDesconto() +
					    "\nValor líquido do produto: " + itemPedido.getValorLiquido() +
					    "\n-----------------------------------------------------------";
		}
		
		return mensagem;
	}
}

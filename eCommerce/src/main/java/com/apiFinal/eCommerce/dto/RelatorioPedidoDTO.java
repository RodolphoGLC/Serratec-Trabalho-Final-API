package com.apiFinal.eCommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.apiFinal.eCommerce.entities.ItemPedido;
import com.apiFinal.eCommerce.entities.Pedido;

@Component	
public class RelatorioPedidoDTO {
	
	//Pedido
	private Integer idPedido;
	private LocalDateTime dataPedido;
	private Double valorTotal;
	
	//Item Pedido
	private List<ItemPedidoDTO> listaItems;
	
	public RelatorioPedidoDTO() {
		super();
	}

	public RelatorioPedidoDTO(Integer idPedido, LocalDateTime dataPedido, Double valorTotal, List<ItemPedidoDTO> listaItems) {
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

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
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

	public String notaFiscal(PedidoDTO pedidoDTO) {
		
		String mensagem = "";
		
		mensagem += "==========================================" +
					"\nId do pedido: " + pedidoDTO.getIdPedido() +
					"\nData do pedido: " + pedidoDTO.getDataPedido() + 
					"\nValor total: " + pedidoDTO.getValorTotal() + 
					"\n==========================================";
		
		for(ItemPedido itemPedido : pedidoDTO.getListaItemPedido()) {
			mensagem += "\nId do produto: " + itemPedido.getProduto() +
					    "\nNome do produto: " + itemPedido.getProduto().getNome() + 
					    "\nPreço venda: " + itemPedido.getProduto().getValorUnitario() + 
					    "\nQuantidade do produto: " + itemPedido.getQuantidade() +
					    "\nValor bruto do produto: " + itemPedido.getValorBruto() +
					    "\nPercentual de desconto: " + itemPedido.getPorcentagemDesconto() +
					    "\nValor líquido do produto: " + itemPedido.getValorLiquido() +
					    "\n-----------------------------------------------------------";
		}
		
		return mensagem;
	}
}

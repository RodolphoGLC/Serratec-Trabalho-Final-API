package com.apiFinal.eCommerce.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class RealizarPedidoDTO {
	
	@NotNull
	private Integer idCliente;
	
	private List<ProdutoPedidoDTO> listaProdutoPedidoDTO;

	public RealizarPedidoDTO() {
		super();
	}

	
	public Integer getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}


	public List<ProdutoPedidoDTO> getListaProdutoPedidoDTO() {
		return listaProdutoPedidoDTO;
	}

	public void setListaProdutoPedidoDTO(List<ProdutoPedidoDTO> listaProdutoPedidoDTO) {
		this.listaProdutoPedidoDTO = listaProdutoPedidoDTO;
	}	
}

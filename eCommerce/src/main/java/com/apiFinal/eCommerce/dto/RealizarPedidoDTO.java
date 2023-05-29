package com.apiFinal.eCommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RealizarPedidoDTO {
	
	@NotNull	
	private Integer idProduto;
	
	@NotNull
	@Min(1)
	private Integer quantidade;
	
	private Double porcentagemDesconto;
	
	@NotNull
	private Integer idCliente;

	public RealizarPedidoDTO() {
		super();
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	//Ver com o professor
	public Double getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(Double porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}
	
}

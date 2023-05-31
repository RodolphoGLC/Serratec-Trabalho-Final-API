package com.apiFinal.eCommerce.dto;

import jakarta.validation.constraints.Min;

public class ProdutoPedidoDTO {
	
	@Min(1)
	private Integer quantidade;
	
	@Min(1)
	private Integer idProduto;
	
	private Double porcentagemDesconto;
	
	private String nome;
	
	public Double getPorcentagemDesconto() {
		return porcentagemDesconto;
	}
	public void setPorcentagemDesconto(Double porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}

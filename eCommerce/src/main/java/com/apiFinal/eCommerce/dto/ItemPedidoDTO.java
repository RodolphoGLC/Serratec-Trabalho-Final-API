package com.apiFinal.eCommerce.dto;

public class ItemPedidoDTO {
	
	private Integer idProduto;
	private String nomeProduto;
	private Double precoVenda;
	private Integer quantidade;
	private Double valorBruto;
	private Double percentualDesconto;
	private Double valorLiquido;
	
	public ItemPedidoDTO(Integer idProduto, String nomeProduto, Double precoVenda, Integer quantidade,
			Double valorBruto, Double percentualDesconto, Double valorLiquido) {
		super();
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.valorBruto = valorBruto;
		this.percentualDesconto = percentualDesconto;
		this.valorLiquido = valorLiquido;
	}

	public ItemPedidoDTO() {
		super();
	}
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}
	public Double getPercentualDesconto() {
		return percentualDesconto;
	}
	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	public Double getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
}
